//package com.kmust.recruitment.utils;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@Slf4j
//public class SpringSecurityAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        String userId = SecurityUtils.getCurrentUserId();
//        if (userId != null){
//            return Optional.of(userId);
//        } else {
//            return Optional.empty();
//        }
//    }
//}
