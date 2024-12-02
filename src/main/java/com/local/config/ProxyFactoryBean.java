package com.local.config;

import com.test.annotation.OpenFeignClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class ProxyFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> interfaceType;
    private final String clientName;

    public ProxyFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
        this.clientName = interfaceType.getAnnotation(OpenFeignClient.class).beanName();
    }

    @Override
    public T getObject() {
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType},
            (proxy, method, args) -> {
                // 在这里实现你的代理逻辑
                System.out.println("Intercepted method: " + method.getName() + " in client " + clientName);
                return null;
            });
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceType;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}