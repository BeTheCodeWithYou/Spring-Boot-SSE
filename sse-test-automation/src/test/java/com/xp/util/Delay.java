package com.xp.util;

public class Delay {

    public void delay(int seconds){

        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}


