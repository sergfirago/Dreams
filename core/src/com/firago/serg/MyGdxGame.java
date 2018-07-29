package com.firago.serg;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.firago.serg.prototypes.Prototype;
import com.firago.serg.view.desktop.DesktopGroup;
import com.firago.serg.view.panels.action.BottomPanel;
import com.firago.serg.view.panels.shape.ItemPanel;

class LayoutGame {
    private float width;
    private float height;
    private float itemSize;

    private Rectangle desktop;
    private Rectangle itemPanel;
    private Rectangle actionPanel;

    private static float itemPanelPart = 0.1f;
    private static float actionPanelPart = 0.2f;

    public LayoutGame(float width, float height) {

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


    private Label text;

    private Stage stage;
    private DesktopGroup group;
    private ItemPanel panel;
    private LayoutGame layoutGame;
    private BottomPanel bottomPanel;

    @Override
    public void create() {
        layoutGame = new LayoutGame(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage();
        Viewport viewport = new ScreenViewport();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage.setViewport(viewport);

        Gdx.input.setInputProcessor(stage);
        group = new DesktopGroup();
        initGroup();
        group.setBounds(layoutGame.getDesktop().x, layoutGame.getDesktop().y,
                layoutGame.getDesktop().width, layoutGame.getDesktop().height);
        stage.addActor(group);

        panel = new ItemPanel(group, layoutGame.getSizeItem());
        stage.addActor(panel);

        bottomPanel = new BottomPanel(group, layoutGame.getButtonSize());
        stage.addActor(bottomPanel);

        initText();
        stage.addActor(text);

    }


    private void initText() {
        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = font;

        text = new Label("Gamever", textStyle);
        text.setBounds(0, .2f, layoutGame.getWidth(), 20);
        text.setFontScale(1f, 1f);
    }

    private void initGroup() {
        group.newActor(Prototype.CHEESE, "cheese");
        group.newActor(Prototype.MOUSE, "mouse");
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
        stage.getViewport().update(width, height, true);
        layoutGame.update(width, height);

        Rectangle actionPanel = layoutGame.getActionPanel();
        bottomPanel.setButtonSize(layoutGame.getButtonSize());
        bottomPanel.setBounds(actionPanel.x, actionPanel.y, actionPanel.width, actionPanel.height);

        Rectangle itemPanel = layoutGame.getItemPanel();
        panel.setItemSize(layoutGame.getSizeItem());
        panel.setBounds(itemPanel.x, itemPanel.y, itemPanel.width, itemPanel.height);

        Rectangle desktop = layoutGame.getDesktop();
        group.setBounds(desktop.x, desktop.y, desktop.width, desktop.height);


        System.out.println("resize "+width +" " +height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}



