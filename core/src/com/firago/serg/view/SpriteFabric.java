package com.firago.serg.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.firago.serg.logic.Prototype;

public class SpriteFabric {
    private static Sprite mouse = null;
    private static Sprite cheese = null;
    private static Texture mouseTexture;
    private static Texture cheeseTextrure;


    public static Sprite getSprite(Prototype prototype){
        switch (prototype){
            case MOUSE: return mouse;
            case CHEESE:return cheese;
        }
        return null;
    }
    public static Texture getTexture(Prototype prototype){
        switch (prototype){
            case MOUSE: return mouseTexture;
            case CHEESE:return cheeseTextrure;
        }
        return null;
    }

    public static void init() {
        mouseTexture = new Texture("mouse.png");
        mouse = new Sprite(mouseTexture);
        cheeseTextrure = new Texture("cheese.png");
        cheese = new Sprite(cheeseTextrure);
    }
}
