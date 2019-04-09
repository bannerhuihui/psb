package com.huban.psb.register.netty.config;

import com.huban.psb.register.netty.rpc.util.NettyBeanScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Netty相关的初始化入口
 * <p>
 *
 * @author huihui
 * @date 2019-04-09 15:56:57
 */
@Configuration
public class NettyConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(NettyConfiguration.class);

    /**
     * 初始化加载Netty相关bean的配置方法
     *
     * @param basePackage 配置的包名
     * @param clientName  配置的Netty实例对象名
     * @return NettyBeanScanner
     */
    @Bean
    public static NettyBeanScanner initNettyBeanScanner(@Value("${netty.basePackage}") String basePackage,
                                                        @Value("${netty.clientName}") String clientName) {
        // 创建对象
        return new NettyBeanScanner(basePackage, clientName);
    }
}
