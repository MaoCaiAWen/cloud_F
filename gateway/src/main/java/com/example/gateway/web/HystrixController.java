package com.example.gateway.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cloud
 * @Package: com.example.gateway.web
 * @ClassName: HystrixController
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/10 0010 11:02
 * @Version: 1.0
 */
@RestController
public class HystrixController {
    @GetMapping("/fallback")
    public String fallback() {
        return "Hello World! from gateway";
    }
}
