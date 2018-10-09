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
		
	
<<<<<<< HEAD
	/**Sends the trade object to rabbitMQ
=======
	/**Sends whole Trading Entity to rabbitMQ through the 
	 * AMQPTemplate.
>>>>>>> 4b1501c0216562ed5ba847a8278dad0956cca4b6
	 * 
	 * @param trade
	 */
	public void send(TradingEntity trade) {
<<<<<<< HEAD
		rabbitTemplate.convertAndSend(queue, trade);
		System.out.println("Send msg = " + trade);
	    
=======
		rabbitTemplate.convertAndSend(queue, trade);	    
>>>>>>> 4b1501c0216562ed5ba847a8278dad0956cca4b6
	}
}