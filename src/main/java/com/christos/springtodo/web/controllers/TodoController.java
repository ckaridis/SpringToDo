package com.christos.springtodo.web.controllers;

import com.christos.springtodo.web.model.Todo;
import com.christos.springtodo.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@org.springframework.stereotype.Controller
//We don't need this anymore. Spring Security stores the Username
//@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    //We use initBinder to transform the format of the Date.
    //The application responds automatically, we don't need to make any further changes.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /*
        ////////////// Refactored method for lower coupling
        private String getLoggedInUserName(ModelMap model) {
            return (String) model.get("name");
        }
      }
    */
    // New method using Spring Security. More info on how this works can be found
    // on the same method in LoginController
    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }


    //////// GET METHOD
    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodoList(ModelMap model) {
        String name = getLoggedInUserName(model);
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
                        /*name */getLoggedInUserName(model),
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

        todoService.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);

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

        todo.setUser(getLoggedInUserName(model));
        todoService.updateTodo(todo);

        return "redirect:/list-todos";
    }

}
