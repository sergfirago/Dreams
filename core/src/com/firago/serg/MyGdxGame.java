package com.firago.serg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.firago.serg.logic.Prototype;
import com.firago.serg.view.SpriteFabric;

class SizeGame {
    private float width;
    private float height;
    private float itemSize;

    private Rectangle desktop;
    private Rectangle itemPanel;
    private Rectangle actionPanel;

    private static float itemPanelPart = 0.1f;
    private static float actionPanelPart = 0.2f;

    public SizeGame(float width, float height) {

        update(width, height);

    }

    public void update(float width, float height) {
        this.width = width;
        this.height = height;

        actionPanel = new Rectangle(0, 0, width, height * actionPanelPart);
        itemPanel = new Rectangle(0, actionPanel.y, width * itemPanelPart, height - actionPanel.y);
        itemSize = 0.5f * itemPanel.width;
        desktop = new Rectangle(itemPanel.width, actionPanel.height, width - itemPanel.width, height - actionPanel.height);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getSizeItem() {
        return itemSize;
    }
    public float getButtonSize(){
        return itemSize*1.75f;
    }
    public Rectangle getDesktop() {
        return desktop;
    }

    public Rectangle getItemPanel() {
        return itemPanel;
    }

    public Rectangle getActionPanel() {
        return actionPanel;
    }

    public static float getItemPanelPart() {
        return itemPanelPart;
    }

    public static float getActionPanelPart() {
        return actionPanelPart;
    }
}


public class MyGdxGame extends ApplicationAdapter {


    Label text;
    Label.LabelStyle textStyle;
    BitmapFont font;
    Button button;

    private Stage stage;
    private DesktopGroup group;
    private Group panel;
//    private Group bottomPanel;
    private SizeGame sizeGame;

    @Override
    public void create() {
        SpriteFabric.init();
        sizeGame = new SizeGame(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        Viewport viewport = new ScreenViewport();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.setViewport(viewport);
//        stage = new Stage(new FillViewport(WIDTH, HEIGHT));

        Gdx.input.setInputProcessor(stage);
        group = new DesktopGroup();
        initGroup();
        group.setBounds(sizeGame.getDesktop().x, sizeGame.getDesktop().y,
                sizeGame.getDesktop().width, sizeGame.getDesktop().height);
        stage.addActor(group);

        initPanel();
        stage.addActor(panel);

        initBottom();
        initText();
        stage.addActor(text);

    }

    private void initBottom() {
        Rectangle actionPanel = sizeGame.getActionPanel();

        Group bottomPanel = createBottomPanel(actionPanel);

        bottomPanel.setBounds(actionPanel.x, actionPanel.y, actionPanel.width, actionPanel.height);
        stage.addActor(bottomPanel);

    }

    private Group createBottomPanel(Rectangle actionPanel) {
        Group bottomPanel = new Group();
        Image image = new Image(new Texture("panel2.png"));
        image.setBounds(actionPanel.x, actionPanel.y, actionPanel.width, actionPanel.height);
        image.setTouchable(Touchable.disabled);
        bottomPanel.addActor(image);

        addActionButton(bottomPanel,20, 20, "cross.png", "cross2.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.deleteActiveItem();
                        return true;
                    }
                });

        addActionButton(bottomPanel, 110,20,"mirror.png", "mirror.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.reflectActiveItem();
                        return true;
                    }
                });
        addActionButton(bottomPanel,200, 20, "mirror.png", "mirror.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.scaleActiveItem();
                        return true;
                    }
                });
        addActionButton(bottomPanel, 290, 20, "mirror.png", "mirror.png", new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                group.flipActiveItem();
                return true;
            }
        });
        return bottomPanel;
    }

    private void addActionButton(Group panel, float x, float y, String upFile, String downFile, InputListener listener) {
        ImageButton button = new ImageButton(new Skin(Gdx.files.internal("button.json")));
        button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(upFile))));
        button.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(downFile))));
        button.setBounds(x, y, sizeGame.getButtonSize(), sizeGame.getButtonSize());
        button.addListener(listener);
        panel.addActor(button);
    }

    private void initPanel() {
        panel = new Group();
        Image bg = new Image(new Texture("panel.png"));
        bg.setTouchable(Touchable.disabled);
        Rectangle itemPanel = sizeGame.getItemPanel();
        bg.setBounds(itemPanel.x, itemPanel.y, itemPanel.width, itemPanel.height);
        panel.addActor(bg);
        final Image mouse = new Image(SpriteFabric.getTexture(Prototype.MOUSE));
        mouse.setBounds(20, itemPanel.x + itemPanel.height - sizeGame.getSizeItem() * 2,
                sizeGame.getSizeItem(), sizeGame.getSizeItem());
        panel.addActor(mouse);
        final Image cheese = new Image(SpriteFabric.getTexture(Prototype.CHEESE));
        cheese.setBounds(20, itemPanel.x + itemPanel.height - sizeGame.getSizeItem() * 4,
                sizeGame.getSizeItem(), sizeGame.getSizeItem());
        panel.addActor(cheese);
        panel.setBounds(itemPanel.x, itemPanel.y, itemPanel.width, itemPanel.height);
        panel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor actor = panel.hit(x, y, true);
                group.setSelected(false);
                if (actor == mouse) {
                    group.newActor(Prototype.MOUSE);
                    return true;
                }
                if (actor == cheese) {
                    group.newActor(Prototype.CHEESE);
                    return true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    private void initText() {
        font = new BitmapFont();
        textStyle = new Label.LabelStyle();
        textStyle.font = font;

        text = new Label("Gamever", textStyle);
        text.setBounds(0, .2f, sizeGame.getWidth(), 20);
        text.setFontScale(1f, 1f);
    }

    private void initGroup() {
        group.newActor(Prototype.CHEESE, "cheese");
        group.newActor(Prototype.MOUSE, "mouse");
//        group.setBounds(0,20f,WIDTH, HEIGHT - 20f);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        text.setText("dt = " + deltaTime);
        stage.act(deltaTime);

        stage.draw();

    }


    public void resize(int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
        sizeGame.update(width, height);

        System.out.println("resize "+width +" " +height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
