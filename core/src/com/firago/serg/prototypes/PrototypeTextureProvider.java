package com.firago.serg.prototypes;

import com.badlogic.gdx.graphics.Texture;

import java.security.InvalidParameterException;

public class PrototypeTextureProvider {
    private static final PrototypeTextureProvider ourInstance = new PrototypeTextureProvider();

    public static PrototypeTextureProvider getInstance() {
        return ourInstance;
    }

    private Texture mouseTexture;
    private  Texture cheeseTexture;
    private  Texture maya1;
    private  Texture maya2;
    private  Texture maya3;
    private PrototypeTextureProvider() {
        mouseTexture = new Texture("mouse.png");
        maya1 = new Texture("maya1.png");
        maya2 = new Texture("maya2.png");
        cheeseTexture = new Texture("cheese.png");
        maya3 = new Texture("maya3.png");

    }
    public Texture getTexture(Prototype prototype){
        switch (prototype){
            case MOUSE: return mouseTexture;
            case CHEESE:return cheeseTexture;
            case MAYA1: return maya1;
            case MAYA2: return maya2;
            case MAYA3: return maya3;
            default: throw new InvalidParameterException("Prototype = "+prototype);

        }
    }
}
