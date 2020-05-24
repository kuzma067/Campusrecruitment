package com.kmust.recruitment.utils;


import java.lang.annotation.*;

/**
 * 使用注解统一校验角色权限
 * @author LonG
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoleCheck {
    String  value();
}