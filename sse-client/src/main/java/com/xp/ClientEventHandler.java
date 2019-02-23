package com.xp;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Neeraj Sidhaye
 */

public class ClientEventHandler implements EventHandler {

    private static final Map<String, String> sseAssertionMap = new ConcurrentHashMap<>();

    @Override
    public void onOpen() throws Exception {
        System.out.println("onOpen");
    }

    @Override
    public void onClosed() throws Exception {
        System.out.println("onClosed");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        System.out.println("sse-client: Data Received:" + messageEvent.getData());

        sseAssertionMap.put(UUID.randomUUID().toString(), messageEvent.getData());

        System.out.println("sse-client: Data written to file for assertion"+sseAssertionMap.size());
    }

    @Override
    public void onComment(String comment) throws Exception {
        System.out.println("onComment");
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError: " + t);
    }

    public int validateAssertion(String str) {

        return sseAssertionMap.size();
    }


}
