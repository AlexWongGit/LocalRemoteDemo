//package com.test.aspect;
//
//import com.test.annotation.FeignClients;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Method;
//
//@Aspect
//@Component
//public class FeignClientsAspect {
//
//    @Autowired
//    private ApplicationContext applicationContext;
//
//    @Around("execution(* *(..)) && @within(feignClients)")
//    public Object executeWithDynamicBeanClassLevel(ProceedingJoinPoint joinPoint, FeignClients feignClients) throws Throwable {
//        // 获取注解中的 beanName
//        String beanName = feignClients.beanName();
//
//        // 从 Spring 容器中获取指定的 Bean
//        Object bean = applicationContext.getBean(beanName);
//
//        // 获取方法参数
//        Object[] args = joinPoint.getArgs();
//
//        // 获取方法签名
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//
//        // 调用指定 Bean 的方法
//        return method.invoke((String) args[0]);
//    }
//
///*    @Around("execution(* *(..)) && @annotation(feignClients)")
//    public Object executeWithDynamicBeanMethodLevel(ProceedingJoinPoint joinPoint, FeignClients feignClients) {
//        // 获取注解中的 beanName
//        String beanName = feignClients.beanName();
//
//        // 从 Spring 容器中获取指定的 Bean
//        Object bean = applicationContext.getBean(beanName);
//
//        // 获取方法参数
//        Object[] args = joinPoint.getArgs();
//
//        // 调用指定 Bean 的方法
//        return bean.doSomething((String) args[0]);
//    }*/
//}