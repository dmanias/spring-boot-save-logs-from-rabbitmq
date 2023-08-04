package com.jtampakakis.bluemaster.savelogs.rabbitmq;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.jtampakakis.bluemaster.savelogs.utils.Utils;

@RestController
@RequestMapping("/rabbitsender")
public class Sender {
	    
    private final RabbitTemplate queueSender;

	public Sender(RabbitTemplate queueSender) {
		this.queueSender = queueSender;
	}

	public String send(RabbitMessage rm) throws JsonProcessingException {
		Message message = new Message(rm.getMsg().getBytes(), rm.getMessageProperties());
		queueSender.convertAndSend(rm.getExchange(),rm.getRoutingKey(), message);
		return "ok. done";
	}

//	public static void GenerateMessage(String resp,String targetClass,String exchange,String routingKey,Map<String,String> head, RabbitTemplate queueSender) {
//		System.out.println("Publishing message ("+exchange+", "+routingKey+"): "+resp);
//		StandardMessage message = new StandardMessage(UUID.randomUUID().toString(),Utils.RetrieveCurrentTmstamp(), System.getProperty("spring.application.name"),routingKey,exchange,resp,targetClass);
//		RabbitMessage msg = new RabbitMessage(new Gson().toJson(message),exchange,routingKey,head);
//		Sender sender = new Sender(queueSender);
//		try {
//			sender.send(msg);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public static void GenerateMessage(String resp,String replyTo, String targetClass,String exchange,String routingKey,Map<String,String> head, RabbitTemplate queueSender) {
		StandardMessage message = new StandardMessage(UUID.randomUUID().toString(),replyTo,Utils.RetrieveCurrentTmstamp(), System.getProperty("spring.application.name"),routingKey,exchange,resp,targetClass);
		RabbitMessage msg = new RabbitMessage(new Gson().toJson(message),exchange,routingKey,head);
		Sender sender = new Sender(queueSender);
		try {
			sender.send(msg);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@PostMapping
	public String sendapi (@RequestBody RabbitMessage rm) throws JsonProcessingException {
		Map<String, String> h = new HashMap<String,String>();
		h.put("test1","yo");
		h.put("test2","yoo");
		rm.createHeader(h);
		
		//RabbitMessage rm = new RabbitMessage("This is a test message from Spring boot - over and out 3", "testex", "test.route",h);
		return send(rm);
	}
	
}
