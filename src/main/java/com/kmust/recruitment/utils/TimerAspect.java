package com.kmust.recruitment.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
		
		// 计算耗时
		System.err.println("耗时：" + (end - start) + "毫秒。");
		
		// 返回切面之后的方法的返回值
		return result;
	}

}






