package me.bayes.vertx.http;

import io.vertx.rxjava.core.AbstractVerticle;

/**
 * Created by kevinbayes on 2015/10/06.
 */
public class VertxStarter extends AbstractVerticle {

    public void start() {

        vertx.createHttpServer().requestHandler(req -> {
            req.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello there");
        }).listen(8080);
    }
}

