package com.test.controller;

import com.test.executor.MethodExecutor;
import com.test.feign.FeignClientService;
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
    public Integer getAge(Integer age,boolean isLocal) throws Exception {
        if (isLocal) {
            return (Integer)methodExecutor.executeMethod("testService", "getAge", age);
        } else {
            return feignClientService.getAge(age);
        }

    }

    @GetMapping("/getName")
    public String getName(boolean isLocal) throws Exception {
        if (isLocal) {
            return (String)methodExecutor.executeMethod("testService", "getName", null);
        } else {
            return feignClientService.getName();
        }
    }
}
