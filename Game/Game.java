package Game;

import java.util.ArrayList;

import Item.Defensive;
import Item.Item;
import Item.Offensive;
import Item.Spell;
import Monster.Agility;
import Monster.Intelligence;
import Monster.Monster;
import Monster.Strength;
import User.Credential;
import Util.Randomizer;
import Util.IO;

public class Game {
	
	public ArrayList<Item> itemList = new ArrayList<Item>();
	public ArrayList<Monster> monsterList = new ArrayList<Monster>();
	public ArrayList<Credential> playerList = new ArrayList<Credential>();
	
	public Credential playerNow;
	public ArrayList<Item> itemBought = new ArrayList<Item>();
	
	Move move = new Move();
	Map map = new Map();
	
	char[][] bigMap = map.generateFilledBigMap(); // 300x300
	
	public void start() {
		do {
			move.setX(Randomizer.randomInt(0, 299-35));
			move.setY(Randomizer.randomInt(0, 299-15));
//			move.setX(265);
//			move.setY(285);
		} while(bigMap[move.getY()][move.getX()] != ' ');
		
		char inputMove = '0';
		do {
			showPortionofMap();
			
			System.out.println();
			
			System.out.println("Player " + playerNow.getEmail() + " Information");
			System.out.println("Health\t\t: " + playerNow.getHealth());
			System.out.println("Money\t\t: " + playerNow.getMoney());
			System.out.println("Mana\t\t: " + playerNow.getMana());
			System.out.println("Base Damage\t: " + playerNow.getDamage());
			System.out.print(">> ");
			
//			System.out.println("\n" + move.getX() + " " + move.getY());
			
			inputMove = Util.scan.next().charAt(0); IO.scan.nextLine();
			
			switch(String.valueOf(inputMove).toLowerCase()) {
				case "i":
					printItemBought();
					break;
				case "z":
					printShopPage();
					break;
				case "e":
					if(playerNow.getHealth() <= 0) {
						losePage();
					}
					else {
						System.out.println("Are you sure? If you exit now, your information will be lost.");
						System.out.print("Yes | No [Case Insensitive]: ");
						String yesno = new String();
						do {
							yesno = IO.scan.nextLine();
							if(yesno.equalsIgnoreCase("yes")) {
								IO.CLEAR_CONSOLE();
								return;
							}
							else if(!yesno.equalsIgnoreCase("no")) {
								System.out.print("Please input Yes | No [Case Insensitive]: ");
							}
						} while(!yesno.equalsIgnoreCase("no"));
					}
					break;
				case "w":
					checkMap(move.getX(), move.getY()-1);
					break;
				case "a":
					checkMap(move.getX()-1, move.getY());
					break;
				case "s":
					checkMap(move.getX(), move.getY()+1);
					break;
				case "d":
					checkMap(move.getX()+1, move.getY());
					break;
				default:
					System.out.println("Invalid input! Please input [w | a | s | d | i | z | e] (case insensitive)");
					break;
			}
			
//			System.out.println("\n" + move.getX() + " " + move.getY());

			IO.CLEAR_CONSOLE();
		} while(inputMove != 'e' || playerNow.getHealth() > 0);
	}

	private void losePage() {
		IO.CLEAR_CONSOLE();
		System.out.println("Sorry, you've lost:( Better luck next time\n");
		IO.PRESS_ENTER();
	}

