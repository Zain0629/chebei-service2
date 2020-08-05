package com.chebei.user.service;

public interface PasswordValidator {
	/**
	 * 校验密码是否正确
	 * 
	 * @param cipherText
	 *            密文
	 * @param originalText
	 *            明文
	 * @return 是否正确
	 */
	public boolean validate(String cipherText, String originalText);

	/**
	 * 密码签名
	 * 
	 * @param originalText
	 *            明文
	 * @return 返回签名后密码
	 */
	public String digest(String originalText);
}
