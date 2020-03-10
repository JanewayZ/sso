本项目是spring boot项目直接本地直接运行com.sso包下的SsoApplication.java启动类即可运行项目
	主要路由IndexController
	项目的端口：8085，端口号可自行修改application.properties文件即可
	
运行本项目，直接访问路径为http://localhost:8085/index即可跳转到sso验证凭证

主要代码：
return new ModelAndView("redirect:http://127.0.0.1:7001/#/account/login?appId=7AAE48A8-9957-4730-9F7C-746F87E05C5A&returnUrl=http://127.0.0.1:8085/oapass");
这里特别注意的参数：

第一个地址链接：
http://127.0.0.1:7001/#/account/login
此链接为sso项目的登录访问地址，根据发布服务器地址进行修改

appId=7AAE48A8-9957-4730-9F7C-746F87E05C5A
此appId由sso服务提供

returnUrl=http://127.0.0.1:8085/oapass
此参数returnUrl是提供sso回调地址返回token,此地址为本项目的地址，oapass路由详见IndexController。对于ip可根据本地运行时的本机ip进行修改


#后续添加测试步骤

