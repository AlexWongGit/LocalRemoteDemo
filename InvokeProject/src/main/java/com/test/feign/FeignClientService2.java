package com.test.feign;

import com.test.annotation.CustomFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CustomFeignClient(beanName = "testServiceImpl2",name = "testServiceImpl2",url = "http://localhost:48081")
public interface FeignClientService2 {

    @GetMapping("/test/getAge")
    Integer getAge(@RequestParam Integer age);

    @GetMapping("/test/getName")
    String getName();

}