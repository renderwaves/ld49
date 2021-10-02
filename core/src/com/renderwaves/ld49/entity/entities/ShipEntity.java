package com.renderwaves.ld49.entity.entities;

import com.renderwaves.ld49.entity.Entity;
import com.renderwaves.ld49.events.GameEventModifierConstantInt;
import com.renderwaves.ld49.logic.GlobalHealthEvent;

/*
 */
public class ShipEntity extends Entity {

    private String TAG = ShipEntity.class.getName();

    private GlobalHealthEvent mHealthEvent;
    private GameEventModifierConstantInt mOutsideDmg;
    private GameEventModifierConstantInt mRepair;

    /*
     */
    public ShipEntity() {
        super();

        mOutsideDmg = new GameEventModifierConstantInt(String.format("%s : Outside Damage", TAG).toString(), -25);
        mRepair = new GameEventModifierConstantInt(String.format("%s: Repair", TAG).toString(), 5);

        mHealthEvent = new GlobalHealthEvent(String.format("%s: Global Health", TAG).toString());
        mHealthEvent.addModifier(mOutsideDmg);
        mHealthEvent.addModifier(mRepair);

        this.addEvent(mHealthEvent);
    }

    public void update() {
        if (mHealthEvent.getValue() < 0) return;
        mHealthEvent.update();
    }

}
