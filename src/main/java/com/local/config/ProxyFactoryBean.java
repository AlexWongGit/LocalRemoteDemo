package com.local.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

public class ProxyFactoryBean<T> implements FactoryBean<T> {

    private final Class<T> interfaceType;

    public ProxyFactoryBean(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() {
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType},
            (proxy, method, args) -> null);
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
