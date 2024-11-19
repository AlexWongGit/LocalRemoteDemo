package com.becalled.service.impl;

import com.becalled.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String getName() {
        return "xiaoming";
    }

    @Override
    public Integer getAge(Integer age) {
        return age;
    }
}
