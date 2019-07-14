package com.learn.cloud.authority.common.constant;

import org.apache.shiro.crypto.hash.Md5Hash;

public class AuthorityConstants {

	public static final String DEFAULT_ALGRITHOM_MD5HASH = Md5Hash.ALGORITHM_NAME;

	public static final int DEFAULT_CRYPTO_ITERATIONS = 5;
	
	public static final String DEFAULT_CRYPTO_SALT = "test";

}
