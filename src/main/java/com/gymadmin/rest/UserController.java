package com.gymadmin.rest;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  
  
  @RequestMapping(method = RequestMethod.GET)
  public Principal user(Principal user) {
	  
	  
	  return user;
  }

}