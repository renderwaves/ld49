package com.renderwaves.ld49.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.renderwaves.ld49.entity.entities.FireExtinguisher;
import com.renderwaves.ld49.entity.entities.PlayerEntity;
import com.renderwaves.ld49.scenes.TemplateScene;

import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager extends ArrayList<Entity> {
    public static int indexOfCreatedEntities = 0;

    public EntityManager() {
        super();
    }

    PlayerEntity playerEntity;
    public FireExtinguisher fireExtinguisher;
    public void handleEntities(SpriteBatch batch) {
        for (int i = 0; i < this.size(); i++) {
            get(i).update();
        }

        for (int i = 0; i < this.size(); i++) {
            if(get(i) instanceof PlayerEntity) {
                playerEntity = (PlayerEntity) get(i);
            }
            else if(get(i) instanceof FireExtinguisher) {
                fireExtinguisher = (FireExtinguisher) get(i);
            }
            else {
                get(i).render(batch);
            }
        }

        for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
            TemplateScene.shipTilemap.fireHandler.get(i).update();
        }
        for(int i = 0; i < TemplateScene.shipTilemap.fireHandler.size(); i++) {
            TemplateScene.shipTilemap.fireHandler.get(i).render(batch);
        }

        playerEntity.render(batch);
        fireExtinguisher.render(batch);
    }

    public Entity addEntity(Entity entity) {
        indexOfCreatedEntities++;
        entity.uniqueID = indexOfCreatedEntities;
        add(entity);
        return entity;
    }
}
