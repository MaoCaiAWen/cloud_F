package com.example.consumer6001.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cloud
 * @Package: com.example.consumer6001.web
 * @ClassName: CustomController
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/10 0010 13:18
 * @Version: 1.0
 */
@RestController
public class CustomController {

    @GetMapping(value = "/test")
    public String hello(@RequestParam String name) {
        System.out.println(" this is 6001 custom name is "+ name);
        return "this is 6001 custom name is "+ name;
    }
}
