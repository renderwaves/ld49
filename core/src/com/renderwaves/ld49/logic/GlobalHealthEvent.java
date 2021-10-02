package com.renderwaves.ld49.logic;

import com.renderwaves.ld49.events.EventModifierConstantInt;
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
        this.mHealth = 100;
    }

    public GlobalHealthEvent(Integer health) {
        this.mHealth = health;
    }

    public void progress() {
        if (mHealth <= 0) return;

        for (int i = 0; i < getNumModifiers(); i++) {

            if (getModifier(i) instanceof EventModifierConstantInt) {
                GameEventModifier<Integer> mod = (EventModifierConstantInt)getModifier(i);
                mHealth = mHealth + mod.get();
                System.out.println(String.format("progress(): modifier '%s' hit value %d", mod.getName(), mod.get()));
            }


            System.out.println(String.format("render(): ship health!: %d", mHealth));

            if (mHealth <= 0) break;
        }
    }

}
