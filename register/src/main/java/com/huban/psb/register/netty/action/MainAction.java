package com.huban.psb.register.netty.action;

import com.huban.psb.common.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.huban.psb.register.netty.rpc.service.DemoService;


import javax.annotation.Resource;

/**
 * 调用netty方法
 * @ClassName MainAction
 * @Description TODO
 * Author huihui
 * Date 2019-04-09 15:53:49
 * Version 1.0
 */
@Component
public class MainAction {

    /**
     * MainAction 日志输出
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MainAction.class);

    /**
     * 测试业务
     */
    @Resource
    private DemoService demoService;

    /**
     * 真正远程调用的方法
     * @throws InterruptedException interruptedException
     */
    public void call() throws InterruptedException {
        // 用于模拟服务器正常启动后，人工调用远程服务代码
        Thread.sleep(10 * 1000);
        LOGGER.warn("[准备进行业务测试]");
        LOGGER.warn("[rpc测试] ");
        int sum = demoService.sum(5, 8);
        LOGGER.warn("[rpc测试结果] {}", sum);
        LOGGER.warn("[字符串测试] ");
        String print = demoService.print();
        LOGGER.warn("[字符串测试结果] {}", print);
        LOGGER.warn("[对象测试] ");
        User userInfo = demoService.getUserInfo();
        LOGGER.warn("[对象测试结果] {}", userInfo);
        LOGGER.warn("[异常测试]");
        try {
            double division = demoService.division(3, 0);
            LOGGER.warn("[异常测试结果] {}", division);
        } catch (Exception e) {
            LOGGER.error("[服务器异常] {}", e.getMessage());
        }
    }
}
