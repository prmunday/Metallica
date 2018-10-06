package com.matallica.NotificationService.Repo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matallica.NotificationService.NotificationServiceApplication;

@Service
public class NotificationServiceRepo {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sendMessage(String msg) {
		rabbitTemplate.convertAndSend(NotificationServiceApplication.queueName, msg);
	}
}
