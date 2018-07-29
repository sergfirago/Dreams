package com.firago.serg.logic;

import java.util.ArrayList;
import java.util.List;

public class Desktop {
    private List<Item> items;

    public Desktop() {
        items = new ArrayList<Item>();
    }

    public List<Item> getItems() {
        return items;
    }
    public void addItem(Item item){
        items.add(item);
    }
}
