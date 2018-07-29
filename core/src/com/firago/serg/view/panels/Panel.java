package com.firago.serg.view.panels;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


abstract public class Panel extends Group {
    public Panel(Image background) {
        this.background = background;
        this.background.setTouchable(Touchable.disabled);
        items = new Group();
        addActor(this.background);
        addActor(items);
    }

    protected Image background;
    protected Group items;

    public Group getItems() {
        return items;
    }

    public void resize(float width, float height){
        background.setBounds(0,0,width, height);
    }

}
