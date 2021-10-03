package com.renderwaves.ld49.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.entities.PlayerEntity;
import com.renderwaves.ld49.entity.entities.Uranium;
import com.renderwaves.ld49.managers.FontManager;

public class CommunicationMenu {
    public static float cooldown = 10.0f;

    Label cooldownLabel;

    public Window window;
    Skin uiSkin;
    public CommunicationMenu() {
        uiSkin = new Skin();
        uiSkin.add("default-font", FontManager.font_droidBb_20);

        uiSkin.addRegions(new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        uiSkin.load(Gdx.files.internal("skins/skin.json"));

        window = new Window("Communication Menu", uiSkin);
        window.setWidth(300);
        window.setHeight(110);
        window.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);

        Table table2 = new Table();
        window.add(table2).left().row();

        Table table = new Table();
        window.add(table).expand().left();

        cooldownLabel = new Label("You cannot buy for " + String.valueOf(cooldown), uiSkin);

        table2.add(cooldownLabel).left().row();
        cooldownLabel.setX(10);

        Label label = new Label("Ship repair service", uiSkin);

        TextButton buyRepairServiceButton = new TextButton("Buy", uiSkin);
        buyRepairServiceButton.setX(10);

        table.add(buyRepairServiceButton).left();
        table.add(label).row();

        buyRepairServiceButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(cooldown > 0) return;
                GlobalShipVariables.shipHealth += 0.5f;
                if(GlobalShipVariables.shipHealth > 1.0f) {
                    GlobalShipVariables.shipHealth = 1.0f;
                }
                cooldown = 10;
            }
        });

        TextButton buyUraniumButton = new TextButton("Buy", uiSkin);
        buyUraniumButton.setX(10);

        Label label2 = new Label("Buy uranium", uiSkin);

        table.add(buyUraniumButton).left();
        table.add(label2).row();

        buyUraniumButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(cooldown > 0) return;
                Uranium uranium = new Uranium(new Vector2(824-64, 185));
                PlayerEntity.uraniumList.add(uranium);
                Game.entityManager.addEntity(uranium);
                cooldown = 10;
            }
        });
        
        TextButton closeButton = new TextButton("Close", uiSkin);

        table.add(closeButton);

        closeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                window.setVisible(false);
            }
        });

        window.setVisible(false);
    }

    public void update() {
        cooldown -= Gdx.graphics.getDeltaTime();
        if(cooldown <= 0) cooldown = 0;

        if(cooldown <= 0) {
            cooldownLabel.setText("You can buy now");
        }
        else if(String.valueOf(cooldown).length() >= 4) {
            cooldownLabel.setText("You cannot buy for " + String.valueOf(cooldown).substring(0, 4));
        }
        else {
            cooldownLabel.setText("You cannot buy for " + String.valueOf(cooldown));
        }
    }
}