package com.renderwaves.ld49.events;

public class GameEventModifierRangeInt extends GameEventModifier<Integer> {

    private int start;
    private int end;
    private int progress;

    /*
     */
    public GameEventModifierRangeInt(String friendlyname, int start, int end) {
        this.setName(friendlyname);

        this.start = start;
        this.end = end;
        this.progress = start;

        set(progress);
    }

    @Override
    void eachTick() {
        if (start < end) {
            progress += 1;
            if (progress >= end) setComplete(true);
        } else if (start > end)  {
            progress -= 1;
            if (progress <= end)  setComplete(true);
        } else {

        }
        set(progress);

        System.out.println(String.format("eachTick(): modifier '%s' progress %d", getName(), get()));
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
