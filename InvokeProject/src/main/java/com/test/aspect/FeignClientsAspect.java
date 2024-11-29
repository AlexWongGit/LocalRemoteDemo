package com.test.aspect;

import com.test.annotation.CustomFeignClient;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Order(1)
@Component
public class FeignClientsAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Around("@within(com.test.annotation.CustomFeignClient)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解（这里要获取代理类上的注解）
        CustomFeignClient customFeignClientAnnotation = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), CustomFeignClient.class);

        if (customFeignClientAnnotation == null) {
            return joinPoint.proceed();
        }

        String beanName = customFeignClientAnnotation.beanName();

        if (!beanName.isEmpty()) {
            Object dynamicBean;
            try {
                dynamicBean = applicationContext.getBean(beanName);
            } catch (NoSuchBeanDefinitionException e) {
                beanName += "FeignClient";
                dynamicBean = applicationContext.getBean(beanName);
            }

            // 在调用方法时，替换目标对象为动态 Bean
            return joinPoint.proceed(new Object[]{dynamicBean});
        }

        return joinPoint.proceed();
    }
}