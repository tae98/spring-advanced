package org.example.expert.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class AdminAcessLogAop {

    // 어노테이션 범위 기반 포인트컷
    //포인트 컷
    @Pointcut("@annotation(org.example.expert.domain.common.annotation.Admin)")
    private void adminAccessLog(){}

    @After("adminAccessLog()")
    public void afterAdminAccessLog(JoinPoint joinPoint){
        // API 요청한 사용자의 ID
        Object[] args = joinPoint.getArgs();
        Long userId = (Long) args[0];

        // API 요청 시각
        long start = System.currentTimeMillis();

        // API 요청 URL
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestUrl = request.getRequestURL().toString();


        log.info("::: 요청한 사용자의 ID :{} :::" , userId);
        log.info("::: API 요청 시각 : {} :::", start);
        log.info("::: API 요청 URL : {} :::", requestUrl);
    }

}