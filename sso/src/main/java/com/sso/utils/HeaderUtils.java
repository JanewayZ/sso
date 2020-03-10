package com.sso.utils;

import org.apache.commons.lang3.StringUtils;



public class HeaderUtils {
	
	public static final String HEADER_AUTHORIZATION ="authorization";
	public static final String HEADER_AUTHORIZATION_BEARER ="Bearer";
	
	public static String getToken(String authorization) {
		String token = "";
		String[] authorizationParts = authorization.split(" ");
		for (String temp : authorizationParts) {
			if (StringUtils.isBlank(temp)) {
				continue;
			}

			if (HeaderUtils.HEADER_AUTHORIZATION_BEARER.equals(temp.trim())) {
				continue;
			}

			token = temp.trim();
			break;
		}

		return token;
	}

}