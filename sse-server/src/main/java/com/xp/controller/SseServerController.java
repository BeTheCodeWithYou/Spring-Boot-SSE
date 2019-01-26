package com.xp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Neeraj Sidhaye
 */

@RestController
public class SseServerController {

    private static  final Logger log = LoggerFactory.getLogger(SseServerController.class);
    private Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping("/subscribe/{token}")
    public SseEmitter subscribeForEvents(@PathVariable("token") String token){
        log.debug("Client Subscribing", token);
        SseEmitter sse = new SseEmitter(60*1000L);
        this.emitters.put(token, sse);
        log.info("Client subscribed");
        return sse;
    }

    @PostMapping("/events/{token}")
    public ResponseEntity<String> pushNewEvents(@RequestBody String eventData, @PathVariable("token") String token) throws IOException {
        log.debug("new event received for client {} ", token);
        if(this.emitters.get(token)!=null) {
            this.emitters.get(token).send(eventData);
            log.info("event sent to subscribed client");
            return new ResponseEntity<String>("event sent to client",HttpStatus.OK);
        }else {
            log.debug("pushNewEvent: subscription not found for client {}", token);
            return new ResponseEntity<String>("client not subscribed", HttpStatus.NOT_FOUND);
        }

    }
}