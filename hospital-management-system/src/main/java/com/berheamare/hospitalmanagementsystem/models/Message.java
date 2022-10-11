package com.berheamare.hospitalmanagementsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity()
//@Table()
public class Message{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ms_id")
//	private long ms_id;
//	
//	@Column(name = "chat_id")
//	private long chat_id;
//	
//	@Column(name = "sender")
//	private String sender;
//	
//	@Column(name = "t_stamp")
//	private String t_stamp;
//	
//	@Column(name = "content")
	private String greeting;
	
	public Message() {
	}

	public Message(String greeting) {
	    this.greeting = greeting;
	}

	public void setGreeting(String greeting) {
	    this.greeting = greeting;
	}

	public String getGreeting() {
	    return this.greeting;
	}
	
}