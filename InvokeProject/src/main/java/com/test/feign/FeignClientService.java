package com.test.feign;

import com.test.annotation.CustomFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@CustomFeignClient(beanName = "testServiceImpl",name = "testServiceImpl",url = "http://localhost:48081")
public interface FeignClientService {

    @GetMapping("/test/getAge")
    Integer getAge(@RequestParam Integer age);

    @GetMapping("/test/getName")
    String getName();

}
