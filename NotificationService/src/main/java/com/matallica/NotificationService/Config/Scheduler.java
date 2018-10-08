//package com.matallica.NotificationService.Config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.matallica.NotificationService.Entity.MessageEntity;
//
//@EnableScheduling
//@Configuration
//public class Scheduler {
//
//	@Autowired
//	SimpMessagingTemplate template;
//	
//	@Scheduled(fixedDelay =3000)
//	public void sendAdhocMessage() {
//		template.convertAndSend("topic/user", new MessageEntity("Trading like Big Bears"));
//	}
//}
