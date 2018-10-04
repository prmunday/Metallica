package com.metallica.LogInService.controller;

import java.io.File;
import java.io.Reader;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metallica.LogInService.entity.Status;
import com.metallica.LogInService.entity.User;
import com.metallica.LogInService.repo.IUSerRepo;

@RestController
@RequestMapping(path="/")
public class LogInController {
	
	@Autowired
	IUSerRepo repo;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user")
	public String user(Principal principal,Model model) {
	    if (principal != null) {
	        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
	        Authentication authentication = oAuth2Authentication.getUserAuthentication();
	        Map<String, String> details = new LinkedHashMap<>();
	        details = (Map<String, String>) authentication.getDetails();
	        System.out.println("details = " + details);  // id, email, name, link etc.
	       
//	        Registering user into db
	        User user = new User(details.get("name"), details.get("email"),Status.ACTIVE );

        	model.addAttribute("name", details.get("name"));
        	model.addAttribute("username", details.get("email"));
        	model.addAttribute("picture", details.get("pictures"));
        	
	        try {
		        repo.save(user);
		        
	        }
	        catch (TransactionSystemException e) {
				return "Welcome";
			}
	        
	        return "Welcome";
	    }
	    return null;
	}
}
