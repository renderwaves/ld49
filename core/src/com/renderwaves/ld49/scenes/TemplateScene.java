package com.renderwaves.ld49.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.renderwaves.ld49.Game;
import com.renderwaves.ld49.other.Background;
import com.renderwaves.ld49.other.GlobalShipVariables;
import com.renderwaves.ld49.entity.entities.*;
import com.renderwaves.ld49.events.*;
import com.renderwaves.ld49.managers.*;
import com.renderwaves.ld49.tilemap.Tile;
import com.renderwaves.ld49.tilemap.Tilemap;
import com.renderwaves.ld49.ui.Arrow;
import com.renderwaves.ld49.ui.CommunicationMenu;
import com.renderwaves.ld49.ui.StatusBar;
import com.renderwaves.ld49.ui.WarningLabel;

import java.util.ArrayList;

import static com.renderwaves.ld49.Game.entityManager;

public class TemplateScene implements Screen {
    public Game game;
    public Integer gameState = 0;
    public GameEventSystem gameEventSystem;
    public SoundManager gameSound;
    public static Label uiLabel;
    Sprite sprite;

    private Stage stage;
    private Table table;
    private SpriteBatch batch;

    TemplateEntity templateEntity;

    public static Tilemap shipTilemap;
    private StatusBar statusBar;
    public ShapeRenderer shapeRenderer;
    private ProgressManager progressManager;

    public static ArrayList<WarningLabel> warningLabels = new ArrayList<WarningLabel>();

    private float ingameTime = 0f;
    private float period = 0.001f;

    private static TemplateScene instance;

    private StatusBar shipHealthBar = new StatusBar(new Vector2(Gdx.graphics.getWidth() - 200, 175), new Vector2(128, 64), GlobalShipVariables.shipHealth, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.shipIndicator, new Vector2(2, 2));

    private boolean disableEvents = false;

    private Background background;

    public static TemplateScene getInstance() {
        return instance;
    }

    public Arrow arrow;

    /*
     */
    public TemplateScene(Game game) {
        instance = this;
        this.game = game;
        this.gameEventSystem = new GameEventSystem();
        this.gameSound = new SoundManager();
        //this.stage = new Stage();

        arrow = new Arrow();
        arrow.position = new Vector2(-100, -100);

        this.batch = game.batch;

        progressManager = new ProgressManager(50.0f, new Sprite(TextureManager.shipIndicator));
        shapeRenderer = new ShapeRenderer();

        shipTilemap = new Tilemap("maps/ship_new.png");
        shipTilemap.generateEntities();

        Sprite shipIndicator = new Sprite(TextureManager.shipIndicator);
        progressManager = new ProgressManager(50.0f, shipIndicator);

        statusBar = new StatusBar(new Vector2(10, 10), new Vector2(128, 64), 1.0f, new Color(255, 255, 255, 255), new Color(255, 0, 0, 255), TextureManager.energyTexture, new Vector2(2, 2));
        communicationMenu = new CommunicationMenu();
        background = new Background();
        background.keepMoving = false;
    }

    @Override
    public void show() {
        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.table = new Table();
        //this.table.setDebug(true);
        this.table.setFillParent(true);
        this.stage.addActor(table);

        Label.LabelStyle uiLabelStyle = new Label.LabelStyle();
        uiLabelStyle.font = FontManager.font_droidBb_20;
        uiLabelStyle.fontColor = new Color(1,1,1,1);

        this.uiLabel = new Label("", uiLabelStyle);
        this.uiLabel.setAlignment(Align.center);

        this.table.add(new Label("", uiSkin));
        this.table.row();
        this.table.add(uiLabel).spaceTop(480);

        sprite = new Sprite(TextureManager.img);
        stage.addActor(communicationMenu.window);
    }

