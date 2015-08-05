package com.khoslalabs.musicplayer.events;

import de.greenrobot.event.EventBus;

/**
 * Created by ankitsrivastava on 05/08/15.
 */
public class SeekbarEvent extends EventBus{
    public String mssg;
    public  int pos;
    public  int duration;

    public SeekbarEvent() {

    }

    public String getMssg() {
        return mssg;
    }

    public void setMssg(String mssg) {
        this.mssg = mssg;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
