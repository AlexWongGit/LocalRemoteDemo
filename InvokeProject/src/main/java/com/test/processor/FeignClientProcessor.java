//package com.test.processor;
//
//import com.test.annotation.CustomFeignClient;
//
//import javax.annotation.processing.AbstractProcessor;
//import javax.annotation.processing.RoundEnvironment;
//import javax.annotation.processing.SupportedAnnotationTypes;
//import javax.annotation.processing.SupportedSourceVersion;
//import javax.lang.model.SourceVersion;
//import javax.lang.model.element.Element;
//import javax.lang.model.element.TypeElement;
//import javax.tools.Diagnostic;
//import java.util.Set;
//
//@SupportedAnnotationTypes("com.test.annotation.CustomFeignClient")
//@SupportedSourceVersion(SourceVersion.RELEASE_8)
//public class FeignClientProcessor  extends AbstractProcessor {
//
//    @Override
//    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//        for (Element element : roundEnv.getElementsAnnotatedWith(CustomFeignClient.class)) {
//            CustomFeignClient feignClients = element.getAnnotation(CustomFeignClient.class);
//            String beanName = feignClients.beanName();
//            String name = feignClients.name();
//            String url = feignClients.url();
//            Class<?>[] configuration = feignClients.configuration();
//
//            String feignClientCode = generateFeignClientCode(beanName, name, url, configuration);
//
//            // 编译时输出代码
//            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, feignClientCode);
//        }
//        return true;
//    }
//
//    private String generateFeignClientCode(String beanName, String name, String url, Class<?>[] configuration) {
//        StringBuilder code = new StringBuilder();
//        code.append("@FeignClient(name = \"").append(name).append("\", url = \"").append(url).append("\", configuration = {");
//        for (Class<?> config : configuration) {
//            code.append(config.getName()).append(".class, ");
//        }
//        code.append("})");
//        return code.toString();
//    }
//}
