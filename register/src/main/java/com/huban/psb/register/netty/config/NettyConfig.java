package com.huban.psb.register.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * netty客户端配置
 * <p>
 *
 * @author huihui
 * @date 2019-04-09 15:56:40
 */
@Component
@ConfigurationProperties(prefix = "netty")
@Data
public class NettyConfig {

    private String url;

    private int port;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
