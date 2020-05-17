package com.kmust.recruitment.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
@Slf4j
@Component
public class LoginInterceptor
	implements HandlerInterceptor {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle方法");
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter writer = response.getWriter();
			JSONObject json = new JSONObject();
			json.put("code","400");
			json.put("msg", "未登录");
			writer.write(json.toJSONString());
			log.info("没有登录，请先登录");
			//response.sendRedirect("/user/index");
			return false;
		}
		log.info("登录拦截通过了！");
		/*System.out.println("preHandle");
		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			System.out.println("cat cast handler to HandlerMethod.class");
			return true;
		}
		// 获取注解
		Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
		if (auth == null) {
			System.out.println("cant find @Auth in this uri:" + request.getRequestURI());
			return true;
		}
		// 从参数中取出用户身份并验证
		String admin = auth.role();
		if (!admin.equals(request.getParameter("user"))) {
			System.out.println("permission denied");
			response.setStatus(403);
			return false;
		}*/

		return true;
	}
	/**
	 * 在请求被处理后，视图渲染之前调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在整个请求结束后调用
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}










