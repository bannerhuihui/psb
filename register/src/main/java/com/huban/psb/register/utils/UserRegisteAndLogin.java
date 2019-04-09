package com.huban.psb.register.utils;

import com.huban.psb.register.model.MyUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

/**
 *用户注册与登录时用到的函数
 */
public class UserRegisteAndLogin
{

    private static final Logger logger = LoggerFactory.getLogger(UserRegisteAndLogin.class);
    /**
     * 用户注册时加密用户的密码
     * 输入密码明文 返回密文与盐值
     * @param password
     * @return 第一个是密文  第二个是盐值
     */
    public static String[] encryptPassword(String password)
    {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        String ciphertext = new Md5Hash(password,salt,3).toString(); //生成的密文

        String[] strings = new String[]{salt, ciphertext};

        return strings;
    }

    /**
     * 获得本次输入的密码的密文
     * @param password
     * @param salt
     * @return
     */
    public static String getInputPasswordCiph(String password, String salt)
    {
        if(salt == null)
        {
            password = "";
        }

        String ciphertext = new Md5Hash(password,salt,3).toString(); //生成的密文

        return ciphertext;
    }

    /**
     * 用户登录函数，在controller里调用
     * @param
     * @param model
     * @return
     */
    public static String userLogin(MyUser user, Model model)
    {
        Subject subject = SecurityUtils.getSubject(); //获得Subject对象
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword()); //将用户输入的用户名写密码封装到一个UsernamePasswordToken对象中

        //用Subject对象执行登录方法，没有抛出任何异常说明登录成功
        try
        {
            logger.info("日志输出：subject"+subject+"token:"+token);
            subject.login(token); //login()方法会调用 Realm类中执行认证逻辑的方法，并将这个参数传递给doGetAuthenticationInfo()方法
            model.addAttribute("user_name", user.getName());
            return "index";
        }
        catch (UnknownAccountException e) //抛出这个异常说明用户不存在
        {
            model.addAttribute("msg", "用户不存在");
            return "login";
        }
        catch (IncorrectCredentialsException e) //抛出这个异常说明密码错误
        {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}