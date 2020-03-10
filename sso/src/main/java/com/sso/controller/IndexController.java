package com.sso.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sso.entity.JsonRootBean;
import com.sso.entity.UserInfo;
import com.sso.interceptor.AuthorizationInterceptor;
import com.sso.utils.HeaderUtils;

@RestController
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	/**
	 * 启动首访问地址，用于跳转到sso服务登录
	 * @return
	 */
	@GetMapping("index")
	public ModelAndView index() {
		/**
		 * http://192.168.2.149:7001/#/account/login此路径是ssourl即sso登录界面的url
		 * returnUrl是此项目的路径http://192.168.2.184:8085/oapass
		  * 详情参照：readme.txt
		 */
		return new ModelAndView("redirect:http://127.0.0.1:7001/#/account/login?appId=7AAE48A8-9957-4730-9F7C-746F87E05C5A&returnUrl=http://127.0.0.1:8085/oapass");
	}
	
	/**
	 * sso回调地址，同时验证token凭证
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping("oapass") 
	public String redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("sso凭证验证");
		String authorization = request.getHeader(HeaderUtils.HEADER_AUTHORIZATION);
		if (StringUtils.isBlank(authorization)) {
			//renderJson(response, "头部信息authorization缺失");
			return "0";
		}
		String token = HeaderUtils.getToken(authorization);
        String url = "http://192.168.0.184/api/BasicUserInfo/7AAE48A8-9957-4730-9F7C-746F87E05C5A";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Bearer " + token);
        CloseableHttpResponse httpResponse = client.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseJson = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(responseJson);
            JSONObject obj = JSON.parseObject(responseJson);
            JsonRootBean info = JSON.toJavaObject(obj, JsonRootBean.class);
            logger.info("验证用户："+info.getData().getUserName());
	        if (info!=null) {
				return "1";
			}
        }
        return "0";
	}
	
	@GetMapping("html")
	public ModelAndView html() {
		return new ModelAndView("fail");
	}

}
