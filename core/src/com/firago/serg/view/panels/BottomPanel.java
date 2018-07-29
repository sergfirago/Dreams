package com.firago.serg.view.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.SnapshotArray;
import com.firago.serg.DesktopGroup;


public class BottomPanel extends Panel {
    public float getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(float buttonSize) {
        this.buttonSize = buttonSize;
    }

    private float buttonSize;
    private float lastButtonX = 20f;
    private float buttonY = 0f;
    public BottomPanel(final DesktopGroup group, float buttonSize) {
        super(new Image(new Texture("panel2.png")));
        this.buttonSize = buttonSize;

        addActionButton("cross.png", "cross2.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.deleteActiveItem();
                        return true;
                    }
                });

        addActionButton( "rotate.png", "rotate.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.reflectActiveItem();
                        return true;
                    }
                });
        addActionButton("size.png", "size.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.scaleActiveItem();
                        return true;
                    }
                });
        addActionButton("Flip.png", "Flip.png", new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                group.flipActiveItem();
                return true;
            }
        });
    }
    private void addActionButton(String upFile, String downFile, InputListener listener) {
        ImageButton button = new ImageButton(new Skin(Gdx.files.internal("button.json")));
        button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(upFile))));
        button.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(downFile))));
        button.setBounds(lastButtonX, buttonY, buttonSize, buttonSize);
        button.addListener(listener);
        getItems().addActor(button);
        lastButtonX+=buttonSize*1.5f;
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        lastButtonX = 20f;
        buttonY = (height - buttonSize)/2.0f;
        SnapshotArray<Actor> children = getItems().getChildren();
        for (Actor child : children) {
            child.setBounds(lastButtonX, buttonY, buttonSize, buttonSize);
            lastButtonX+=buttonSize*1.5f;
        }

    }
}
