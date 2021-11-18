package com.bossoh.gmsscbackend.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class UtilRandom {
	private final Random RANDOM=new SecureRandom();
	private final String ALPHABET= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final String ALP= "1234567890";
	public String generetedUserID(int lenght) {
		return generatedRandomString(lenght);
	}
	public String generetString(int lenght) {
		return generatedRandomNumber(lenght);
	}

	public String generatedRandomString(int lenght) {
		StringBuilder returnValue=new StringBuilder(lenght);
		for (int i = 0; i < lenght; i++) {
			
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
			
		}
		return new String(returnValue) ;
	}

	public String generatedRandomNumber(int lenght) {
		StringBuilder returnValue=new StringBuilder(lenght);
		for (int i = 0; i < lenght; i++) {

			returnValue.append(ALP.charAt(RANDOM.nextInt(ALP.length())));

		}
		return new String(returnValue) ;
	}
}
