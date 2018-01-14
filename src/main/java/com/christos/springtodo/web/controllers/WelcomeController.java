package com.christos.springtodo.web.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//We don't need this anymore. Spring Security stores the Username
//@SessionAttributes("name")
public class WelcomeController {

/*
// LoginService class is not needed anymore. Spring Security is used instead.
    @Autowired
    LoginService loginService;
*/


    //////// GET METHOD for login controller (new for Spring Security)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage(ModelMap model) {
        //we hardcode the login temporarily
        model.put("name", getLoggedInUserName(model));
        return "welcome";
    }

    //////////////////////////////////////////////////////////////////////////
    // The logged in user is Spring Security is called a "Principal".       //
    // Using the following method we can retrieve the logged in user.       //
    // Spring Security uses the class UserDetails to store the user details.//
    //////////////////////////////////////////////////////////////////////////
    // Get the logged in user.                                              //
    // Go to Spring Security and get the logged in user bean.               //
    // From witch, we need to get the username.                             //
    //////////////////////////////////////////////////////////////////////////
    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    // For Logout to work we need the request, the response, and the current user details (auth).
    // When the user is logged out, we redirect him to the Login Page (/).
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}

///////////////////// OLDER CONTROLLERS

/*

    //////// GET METHOD for home to redirect to login (old home controller)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "redirect:/login";
    }

    //////// GET METHOD - Old login controller
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        return "login";
    }
*/

/*  ////////// OLD POST CONTROLLER for login. Was replaced by Spring Securty
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String welcomePage(ModelMap model, @RequestParam String name, @RequestParam String password) {

        boolean isValidUser = loginService.validateUser(name, password);

        if (!isValidUser) {
            model.addAttribute("message", "Invalid Credentials");
            return "login";
        }

        model.put("name", name);
        model.put("password", password);

        return "welcome";
    }
    */
