package com.local.config;

import com.test.feign.FeignClientService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import java.lang.reflect.Proxy;

@Component
public class FeignClientFactoryBean implements FactoryBean<FeignClientService> {

    @Override
    public FeignClientService getObject() throws Exception {
        return (FeignClientService)Proxy.newProxyInstance(FeignClientService.class.getClassLoader(), new Class<?>[] {FeignClientService.class},
            (proxy, method, args) -> null);
    }
    @Override
    public Class<?> getObjectType() {
        return FeignClientService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
