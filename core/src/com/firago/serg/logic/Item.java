package com.firago.serg.logic;

public class Item {
    private final Prototype prototype;
    private final ItemState itemState;
    public Item(Prototype prototype) {
        this.prototype = prototype;
        itemState = new ItemState();
    }

    public ItemState getItemState() {
        return itemState;
    }

    public Prototype getPrototype() {
        return prototype;
    }
}
