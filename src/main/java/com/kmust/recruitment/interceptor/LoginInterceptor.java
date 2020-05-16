//package com.kmust.recruitment.interceptor;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * 登录拦截器
// */
//public class LoginInterceptor
//	implements HandlerInterceptor {
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		System.out.println("preHandle方法");
//		HttpSession session = request.getSession();
//		if (session.getAttribute("uid") == null) {
//			System.out.println("没有登录，请先登录");
//			//response.sendRedirect("/user/index");
//			return false;
//		}
//		return true;
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
