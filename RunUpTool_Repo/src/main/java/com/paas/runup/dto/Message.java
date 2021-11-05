package com.paas.runup.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
	public enum MessageType{
		ENTER, COMM
	}
	
	private MessageType messageType;
	private String roomId;
	private String sender;
	private String message;	
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Message(MessageType messageType, String roomId, String sender, String message) {
		super();
		this.messageType = messageType;
		this.roomId = roomId;
		this.sender = sender;
		this.message = message;
	}
	
	
	
}
