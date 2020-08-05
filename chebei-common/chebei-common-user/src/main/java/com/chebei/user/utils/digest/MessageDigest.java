package com.chebei.user.utils.digest;

public interface MessageDigest {

    public String digest(String text);

    public String digest(String text, String encoding);

}
