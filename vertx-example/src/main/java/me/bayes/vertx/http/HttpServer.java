package me.bayes.vertx.http;

import io.vertx.core.Vertx;
import io.vertx.core.impl.VertxFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by kevinbayes on 2015/10/06.
 */
@SpringBootApplication
public class HttpServer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HttpServer.class, args);
    }

    @Bean
    public Vertx vertx() {
        return new VertxFactoryImpl().vertx();
    }

    @Bean
    @Autowired
    public boolean launchCommonVertx(Vertx vertx) {

        vertx.deployVerticle("service:vertx-example", result -> {

            System.out.println("Succeeded deploying verticle? " + result.succeeded());
            if (result.failed()) {
                result.cause().printStackTrace();
            }
        });

        return true;
    }
}
