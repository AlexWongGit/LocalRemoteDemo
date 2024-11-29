package com.test.aspect;

import com.test.annotation.OpenFeignClient;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Aspect
@Order(1)
@Component
public class FeignClientsAspect implements ApplicationContextAware {

    public static ConcurrentMap<String, Object> feignClientMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Around("@within(com.test.annotation.OpenFeignClient)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        // 获取注解（这里要获取代理类上的注解）
        OpenFeignClient openFeignClientAnnotation = AnnotationUtils.findAnnotation(target.getClass(), OpenFeignClient.class);

        if (openFeignClientAnnotation == null) {
            return joinPoint.proceed();
        }

        String beanName = openFeignClientAnnotation.beanName();

        if (!beanName.isEmpty()) {
            Object dynamicBean;
            if (feignClientMap.containsKey(beanName)) {
                dynamicBean = feignClientMap.get(beanName);
            } else {
                try {
                    dynamicBean = applicationContext.getBean(beanName);
                } catch (NoSuchBeanDefinitionException e) {
                    return joinPoint.proceed();
                }
                feignClientMap.put(beanName, dynamicBean);
            }

            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            if (methodSignature.getDeclaringType().isAssignableFrom(dynamicBean.getClass())) {
                return joinPoint.proceed();
            }else {
                Method trueMethod = dynamicBean.getClass().getMethod(method.getName(), method.getParameterTypes());
                return trueMethod.invoke(dynamicBean, joinPoint.getArgs());
            }
        }

        return joinPoint.proceed();
    }
}