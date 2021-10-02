package com.renderwaves.ld49.events;

import java.util.ArrayList;

/*
 */
public abstract class GameEvent {

    private boolean isComplete;
    private ArrayList<GameEventModifier> modifiers = new ArrayList<GameEventModifier>(0);

    private Integer constValueI;
    private float constValueF;

    /*
     */
    protected void setValueI(int value) {
        this.constValueI = value;
    }

    protected int getValueI() {
        return this.constValueI;
    }

    private Integer progressValue = 100;
    public Integer getValue() { return progressValue; }

    /*
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    /*
     */
    public void update() {
        if (modifiers.size() <= 0) return;

        for (GameEventModifier mod : modifiers) {
            // behavior for event modifiers
            if (mod instanceof GameEventModifierConstantInt) {
                final GameEventModifier<Integer> constInt = (GameEventModifierConstantInt)mod;
                progressValue += constInt.get();
            } else if ( mod instanceof GameEventModifierRangeInt) {
                final GameEventModifierRangeInt rangeInt = (GameEventModifierRangeInt)mod;
                progressValue += rangeInt.get();
            } else {

            }

            // update modifiers
            mod.update();
            System.out.println(String.format("eachTick(): modifier '%s' progress %d", mod.getName(), progressValue));
        }

        removeModifier();
    }

    /*
     */
    public void addModifier(GameEventModifier mod) {
        System.out.println(String.format("addModifier(): %s", mod.getName()));
        this.modifiers.add(mod);
    }

    /*
     */
    public void removeModifier() {
        for (int i = 0; i < modifiers.size(); i++) {
            if (modifiers.get(i).isComplete() == true) {
                System.out.println(String.format("removeModifier(): %s", modifiers.get(i).getName()));
                modifiers.remove(i);
            }
        }
    }

    public GameEventModifier getModifier(int id) {
        if (id > modifiers.size() || id < 0) return null;

        GameEventModifier modifier = modifiers.get(id);
        if (modifier == null) return null;

        return modifier;
    }

    public int getNumModifiers() {
        return this.modifiers.size();
    }
}
