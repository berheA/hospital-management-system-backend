package com.berheamare.hospitalmanagementsystem.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.berheamare.hospitalmanagementsystem.models.Chat;
import com.berheamare.hospitalmanagementsystem.models.Message;
@RestController
//@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {

//	@Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//	@Autowired
//	private ChatRepository chatRepository;
//	@Autowired
//	private MessageRepository messageRepository;
	
	@MessageMapping("/msgMap")
	@SendTo("/chat/hi")
	public Message greeting(Chat user) throws Exception {
	    return new Message(user.getName() + " " + user.getContent()+ "!");
	}
}

//    @MessageMapping("/chat/{to}") //to = nome canale
//    public void sendMessage(@DestinationVariable String to , Message message) {
//        System.out.println("handling send message: " + message + " to: " + to);
//        message.setChat_id(createAndOrGetChat(to));
//        message.setT_stamp(generateTimeStamp());
//        
//        message = messageRepository.save(message);
//        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
//    }
//    
//    @PostMapping("/getChats")
//    public List<Chat> getChats(@RequestBody String user){
//    	return chatRepository.findByPartecipant(user);
//    }
//    
//    //returns an empty list if the chat doesn't exist
//    @PostMapping("/getMessages")
//	public List<Message> getMessages(@RequestBody String chat) {
//    	Chat ce = chatRepository.findByName(chat);
//    	
//    	if(ce != null) {
//    		return messageRepository.findAllByChat(ce.getChat_id());
//    	}
//    	else{
//    		return new ArrayList<Message>();
//    	}
//    }
//    
//    //finds the chat whose name is the parameter, if it doesn't exist it gets created, the ID gets returned either way 
//    private Long createAndOrGetChat(String name) {
//    	Chat ce = chatRepository.findByName(name);
//    	
//    	if (ce != null) {
//    		return ce.getChat_id();
//    	}
//    	else {
//    		Chat newChat = new Chat(name);
//    		return chatRepository.save(newChat).getChat_id();
//    	}
//    }
//    
//    private String generateTimeStamp() {
//		Instant i = Instant.now();
//		String date = i.toString();
//		System.out.println("Source: " + i.toString());
//		int endRange = date.indexOf('T');
//		date = date.substring(0, endRange);
//		date = date.replace('-', '/');
//		System.out.println("Date extracted: " + date);
//		String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
//		time += ":";
//
//		int minutes = i.atZone(ZoneOffset.UTC).getMinute();
//		if (minutes > 9) {
//			time += Integer.toString(minutes);
//		} else {
//			time += "0" + Integer.toString(minutes);
//		}
//
//		System.out.println("Time extracted: " + time);
//		String timeStamp = date + "-" + time;
//		return timeStamp;
//	}
//}