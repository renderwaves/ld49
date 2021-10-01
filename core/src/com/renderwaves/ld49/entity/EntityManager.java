package com.renderwaves.ld49.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager extends ArrayList<Entity> {
    public static int indexOfCreatedEntities = 0;

    public EntityManager() {
        super();
    }

    public void handleEntities(SpriteBatch batch) {
        for (int i = 0; i < this.size(); i++) {
            get(i).render(batch);
        }

        for (int i = 0; i < this.size(); i++) {
            get(i).update();
        }
    }

    public void addEntity(Entity entity) {
        indexOfCreatedEntities++;
        entity.uniqueID = indexOfCreatedEntities;
        add(entity);
    }
}
