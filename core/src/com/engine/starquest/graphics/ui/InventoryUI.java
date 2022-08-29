package com.engine.starquest.graphics.ui;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.engine.starquest.Utils.Assets;
import com.engine.starquest.items.Item;

//muss noch stark dran gearbeitet werden
public class InventoryUI extends Window {
    List<Item> itemList;

    public InventoryUI() {
        super("Inventory", Assets.UI.UI_SKIN, "default");
        itemList = new ArrayList<>();
    }

}
