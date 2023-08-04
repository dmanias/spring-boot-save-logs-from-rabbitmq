package com.jtampakakis.bluemaster.savelogs.rabbitmq;

public class StandardMessage {
	
	private String uuid;
	private String replyToUuid;
	private String dateSent;
	private String sender;
	private String topic;
	private String exchange;
	private String msgContent;
	private String targetObject;
	
	public StandardMessage(String uuid, String dateSent, String sender, String topic, String exchange, String msgContent,String targetObject) {
		this.uuid = uuid;
		this.dateSent = dateSent;
		this.sender = sender;
		this.topic = topic;
		this.exchange = exchange;
		this.msgContent = msgContent;
		this.targetObject = targetObject;
	}
	
	// For replying messages
	public StandardMessage(String uuid,String replyToUuid, String dateSent, String sender, String topic, String exchange, String msgContent,String targetObject) {
		this.uuid = uuid;
		this.replyToUuid=replyToUuid;
		this.dateSent = dateSent;
		this.sender = sender;
		this.topic = topic;
		this.exchange = exchange;
		this.msgContent = msgContent;
		this.targetObject = targetObject;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getReplyToUuid() {
		return replyToUuid;
	}
	public void setReplyToUuid(String replyToUuid) {
		this.replyToUuid = replyToUuid;
	}
	public String getDateSent() {
		return dateSent;
	}
	public void setDateSent(String dateSent) {
		this.dateSent = dateSent;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}	
}