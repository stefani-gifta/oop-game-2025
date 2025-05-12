package Item;

import java.util.ArrayList;

public class AddingItem {

    private static ArrayList<Item> itemList = new ArrayList<>();

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public AddingItem() {
        addOffensiveItem();
        addDefensiveItem();
        addSpellItem();
    }

    private static void addOffensiveItem() {
		itemList.add(new Offensive("BTLR", "Battle Fury", 91, 100, 6, 6));
        itemList.add(new Offensive("UTMTOB", "Ultimate Orb", 77, 50, 8, 8));
        itemList.add(new Offensive("WRHBND", "Wraith Band", 5, 3, 9, 9));
        itemList.add(new Offensive("VNDCT", "Vindicator's", 20, 15, 2, 2));
        itemList.add(new Offensive("SHDWBLD", "Shadow Blade", 21, 15, 5, 5));
    }

    private static void addDefensiveItem() {
		itemList.add(new Defensive("STNIC", "Satanic", 42, 30, 3, 3));
        itemList.add(new Defensive("SCRC", "Sacred Relic", 18, 15, 4, 4));
        itemList.add(new Defensive("URNSHDW", "Urn Of Shadows", 27, 21, 6, 6));
        itemList.add(new Defensive("STTSHLD", "Stout Shield", 23, 24, 2, 2));
        itemList.add(new Defensive("VNGRD", "Vanguard", 27, 20, 4, 4));
    }

    private static void addSpellItem() {
		itemList.add(new Spell("RBOOCH", "Revenants Brooch", 64, 50, 7));
        itemList.add(new Spell("MYSTF", "Mystic Staff", 36, 30, 5));
        itemList.add(new Spell("WCTHBLD", "Witch Blade", 7, 5, 2));
        itemList.add(new Spell("TMLRLC", "Timeless Relic", 30, 20, 3));
        itemList.add(new Spell("TRDNT", "Trident", 22, 15, 1));
	}

}
