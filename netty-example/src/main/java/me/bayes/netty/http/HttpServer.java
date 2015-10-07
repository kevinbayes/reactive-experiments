package me.bayes.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by kevinbayes on 2015/10/06.
 */
@SpringBootApplication
public class HttpServer  {

    static EventLoopGroup master = new NioEventLoopGroup(1);
    static EventLoopGroup worker = new NioEventLoopGroup();

    Channel serverChannel;

    @PostConstruct
    public void start() throws Exception {

        ServerBootstrap server = new ServerBootstrap();
        server.option(ChannelOption.SO_BACKLOG, 1024);
        server.group(master, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new HttpServerInitializer());

        serverChannel = server.bind(8280).sync().channel().closeFuture().channel();
    }

    @PreDestroy
    public void stop() {
        if(serverChannel != null) {
            serverChannel.close();
        }

        master.shutdownGracefully();
        worker.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HttpServer.class, args);
    }

}
