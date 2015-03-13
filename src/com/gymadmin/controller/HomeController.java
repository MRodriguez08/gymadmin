package com.gymadmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("/home")
public class HomeController {
 
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHome() {
 
        return new ModelAndView("index");
    }
}