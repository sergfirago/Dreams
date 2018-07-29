package com.firago.serg.view.desktop;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.firago.serg.prototypes.Prototype;

public class DesktopGroup extends Group {
    public DesktopGroup() {
        initListener();
    }

    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected){
            setSelected(false);
            int top = getTopItemIndex();
            if (top<0) return;
            Actor actor = getChildren().get(top);
            if (actor instanceof ShapeImage){
                ShapeImage shapeImage = (ShapeImage) actor;
                shapeImage.setSelected(true);
            }
        }else {
            Actor[] items = getChildren().items;
            for (Actor actor : items) {
                if (actor instanceof ShapeImage){
                    ((ShapeImage) actor).setSelected(false);
                }
            }
        }
    }

    public void deleteActiveItem() {
//        if (isSelected()){
            int top = getTopItemIndex();
            if (top>=0) {
                getChildren().removeIndex(top);
            }

    }

    private int getTopItemIndex() {
        return getChildren().size - 1;
    }

    public void newActor(Prototype prototype, String name) {
        ShapeImage actor = new ShapeImage(prototype);
        if (name!=null) actor.setName(name);
        addActor(actor);
    }
    public void newActor(Prototype prototype){
        newActor(prototype, null);
    }
    private void initListener(){
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor actor = hit(x, y, true);

                if (actor != null) {
                    int lastIndex = getChildren().size - 1;
                    Actor last = getChildren().get(lastIndex);
                    actor.setZIndex(last.getZIndex() + 1);
                    System.out.println("index :" + actor.getZIndex());
                }
                setSelected(actor != null);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.M) {
                    Prototype prototype = Prototype.MOUSE;
                    newActor(prototype);
                } else if (keycode == Input.Keys.C) {
                    newActor(Prototype.CHEESE);
                } else if (keycode == Input.Keys.D) {
                    deleteActiveItem();
                    System.out.println("delete");
                }
                return super.keyUp(event, keycode);
            }


        });
    }

    public void reflectActiveItem() {
//        if (isSelected()){
            int lastIndex =getTopItemIndex();
            if (lastIndex>=0) {
                ShapeImage actor = (ShapeImage) getChildren().get(lastIndex);
                actor.rotate();
            }
//        }
    }

    public void scaleActiveItem() {
        int lastIndex =getTopItemIndex();
        if (lastIndex>=0) {
            ShapeImage actor = (ShapeImage) getChildren().get(lastIndex);
            actor.scale();
        }
    }
    public void flipActiveItem() {
        int lastIndex =getTopItemIndex();
        if (lastIndex>=0) {
            ShapeImage actor = (ShapeImage) getChildren().get(lastIndex);
            actor.flip();
        }
    }


}
