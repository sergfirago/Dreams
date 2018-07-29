package com.firago.serg.view.panels.shape;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.firago.serg.view.desktop.DesktopGroup;
import com.firago.serg.prototypes.Prototype;
import com.firago.serg.view.panels.Panel;
import com.firago.serg.view.panels.action.ShapeButton;

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
        ShapeButton.ClickListener clickListener = new ShapeButton.ClickListener() {
            @Override
            public void onClick(Prototype prototype) {
                desktopGroup.setSelected(false);
                desktopGroup.newActor(prototype);
            }
        };

        addItem(new ShapeButton(Prototype.MOUSE, clickListener));
        addItem(new ShapeButton(Prototype.CHEESE, clickListener));
        addItem(new ShapeButton(Prototype.MAYA1, clickListener));
        addItem(new ShapeButton(Prototype.MAYA2, clickListener));
        addItem(new ShapeButton(Prototype.MAYA3, clickListener));

    }

    private void addItem(Image image){
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
