package com.systemmanagement.payload.response;

public class MessageResponses {
	private String message;

	public MessageResponses(String message) {
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
