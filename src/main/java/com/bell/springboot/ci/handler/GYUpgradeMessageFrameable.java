package com.bell.springboot.ci.handler;

import org.springframework.stereotype.Component;

import com.bell.springboot.ci.MessageFrame;

@Component("GYUpgradeMessageFrameable")
public class GYUpgradeMessageFrameable implements MessageFrameable{

	@Override
	public String getCommandCode() {
		return Integer.toHexString(MessageFrame.GY_UPGRADE);
	}

	@Override
	public void handle(String deviceId, MessageFrame messageFrame) {
		System.out.println("deviceid:"+deviceId);
		System.out.println("messageFrame:"+messageFrame);
	}

}
