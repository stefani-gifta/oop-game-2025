package Game;

import java.util.ArrayList;

import Item.*;
import Monster.*;
import User.Credential;
import Util.IO;

public class Game {

	private ArrayList<Item> itemList;
	private ArrayList<Monster> monsterList;
	
	private Credential playerNow;

	public Game(Credential player) {
		// register items and monsters
		new AddingItem();
		itemList = AddingItem.getItemList();
		new AddingMonster();
		monsterList = AddingMonster.getMonsterList();

		// remember player who's playing
		playerNow = player;

		// print game page
		IO.CLEAR_CONSOLE();
		new GameMenu(this);
	}

	public Credential getPlayerNow() {
		return playerNow;
	}

	public ArrayList<Item> getItemList() {
		return itemList;
	}

	public ArrayList<Monster> getMonsterList() {
		return monsterList;
	}
	
}
