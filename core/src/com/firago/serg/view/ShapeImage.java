package com.firago.serg.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.firago.serg.logic.Prototype;

public class ShapeImage extends Image {
    private final SpriteDrawable sprite;
    int scale = 0;
    public void rotate() {
        this.rotateBy(45f);
    }
    public void scale(){
        scale %=7;
        int v = scale;
        v -=3;
        float scaleValue = 1.0f / (1.0f - v/4.0f);
        setScale(scaleValue);
        scale++;
    }

    public void flip() {
        Drawable drawable = getDrawable();
        if (drawable instanceof SpriteDrawable) {
            ((SpriteDrawable)drawable).getSprite().flip(true, false);
        }
        setRotation(-getRotation());
    }

    class ActorListener extends DragListener{
        private Vector2 clickCoordinate = new Vector2();
        @Override
        public void dragStart(InputEvent event, float x, float y, int pointer) {
            clickCoordinate.set(x,y);
            super.dragStart(event, x, y, pointer);
        }


        @Override
        public void drag(InputEvent event, float x, float y, int pointer) {
            Vector2 newPosition = new Vector2(-clickCoordinate.x, -clickCoordinate.y);
            newPosition.rotate(-getRotation());
            newPosition.add(x,y);
            newPosition.rotate(getRotation());
            moveBy(newPosition.x, newPosition.y);

        }
    }

    ShapeRenderer renderer = new ShapeRenderer();
    private boolean selected;

    public ShapeImage(Prototype prototype) {
        super();
        sprite = new SpriteDrawable(new Sprite(
                SpriteFabric.getTexture(prototype)));
        setDrawable(sprite);
//        spriteFlip = new SpriteDrawable(SpriteFabric.getSprite(prototype));
        setWidth(100);
        setHeight(100);
        setOrigin(getWidth()/2, getHeight()/2);

        addListener(new ActorListener());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (!selected) return;
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.translate(getX(), getY(), 0);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(0, 0,
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation());
        renderer.end();
        batch.begin();

    }
}
