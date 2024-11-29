package com.local.config;

import com.test.feign.FeignClientService;
import com.test.feign.FeignClientService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"com.test","com.becalled"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value = {com.test.InvokeApplication.class,
            com.becalled.controller.TestController.class, com.becalled.IsInvokedApplication.class}))
public class ProxyConfig {

    @Bean
    public ProxyFactoryBean<FeignClientService> feignClientServiceProxyFactoryBean() {
        return new ProxyFactoryBean<>(FeignClientService.class);
    }

    @Bean
    public ProxyFactoryBean<FeignClientService2> feignClientService2ProxyFactoryBean() {
        return new ProxyFactoryBean<>(FeignClientService2.class);
    }

}
