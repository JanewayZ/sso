package com.sso.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSON;
import com.sso.utils.HeaderUtils;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

	
		String authorization = request.getHeader(HeaderUtils.HEADER_AUTHORIZATION);
		if (StringUtils.isBlank(authorization)) {
			logger.info("AuthorizationInterceptor头部信息authorization缺失");
			renderJson(response, "头部信息authorization缺失");
			return false;
		}
		return true;
		
		
		

	}

	private void renderJson(HttpServletResponse response, String str) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(JSON.toJSONString(str));
	}
}
