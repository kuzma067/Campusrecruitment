package com.kmust.recruitment.config;


///**
// * 拦截器的配置类
// */

import com.kmust.recruitment.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/user/login",
                "/user/reg","/user/ver","/user/login_code","/user/phone_login","/home/**","/user/miss_password");
		/*HandlerInterceptor interceptor = new LoginInterceptor();

		List<String> paths = new ArrayList<>();



		paths.add("/user/reg");
		paths.add("/user/login");
		paths.add("/user/ver");

		paths.add("/admin/**");
		//paths.add("/info/**");

		paths.add("/projects/**");
		paths.add("/no_projects/**");
		paths.add("/a/*");

		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(paths);*/
	}

}

