package com.atelier.sunny.event;

import java.util.TimerTask;

public abstract class BetterTimerTask extends TimerTask {
    private final String id;

    protected BetterTimerTask(String id){
        super();
        this.id = id;
    }

    public final String getId() {return this.id;}
}
