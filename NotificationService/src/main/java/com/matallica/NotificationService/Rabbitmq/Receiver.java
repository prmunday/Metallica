package com.matallica.NotificationService.Rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.matallica.NotificationService.Entity.TradingEntity;


@Component
public class Receiver {
	

	@RabbitListener(queues= "${queue.name}", containerFactory="jsaFactory")
	public void receiveMessage(TradingEntity trade) {
		System.out.println("Received <"+ trade +">");
	}
	}
