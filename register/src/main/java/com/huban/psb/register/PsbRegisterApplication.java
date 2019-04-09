package com.huban.psb.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @ClassName PsbRegisterApplication
 * @Description TODO
 * Author huihui
 * Date 19-4-8 上午10:52
 * Version 1.0
 */
@SpringBootApplication
@MapperScan(value = "com.huban.psb.register.mapper")
public class PsbRegisterApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(PsbRegisterApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PsbRegisterApplication.class);
    }


}
