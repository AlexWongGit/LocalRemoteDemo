package com.test.executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import org.springframework.util.ReflectionUtils;

@Component
public class MethodExecutor {

    @Autowired
    private ApplicationContext applicationContext;
    public Object executeMethod(String beanName, String methodName, Object... args) throws Exception {
        // 获取类的实例
        Object bean = applicationContext.getBean(beanName);

        // 获取方法对象
        Method method = ReflectionUtils.findMethod(bean.getClass(), methodName, getParameterTypes(args));

        if (method == null) {
            throw new NoSuchMethodException("Method '" + methodName + "' not found in bean '" + beanName + "'.");
        }

        // 执行方法
        ReflectionUtils.makeAccessible(method);
        return method.invoke(bean, args);
    }

    private Class<?>[] getParameterTypes(Object... args) {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }
}
