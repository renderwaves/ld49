package com.renderwaves.ld49.events;

/*
 */
public abstract class GameEventModifier<Type> {

    private boolean isComplete;
    private String name;
    private Type value;

    public void setComplete(boolean state) {
        this.isComplete = state;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    abstract void eachTick();
    abstract void onStart();
    abstract void onStop();

    public void update() {
        eachTick();
    }

    public void set(Type value) {
        this.value = value;
    }

    public Type get() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
