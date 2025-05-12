package Game;

import java.util.ArrayList;

import Item.Defensive;
import Item.Item;
import Item.Offensive;
import Item.Spell;
import User.Credential;
import Util.*;

public class Shop {

	private Credential playerNow;
	private ArrayList<Item> itemList;
	private ArrayList<Item> itemBought;

    public Shop(Gameplay gameplay) {
		playerNow = gameplay.playerNow;
		itemList = gameplay.game.getItemList();
		itemBought = playerNow.getItemBought();
    }

    public void printShopPage() {
		IO.CLEAR_CONSOLE();
		int menuInput;
		do {
            System.out.println("Let's buy some items!");
		    System.out.println("You have " + playerNow.getMoney() + " coins now.\n");
			System.out.println("1. Buy Offensive Item");
			System.out.println("2. Buy Defensive Item");
			System.out.println("3. Buy Spell Item");
			System.out.println("4. Exit Shop");
			System.out.print(">> ");
			menuInput = IO.scan.nextInt(); IO.scan.nextLine();

			IO.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					buyOffensiveItem();
					break;
				case 2:
					buyDefensiveItem();
					break;
				case 3:
					buySpellItem();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while(menuInput != 4);
	}

	private void buyOffensiveItem() {
        // has damage, max use
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use");
		for(Item bought : itemList) {
			if(bought instanceof Offensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d |\n",
						bought.getID(), bought.getName(), "Offensive", bought.getPrice(),
						((Offensive) bought).getDamage(), ((Offensive) bought).getMaxUse());
			}
		}
		System.out.println();
		inputItemIDtoBuy();
	}

	private void buyDefensiveItem() {
        // has deflect, max use
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Deflect", "Max Use");
		for(Item bought : itemList) {
			if(bought instanceof Defensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d |\n",
						bought.getID(), bought.getName(), "Defensive", bought.getPrice(),
						((Defensive) bought).getDeflect(), ((Defensive) bought).getMaxUse());
			}
		}
		System.out.println();
		inputItemIDtoBuy();
	}

	private void buySpellItem() {
        // has damage, mana
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Mana");
		for(Item bought : itemList) {
			if(bought instanceof Spell) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10f |\n",
						bought.getID(), bought.getName(), "Spell", bought.getPrice(),
						((Spell) bought).getDamage(), ((Spell) bought).getMana());
			}
		}
		System.out.println();
		inputItemIDtoBuy();
	}

	private void inputItemIDtoBuy() {
		Item toBuy = chooseItemToBuy();

		if(toBuy == null) {
			return;
		}

		if(!checkIfMoneyEnough(toBuy)) {
			return;
		}
		
		for(Item alreadyBought : itemBought) {
			if(toBuy.getID().equals(alreadyBought.getID())) {
				if(toBuy instanceof Spell)
					System.out.println("You already have this Spell item");
				else {
					System.out.print("You already have this item and ");
					if(toBuy instanceof Offensive) {
						System.out.print("it is offensive type.");
						((Offensive)alreadyBought).setMaxUse(((Offensive)toBuy).getMaxUse());
					}
					else if(toBuy instanceof Defensive) {
						System.out.print("it is defensive type.");
						((Defensive)alreadyBought).setMaxUse(((Defensive)toBuy).getMaxUse());
					}
					System.out.print(" Resetting item's use amount\n");
				}
				break;
			}
		}
		playerNow.setMoney(playerNow.getMoney() - toBuy.getPrice());

		playerNow.addItemBought(toBuy);
		System.out.println("Item added to inventory");

		IO.PRESS_ENTER();
	}

	private Item chooseItemToBuy() {
		boolean found = false;
		do {
			System.out.print("Input item's ID ['Exit' to cancel]: ");
			String ID = IO.scan.nextLine();
			if(!ID.equalsIgnoreCase("exit")) {
				for(Item bought : itemBought) {
					if(ID.equals(bought.getID())) {
						found = true;
						return bought;
					}
				}
			} else {
				IO.CLEAR_CONSOLE();
				return null;
			}
			if(!found) System.out.println("Item not found!");
		} while(!found);
		return null;
	}

	private boolean checkIfMoneyEnough(Item toBuy) {
		if(playerNow.getMoney() < toBuy.getPrice()) {
			System.out.println("You don't have enough money!");
			IO.PRESS_ENTER();
			return false;
		}
		return true;
	}

}
