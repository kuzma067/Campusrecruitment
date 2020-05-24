package com.kmust.recruitment.utils;

import com.kmust.recruitment.constant.ErrorMessage;
import com.kmust.recruitment.service.ex.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 角色权限校验-AOP
 * @author  zhanglf
 * @date 2019-04-29
 */
@Aspect
@Component
@Slf4j
public class RoleCheckAspect {

    //切入点表达式决定了用注解方式的方法切还是针对某个路径下的所有类和方法进行切，方法必须是返回void类型
    @Pointcut("@annotation(com.kmust.recruitment.utils.RoleCheck)")
    private void permissionCheckCut(){};

    //定义了切面的处理逻辑。即方法上加了@PermissionCheck
    @Around("permissionCheckCut()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        log.info("==========哈哈哈，进入AOP============================");
        //1.记录日志信息
        Signature signature = point.getSignature();
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        log.info("className:{},methodName:{}", className, methodName);

        Object target = point.getTarget();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        RoleCheck methodAnnotation = method.getAnnotation(RoleCheck.class);
        if (methodAnnotation != null) {
            String methodRole = methodAnnotation.value();


            //从session或者缓存中取出用户的角色信息,进行对比,,如果不符合返回权限不足
            log.info("当前接口请求要求的用户角色role:{}", methodRole);
            if (StringUtils.isNotEmpty(methodRole)) {
                String[] roles = methodRole.split(",");//接口允许的角色
                List<String> list = Arrays.asList(roles);
                //如果该接口只允许老师角色访问。则要获取当前用户是不是老师角色。
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                //从获取RequestAttributes中获取HttpServletRequest的信息
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
                //用户的角色
                String userRole = (String) request.getSession().getAttribute("role");
                log.info("当前接口请求用户的角色role:{}", userRole);
                if (list.contains(userRole)) {
                    log.info("AOP权限角色校验通过，进入业务层处理！");
                    //3.执行业务逻辑，放行
                    //return point.proceed();
                } else {
                    throw new PermissionException(ErrorMessage.ROLE_NOT_ALLOW);
                }
            }

        }

        /*//注解打在类上
        RoleCheck classAnnotation = (RoleCheck) point.getSignature().getDeclaringType().getAnnotation(RoleCheck.class);;
        if (classAnnotation != null) {
            String classRole = classAnnotation.value();


            //从session或者缓存中取出用户的角色信息,进行对比,,如果不符合返回权限不足
            log.info("当前接口请求要求的用户角色role:{}", classRole);
            if (StringUtils.isNotEmpty(classRole)) {
                String[] roles = classRole.split(",");//接口允许的角色
                List<String> list = Arrays.asList(roles);
                //如果该接口只允许老师角色访问。则要获取当前用户是不是老师角色。
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                //从获取RequestAttributes中获取HttpServletRequest的信息
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
                //用户的角色
                String userRole = (String) request.getSession().getAttribute("role");
                log.info("当前接口请求用户的角色role:{}", userRole);
                if (list.contains(userRole)) {
                    log.info("AOP权限角色校验通过，进入业务层处理！");
                    //3.执行业务逻辑，放行
                    return point.proceed();
                } else {
                    throw new PermissionException();
                }

            }
        }*/

        /*log.info("==========哈哈哈，进入AOP============================");
        //1.记录日志信息
        Signature signature = point.getSignature();
        String className = point.getTarget().getClass().getSimpleName();
        String methodName = signature.getName();
        log.info("className:{},methodName:{}",className,methodName);

        //2.角色权限校验
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();
        if(targetMethod.isAnnotationPresent(RoleCheck.class)){
            //获取方法上注解中表明的权限
            RoleCheck permission = (RoleCheck) targetMethod.getAnnotation(RoleCheck.class);
            String role = permission.value();
            log.info("当前接口请求要求的用户角色role:{}",role);
            if(StringUtils.isNotEmpty(role)){
                String[] roles = role.split(",");//接口允许的角色
                List<String> list = Arrays.asList(roles);
                //如果该接口只允许老师角色访问。则要获取当前用户是不是老师角色。
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                //从获取RequestAttributes中获取HttpServletRequest的信息
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
                //用户的角色
                String userRole = (String) request.getSession().getAttribute("role");
                log.info("当前接口请求用户的角色role:{}",userRole);
                if(list.contains(userRole)){
                    log.info("AOP权限角色校验通过，进入业务层处理！");
                    //3.执行业务逻辑，放行
                    return point.proceed();
                }else{
                    log.info("AOP权限角色校验不通过");
                    return point.;
                }
            }
        }
     return ErrorMessage.ROLE_NOT_ALLOW;
    }
*/


        throw new PermissionException();
    }
}