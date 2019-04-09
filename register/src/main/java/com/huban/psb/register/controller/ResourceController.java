package com.huban.psb.register.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ResourceController
 * @Description TODO
 * Author huihui
 * Date 19-4-8 下午12:19
 * Version 1.0
 */
@RestController
@RequestMapping(value = "/resouces")
public class ResourceController {

    @GetMapping("/a")
    public String resA()
    {
        return "这是资源A aaaaaaaaaaa";
    }

    @GetMapping("/b")
    public String resB()
    {
        return "这是资源B bbbbbbbbbbb";
    }

    @GetMapping("/haveNoPer")
    public String haveNoPer()
    {
        return "你没有权限访问这个资源";
    }
}
