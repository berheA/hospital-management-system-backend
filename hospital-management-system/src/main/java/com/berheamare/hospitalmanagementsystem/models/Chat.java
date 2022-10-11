package com.berheamare.hospitalmanagementsystem.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity()
//@Table()
public class Chat{
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "chat_id")
//	private long chat_id;
//	@Column(name = "name")
	private String name;
	private String content;
	
public void setName(String name) {
    this.name = name;
}

public String getName() {
    return this.name;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}

}
