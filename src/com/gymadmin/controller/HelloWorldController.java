package com.gymadmin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("/hello")
public class HelloWorldController {
 
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView helloWorld() {
 
    	
    	
        String message = "Hello World, Spring 3.0!";
        return new ModelAndView("plans", "message", message);
    }
}