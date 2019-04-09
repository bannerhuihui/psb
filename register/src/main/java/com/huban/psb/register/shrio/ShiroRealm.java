package com.huban.psb.register.shrio;

import com.huban.psb.register.model.MyUser;
import com.huban.psb.register.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName ShiroRealm
 * @Description TODO
 * Author huihui
 * Date 19-4-1 下午2:32
 * Version 1.0
 */
public class ShiroRealm extends AuthorizingRealm {


    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private UserService userservice;

    /**
     * 权限操作
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        LOGGER.info("ShiroRealm.doGetAuthorizationInfo()权限操作");
        Subject subject = SecurityUtils.getSubject(); //获得一个Subject对象
        MyUser user = (MyUser) subject.getPrincipal(); //获得登录的对象


        String str = user.getPer(); //获得登录对象的权限字符串

        //String str = "get:a";
        if(str==null || str.isEmpty())
        {
            str = "noAnyAuth";
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); //创建一个这样的对象，它是返回的类型的子类
        info.addStringPermission(str); //添加权限，这个方法的参数字符串如果为 null或"" 会抛出异常
        return info;
    }

    /**
     * 用来进行身份认证，账号密码是否正确
     * @param
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LOGGER.info("ShiroRealm.doGetAuthenticationInfo()用户名密码认证");
        //获取用户的输入账号密码信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken; //获得用户输入的用户名,这个对象就是login()传递过来的，将它强转以取出封装的用户名
        String userNameInput = token.getUsername();

        MyUser selectUser = userservice.selectOneUserByName(userNameInput);

        if(selectUser == null) //用户不存在，返回null
        {
            return null;
        }
        return new SimpleAuthenticationInfo(selectUser, selectUser.getPassword(), "");
    }
}
