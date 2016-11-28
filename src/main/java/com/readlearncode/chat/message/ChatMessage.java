package com.readlearncode.chat.message;

public class ChatMessage {
	private MessageType messageType;
	private String message;

	public void setMessageType(MessageType v) { this.messageType = v; }
	public MessageType getMessageType() { return messageType; }
	public void setMessage(String v) { this.message = v; }
	public String getMessage() { return this.message; }
}
