package com.christos.springtodo.web.controllers;

import com.christos.springtodo.web.model.Todo;
import com.christos.springtodo.web.service.LoginService;
import com.christos.springtodo.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@org.springframework.stereotype.Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;


    //////// GET METHOD
    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model){
        String name = (String) model.get("name");
            model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    //////// GET METHOD
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAtTodoPage(ModelMap model){
        model.addAttribute(
                // Here we create the command bean we're going to send to the model.
                // We set the default values here, to be replaced.
                "todo",
                new Todo(
                        0,
                        /*name */(String) model.get("name"),
                        "",
                        /*Target Date*/new Date(),
                        false));
        return "todo";
    }

    //////// GET METHOD
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String showAddTodo(ModelMap model, Todo todo){
        todoService.addTodo((String) model.get("name"),todo.getDesc(), new Date(),false);

        return "redirect:/list-todos";
    }

    //////// GET METHOD to delete To-do
    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

}
