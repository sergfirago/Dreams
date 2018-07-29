package com.firago.serg.view.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.firago.serg.DesktopGroup;


public class BottomPanel extends Panel {
    private final float buttonSize;

    public BottomPanel(Rectangle actionPanel, final DesktopGroup group, float buttonSize) {
        super(new Image(new Texture("panel2.png")));
        this.buttonSize = buttonSize;

        background.setBounds(actionPanel.x, actionPanel.y, actionPanel.width, actionPanel.height);
        background.setTouchable(Touchable.disabled);
        addActor(background);

        addActionButton(20, 20, "cross.png", "cross2.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.deleteActiveItem();
                        return true;
                    }
                });

        addActionButton( 110,20,"mirror.png", "mirror.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.reflectActiveItem();
                        return true;
                    }
                });
        addActionButton(200, 20, "mirror.png", "mirror.png",
                new InputListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        group.scaleActiveItem();
                        return true;
                    }
                });
        addActionButton(290, 20, "mirror.png", "mirror.png", new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                group.flipActiveItem();
                return true;
            }
        });
    }
    private void addActionButton( float x, float y, String upFile, String downFile, InputListener listener) {
        ImageButton button = new ImageButton(new Skin(Gdx.files.internal("button.json")));
        button.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(upFile))));
        button.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(downFile))));
        button.setBounds(x, y, buttonSize, buttonSize);
        button.addListener(listener);
        getItems().addActor(button);
    }

}
