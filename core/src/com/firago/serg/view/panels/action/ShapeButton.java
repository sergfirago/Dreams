package com.firago.serg.view.panels.action;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.firago.serg.prototypes.Prototype;
import com.firago.serg.prototypes.PrototypeTextureProvider;

public class ShapeButton extends Image {
    public interface ClickListener {
        void onClick(Prototype prototype);
    }


    private final Prototype prototype;
    private final ClickListener listener;
    public ShapeButton(final Prototype prototype, final ClickListener listener) {
        super(PrototypeTextureProvider.getInstance().getTexture(prototype));
        this.prototype = prototype;
        this.listener = listener;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                listener.onClick(prototype);
                return true;
            }
        });
    }


}
