package com.firago.serg;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.firago.serg.logic.Prototype;
import com.firago.serg.view.ShapeImage;

class ActorDragListener extends DragListener {
    private final Actor actor;
    private final Color color;
    private final Color dragColor;
    Vector2 vector = new Vector2();
    private Vector2 localCoordinates = new Vector2();

    public ActorDragListener(Actor actor) {
        this.actor = actor;
        color = actor.getColor();
        dragColor = new Color();
        dragColor.set(color.r, color.g, color.b, color.a);
        dragColor.a = 0.5f;
    }

    @Override
    public void dragStart(InputEvent event, float x, float y, int pointer) {
        System.out.println("Drag " + actor.getName());
        super.dragStart(event, x, y, pointer);
        vector.set(x,y);
        localCoordinates = actor.stageToLocalCoordinates(vector);
        System.out.println(localCoordinates);
//        System.out.println(dragColor);
//        System.out.println(color);
//        if (isDragging()) {
//            actor.setColor(dragColor);
//        }else{
//            actor.setColor(color);
//        }
    }

    @Override
    public void dragStop(InputEvent event, float x, float y, int pointer) {
        System.out.println("Stop drag " + actor.getName() + " " + color);
//        actor.setColor(color);
        super.dragStop(event, x, y, pointer);

    }



    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        actor.moveBy(x-localCoordinates.x,y-localCoordinates.y);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        localCoordinates.set(x,y);
        System.out.println(actor.getName()+" "+x+ " "+y);
        return super.touchDown(event, x, y, pointer, button);
    }
}


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
//        }
    }

    private int getTopItemIndex() {
        return getChildren().size - 1;
    }

    public void newActor(Prototype prototype, String name) {
        ShapeImage actor = new ShapeImage(prototype);
//        actor.addListener(new ActorDragListener(actor));
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
