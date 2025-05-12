package Game;

import java.util.ArrayList;

import Item.*;
import Monster.*;
import User.Credential;
import Util.IO;

public class Game {

	public ArrayList<Item> itemList;
	public ArrayList<Monster> monsterList;
	
	public Credential playerNow;

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
	
}
