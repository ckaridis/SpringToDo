package com.christos.springtodo.web.controllers;

import com.christos.springtodo.web.model.Todo;
import com.christos.springtodo.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;

@org.springframework.stereotype.Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;


    //////// GET METHOD
    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model) {
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "list-todos";
    }

    //////// GET METHOD
    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAtTodoPage(ModelMap model) {
        model.addAttribute(
                // Here we create the command bean we're going to send to the model.
                // We set the default values here, to be replaced.
                "todo",
                new Todo(
                        0,
                        /*name */(String) model.get("name"),
                        "Default Description",
                        /*Target Date*/new Date(),
                        false));
        return "todo";
    }

    //////// GET METHOD
    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    //@Valid enables the validation specified in our model
    //BindResult returns any errors that might occur during the validation process.
    public String showAddTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }

        todoService.addTodo((String) model.get("name"), todo.getDesc(), new Date(), false);

        return "redirect:/list-todos";
    }

    //////// GET METHOD to delete To-do
    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }

    //////// GET METHOD to update To-do
    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        //We're now populating the current to-do and we return the appropriate page.
        Todo todo = todoService.retrieveTodo(id);
        model.put("todo", todo);
        return "todo";
    }

    //////// POST METHOD to update To-do
    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser((String) model.get("name"));
        todoService.updateTodo(todo);

        return "redirect:/list-todos";
    }

}
