package com.local.config;

import com.test.feign.FeignClientService;
import com.test.feign.FeignClientService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
