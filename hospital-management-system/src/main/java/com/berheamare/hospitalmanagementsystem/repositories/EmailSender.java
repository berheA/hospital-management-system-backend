package com.berheamare.hospitalmanagementsystem.repositories;

public interface EmailSender {

	void send(String to, String email);
}
