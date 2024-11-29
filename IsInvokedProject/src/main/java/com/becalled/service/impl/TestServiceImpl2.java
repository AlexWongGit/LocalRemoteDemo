package com.becalled.service.impl;

import com.becalled.service.TestService2;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl2 implements TestService2 {

    @Override
    public String getName() {
        return "xiaogang";
    }

    @Override
    public Integer getAge(Integer age) {
        return age*2;
    }
}
