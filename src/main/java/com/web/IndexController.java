package com.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/")
public class IndexController {

    @RequestMapping ("asd")
    public String index () {
        System.out.println("sss");
        return "Hello World!";
    }
}
