package com.test.controller;

import com.test.executor.MethodExecutor;
import com.test.feign.FeignClientService;
import com.test.utils.SpringContextUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FeignClientService feignClientService;

    @Autowired
    private MethodExecutor methodExecutor;



    @GetMapping("/getAge")
    public Integer getAge(Integer age) throws Exception {
        try {
            Object bean = SpringContextUtil.getBean("testService");
            return (Integer)methodExecutor.executeMethod(bean, "getAge", age);
        } catch (NoSuchBeanDefinitionException e) {
            return feignClientService.getAge(age);
        }
    }
}
