package com.test.controller;

import com.test.feign.FeignClientService;
import com.test.feign.FeignClientService2;
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
    private FeignClientService2 feignClientService2;

    @GetMapping("/getAge")
    public Integer getAge(Integer age) {
        return feignClientService.getAge(age);
    }

    @GetMapping("/getAge2")
    public Integer getAge2(Integer age) {
        return feignClientService2.getAge(age);
    }
}
