//package com.kmust.recruitment.config;
//
//
/////**
//// * 拦截器的配置类
//// */
//
//import com.kmust.recruitment.interceptor.LoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class LoginInterceptorConfigurer implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		HandlerInterceptor interceptor = new LoginInterceptor();
//
//		List<String> paths = new ArrayList<>();
//
//
//
//		paths.add("/user/reg");
//		paths.add("/user/login");
//		paths.add("/user/ver");
//
//		paths.add("/admin/**");
//		//paths.add("/info/**");
//
//		paths.add("/projects/**");
//		paths.add("/no_projects/**");
//		paths.add("/a/*");
//
//		registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(paths);
//	}
//
//}
//
