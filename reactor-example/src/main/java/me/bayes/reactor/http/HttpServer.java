package me.bayes.reactor.http;

import me.bayes.reactor.http.service.EchoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.io.buffer.Buffer;
import reactor.io.net.NetStreams;
import reactor.io.net.tcp.TcpServer;
import reactor.spring.context.config.EnableReactor;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by kevinbayes on 2015/10/06.
 */

/**
 * Simple Spring Boot app to start a Reactor+Netty-based REST API server for thumbnailing uploaded images.
 */
@SpringBootApplication
public class HttpServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HttpServer.class, args);
    }

    @PostConstruct
    public void start() throws Exception {
        Environment.initialize();

        NetStreams.httpServer(8180)
                .get("", EchoService.getHandler())
                .start().awaitSuccess();
    }
}
