package com.kmust.recruitment.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimerAspect {

	@Around("execution(* com.kmust.recruitment.service.*.*(..))")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		// 记录起始时间
		long start = System.currentTimeMillis();
		
		// 执行方法，获取返回值
		Object result = pjp.proceed();
		
		// 记录结束时间
		long end = System.currentTimeMillis();

		/*//获取到当前线程绑定的请求对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//已经拿到session,就可以拿到session中保存的用户信息了。
		log.info(String.valueOf(request.getSession().getServletContext()));
		log.info((String) request.getSession().getAttribute("username"));
		log.info("SessionId:"+request.getSession().getId());*/
		// 计算耗时
		log.info("耗时：" + (end - start) + "毫秒。");
		
		// 返回切面之后的方法的返回值
		return result;
	}

}







