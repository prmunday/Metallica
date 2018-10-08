//package com.matallica.NotificationService.Config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;
//
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {
//
//	@Override
//	protected void registerStompEndpoints(StompEndpointRegistry registry) {
//		registry.addEndpoint("/websocket").withSockJS();		
//	}
//
//	@Override
//	protected void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.enableSimpleBroker("/topic");
//		registry.setApplicationDestinationPrefixes("/app");
//	}
//
//	@Override
//	protected SimpUserRegistry createLocalUserRegistry() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}
