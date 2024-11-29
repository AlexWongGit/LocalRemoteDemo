package com.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "testServiceImpl2",url = "http://localhost:48081")
public interface FeignClientService2 {

    @GetMapping("/test/getAge")
    Integer getAge(@RequestParam Integer age);

    @GetMapping("/test/getName")
    String getName();

}