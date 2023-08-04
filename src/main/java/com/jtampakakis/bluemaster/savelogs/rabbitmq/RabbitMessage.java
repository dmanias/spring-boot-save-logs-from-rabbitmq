package com.jtampakakis.bluemaster.savelogs.rabbitmq;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.amqp.core.MessageProperties;

public class RabbitMessage {

    private String msg;
    private String exchange;
    private String routingKey;
    
    private MessageProperties messageProperties;

    public RabbitMessage() {}
	public RabbitMessage(String msg, String exchange, String routingKey,Map<String, String> head) {
		this.msg = msg;
		this.exchange = exchange;
		this.routingKey = routingKey;
		createHeader(head);
	}
	
	public void createHeader(Map<String, String> head) {
		messageProperties = new MessageProperties();
	    for(Entry<String, String> entry: head.entrySet()) {
	      messageProperties.setHeader(entry.getKey(),entry.getValue());
	    }
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public MessageProperties getMessageProperties() {
		return messageProperties;
	}

	public void setMessageProperties(MessageProperties messageProperties) {
		this.messageProperties = messageProperties;
	} 
}