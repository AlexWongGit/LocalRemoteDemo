package com.test.controller;

import com.test.executor.LocalMethodExecutor;
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

    @GetMapping("/getAge")
    public Integer getAge(Integer age) {
        return feignClientService.getAge(age);
    }
}
