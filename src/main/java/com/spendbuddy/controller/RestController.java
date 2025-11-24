package com.spendbuddy.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	
	@GetMapping("/sample-api")
	public String helloWorld() {
		return "Hello World";
	}

}
