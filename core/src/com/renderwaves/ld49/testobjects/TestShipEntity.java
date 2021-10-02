package com.renderwaves.ld49.testobjects;

import com.renderwaves.ld49.events.GameEventModifierConstantInt;
import com.renderwaves.ld49.events.GameEventSystem;
import com.renderwaves.ld49.logic.GlobalHealthEvent;

/*
 */
public class TestShipEntity {

    private String TAG = TestShipEntity.class.getName();

    private GameEventSystem mBaseEvent;
    private GlobalHealthEvent mHealthEvent;

    private GameEventModifierConstantInt mDamage;
    private GameEventModifierConstantInt mRepair;

    /*
     */
    public TestShipEntity() {
        mDamage = new GameEventModifierConstantInt(String.format("%s : Outside Damage", TAG).toString(), -25);
        mRepair = new GameEventModifierConstantInt(String.format("%s: Repair", TAG).toString(), 5);

        mHealthEvent = new GlobalHealthEvent(String.format("%s: Global Health", TAG).toString());
        mHealthEvent.addModifier(mDamage);
        mHealthEvent.addModifier(mRepair);

        mBaseEvent = new GameEventSystem();
        mBaseEvent.addEvent(mHealthEvent);
    }

    private void onDamage() {

    }

    public void update() {
        //mHealthEvent.progress();
        mBaseEvent.update();
    }

}
