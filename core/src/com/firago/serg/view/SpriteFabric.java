package com.firago.serg.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.firago.serg.prototypes.Prototype;

public class SpriteFabric {
    private static Sprite mouse = null;
    private static Sprite cheese = null;
    private static Texture mouseTexture;
    private static Texture cheeseTexture;
    private static Texture maya1;
    private static Texture maya2;
    private static Texture maya3;

    public static Texture getTexture(Prototype prototype){
        switch (prototype){
            case MOUSE: return mouseTexture;
            case CHEESE:return cheeseTexture;
            case MAYA1: return maya1;
            case MAYA2: return maya2;
            case MAYA3: return maya3;

        }
        return null;
    }

    public static void init() {
        mouseTexture = new Texture("mouse.png");
        maya1 = new Texture("maya1.png");
        maya2 = new Texture("maya2.png");
        cheeseTexture = new Texture("cheese.png");
        maya3 = new Texture("maya3.png");

    }
}
