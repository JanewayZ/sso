package com.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sso.interceptor.AuthorizationInterceptor;
import com.sso.interceptor.CorsInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	

	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Autowired
	private CorsInterceptor corsInterceptor;
	
	private String[] excludes = {"/index","/html"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	 // 跨域拦截器需放在最上面
    	registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        InterceptorRegistration authorizationRegistration = registry.addInterceptor(authorizationInterceptor);

        //排除不拦截的，包括自己登录的页面不用拦截
        for(String temp : excludes){
        	authorizationRegistration.excludePathPatterns(temp);
        }
    }

}
