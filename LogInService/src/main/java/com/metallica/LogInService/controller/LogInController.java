package com.metallica.LogInService.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.metallica.LogInService.entity.Status;
import com.metallica.LogInService.entity.User;
import com.metallica.LogInService.repo.IUserRepo;

@Controller
@RequestMapping(path="/")
public class LogInController {
	
	@Autowired
	IUserRepo repo;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user")
	public String user(Principal principal,Model model) {
	    if (principal != null) {
	        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
	        Authentication authentication = oAuth2Authentication.getUserAuthentication();
			Map<String, String> details = (Map<String, String>) authentication.getDetails();
	       
//	        Registering user into db
	        User user = new User(details.get("name"), details.get("email"),Status.ACTIVE );

        	model.addAttribute("name", details.get("name"));
        	model.addAttribute("username", details.get("email"));
        	model.addAttribute("picture", details.get("pictures"));
        	
	        try {
		        repo.save(user);
	        }
	        catch (TransactionSystemException e) {
	        	return "Welcome.html";
			}
	        
	        return "Welcome.hmtl";
	    }
	    return null;
	}
	
	public String logoutFromAuthServer() {
		return "https://accounts.google.com/Logout?hl=en&continue=https://mail.google.com/mail&service=mail&timeStmp=1539148156&secTok=.AG5fkS-MAYaCDW1ClojD3Qtm08EuHznySQ";
	}
}
