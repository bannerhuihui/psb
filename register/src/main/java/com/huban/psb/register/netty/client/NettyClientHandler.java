package com.huban.psb.register.netty.client;

import com.huban.psb.common.util.ObjectCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author huihui
 * @date 2019-04-09 15:55:10
 */
public class NettyClientHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 基于定长的方式解决粘包/拆包问题
        pipeline.addLast(new LengthFieldPrepender(2));
        pipeline.addLast(new LengthFieldBasedFrameDecoder(1024 * 1024, 0, 2, 0, 2));
        // 序列化方式 可使用 MsgPack 或 Protobuf 进行序列化扩展 具体可参考study-netty项目下的相关使用例子
        pipeline.addLast(new ObjectCodec());
        // 心跳机制
        pipeline.addLast(new IdleStateHandler(3, 10, 0, TimeUnit.SECONDS));
        pipeline.addLast(new NettyClientHandlerAdapter());
    }
}
