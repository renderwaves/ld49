package com.renderwaves.ld49.events;

/*
 */
public class GameEventModifierConstantInt extends GameEventModifier<Integer> {

    private String name;
    private Integer value;

    /*
     */
    public GameEventModifierConstantInt(String friendlyName, Integer value) {
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

    @Override
    void onColision() {

    }

    @Override
    void onMovement() {

    }

    @Override
    void onAction() {

    }

    @Override
    void onRender() {

    }
}
