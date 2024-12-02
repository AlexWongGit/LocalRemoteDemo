package com.local.config;

import com.test.feign.FeignClientService;
import com.test.feign.FeignClientService2;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.test","com.becalled"},
    excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        value = {com.test.InvokeApplication.class,
            com.becalled.controller.TestController.class, com.becalled.IsInvokedApplication.class}))
public class AppConfig {


    /**
     * @description 一种创建代理对象的方式
     * @return com.local.config.ProxyFactoryBean<com.test.feign.FeignClientService>
     */
/*    @Bean
    public ProxyFactoryBean<FeignClientService> feignClientServiceProxyFactoryBean() {
        return new ProxyFactoryBean<>(FeignClientService.class);
    }

    @Bean
    public ProxyFactoryBean<FeignClientService2> feignClientService2ProxyFactoryBean() {
        return new ProxyFactoryBean<>(FeignClientService2.class);
    }*/

}
