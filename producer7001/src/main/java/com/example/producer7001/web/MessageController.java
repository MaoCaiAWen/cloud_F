package com.example.producer7001.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cloud
 * @Package: web
 * @ClassName: MessageController
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/9 0009 10:35
 * @Version: 1.0
 */
@RestController
public class MessageController {

    @Value("${server.port}")
    String port;

    @GetMapping("/get")
    public String getMsg(@RequestParam("name")String name){
        return "you name is "+name+" and get port is "+port;
    }

    @RequestMapping("/foo")
    public String foo(String foo) {
        return "hello "+foo+"!7001";
    }
}
