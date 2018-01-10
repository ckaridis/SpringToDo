package com.christos.springtodo.web.controllers;

import com.christos.springtodo.web.service.LoginService;
import com.christos.springtodo.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@org.springframework.stereotype.Controller
@SessionAttributes("name")
public class LoginController {


    @Autowired
    LoginService loginService;

    //////// GET METHOD
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model){

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String welcomePage( ModelMap model, @RequestParam String name, @RequestParam String password){

        boolean isValidUser = loginService.validateUser(name,password);

        if (!isValidUser){
            model.addAttribute("message", "Invalid Credentials");
            return "login";
        }

        model.put("name", name);
        model.put("password", password);

        return "welcome";
    }
}