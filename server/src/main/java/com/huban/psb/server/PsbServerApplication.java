package com.huban.psb.server;

import com.huban.psb.server.server.listener.NettyServerListener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @ClassName PsbServerApplication
 * @Description TODO
 * Author huihui
 * Date 19-4-8 上午10:33
 * Version 1.0
 */
@SpringBootApplication
public class PsbServerApplication implements CommandLineRunner {

    @Resource
    private NettyServerListener listener;
    public static void main(String[] args) {
        SpringApplication.run(PsbServerApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        listener.start();
    }
}
