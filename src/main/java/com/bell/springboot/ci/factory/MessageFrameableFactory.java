package com.bell.springboot.ci.factory;

import com.bell.springboot.ci.handler.MessageFrameable;

public interface MessageFrameableFactory {
	
	public MessageFrameable setMessageFrameable(MessageFrameable messageFrameable, String name);
	
	public MessageFrameable getMessageFrameable(String commandCode);
}
