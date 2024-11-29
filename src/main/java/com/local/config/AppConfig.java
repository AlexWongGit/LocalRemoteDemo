package com.local.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.test","com.becalled"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value = {com.test.InvokeApplication.class,
            com.becalled.controller.TestController.class, com.becalled.IsInvokedApplication.class}))
public class AppConfig {

}
