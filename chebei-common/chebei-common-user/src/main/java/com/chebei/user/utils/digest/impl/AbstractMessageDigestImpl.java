package com.chebei.user.utils.digest.impl;



import com.chebei.user.utils.digest.MessageDigest;

import java.io.UnsupportedEncodingException;

public abstract class AbstractMessageDigestImpl implements MessageDigest {

	private static final String DEFAULT_CHARSET = "UTF-8";

	private String salt;
	private String encoding = DEFAULT_CHARSET;

	public void setSalt(String salt) {
		if (salt != null && !"".equals(salt)) {
			this.salt = "{" + salt + "}";
		}
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public String digest(String text) {
		return digest(text, this.encoding);
	}

	@Override
	public String digest(String text, String encoding) {
		if (text == null) {
			return null;
		}
		if (encoding == null) {
			encoding = this.encoding;
		}

		byte[] bytes = null;
		try {
			bytes = mergeMessageAndSalt(text).getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("convert string to byte array failed, unsupport encoding: " + encoding, e);
		}
		return encodeBytesVisibled(digestInternal(bytes));
	}

	/**
	 * 解码
	 * @param bytes is bytes
	 * @return byte[]
	 */
	protected abstract byte[] digestInternal(byte[] bytes);

	protected String encodeBytesVisibled(byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		byte tb;
		char low;
		char high;
		char tmpChar;

		String hexStr = "";

		for (int i = 0; i < bytes.length; i++) {
			tb = bytes[i];

			tmpChar = (char) ((tb >>> 4) & 0x000f);

			if (tmpChar >= 10) {
				high = (char) (('a' + tmpChar) - 10);
			} else {
				high = (char) ('0' + tmpChar);
			}

			hexStr += high;
			tmpChar = (char) (tb & 0x000f);

			if (tmpChar >= 10) {
				low = (char) (('a' + tmpChar) - 10);
			} else {
				low = (char) ('0' + tmpChar);
			}

			hexStr += low;
		}

		return hexStr;
	}

	protected String mergeMessageAndSalt(String text) {
		if (text == null) {
			text = "";
		}
		if (this.salt == null) {
			return text;
		} else {
			return text + this.salt;
		}
	}

}