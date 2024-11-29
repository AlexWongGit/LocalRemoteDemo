package com.test.annotation;

import org.springframework.cloud.openfeign.FeignClient;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@FeignClient
@Documented
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
public @interface OpenFeignClient {

    String beanName() default "";

    String name() default "";

    String url() default "";

    Class<?>[] configuration() default {};

}
