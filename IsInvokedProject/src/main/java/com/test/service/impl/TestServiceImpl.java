package com.test.service.impl;

import com.test.service.TestService;
import org.springframework.stereotype.Service;

@Service(value = "testService")
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
