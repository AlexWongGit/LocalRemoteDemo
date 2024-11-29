package com.test.processor;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.test.annotation.CustomFeignClient;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

public class FeignClientRegistrar implements ImportBeanDefinitionRegistrar {

    @Value("start.local")
    private boolean startLocal;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = createScanner();
        scanner.addIncludeFilter(new AnnotationTypeFilter(CustomFeignClient.class));

        Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents("com.test.annotation");
        for (BeanDefinition candidateComponent : candidateComponents) {
            if (candidateComponent instanceof AnnotatedBeanDefinition) {
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                AnnotationMetadata annotationMetadata = annotatedBeanDefinition.getMetadata();
                if (annotationMetadata.hasAnnotation(CustomFeignClient.class.getName())) {
                    Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(CustomFeignClient.class.getName());
                    String beanName = (String) attributes.get("beanName");
                    String name = (String) attributes.get("name");
                    String url = (String) attributes.get("url");
                    Class<?>[] configuration = (Class<?>[]) attributes.get("configuration");

                    // 非本地启动时，注册FeignClient
                    if (!startLocal) {
                        BeanDefinition feignClientDefinition = BeanDefinitionBuilder.genericBeanDefinition(FeignClient.class)
                            .addPropertyValue("name", name)
                            .addPropertyValue("url", url)
                            .addPropertyValue("configuration", configuration)
                            .getBeanDefinition();
                        registry.registerBeanDefinition(beanName, feignClientDefinition);
                    }

                }
            }
        }
    }

    private ClassPathScanningCandidateComponentProvider createScanner() {
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().hasAnnotation(CustomFeignClient.class.getName());
            }
        };
    }
}