package com.christos.springtodo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("error")
public class ErrorController {

    // Here we make a controller which will handle the errors.
    // We pass as arguements the Request and the Exception.
    // We set to the model and view the exception and the URl where the exception occurred.
    // We "redirect" the user to the error page.
    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(HttpServletRequest request, Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", ex.getStackTrace());
        mv.addObject("url", request.getRequestURI());
        mv.setViewName("error");
        return mv;
    }


}



