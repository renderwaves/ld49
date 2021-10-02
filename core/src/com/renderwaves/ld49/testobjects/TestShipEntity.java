package com.renderwaves.ld49.testobjects;

import com.renderwaves.ld49.events.EventModifierConstantInt;
import com.renderwaves.ld49.events.GameEvent;
import com.renderwaves.ld49.events.GameEventSystem;
import com.renderwaves.ld49.logic.GlobalHealthEvent;

/*
 */
public class TestShipEntity {

    private String TAG = TestShipEntity.class.getName();

    private GameEventSystem mBaseEvent;
    private GlobalHealthEvent mHealthEvent;

    private EventModifierConstantInt mOutsideDmg;
    private EventModifierConstantInt mRepair;

    /*
     */
    public TestShipEntity() {
        mOutsideDmg = new EventModifierConstantInt(String.format("%s : Outside Damage", TAG).toString(), -25);
        mRepair = new EventModifierConstantInt(String.format("%s: Repair", TAG).toString(), 5);

        mHealthEvent = new GlobalHealthEvent(String.format("%s: Global Health", TAG).toString());
        mHealthEvent.addModifier(mOutsideDmg);
        mHealthEvent.addModifier(mRepair);

        mBaseEvent = new GameEventSystem();
        mBaseEvent.addEvent(mHealthEvent);
    }

    private void onDamage() {

    }

    public void update() {
        mHealthEvent.progress();
        mBaseEvent.update();
    }

}
