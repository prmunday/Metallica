package com.matallica.NotificationService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.matallica.NotificationService.Entity.MessageEntity;
import com.matallica.NotificationService.Repo.NotificationServiceRepo;

@Controller
@RequestMapping("/msg")
@CrossOrigin("*")
public class NotificationServiceController {
	
	@Autowired
	NotificationServiceRepo repo;
	
	@RequestMapping(path = "/send/{msg}", method = RequestMethod.GET)
	public ResponseEntity<String> sendMessage(@PathVariable("msg") String msg) {
		repo.sendMessage(msg);
		return new ResponseEntity<String>("Meassage sent, check with receiver.", HttpStatus.ACCEPTED);
	}
	
//	Web Sockets without Rabbit
	@MessageMapping("/user")
	@SendTo("/topic/user")
	public MessageEntity getTradeUpdate(MessageEntity entity){
		
		return new MessageEntity("It is --->"+entity.getName());
	}
}
