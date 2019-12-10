package com.example.consumer6002.web;

import com.example.consumer6002.service.SchedualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: cloud
 * @Package: com.example.consumer6002.web
 * @ClassName: MessageController
 * @Author: MC
 * @Description: ${description}
 * @Date: 2019/12/9 0009 11:52
 * @Version: 1.0
 */
@RestController
public class MessageController {
    @Autowired
    private SchedualService schedualService;

    @GetMapping(value = "/hello")
    public String hello(@RequestParam String name) {
        return schedualService.hello( name );
    }
}
