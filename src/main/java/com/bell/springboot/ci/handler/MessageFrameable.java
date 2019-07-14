package com.bell.springboot.ci.handler;

import com.bell.springboot.ci.MessageFrame;

public interface MessageFrameable {
	
	String getCommandCode();
	
	void handle(String deviceId, MessageFrame messageFrame);
}
