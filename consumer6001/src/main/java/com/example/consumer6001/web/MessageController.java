package com.example.consumer6001.web;

import com.example.consumer6001.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ProjectName: cloud
 * @Package: com.example.consumer6001.web
 * @ClassName: MessageController
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/9 0009 11:32
 * @Version: 1.0
 */
@RestController
public class MessageController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HelloService helloService;

    @Value("${name}")
    private String name;

    @GetMapping("/show")
    public String showMessage(@RequestParam String name){
        return restTemplate.getForObject("http://cloud-producer/get?name="+name, String.class);
    }

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return helloService.hello( name );
    }

    @GetMapping(value = "/configDev")
    public String configDev() {
        return "config dev = "+name;
    }


}
