package com.renderwaves.ld49.events;

public class EventModifierRangeInt extends GameEventModifier<Integer> {

    private int start;
    private int end;
    private int progress;

    /*
     */
    public EventModifierRangeInt(String friendlyname, int start, int end) {
        this.start = start;
        this.end = end;
        this.progress = start;

        this.setName(friendlyname);
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
        System.out.println(String.format("render(): modifier '%s' progress %d", getName(), progress));
    }

    @Override
    void onStart() {

    }

    @Override
    void onStop() {

    }
}
