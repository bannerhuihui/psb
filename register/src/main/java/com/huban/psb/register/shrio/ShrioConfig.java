package com.huban.psb.register.shrio;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName ShrioConfig
 * @Description TODO
 * Author huihui
 * Date 19-4-1 下午3:47
 * Version 1.0
 */
@Configuration
public class ShrioConfig {
    /**
     * 这个方法关联一个安全管理器
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("manager") DefaultWebSecurityManager defaultWebSecurityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager); //关联安全管理器

        /**
         * 设置拦截URL
         */
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/resouces/a", "authc");
        map.put("/resouces/b", "perms[get:b]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        shiroFilterFactoryBean.setLoginUrl("/toPage/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/resouces/haveNoPer");

        return  shiroFilterFactoryBean;
    }

    /**
     * 获得一个安全管理器
     * 这个方法关联一个realm类
     * @param userRealm
     * @return
     */
    @Bean(name = "manager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") ShiroRealm userRealm)
    {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm); //设置realm

        return manager;
    }

    /**
     * 获得一个realm类
     * @return
     */
    @Bean(name = "realm")
    public ShiroRealm getRealm()
    {
        return new ShiroRealm();
    }

    @Bean(name = "shiroDialect")
    public ShiroDialect getShiroDialect()
    {
        return new ShiroDialect();
    }

    /**
     * 配置 redis-shrioManager
     * @return
     */

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("192.168.1.254");
        redisManager.setPort(6380);
        redisManager.setTimeout(1000);
        redisManager.setPassword("123456");
        return redisManager;
    }


    /**
     * sessionManager 属性设置
     */

    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        //设置sessionredis的数据持久层
        sessionManager.setSessionDAO(redisSessionDAO);
        //设置session超时时间
        sessionManager.setGlobalSessionTimeout(1800000);
        //定时检查失效的session
        sessionManager.setDeleteInvalidSessions(true);
        //定时检查失效的session
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //设置扫描信息时间间隔 单位为毫秒
        sessionManager.setSessionValidationInterval(100000);
        //所有的session一定要将id设置到cookie之中，需要提供Cookie的操作模板
        sessionManager.setSessionIdCookie(simpleCookie());
        //定义sessionidCookie模板启动
        sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }


    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * 开启shiro注解
     */

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("defSession.session.id");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager secu = new DefaultWebSecurityManager();
        secu.setRealm(getRealm());
        secu.setSessionManager(sessionManager());
        secu.setCacheManager(cacheManager());
        return secu;
    }
}
