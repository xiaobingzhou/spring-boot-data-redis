package com.bell.springboot.ci.factory;

import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.bell.springboot.ci.handler.MessageFrameable;


@Component
public class MessageFrameableBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	MessageFrameableFactory messageFrameableFactory;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof MessageFrameable) {
			MessageFrameable duplicate = messageFrameableFactory.setMessageFrameable((MessageFrameable)bean, beanName);
			if (Objects.nonNull(duplicate)) {
				throw new BeanCreationException("MessageFrameable接口的实现类["+
					beanName+"]和["+duplicate.getClass().getName()+
					"]的commandCod相同:"+duplicate.getCommandCode());
			};
		}
		return bean;
	}

	
	
}
