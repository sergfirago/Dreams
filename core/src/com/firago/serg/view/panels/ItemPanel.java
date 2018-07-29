package com.firago.serg.view.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.SnapshotArray;
import com.firago.serg.DesktopGroup;
import com.firago.serg.logic.Prototype;
import com.firago.serg.view.SpriteFabric;

public class ItemPanel extends Panel {
    private final Vector2 panelSize;
    private final float itemSize;

    public ItemPanel(final DesktopGroup desktopGroup, Vector2 panelSize, float itemSize) {
        super(new Image(new Texture("panel.png")));
        this.panelSize = panelSize;
        this.itemSize = itemSize;

        final Image mouse = new Image(SpriteFabric.getTexture(Prototype.MOUSE));
         addItem(mouse);
        final Image cheese = new Image(SpriteFabric.getTexture(Prototype.CHEESE));
        addItem(cheese);

        getItems().addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor actor = hit(x, y, true);
                desktopGroup.setSelected(false);
                if (actor == mouse) {
                    desktopGroup.newActor(Prototype.MOUSE);
                    return true;
                }
                if (actor == cheese) {
                    desktopGroup.newActor(Prototype.CHEESE);
                    return true;
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });



    }

    public void addItem(Image image){
        getItems().addActor(image);
    }
    @Override
    public void resize(float width, float height) {
        super.resize(width, height);
        SnapshotArray<Actor> children = getItems().getChildren();
        float y = 20;
        float startX = (width - itemSize)/2.0f;
        float itemSize = 50;

        for (Actor child : children) {
            child.setBounds(startX, y, itemSize, itemSize);
            y+=itemSize + 20;
        }
    }

}
