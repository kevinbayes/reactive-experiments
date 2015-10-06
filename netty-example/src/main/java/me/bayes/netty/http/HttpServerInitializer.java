package me.bayes.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import me.bayes.netty.http.service.EchoService;
import me.bayes.netty.http.service.LogInputService;

/**
 * Created by kevinbayes on 2015/10/06.
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    public HttpServerInitializer() {
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new LogInputService());
        p.addLast(new EchoService());
    }
}
