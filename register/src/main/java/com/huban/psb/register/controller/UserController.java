package com.huban.psb.register.controller;

import com.huban.psb.register.common.Log;
import com.huban.psb.register.model.MyUser;
import com.huban.psb.register.service.UserService;
import com.huban.psb.register.utils.UserRegisteAndLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log(value = "aspect test",ignore = true)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}.html", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        LOGGER.info("进入findAllUser方法！");
        return userService.findAllUser(pageNum,pageSize);
    }

    @RequestMapping(value = "/userRegister")
    public String userRegist(MyUser user, Model model){
        String[] saltAndCiphertext = UserRegisteAndLogin.encryptPassword(user.getPassword());
        user.setPer(saltAndCiphertext[0]);
        user.setPassword(saltAndCiphertext[1]);
        userService.userRegister(user);
        return UserRegisteAndLogin.userLogin(user,model);
    }

    /**
     * 处理用户的登录请求
     */
    @PostMapping("/userLogin")
    public String userLogin(MyUser user, Model model)
    {
        user.setPassword(UserRegisteAndLogin.getInputPasswordCiph(user.getPassword(), userService.selectAsaltByName(user.getName())));

        return UserRegisteAndLogin.userLogin(user, model);
    }


}
