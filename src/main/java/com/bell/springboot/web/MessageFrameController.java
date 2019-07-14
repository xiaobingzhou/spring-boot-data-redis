package com.bell.springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bell.springboot.ci.MessageFrame;
import com.bell.springboot.ci.factory.MessageFrameableFactory;
import com.bell.springboot.ci.handler.MessageFrameable;

@RestController
public class MessageFrameController {
	
	@Autowired
	private MessageFrameableFactory messageFrameableFactory;
	
	@GetMapping("/messageFrame")
	public String messageFrame(String commandCode) {
		MessageFrameable messageFrameable = messageFrameableFactory.getMessageFrameable(commandCode);
		messageFrameable.handle("123", new MessageFrame());
		return commandCode;
	}
}
