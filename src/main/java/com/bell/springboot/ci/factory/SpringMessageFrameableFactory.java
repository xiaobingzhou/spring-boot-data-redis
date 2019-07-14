package com.bell.springboot.ci.factory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.bell.springboot.ci.handler.MessageFrameable;

@Component
public class SpringMessageFrameableFactory implements MessageFrameableFactory, ApplicationContextAware{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	private static Map<String, String> messageFrameableMap = new HashMap<>(128);
	
	public MessageFrameable setMessageFrameable(MessageFrameable messageFrameable, String beanName) {
		if (messageFrameable == null) {
			return null;
		}
		String commandCode = messageFrameable.getCommandCode();
		if (isEmpty(commandCode)) {
			String message = messageFrameable.getClass().getName()+" commandCode is empty!";
			logger.warn(message);
			throw new RuntimeException(message);
		}
		String put = messageFrameableMap.put(commandCode, beanName);
		return put == null ? null : (MessageFrameable) applicationContext.getBean(put);
	}

	public MessageFrameable getMessageFrameable(String commandCode) {
		if (isEmpty(commandCode)) {
			return null;
		}
		String beanName = messageFrameableMap.get(commandCode);
		return beanName == null ? null : (MessageFrameable) applicationContext.getBean(beanName);
	}

	private boolean isEmpty(String commandCode) {
		return commandCode == null || "".equals(commandCode);
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
