package com.test.executor;

import com.test.utils.SpringContextUtil;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @description 本地方法调用执行器
 * @author wangzf
 * @date 2024/11/19
 */
@Component
public class LocalMethodExecutor {

    /**
     * 执行指定对象的方法，并返回指定泛型类型的结果
     *
     * @param <T>         返回类型
     * @param bean        目标对象
     * @param returnType  返回类型
     * @param methodName  方法名
     * @param args        方法参数
     * @return 方法执行结果
     * @throws Exception 如果方法不存在或执行失败
     */
    public <T> T executeMethod(String beanName, Class<T> returnType, String methodName, Object... args) throws Exception {
        Object bean;
        try {
            bean = SpringContextUtil.getBean(beanName);
        } catch (NoSuchBeanDefinitionException e) {
            beanName += "FeignClient";
            bean = SpringContextUtil.getBean(beanName);
        }

        if (bean == null) {
            throw new IllegalArgumentException("Bean not found.");
        }

        Method method = findMethod(bean.getClass(), methodName, args);

        if (method == null) {
            throw new NoSuchMethodException("Method '" + methodName + "' not found in bean '" + bean + "'.");
        }

        ReflectionUtils.makeAccessible(method);
        Object result = method.invoke(bean, args);

        // 如果返回类型不是 void 且 result 不是 null，则进行类型转换
        if (returnType != void.class && result != null) {
            return returnType.cast(result);
        }

        return null;
    }

    /**
     * 从类中查找指定方法
     *
     * @param clazz       目标类
     * @param methodName  方法名
     * @param args        方法参数
     * @return 找到的方法对象，如果未找到返回 null
     */
    private Method findMethod(Class<?> clazz, String methodName, Object... args) {
        return ReflectionUtils.findMethod(clazz, methodName, getParameterTypes(args));
    }

    /**
     * 获取参数类型数组
     *
     * @param args 方法参数
     * @return 参数类型数组
     */
    private Class<?>[] getParameterTypes(Object... args) {
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }
}