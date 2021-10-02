package com.renderwaves.ld49.logic;

import com.renderwaves.ld49.events.GameEventModifierConstantInt;
import com.renderwaves.ld49.events.GameEvent;
import com.renderwaves.ld49.events.GameEventModifier;

/*
 */
public class GlobalHealthEvent extends GameEvent {

    private Integer mHealth;
    private String mName;

    /*
     */
    public GlobalHealthEvent(String friendlyName) {
        this.setValueI(100);
    }
    public GlobalHealthEvent(Integer health) {
        this.setValueI(health);
    }
}
