package com.renderwaves.ld49.entity.entities;

import com.renderwaves.ld49.entity.Entity;
import com.renderwaves.ld49.events.EventModifierConstantInt;
import com.renderwaves.ld49.events.GameEventSystem;
import com.renderwaves.ld49.logic.GlobalHealthEvent;
import com.renderwaves.ld49.testobjects.TestShipEntity;

/*
 */
public class ShipEntity extends Entity {

    private String TAG = ShipEntity.class.getName();

    private GlobalHealthEvent mHealthEvent;
    private EventModifierConstantInt mOutsideDmg;
    private EventModifierConstantInt mRepair;

    /*
     */
    public ShipEntity() {
        super();

        mOutsideDmg = new EventModifierConstantInt(String.format("%s : Outside Damage", TAG).toString(), -25);
        mRepair = new EventModifierConstantInt(String.format("%s: Repair", TAG).toString(), 5);

        mHealthEvent = new GlobalHealthEvent(String.format("%s: Global Health", TAG).toString());
        mHealthEvent.addModifier(mOutsideDmg);
        mHealthEvent.addModifier(mRepair);

        this.addEvent(mHealthEvent);
    }

    public void update() {
        mHealthEvent.progress();
        mHealthEvent.update();
    }

}
