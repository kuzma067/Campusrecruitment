package com.kmust.recruitment.utils;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class WebControllerAop {

    Logger logger = LoggerFactory.getLogger(WebControllerAop.class);

    @Pointcut("execution(* com.kmust.recruitment.controller.*.*(..))")
    public void executeService(){

    }

    @Before("executeService()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        logger.info("==> controller方法调用开始...");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        //AOP代理类的信息
        joinPoint.getThis();
        //代理的目标对象
        joinPoint.getTarget();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        logger.info("==> 代理的是哪一个方法 :"+signature.getName());
        //AOP代理类的名字
        logger.info("==> AOP代理类的名字:"+signature.getDeclaringTypeName());
        //AOP代理类的类（class）信息
        signature.getDeclaringType();
        //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        logger.info("==> 请求者的IP："+request.getRemoteAddr());
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        //获取到当前线程绑定的请求对象
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //已经拿到session,就可以拿到session中保存的用户信息了。
        logger.info("==> 请求者的username:"+(String) request.getSession().getAttribute("username"));
        logger.info("==> 请求者的role:"+(String) request.getSession().getAttribute("role"));
        logger.info("==> 请求者的SessionId:"+request.getSession().getId());

        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = new HashMap<String,String>();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length > 0) {
            logger.info("==> 请求的参数信息为："+str);
        }
    }

}
