package com.becalled.controller;

import com.becalled.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TTController {

    @Autowired
    private TestService testService;
    @GetMapping("/getAge")
    public Integer getAge(@RequestParam Integer age) {
        return testService.getAge(age);
    }
}
