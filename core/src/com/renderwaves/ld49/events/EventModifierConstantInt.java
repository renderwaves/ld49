package com.renderwaves.ld49.events;

/*
 */
public class EventModifierConstantInt extends GameEventModifier<Integer> {

    private String name;
    private Integer value;

    /*
     */
    public EventModifierConstantInt(String friendlyName, Integer value) {
        this.name = friendlyName;
        this.value = value;
        this.setName(friendlyName);
        set(this.value);
    }

    @Override
    void eachTick() {

    }

    @Override
    void onStart() {

    }

    @Override
    void onStop() {

    }
}
