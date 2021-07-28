package com.capgemini;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

	@RequestMapping(value="/hello-spring-mvc", method=RequestMethod.GET)
	public String sayHello(ModelMap map){
		
		String destinationPage = "welcome";
		
		map.addAttribute("message", "Hello, world!");
		System.out.println("inside sayHello()...");
		return destinationPage;
	}
	
	
	
}
