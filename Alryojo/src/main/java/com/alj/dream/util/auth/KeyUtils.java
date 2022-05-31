package com.alj.dream.util.auth;

import java.util.UUID;

public class KeyUtils {
	
	
	public static String createAuthKey() {
		
		String key="";
		for(int i=0; i<4; i++) {
			key=key.concat(String.valueOf((char)((Math.random()*26)+65)));
		}
		
		System.out.println(key);
		return key;
	}
	
	public static String createRandomKeyCode() {
		return UUID.randomUUID().toString();
	}

}
