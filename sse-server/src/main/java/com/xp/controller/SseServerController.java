package com.xp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Neeraj Sidhaye
 */

@RestController
public class SseServerController {

    private static final Logger LOG = LoggerFactory.getLogger(SseServerController.class);
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @GetMapping("/subscribe")
    public SseEmitter subscribeForEvents() {
        LOG.info("/subscribe called ");
        SseEmitter emitter = new SseEmitter(60_000L); // keep connection open for 60 seconds
        this.emitters.add(emitter);
        LOG.info("Client subscribed");
        return emitter;
    }

    @PostMapping("/events")
    public void pushNewEventsToClients(@RequestBody String eventData) throws IOException {
        LOG.info("/events : event received for client {}", eventData);

        List<SseEmitter> deadEmitters = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        String event = objectMapper.writeValueAsString(eventData);

        this.emitters.forEach(emitter -> {

            try {
                emitter.send(event); // broadcasting same message to all the connected clients.
                LOG.info("event sent to client");

            } catch (Exception e) {

                deadEmitters.add(emitter);
            }

        });

        this.emitters.removeAll(deadEmitters);
    }

}