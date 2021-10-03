package com.renderwaves.ld49.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.renderwaves.ld49.GlobalShipVariables;
import com.renderwaves.ld49.entity.TexturedEntity;
import com.renderwaves.ld49.managers.FontManager;
import com.renderwaves.ld49.managers.InputManager;
import com.renderwaves.ld49.managers.TextureManager;
import com.renderwaves.ld49.scenes.DeathScene;
import com.renderwaves.ld49.scenes.MenuScene;
import com.renderwaves.ld49.scenes.TemplateScene;
import com.renderwaves.ld49.tilemap.Tile;
import com.renderwaves.ld49.tilemap.Tilemap;
import com.renderwaves.ld49.ui.StatusBar;

import static com.renderwaves.ld49.Game.entityManager;

public class PlayerEntity extends TexturedEntity {

    private String TAG = PlayerEntity.class.getName();

    private float movementSpeed = 100.0f;
    private float sprint = 1.0f;
    private Vector2 velocity;
    private boolean nearGenerator = false;
    private boolean nearLifeSupport = false;
    private boolean nearMedBay = false;
    private boolean nearSpacesuit = false;
    private boolean nearFireExtanguisher = false;
    private boolean nearComms = false;
    private boolean nearEngine = false;
    private boolean nearNavigation = false;

    private boolean hasSpacesuit = false;
    private boolean hasFireExtinguisher = false;

    private boolean touchingFire = false;

    public static float health = 1.0f;

    private StatusBar healthBar = new StatusBar(new Vector2(Gdx.graphics.getWidth() - 200, 25), new Vector2(128, 64), health, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.lifesupporticon, new Vector2(2, 2));

    /*
     */
    public PlayerEntity(Vector2 position, Vector2 scale) {
        super(position, scale, TextureManager.playerEntity);
        velocity = new Vector2(0, 0);

        rectangle = new Rectangle(position.x, position.y, texture.getWidth() * scale.x, texture.getHeight() * scale.y);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        super.render(spriteBatch);

        if(GlobalShipVariables.oxygenLevel <= 0.5f) {
            health -= (Gdx.graphics.getDeltaTime() * (1 - GlobalShipVariables.oxygenLevel)) / 20;
        }

        healthBar.renderSprite(spriteBatch);

        if(nearGenerator) {
            FontManager.font_droidBb_20.draw(spriteBatch, "ADDING FUEL TO REACTOR", Gdx.graphics.getWidth() / 2 - "ADDING FUEL TO REACTOR".length() * 7, 100);
            GlobalShipVariables.generatorHealth += Gdx.graphics.getDeltaTime() / 2;
            GlobalShipVariables.generatorFuel += Gdx.graphics.getDeltaTime() / 4;
        }
        else if(nearLifeSupport) {
            FontManager.font_droidBb_20.draw(spriteBatch, "REPAIRING LIFE SUPPORT", Gdx.graphics.getWidth() / 2 - "REPAIRING LIFE SUPPORT".length() * 7, 100);
            GlobalShipVariables.lifeSupportHealth += Gdx.graphics.getDeltaTime() / 2;
        }
        else if(nearMedBay) {
            FontManager.font_droidBb_20.draw(spriteBatch, "HEALING", Gdx.graphics.getWidth() / 2 - "HEALING".length() * 7, 100);
            health += Gdx.graphics.getDeltaTime() / 4;
        }
        else if(nearSpacesuit) {
            FontManager.font_droidBb_20.draw(spriteBatch, (hasSpacesuit ? "PUT BACK" : "TAKE") + " SPACESUIT <" + Input.Keys.toString(InputManager.TakeSpacesuit.key1) + ">", Gdx.graphics.getWidth() / 2 - "ADDING FUEL TO GENERATOR".length() * 7, 100);
        }
        else if(nearFireExtanguisher){
            FontManager.font_droidBb_20.draw(spriteBatch, (hasFireExtinguisher ? "PUT DOWN" : "TAKE") + " FIRE EXTINGUISHER <" + Input.Keys.toString(InputManager.TakeFireExtinguisher.key1) + ">", Gdx.graphics.getWidth() / 2 - "ADDING FUEL TO GENERATOR".length() * 7, 100 );
        }
        else if (nearNavigation) {
            FontManager.font_droidBb_20.draw(spriteBatch, "REPARING NAVIGATION", Gdx.graphics.getWidth() / 2 - "REPARING NAVIGATION".length() * 7, 100);
            GlobalShipVariables.navigationHealth += Gdx.graphics.getDeltaTime() / 4;
        }
        else if (nearComms) {
            FontManager.font_droidBb_20.draw(spriteBatch, "REPARING COMMUNICATION", Gdx.graphics.getWidth() / 2 - "REPARING COMMUNICATION".length() * 7, 100);
            GlobalShipVariables.communicationsHealth += Gdx.graphics.getDeltaTime() / 4;
        }
        else if (nearEngine) {
            FontManager.font_droidBb_20.draw(spriteBatch, "REPARING ENGINE", Gdx.graphics.getWidth() / 2 - "REPARING ENGINE".length() * 7, 100);
            GlobalShipVariables.engine1Health += Gdx.graphics.getDeltaTime() / 4;
        }
        if (touchingFire) {
            if(!hasSpacesuit) health -= Gdx.graphics.getDeltaTime() / 10;
        }


        boolean useKeyPressed = Gdx.input.isKeyJustPressed(Input.Keys.E);

        for(int i = 0; i < TemplateScene.shipTilemap.doorHandler.size(); i++) {
            Tilemap.DoorInstance instance = TemplateScene.shipTilemap.doorHandler.get(i);
            if(instance.closed) {
                int x = instance.x;
                int y = instance.y;

                Vector2 pPosition = TemplateScene.shipTilemap.globalPositionToTilemapPosition(position.x, position.y);

                float distance = (float)Math.sqrt(Math.pow(instance.x - pPosition.x, 2) + Math.pow(instance.y - pPosition.y, 2));

                if(distance <= 1.5f) {
                    //FontManager.font_droidBb_18.draw(spriteBatch, "OPEN DOOR USING <" + Input.Keys.toString(InputManager.TakeSpacesuit.key1) + ">", Gdx.graphics.getWidth() / 2 - "OPEN DOOR USING <E>".length() * 7, 100);
                    TemplateScene.shipTilemap.setTile(x, y, Tile.DoorTileOpened);
                    instance.closed = false;
                    instance.timer = 5.0f;
                }
            }
        }
    }