    /*
     */
    public static int tutorialStage = 0;
    public static String tutorialText = "";
    private GlyphLayout glyphLayout = new GlyphLayout(FontManager.font_droidBb_40, tutorialText);
    private boolean tempBool = false;
    private boolean left = false, right = false, up = false, down = false, shift = false;
    public void update(float delta) {
        System.out.println("x = " + Gdx.input.getX());
        System.out.println("y = " + Gdx.input.getY());

        if(GlobalShipVariables.tutorialMode) {
            if(tutorialStage == 0  && !tempBool) {
                tutorialText = "YOU CAN MOVE USING WASD AND SPRINT USING SHIFT.";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 1) {
                tutorialStage++;
                tempBool = false;
            }
            else if(tutorialStage == 2 && !tempBool) {
                gameEventSystem.addEvent(new GeneratorEvent(0.5f));
                tutorialText = "YOU CAN FIX THE REACTOR BY COMING NEAR IT.";
                arrow.position.x = 600-16;
                arrow.position.y = 255+32;
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 3) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 4 && !tempBool) {
                tutorialText = "LOOKS LIKE NAVIGATION BROKE DOWN. GO FIX IT.";
                arrow.position.x = 1028-16;
                arrow.position.y = 285+32;
                gameEventSystem.addEvent(new NavigationEvent(0.5f));
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 5) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 6 && !tempBool) {
                tutorialText = "LOOKS LIKE COMMUNICATION BROKE DOWN. GO FIX IT.";
                arrow.position.x = 853;
                arrow.position.y = 288+32;
                gameEventSystem.addEvent(new CommsEvent(0.5f));
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 7) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 8 && !tempBool) {
                tutorialText = "LOOKS LIKE LIFE SUPPORT BROKE DOWN. GO FIX IT.";
                arrow.position.x = 372+4;
                arrow.position.y = 288+32;
                gameEventSystem.addEvent(new LifesupportEvent(0.5f));
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 9) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 10 && !tempBool) {
                tutorialText = "LOOKS LIKE ENGINE BROKE DOWN. GO FIX IT.";
                gameEventSystem.addEvent(new EngineEvent(0.5f));
                if(GlobalShipVariables.engineFailed == 1) {
                    arrow.position.x = 342;
                    arrow.position.y = 130+32;
                }
                else if(GlobalShipVariables.engineFailed == 2) {
                    arrow.position.x = 340;
                    arrow.position.y = 450+32;
                }
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 11) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 12 && !tempBool) {
                GlobalShipVariables.generatorFuel = 0.2f;
                arrow.position.x = 600-16;
                arrow.position.y = 255+32;
                tutorialText = "LOOKS LIKE REACTOR FUEL IS RUNNING OUT.\nGO TO COMMUNICATION AND BUY URANIUM.";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage == 13) {
                tutorialText = "";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tutorialStage++;
                arrow.position.x = -100;
                arrow.position.y = -100;
                tempBool = false;
            }
            else if(tutorialStage == 14 && !tempBool) {
                if(!shipTilemap.player.hasFireExtinguisher) {
                    arrow.position.x = entityManager.fireExtinguisher.position.x;
                    arrow.position.y = Gdx.graphics.getHeight()-entityManager.fireExtinguisher.position.y-32;
                }
                else {
                    tutorialStage++;
                }
                tutorialText = "PICK UP EXTINGUISHER.";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
            }
            else if (tutorialStage == 15 && !tempBool) {
                boolean placeFire = true;
                while(placeFire) {
                    Vector2 pos = new Vector2((int)((float)Math.random() * shipTilemap.getWidth()), (int)((float)Math.random() * shipTilemap.getHeight()));
                    if(shipTilemap.getTileByPosition((int)pos.x, (int)pos.y).tileID == Tile.GroundTile.tileID) {
                        shipTilemap.fireHandler.add(new Fire(new Vector2(pos.x*32+shipTilemap.offset.x, pos.y*32+shipTilemap.offset.y)));
                        placeFire = false;

                        if(shipTilemap.player.hasFireExtinguisher) {
                            arrow.position.x = pos.x*32+shipTilemap.offset.x;
                            arrow.position.y = Gdx.graphics.getHeight()-(pos.y*32+shipTilemap.offset.y+32);
                        }
                    }
                }
                tutorialText = "FIRE BROKE OUT. EXTINGUISH IT BY COMING NEAR IT.";
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
                tempBool = true;
            }
            else if(tutorialStage >= 16) {
                tutorialText = "GOOD JOB! YOU FINISHED TUTORIAL. PRESS SPACE FOR MENU.";
                arrow.position.x = -100;
                arrow.position.y = -100;
                glyphLayout.setText(FontManager.font_droidBb_40, tutorialText);
            }
            return;
        }

        int min = -16;
        int max = 16;
        int gameState = (int)(Math.random()*(max-min+1)+min);

        switch(gameState) {
            case 0:
            {
                for (int i = 0; i < gameEventSystem.numEvents(); i++)
                    if (gameEventSystem.getEvent(i) instanceof CommsEvent)
                        return;
                gameEventSystem.addEvent(new CommsEvent(0.5f));
                break;
            }
            // lifesupport, oxygen failure (space suit)
            case 1:
            {
                for (int i = 0; i < gameEventSystem.numEvents(); i++)
                    if (gameEventSystem.getEvent(i) instanceof LifesupportEvent)
                        return;
                gameEventSystem.addEvent(new LifesupportEvent(0.0f));
                break;
            }
            // generator failure
            case 2: {
                for (int i = 0; i < gameEventSystem.numEvents(); i++)
                    if (gameEventSystem.getEvent(i) instanceof GeneratorEvent)
                        return;
                gameEventSystem.addEvent(new GeneratorEvent(0.5f));
                break;
            }
            // navigation failure
            case 3:
            {
                for (int i = 0; i < gameEventSystem.numEvents(); i++)
                    if (gameEventSystem.getEvent(i) instanceof NavigationEvent)
                        return;
                gameEventSystem.addEvent(new NavigationEvent(0.5f));
                break;
            }
            // engine failure
            case 4:
            {
                for (int i = 0; i < gameEventSystem.numEvents(); i++)
                    if (gameEventSystem.getEvent(i) instanceof EngineEvent)
                        return;
                gameEventSystem.addEvent(new EngineEvent(0.5f));
                break;
            }
            // door failure
            case 5:
            {
                gameEventSystem.addEvent(new DoorEvent());
                break;
            }
            // door failure
            case 6:
            {
                gameEventSystem.addEvent(new DoorEvent());
                break;
            }
            // door failure
            case 7:
            {
                gameEventSystem.addEvent(new DoorEvent());
                break;
            }
            default:
                break;
        }
    }

    private Generator generator;
    public static boolean fireEvent = false;
    public OxygenManager oxygenManager = new OxygenManager();
    public static CommunicationMenu communicationMenu;

    private boolean hasGeneratorEvent = false;
    private boolean hasLifeSupportEvent = false;
    private boolean hasNavigationEvent = false;
    private boolean hasCommsEvent = false;
    private boolean hasEngine1FailedEvent = false;
    private boolean hasEngine2FailedEvent = false;
    @Override
    public void render(float delta) {
        //background.renderShape(shapeRenderer);

        if(tutorialStage == 0) {
            if(!left) left = Gdx.input.isKeyPressed(InputManager.MoveLeft.key1);
            if(!right) right = Gdx.input.isKeyPressed(InputManager.MoveRight.key1);
            if(!up) up = Gdx.input.isKeyPressed(InputManager.MoveUp.key1);
            if(!down) down = Gdx.input.isKeyPressed(InputManager.MoveDown.key1);
            if(!shift) shift = Gdx.input.isKeyPressed(InputManager.Sprint.key1);

            if(left && right && up && down && shift) {
                tutorialStage++;
            }
        }

        if(tutorialStage >= 16 && GlobalShipVariables.tutorialMode) {
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                TemplateScene.shipTilemap.doorHandler.clear();
                TemplateScene.shipTilemap.fireHandler.clear();
                TemplateScene.getInstance().gameEventSystem.completeAllEvents();
                TemplateScene.warningLabels.clear();
                com.renderwaves.ld49.Game.entityManager.clear();
                PlayerEntity.health = 1.0f;
                GlobalShipVariables.engine1Health = 1.0f;
                GlobalShipVariables.oxygenLevel = 1.0f;
                GlobalShipVariables.shipHealth = 1.0f;
                GlobalShipVariables.communicationsHealth = 1.0f;
                GlobalShipVariables.navigationHealth = 1.0f;
                GlobalShipVariables.lifeSupportHealth = 1.0f;
                GlobalShipVariables.generatorFuel = 1.0f;
                GlobalShipVariables.generatorHealth = 1.0f;
                GlobalShipVariables.engine2Health = 1.0f;
                GlobalShipVariables.score = 0;
                GlobalShipVariables.globalShipTimer = 25000;
                this.game.setScreen(new MenuScene(this.game));
            }
        }

        period = (float)(Math.random()*(1.0f-0.01f + 1.0f)+1.0f);

        hasGeneratorEvent = false;
        hasNavigationEvent = false;
        hasLifeSupportEvent = false;
        hasCommsEvent = false;
        hasEngine2FailedEvent = false;
        hasEngine1FailedEvent = false;

        for(int i = 0; i < gameEventSystem.numEvents(); i++) {
            if(gameEventSystem.getEvent(i) instanceof NavigationEvent) {
                hasNavigationEvent = true;
            }
            else if(gameEventSystem.getEvent(i) instanceof CommsEvent) {
                hasCommsEvent = true;
            }
            else if(gameEventSystem.getEvent(i) instanceof LifesupportEvent) {
                hasLifeSupportEvent = true;
            }
            else if(gameEventSystem.getEvent(i) instanceof GeneratorEvent) {
                hasGeneratorEvent = true;
            }
            else if(gameEventSystem.getEvent(i) instanceof EngineEvent && GlobalShipVariables.engineFailed == 1) {
                if(GlobalShipVariables.engineFailed != 2) hasEngine1FailedEvent = true;
            }
            else if(gameEventSystem.getEvent(i) instanceof EngineEvent && GlobalShipVariables.engineFailed == 2) {
                if(GlobalShipVariables.engineFailed != 1) hasEngine2FailedEvent = true;
            }
        }


        if(!hasNavigationEvent && GlobalShipVariables.navigationHealth < 1.0) {
            gameEventSystem.addEvent(new NavigationEvent(0.0f));
        }

        if(!hasCommsEvent && GlobalShipVariables.communicationsHealth < 1.0) {
            gameEventSystem.addEvent(new CommsEvent(0.0f));
        }

        if(!hasGeneratorEvent && GlobalShipVariables.generatorHealth < 1.0) {
            gameEventSystem.addEvent(new GeneratorEvent(0.0f));
        }

        if(!hasLifeSupportEvent && GlobalShipVariables.lifeSupportHealth < 1.0) {
            gameEventSystem.addEvent(new LifesupportEvent(0.0f));
        }

        if(!hasEngine1FailedEvent && GlobalShipVariables.engine1Health < 1.0) {
            if(GlobalShipVariables.engineFailed == 1) {
                gameEventSystem.addEvent(new EngineEvent(0.0f));
            }
        }

        if(!hasEngine2FailedEvent && GlobalShipVariables.engine2Health < 1.0) {
            if(GlobalShipVariables.engineFailed == 2) {
                gameEventSystem.addEvent(new EngineEvent(0.0f));
            }
        }


        communicationMenu.update();

        shipHealthBar.status = GlobalShipVariables.shipHealth;

        if(shipTilemap.fireHandler.size() > 0 && !fireEvent) {
            gameEventSystem.addEvent(new FireEvent());
            fireEvent = true;
        }

        ingameTime += Gdx.graphics.getRawDeltaTime();
        if (ingameTime > period) {
            ingameTime -= period;

            if (disableEvents == false) {
                if(MenuScene.difficulty == 1) {
                    if (gameEventSystem.numEvents() - DoorEvent.numberOfDoorEvents < 2)
                        update(delta);
                }
                else if(MenuScene.difficulty == 2) {
                    if (gameEventSystem.numEvents() - DoorEvent.numberOfDoorEvents < 3)
                        update(delta);
                }
                else if(MenuScene.difficulty == 3) {
                    if (gameEventSystem.numEvents() - DoorEvent.numberOfDoorEvents < 4)
                        update(delta);
                }
                else if(MenuScene.difficulty == 4) {
                    update(delta);
                }
            }
        }

        gameEventSystem.update(ingameTime);

        if (shipTilemap.player.hasDied())
            gameSound.forceStop();

        ScreenUtils.clear(0, 0, 0, 1);

        background.renderShape(shapeRenderer);

        batch.begin();
            background.renderSprite(batch);
            shipTilemap.render(batch);
            entityManager.handleEntities(batch);
            gameEventSystem.render(batch);

            progressManager.renderSprites(batch);
            if(progressManager.getProgress() >= 1.0f) progressManager.setProgress(0.0f);
            if(GlobalShipVariables.generatorFuel > 0) {
                if(!GlobalShipVariables.tutorialMode) {
                    GlobalShipVariables.progression = progressManager.getProgress() + (delta / ((200 + ((1 - GlobalShipVariables.engine1Health) * 250 + (1 - GlobalShipVariables.navigationHealth) * 250)))) * GlobalShipVariables.generatorHealth;
                    progressManager.setProgress(GlobalShipVariables.progression);
                }
            }
            if(progressManager.getProgress() >= 1.0f) {
                gameSound.forceStop();
                GlobalShipVariables.score += GlobalShipVariables.globalShipTimer;
                game.setScreen(new YouWonScene(game));
            }

            for(int i = 0; i < warningLabels.size(); i++) {
                warningLabels.get(i).render(batch, i);
            }

            oxygenManager.renderSprite(batch);
            shipHealthBar.renderSprite(batch);

            if(GlobalShipVariables.tutorialMode) {
                arrow.render(batch);
            }
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            gameEventSystem.render(shapeRenderer);

            progressManager.renderShapes(shapeRenderer);

            if(generator == null) {
                for(int i = 0; i < entityManager.size(); i++) {
                    if(entityManager.get(i) instanceof Generator) {
                        generator = (Generator) entityManager.get(i);
                    }
                }
            }
            else {
                generator.renderShape(shapeRenderer);
            }

            shipTilemap.player.renderShape(shapeRenderer);

            oxygenManager.renderShape(shapeRenderer);
            shipHealthBar.renderShape(shapeRenderer);
        shapeRenderer.end();

        // overlay fonts
        batch.begin();
            progressManager.renderFonts(FontManager.font_droidBb_18, batch);
            if(GlobalShipVariables.tutorialMode && tutorialText.length() > 0) {
                FontManager.font_droidBb_40.setColor(Color.WHITE);
                FontManager.font_droidBb_40.draw(batch, this.glyphLayout, ((float)Gdx.graphics.getWidth()/2) - (glyphLayout.width/2), 75);
            }
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        if(Gdx.input.isKeyJustPressed(Input.Keys.F11)) {
            gameEventSystem.completeAllEvents();
        }

        if(GlobalShipVariables.navigationHealth > 1.0f) {
            GlobalShipVariables.navigationHealth = 1.0f;
        }
        else if(GlobalShipVariables.navigationHealth < 0.0f) {
            GlobalShipVariables.navigationHealth = 0.0f;
        }

        if(GlobalShipVariables.shipHealth > 1.0f) {
            GlobalShipVariables.navigationHealth = 1.0f;
        }
        else if(GlobalShipVariables.shipHealth < 0.0f) {
            GlobalShipVariables.navigationHealth = 0.0f;
        }

        if(GlobalShipVariables.generatorFuel > 1.0f) {
            GlobalShipVariables.generatorFuel = 1.0f;
        }
        else if(GlobalShipVariables.generatorFuel < 0.0f) {
            GlobalShipVariables.generatorFuel = 0.0f;
        }

        if(GlobalShipVariables.generatorHealth > 1.0f) {
            GlobalShipVariables.generatorHealth = 1.0f;
        }
        else if(GlobalShipVariables.generatorHealth < 0.0f) {
            GlobalShipVariables.generatorHealth = 0.0f;
        }

        if(GlobalShipVariables.engine1Health > 1.0f) {
            GlobalShipVariables.engine1Health = 1.0f;
        }
        else if(GlobalShipVariables.engine1Health < 0.0f) {
            GlobalShipVariables.engine1Health = 0.0f;
        }

        if(GlobalShipVariables.engine2Health > 1.0f) {
            GlobalShipVariables.engine2Health = 1.0f;
        }
        else if(GlobalShipVariables.engine2Health < 0.0f) {
            GlobalShipVariables.engine2Health = 0.0f;
        }

        if(GlobalShipVariables.lifeSupportHealth > 1.0f) {
            GlobalShipVariables.lifeSupportHealth = 1.0f;
        }
        else if(GlobalShipVariables.lifeSupportHealth < 0.0f) {
            GlobalShipVariables.lifeSupportHealth = 0.0f;
        }

        if(GlobalShipVariables.communicationsHealth > 1.0f) {
            GlobalShipVariables.communicationsHealth = 1.0f;
        }
        else if(GlobalShipVariables.communicationsHealth < 0.0f) {
            GlobalShipVariables.communicationsHealth = 0.0f;
        }

        if(GlobalShipVariables.oxygenLevel > 1.0f) {
            GlobalShipVariables.oxygenLevel = 1.0f;
        }
        else if(GlobalShipVariables.oxygenLevel < 0.0f) {
            GlobalShipVariables.oxygenLevel = 0.0f;
        }

        if(GlobalShipVariables.shipHealth <= 0.0f) {
            gameSound.forceStop();
            gameEventSystem.completeAllEvents();
            game.setScreen(new DeathScene(game));
        }

        GlobalShipVariables.globalShipTimer -= Gdx.graphics.getDeltaTime();
        if(GlobalShipVariables.globalShipTimer < 0) {
            GlobalShipVariables.globalShipTimer = 0;
        }

        if(GlobalShipVariables.generatorHealth <= 0.0f || GlobalShipVariables.generatorFuel <= 0.0f) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0, 0, 0, 0.8f));
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