	public void showPortionofMap() {
		for(int i = move.getY(); i < move.getY()+15; i++) {
			for(int j = move.getX(); j < move.getX()+35; j++) {
				try {
					if(i == move.getY()+7 && j == move.getX()+17) {
						System.out.print('X');
					}
					else System.out.print(bigMap[i][j]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("\nYou've reached the end of the world!");
					System.out.println("Go back!");
					return;
				}
			}
			System.out.println();
		}
	}

	private void checkMap(int newX, int newY) {
//		System.out.println("new x: " + newX + " new y: " + newY);
		
		if(newX < 0 || newY < 0) {
			return;
		}
		else if(newX >= 300 || newY >= 300) {
			return;
		}
		else if(bigMap[newY+7][newX+17] == '#') {
			return;
		}
		else if(bigMap[newY+7][newX+17] == 'O') {
			bigMap[newY+7][newX+17] = ' ';
			System.out.print(playerNow.getMoney());
			playerNow.setMoney(playerNow.getMoney() + 5);
			System.out.println(" but just got a coin. +5 money, now: " + playerNow.getMoney());
		}
		else if(bigMap[newY+7][newX+17] == 'v' || bigMap[newY+7][newX+17] == 'V') {
			whatsInTheGrass();
			if(playerNow.getHealth() <= 0) {
				return;
			}
		}
		
		move.setX(newX);
		move.setY(newY);
	}

	private void showAttributesInformation(Monster monster) {
		System.out.println("Player " + playerNow.getEmail() + " Information");
		System.out.println("Health\t\t: " + playerNow.getHealth());
		System.out.println("Money\t\t: " + playerNow.getMoney());
		System.out.println("Mana\t\t: " + playerNow.getMana());
		System.out.println("Base Damage\t: " + playerNow.getDamage());
		
		System.out.println();
		
		System.out.println("Monster " + monster.getName() + " Information");
		System.out.println("Health\t\t: " + monster.getHealth());
		System.out.println("Base Damage\t: " + monster.getDamage());
		
		System.out.println();
	}

	private void printItemBought() {
		if(itemBought.isEmpty()) {
			System.out.println("You haven't bought any item yet. Go to shop menu (Z) to buy.");
			IO.PRESS_ENTER();
			return;
		}
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-12s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use/Mana", "Use Left");
		for(Item bought : itemBought) {
			if(bought instanceof Offensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-12d | %-10d |\n",
						bought.getID(), bought.getName(), "Offensive", bought.getPrice(),
						((Offensive) bought).getDamage(), ((Offensive) bought).getMaxUse(), ((Offensive) bought).getUseLeft());
			}
			else if(bought instanceof Defensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-12d | %-10d |\n",
						bought.getID(), bought.getName(), "Defensive", bought.getPrice(),
						((Defensive) bought).getDeflect(), ((Defensive) bought).getMaxUse(), ((Defensive) bought).getUseLeft());
			}
			else if(bought instanceof Spell) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-12f | %-10s |\n",
						bought.getID(), bought.getName(), "Spell", bought.getPrice(),
						((Spell) bought).getDamage(), ((Spell) bought).getMana(), "-");
			}
		}
		IO.PRESS_ENTER();
	}

	private void showOffensivesAndSpellsBought() {
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-12s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use/Mana", "Use Left");
		for(Item bought : itemBought) {
			if(bought instanceof Offensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-12d | %-10d |\n",
						bought.getID(), bought.getName(), "Offensive", bought.getPrice(),
						((Offensive) bought).getDamage(), ((Offensive) bought).getMaxUse(), ((Offensive) bought).getUseLeft());
			}
			else if(bought instanceof Spell) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-12f | %-10s |\n",
						bought.getID(), bought.getName(), "Spell", bought.getPrice(),
						((Spell) bought).getDamage(), ((Spell) bought).getMana(), "-");
			}
		}
	}

	private void showDefensivesBought() {
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use", "Use Left");
		for(Item bought : itemBought) {
			if(bought instanceof Defensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d | %-10d |\n",
						bought.getID(), bought.getName(), "Defensive", bought.getPrice(),
						((Defensive) bought).getDeflect(), ((Defensive) bought).getMaxUse(), ((Defensive) bought).getUseLeft());
			}
		}
	}

	private void printShopPage() {
		IO.CLEAR_CONSOLE();
		int menuInput;
		do {
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
		System.out.println("Your money now is " + playerNow.getMoney());
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use");
		for(Item bought : itemList) {
			if(bought instanceof Offensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d |\n",
						bought.getID(), bought.getName(), "Offensive", bought.getPrice(),
						((Offensive) bought).getDamage(), ((Offensive) bought).getMaxUse());
			}
		}
		System.out.println();
		String ID = new String();
		inputItemIDtoBuy(ID);
	}

	private void buyDefensiveItem() {
		System.out.println("Your money now is " + playerNow.getMoney());
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Deflect", "Max Use");
		for(Item bought : itemList) {
			if(bought instanceof Defensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d |\n",
						bought.getID(), bought.getName(), "Defensive", bought.getPrice(),
						((Defensive) bought).getDeflect(), ((Defensive) bought).getMaxUse());
			}
		}
		System.out.println();
		String ID = new String();
		inputItemIDtoBuy(ID);
	}

	private void buySpellItem() {
		System.out.println("Your money now is " + playerNow.getMoney());
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Mana");
		for(Item bought : itemList) {
			if(bought instanceof Spell) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10f |\n",
						bought.getID(), bought.getName(), "Spell", bought.getPrice(),
						((Spell) bought).getDamage(), ((Spell) bought).getMana());
			}
		}
		System.out.println();
		String ID = new String();
		inputItemIDtoBuy(ID);
	}

	private void inputItemIDtoBuy(String ID) {
		boolean found = false;
		Item toBuy = new Item(ID, "", 0);
		do {
			System.out.print("Input item's ID ['Exit' to cancel]: ");
			ID = IO.scan.nextLine();
			if(ID.equalsIgnoreCase("exit")) {
				return;
			}
			for(Item bought : itemList) {
				if(ID.equals(bought.getID())) {
					found = true;
					toBuy = bought;
					break;
				}
			}
			if(!found) System.out.println("Item not found!");
		} while(!found);
		
		if(playerNow.getMoney() < toBuy.getPrice()) {
			System.out.println("You don't have enough money!");
			IO.PRESS_ENTER();
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
		itemBought.add(toBuy);
		System.out.println("Item added to inventory");
		IO.PRESS_ENTER();
	}
	
	private void whatsInTheGrass() {
		int monster = Randomizer.randomInt(0, 100);
		if(monster < 30) {
			IO.CLEAR_CONSOLE();
			System.out.println("Welcome to the Fight Arena");
			IO.PRESS_ENTER();
			goToArena();
		}
	}

	private void goToArena() {
		// generate random monster
		int monsterInd = Randomizer.randomInt(0, monsterList.size()-1);
		Monster monster = monsterList.get(monsterInd);
		// randomize turn
		int turn = Randomizer.randomInt(1, 2);
		switch(turn) {
			case 1:
				// player first
				while(playerNow.getHealth() > 0 && monster.getHealth() > 0) {
					showAttributesInformation(monster);
					if(playerNow.getHealth() > 0) playerTurntoFight(monster);
					else return;
					showAttributesInformation(monster);
					if(monster.getHealth() > 0) monsterTurntoFight(monster);
				}
				break;
			case 2:
				// monster first
				while(playerNow.getHealth() > 0 && monster.getHealth() > 0) {
					showAttributesInformation(monster);
					if(monster.getHealth() > 0) monsterTurntoFight(monster);
					else return;
					showAttributesInformation(monster);
					if(playerNow.getHealth() > 0) playerTurntoFight(monster);
				}
				break;
		}
		if(monster.getHealth() <= 0) {
			System.out.println("Monster killed!");
			int moneyGotten = Randomizer.randomInt(0, 50);
			playerNow.setMoney(playerNow.getMoney() + moneyGotten);
		}
		IO.PRESS_ENTER();
	}

	private void playerTurntoFight(Monster monster) {
		int input = -1;
		do {
			System.out.println("1. Pure Attack");
			System.out.println("2. Attack with Item");
			System.out.println("3. Store Mana");
			System.out.print("Input action to take >> ");
			input = IO.scan.nextInt(); IO.scan.nextLine();
			if(input < 1 || input > 3) {
				System.out.println("You can only input 1, 2, or 3");
			}
		} while(input < 1 || input > 3);
		switch(input) {
			case 1:
				System.out.println("Attacking " + monster.getName());
				System.out.println(monster.getName() + " got damage " + playerNow.getDamage());
				monster.setHealth(monster.getHealth() - playerNow.getDamage());
				break;
			case 2:
				showOffensivesAndSpellsBought();
				System.out.println("Please choose an item");
				Item use = inputItemIDtoUsetoAttack();
				double damage = playerNow.getDamage();
				if(use != null) {
					// check item type
					if(use instanceof Offensive) {
						if(((Offensive)use).checkValidity()) {
							System.out.println("Attacking " + monster.getName() + " with " + use.getName());
							((Offensive)use).setUseLeft(((Offensive)use).getUseLeft() - 1);
							System.out.println(use.getName() + " used. " + ((Offensive)use).getUseLeft() + " left to use this item.\n");
							
							damage += ((Offensive)use).addDamage(damage);
						}
						else {
							System.out.println("No use left! Use pure attack instead");
							System.out.println("Attacking " + monster.getName());
							System.out.println(monster.getName() + " got damage " + playerNow.getDamage());
							monster.setHealth(monster.getHealth() - playerNow.getDamage());
						}
					}
					else if(use instanceof Spell) {
						if(playerNow.getMana() >= ((Spell)use).getMana()) {
							System.out.println("Attacking " + monster.getName() + " with " + use.getName());
							System.out.println(use.getName() + " used.\n");
							
							damage += ((Spell)use).addDamage(damage);
							playerNow.setMana(playerNow.getMana() - ((Spell)use).getMana());
						}
						else {
							System.out.println("Not enough mana to use item! Use pure attack instead");
							System.out.println("Attacking " + monster.getName());
							System.out.println(monster.getName() + " got damage " + playerNow.getDamage());
							monster.setHealth(monster.getHealth() - playerNow.getDamage());
						}
					}
					// check monster's special ability
					if(monster instanceof Strength) {
						damage = ((Strength)monster).deflectDamage(damage);
						System.out.println(monster.getName() + " has armor to deflect your damage");
					}
					monster.setHealth(monster.getHealth() - damage);
					System.out.println(monster.getName() + " got " + damage + " of damage");
					monster.setHealth(monster.getHealth() - damage);
				}
				break;
			case 3:
				System.out.println("Storing mana ...");
				playerNow.setMana(playerNow.getMana() + 10);
				System.out.println("Added 10.00 mana");
				break;
		}
		IO.PRESS_ENTER();
	}

	private Item inputItemIDtoUsetoAttack() {
		if(!itemBought.isEmpty()) {
			String ID = new String();
			boolean found = false;
			do {
				System.out.print("Input item's ID : ");
				ID = IO.scan.nextLine();
				for(Item bought : itemBought) {
					if(ID.equals(bought.getID())) {
						found = true;
						return bought;
					}
				}
			} while(!found);
		}
		System.out.println("You have not bought any yet!");
		return null;
	}

	private void monsterTurntoFight(Monster monster) {
		System.out.println("Monster is going to attack");
		Item use = new Item("", "", 0);
		for(Item bought : itemBought) {
			if(bought instanceof Defensive) {
				System.out.println("Do you want to use your defensive item?");
				showDefensivesBought();
				String yesno = new String();
				System.out.print("Yes | No [Case Insensitive]: ");
				do {
					yesno = IO.scan.nextLine();
					if(yesno.equalsIgnoreCase("yes")) {
						use = inputItemIDtoUsetoDeflect();
						break;
					}
					else if(!yesno.equalsIgnoreCase("no")) {
						System.out.print("Please input Yes | No [Case Insensitive]: ");
					}
				} while(!yesno.equalsIgnoreCase("no"));
				break;
			}
		}
		double damage = monster.getDamage();
		System.out.println(monster.getName() + " is attacking with a base damage of " + monster.getDamage());
		System.out.print(monster.getName() + " is a type of " + monster.getClass().getSimpleName() + " hero, ");
		// check monster's special abilities
		if(monster instanceof Intelligence) {
			double addition = ((Intelligence)monster).addDamage();
			System.out.println("using skill gave bonus damage " + addition);
			damage += addition;
		}
		else if(monster instanceof Agility) {
			System.out.println("it has a critical damage of " + ((Agility)monster).getCritical());
			damage += ((Agility)monster).getCritical();
		}
		else {
			System.out.println("it has no special ability");
		}
		System.out.println();
		// check item bought
		if(use != null) {
			if(use instanceof Defensive) {
				if(((Defensive)use).checkValidity()) {
					System.out.println(use.getName() + " was equipped");
					((Defensive)use).setUseLeft(((Defensive)use).getUseLeft() - 1);
					System.out.println(use.getName() + " used. " + ((Defensive)use).getUseLeft() + " left to use this item.\n");
					
					System.out.println("Received damage " + damage + ", but deflected " + ((Defensive)use).getDeflect() + " using " + ((Defensive)use).getName());
					damage = ((Defensive)use).deflectDamage(damage);
				}
				else {
					System.out.println("No use left!");
				}
			}
		}
		else {
			System.out.println("Received damage " + damage);
		}
		if(damage > 0) playerNow.setHealth(playerNow.getHealth() - damage);
		IO.PRESS_ENTER();
	}

	private Item inputItemIDtoUsetoDeflect() {
		String ID = new String();
		System.out.print("Input item's ID : ");
		ID = IO.scan.nextLine();
		for(Item bought : itemBought) {
			if(ID.equals(bought.getID())) {
				return bought;
			}
		}
		return null;
	}
	
}