    private void movement() {
        rectangle.x = position.x;
        rectangle.y = position.y;

        if(Gdx.input.isKeyPressed(InputManager.Sprint.key1) || Gdx.input.isKeyPressed(InputManager.Sprint.key2)) {
            if(!hasSpacesuit && !hasFireExtinguisher)
                sprint = 2.0f;
        }

        if(Gdx.input.isKeyPressed(InputManager.MoveUp.key1) || Gdx.input.isKeyPressed(InputManager.MoveUp.key2)) {
            velocity.y = Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        else if(Gdx.input.isKeyPressed(InputManager.MoveDown.key1) || Gdx.input.isKeyPressed(InputManager.MoveDown.key2)) {
            velocity.y = -Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        if(Gdx.input.isKeyPressed(InputManager.MoveRight.key1) || Gdx.input.isKeyPressed(InputManager.MoveRight.key2)) {
            velocity.x = Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        else if(Gdx.input.isKeyPressed(InputManager.MoveLeft.key1) || Gdx.input.isKeyPressed(InputManager.MoveLeft.key2)) {
            velocity.x = -Gdx.graphics.getDeltaTime() * movementSpeed;
        }

        Vector2 playerPositionOnTilemap = TemplateScene.shipTilemap.globalPositionToTilemapPosition(position.x+8 + velocity.x, position.y-4 + velocity.y);
        boolean collidable = TemplateScene.shipTilemap.getTileByPosition((int)playerPositionOnTilemap.x, (int)playerPositionOnTilemap.y).collidable;
        if(collidable) {
            velocity.x = 0;
            velocity.y = 0;
        }

        position.x += velocity.x * sprint;
        position.y += velocity.y * sprint;

        velocity.x = 0;
        velocity.y = 0;
    }

    private Generator generator;
    private Spacesuit spacesuit;
    private LifeSupport lifeSupport;
    private MedBay medBay;
    private FireExtinguisher fireExtinguisher;
    private Comms comms;
    private Engine engine;
    private Navigation navigation;

    private void collision() {
        if(generator == null && spacesuit == null) {
            for(int i = 0; i < entityManager.size(); i++) {
                if(entityManager.get(i) instanceof Generator) {
                    generator = (Generator) entityManager.get(i);
                }
                else if(entityManager.get(i) instanceof Spacesuit) {
                    spacesuit = (Spacesuit) entityManager.get(i);
                }
                else if(entityManager.get(i) instanceof  LifeSupport){
                    lifeSupport = (LifeSupport) entityManager.get(i);
                 }
                else if(entityManager.get(i) instanceof  MedBay){
                    medBay = (MedBay) entityManager.get(i);
                 }
                else if(entityManager.get(i) instanceof  FireExtinguisher){
                    fireExtinguisher = (FireExtinguisher) entityManager.get(i);
                }
                else if(entityManager.get(i) instanceof  Comms){
                    comms = (Comms) entityManager.get(i);
                }
                else if(entityManager.get(i) instanceof  Engine){
                    engine = (Engine) entityManager.get(i);
                }
                else if(entityManager.get(i) instanceof  Navigation){
                    navigation = (Navigation) entityManager.get(i);
                }
            }
        }
        else {
            if(generator.rectangle.overlaps(rectangle)) {
                nearGenerator = true;
            }
            else {
                nearGenerator = false;
            }

            if(spacesuit.rectangle.overlaps(rectangle)) {
                nearSpacesuit = true;
            }
            else {
                nearSpacesuit = false;
            }

            if(lifeSupport.rectangle.overlaps(rectangle)){
                nearLifeSupport = true;
            }
            else{
                nearLifeSupport = false;
            }

            if(medBay.rectangle.overlaps(rectangle)){
                nearMedBay = true;
            }
            else{
                nearMedBay = false;
            }
            if(fireExtinguisher.rectangle.overlaps((rectangle))){
                nearFireExtanguisher = true;
            }
            else {
                nearFireExtanguisher = false;
            }
            if(comms.rectangle.overlaps((rectangle))){
                nearComms = true;
            }
            else {
                nearComms = false;
            }
            if(engine.rectangle.overlaps((rectangle))){
                nearEngine = true;
            }
            else {
                nearEngine = false;
            }
            if(navigation.rectangle.overlaps((rectangle))){
                nearNavigation = true;
            }
            else {
                nearNavigation = false;
            }
        }

        touchingFire = false;
        for(int i = 0; i < TemplateScene.getInstance().shipTilemap.fireHandler.size(); i++) {
            if(rectangle.overlaps(TemplateScene.getInstance().shipTilemap.fireHandler.get(i).rectangle)) {
                touchingFire = true;
                if(hasFireExtinguisher) {
                    TemplateScene.getInstance().shipTilemap.fireHandler.get(i).health -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    @Override
    public void update() {
        super.update();
        movement();
        collision();

        healthBar.status = health;

        if(nearSpacesuit) {
            if(Gdx.input.isKeyJustPressed(InputManager.TakeSpacesuit.key1)) {
                hasSpacesuit = !hasSpacesuit;
                spacesuit.spacesuitTaken = hasSpacesuit;
                if(hasSpacesuit) {
                    sprite.setTexture(TextureManager.spacesuitTexture);
                }
                else {
                    sprite.setTexture(TextureManager.playerEntity);
                }
            }
        }
        if(nearFireExtanguisher){
            if(Gdx.input.isKeyJustPressed(InputManager.TakeFireExtinguisher.key1)){
                hasFireExtinguisher = !hasFireExtinguisher;
            }
        }
        if(hasFireExtinguisher) fireExtinguisher.setPosition(this.position.x + 10, this.position.y);

        if(health <= 0.0f) {
            TemplateScene.getInstance().game.setScreen(new DeathScene(TemplateScene.getInstance().game));
        }

        sprint = 1.0f;
    }

    public void renderShape(ShapeRenderer sr) {
        healthBar.renderShape(sr);
    }
}
