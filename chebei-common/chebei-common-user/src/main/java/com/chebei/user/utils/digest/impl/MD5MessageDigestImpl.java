package com.chebei.user.utils.digest.impl;

import org.apache.commons.codec.digest.DigestUtils;

public final class MD5MessageDigestImpl extends AbstractMessageDigestImpl {

	@Override
	protected byte[] digestInternal(byte[] bytes) {
		if (bytes == null) {
			throw new IllegalArgumentException("paramter bytes can't be null");
		}
		return DigestUtils.md5(bytes);
	}

}
