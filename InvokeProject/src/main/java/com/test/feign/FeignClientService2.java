package com.test.feign;

import com.test.annotation.OpenFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@OpenFeignClient(beanName = "testServiceImpl2",name = "testServiceImpl2",url = "http://localhost:48081")
public interface FeignClientService2 {

    @GetMapping("/test/getAge2")
    Integer getAge(@RequestParam Integer age);


}