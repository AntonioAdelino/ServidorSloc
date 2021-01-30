package com.springblank.resource;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@RequestMapping("/")
	
	public String home() {
			return "<h2> Bem vindo ao Spring Boot, Antonio!!</h2>";    
	}		    

	
}