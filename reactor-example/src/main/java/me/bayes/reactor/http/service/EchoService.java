package me.bayes.reactor.http.service;

import reactor.bus.Event;
import reactor.fn.Function;
import reactor.io.buffer.Buffer;
import reactor.io.net.ReactorChannelHandler;
import reactor.io.net.http.HttpChannel;
import reactor.rx.Streams;

/**
 * Created by kevinbayes on 2015/10/06.
 */
public class EchoService  {

    private EchoService() {
    }

    public static ReactorChannelHandler<Buffer, Buffer, HttpChannel<Buffer, Buffer>> getHandler() {
        return channel -> {
            channel.headers().entries().forEach(entry1 -> System.out.println(String.format("header [%s=>%s]", entry1
                    .getKey
                            (), entry1.getValue())));

            StringBuilder response = new StringBuilder().append("hello there.");
            return channel.writeWith(Streams.just(Buffer.wrap(response.toString())));
        };
    }
}
