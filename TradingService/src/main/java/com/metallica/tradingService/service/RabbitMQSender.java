package com.metallica.tradingService.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.metallica.tradingService.entities.TradingEntity;

@Component
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	
	@Value("${queue.name}")
	private String queue;
		
	
	/**Sends the trade object to rabbitMQ
	 * 
	 * @param trade
	 */
	public void send(TradingEntity trade) {
		rabbitTemplate.convertAndSend(queue, trade);
		System.out.println("Send msg = " + trade);
	    
	}
}