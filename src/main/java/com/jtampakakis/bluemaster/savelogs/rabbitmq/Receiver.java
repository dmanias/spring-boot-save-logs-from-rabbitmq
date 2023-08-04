package com.jtampakakis.bluemaster.savelogs.rabbitmq;

import java.util.logging.Logger;

import com.jtampakakis.bluemaster.savelogs.services.MessageService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.jtampakakis.bluemaster.savelogs.Startup;

@Component
public class Receiver {
	private final static Logger LOGGER = Startup.LOGGER;

	private final RabbitTemplate queueSender;

	private final MessageService messageService;

	public Receiver(RabbitTemplate queueSender, MessageService messageService) {
		this.queueSender = queueSender;
		this.messageService = messageService;
	}

	@RabbitListener(queues = { "${queue.name}" })
	public void receive(@Payload String fileBody, Message message) throws JsonProcessingException {
		MessageProperties props = message.getMessageProperties();
		LOGGER.info("AMPQ - Received message with routing key "+props.getReceivedRoutingKey());
		LOGGER.info("FileBody content: " + fileBody);
		// String routingKey = props.getReceivedRoutingKey();  // Not used at the moment

		StandardMessage standardMessage = new Gson().fromJson(fileBody, StandardMessage.class);

		// Log the entire StandardMessage object
		LOGGER.info("StandardMessage: " + new Gson().toJson(standardMessage));

		try {
			messageService.convertAndSave(standardMessage);
			LOGGER.info("Message has been saved to MySQL database successfully");
		} catch (RuntimeException e) {
			LOGGER.severe("Failed to save message to MySQL database: " + e.getMessage());
		}
	}
}
