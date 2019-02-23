package com.xp;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

/**
 * @author Neeraj Sidhaye
 */

public class SseClient {

    public static void main(String[] args) throws InterruptedException {
        EventHandler eventHandler = new ClientEventHandler();
        String url = String.format("http://localhost:8080/subscribe");
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));

        try (EventSource eventSource = builder.build()) {
            eventSource.setReconnectionTimeMs(60000);
            eventSource.start();

            TimeUnit.MINUTES.sleep(10);
        }
    }


    public Map<String, String> subscribeClient(String uuid) throws InterruptedException {

        EventHandler eventHandler = new ClientEventHandler();
        String url = String.format("http://localhost:8080/subscribe");
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        System.out.println("client establishing connection");
        try (EventSource eventSource = builder.build()) {
            eventSource.setReconnectionTimeMs(3000);
            eventSource.start();

            TimeUnit.SECONDS.sleep(5);
        }

        System.out.println("execution success");
        return new HashMap() {{put("status", "success");}};
    }

}
