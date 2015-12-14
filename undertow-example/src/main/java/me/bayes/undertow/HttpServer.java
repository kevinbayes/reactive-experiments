package me.bayes.undertow;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.Environment;
import reactor.io.net.NetStreams;

import javax.annotation.PostConstruct;

/**
 * Created by kevinbayes on 2015/12/10.
 */
@SpringBootApplication
public class HttpServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HttpServer.class, args);
    }

    @PostConstruct
    public void start() throws Exception {
        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(exchange -> {
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                    exchange.getResponseSender().send("Hello World");
                }).build();
        server.start();
    }
}
