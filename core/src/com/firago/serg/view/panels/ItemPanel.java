package com.firago.serg.view.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.firago.serg.DesktopGroup;
import com.firago.serg.prototypes.Prototype;
import com.firago.serg.view.SpriteFabric;

public class ItemPanel extends Panel {
    public static final int SPACE = 20;
    private float itemSize;

    public float getItemSize() {
        return itemSize;
    }

    public void setItemSize(float itemSize) {
        this.itemSize = itemSize;
    }

    public ItemPanel(final DesktopGroup desktopGroup, float itemSize) {
        super(new Image(new Texture("panel.png")));
        this.itemSize = itemSize;

        final Image mouse = new Image(SpriteFabric.getTexture(Prototype.MOUSE));
         addItem(mouse);
        final Image cheese = new Image(SpriteFabric.getTexture(Prototype.CHEESE));
        addItem(cheese);

        final Image maya1 = new Image(SpriteFabric.getTexture(Prototype.MAYA1));
        addItem(maya1);

        final Image maya2 = new Image(SpriteFabric.getTexture(Prototype.MAYA2));
        addItem(maya2);

        final Image maya3 = new Image(SpriteFabric.getTexture(Prototype.MAYA3));
        addItem(maya3);

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
                if (actor == maya1){
                    desktopGroup.newActor(Prototype.MAYA1);
                }
                if (actor==maya2){
                    desktopGroup.newActor(Prototype.MAYA2);
                }
                if (actor==maya3){
                    desktopGroup.newActor(Prototype.MAYA3);
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });



    }

    public void addItem(Image image){
        image.setWidth(itemSize);
        image.setHeight(itemSize);
        getItems().addActor(image);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        float yPos = getHeight() - SPACE -itemSize;
        float xPos = (getWidth() - itemSize)/2.0f;

        for (Actor child : getItems().getChildren()) {
            child.setBounds(xPos, yPos, itemSize, itemSize);
            System.out.println(child.getX()+" "+child.getY()+" "+ child.getWidth()+" +"+ child.getHeight());
            yPos-=itemSize + SPACE;
        }

    }

}
