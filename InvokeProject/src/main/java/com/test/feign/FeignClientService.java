package com.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "testService",url = "http://localhost:48082")
public interface FeignClientService {
    @GetMapping("/test/getAge")
    Integer getAge(@RequestParam Integer age);

    @GetMapping("/test/getName")
    String getName();

}
