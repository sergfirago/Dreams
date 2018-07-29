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

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        background.setBounds(x, y, width, height);
    }

}
