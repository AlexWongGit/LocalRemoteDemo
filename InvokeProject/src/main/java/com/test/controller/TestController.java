package com.test.controller;

import com.test.executor.LocalMethodExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private LocalMethodExecutor localMethodExecutor;



    @GetMapping("/getAge")
    public Integer getAge(Integer age) throws Exception {
        return localMethodExecutor.executeMethod("testServiceImpl2", Integer.class,"getAge", age);
    }
}
