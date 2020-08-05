package com.chebei.user.service.impl;


import com.chebei.user.service.PasswordValidator;
import com.chebei.user.utils.digest.MessageDigest;
import org.springframework.stereotype.Service;

@Service
public class PasswordValidatorImpl implements PasswordValidator {

	private MessageDigest messageDigest;

	@Override
	public boolean validate(String cipherText, String originalText) {
		String newText = messageDigest.digest(originalText);
		return newText.equals(cipherText);
	}

	@Override
	public String digest(String originalText) {
		return messageDigest.digest(originalText);
	}

	public MessageDigest getMessageDigest() {
		return messageDigest;
	}

	public void setMessageDigest(MessageDigest messageDigest) {
		this.messageDigest = messageDigest;
	}
}
