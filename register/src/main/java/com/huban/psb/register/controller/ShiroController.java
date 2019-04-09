package com.huban.psb.register.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ShiroController
 * @Description TODO
 * Author huihui
 * Date 19-4-1 下午4:47
 * Version 1.0
 */
@Controller
public class ShiroController {
    @RequiresPermissions(value = "user:add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(){
        System.out.println("--add--");
        return "有权限";
    }

    @RequestMapping(value = "/404")
    public String noAuth(){
        return "404";
    }

    @RequestMapping(value = "/login")
    public String login(){

        return "index";
    }

    @RequestMapping(value = "/doLogin")
    public String doLogin(String username,String password){
        //生成盐
        String s = new SecureRandomNumberGenerator().nextBytes().toHex();
        //使用Md5实现三次加密
        Md5Hash md5Hash = new Md5Hash(password, s, 3);


        return "login";
    }

}
