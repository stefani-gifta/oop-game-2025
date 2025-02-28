import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import Util.Randomizer;
import Util.Util;
import Game.Game;
import Item.Defensive;
import Item.Item;
import Item.Offensive;
import Item.Spell;
import Monster.Agility;
import Monster.Intelligence;
import Monster.Strength;
import User.Credential;

public class Main {
	
	Game game = new Game();
	
	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		readCredential();
		readItem();
		readMonster();
		
		/*
		for (Item item : game.itemList) {
			System.out.print(item.getID() + " " + item.getName() + " " + item.getPrice());
			if(item instanceof Offensive) {
				System.out.print(" -O- " + ((Offensive)item).getDamage() + " " + ((Offensive)item).getMaxUse() + " " + ((Offensive)item).getUseLeft() + "\n");
			}
			else if(item instanceof Defensive) {
				System.out.print(" -D- " + ((Defensive)item).getDeflect() + " " + ((Defensive)item).getMaxUse() + " " + ((Defensive)item).getUseLeft() + "\n");
			}
			else {
				System.out.print(" -S- " + ((Spell)item).getDamage() + " " + ((Spell)item).getMana() + "\n");
			}
		}
		
		System.out.println();
		
		int i = 0;
		for (Monster monster : game.monsterList) {
			System.out.println(i++ + ". " + monster.getName() + " " + monster.getDamage() + " " + monster.getHealth());
		}
		
		System.out.println();
		
		int index = (int) random(0, game.monsterList.size());
		System.out.println(index + ": " + game.monsterList.get(index).getName() + " " + game.monsterList.get(index).getDamage() + " " + game.monsterList.get(index).getHealth());
		
		System.out.println();
		
		for (Credential cred : game.playerList) {
			System.out.println(cred.getEmail() + " " + cred.getPassword() + " " + cred.getMoney());
			if(cred.getItemSaved() != null) System.out.println(cred.getItemSaved());
		}
		*/
		
		System.out.println("Welcome to DOTW ...");
		try {
            Thread.sleep(1000); // 1 second
        } catch (InterruptedException e) {
        	System.out.println("Loading failed");
            // e.printStackTrace();
        }
		Util.LOADING();
		Util.CLEAR_CONSOLE();
		
		int menuInput;
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Exit");
			System.out.print(">> ");
			menuInput = Util.scan.nextInt(); Util.scan.nextLine();

			Util.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					login();
					break;
				case 2:
					register();
					Util.CLEAR_CONSOLE();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while(menuInput != 3);
		
		exitAnimation();
	}

	private void login() {
		while(true) {
			System.out.println("Input 'Exit' to quit");
			
			System.out.print("Enter email: ");
	        String email = Util.scan.nextLine();
	        if(email.equalsIgnoreCase("exit")) {
	        	return;
	        }
	        System.out.print("Enter password: ");
	        String password = Util.scan.nextLine();
	        if(password.equalsIgnoreCase("exit")) {
	        	return;
	        }
	        
	        for(int i = 0; i < game.playerList.size(); i++) {
	        	if(email.equals(game.playerList.get(i).getEmail())) {
	        		if(password.equals(game.playerList.get(i).getPassword())) {
	        			if(game.playerList.get(i).getItemSaved() != null) searchItem(game.playerList.get(i).getItemSaved());
	        			game.playerNow = game.playerList.get(i);
	        			game.playerNow.setMoney(100);
	        			game.playerNow.setDamage(30);
	        			game.playerNow.setMana(30);
	        			game.playerNow.setHealth(300);
						Util.CLEAR_CONSOLE();
						printGamePage();
	        			return;
	        		}
	        	}
	        }
	        
	        System.out.println("Email or password unregistered");
		}
	}

	private void register() {
		while(true) {
			System.out.print("Enter new email: ");
	        String email = Util.scan.nextLine();
	
	        if(isValidEmail(email)) {
	            System.out.print("Enter new password: ");
	            String password = Util.scan.nextLine();
	
	            if(isValidPassword(password)) {
	            	updateCredential(email, password);
	                Credential newCredential = new Credential(email, password, 999, 0, 0, 0);
	                game.playerList.add(newCredential);
	                System.out.println("Registration successful!");
	    	        Util.PRESS_ENTER();
	                break;
	            } else {
	                System.out.println("Password length must be between 8 and 30 characters");
	            }
	        } else {
	            System.out.println("Email must be in the format 'email@domain.domain' (Example: hi@gmail.com)");
	        }
	        
	        Util.PRESS_ENTER();
		}
	}

	private boolean isValidPassword(String password) {
		return password.length() >= 8 && password.length() <= 30;
	}

	private boolean isValidEmail(String email) {
		return email.matches("[^@]+@[^@]+\\.[^@][^\\.]+");
	}
	
	private void printGamePage() {
		int menuInput;
		do {
			System.out.println("1. Start Game");
			System.out.println("2. Game Guide");
			System.out.println("3. Exit Game");
			System.out.print(">> ");
			menuInput = Util.scan.nextInt(); Util.scan.nextLine();

			Util.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					game.start();
					break;
				case 2:
					howToPlayPage();
					Util.CLEAR_CONSOLE();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while(menuInput != 3);
	}

	private void howToPlayPage() {
		System.out.println("The game is really simple. You move inside the game using general controls.");
		System.out.println("In the game, you can collect coins as you move. You can also meet enemies while going through the grass in the game.");
		System.out.println("If you have killed an enemy, you will be rewarded with money that you can use to buy items.\n");
		
		System.out.println("Character Informations\n");
		
        System.out.println("  Coin       : O");
        System.out.println("  Grass      : v | V");
        System.out.println("  Character  : X");
        System.out.println("  Wall       : #\n");

        System.out.println("Keybind Information\n");

        System.out.println("  w          : Move character up");
        System.out.println("  a          : Move character left");
        System.out.println("  s          : Move character down");
        System.out.println("  d          : Move character right");
        System.out.println("  i          : Show all character's item");
        System.out.println("  z          : Shop Menu");
        System.out.println("  e          : Exit game and save your information");
        
        Util.PRESS_ENTER();
	}

	private void readCredential() {
		Scanner scan = null;
		try {
			File items = new File("credential.txt");
			scan = new Scanner(items);
		} catch (Exception e) {
			System.out.printf("File credential.txt not found!");
			// e.printStackTrace();
		}
		try {
			while(scan.hasNextLine()) {
				String row = scan.nextLine();
				String[] split = row.split("#");
				
				Credential credential;
				if(split.length > 2) {
					credential = new Credential(split[0], split[1], Integer.parseInt(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), 30.00);
				}
				else { // new player
					credential = new Credential(split[0], split[1], 999, 0, 0, 0);
				}
				if(split.length > 5) {
//					System.out.println(split[5]);
					credential.setItemSaved(split[5]);
				}
				game.playerList.add(credential);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.close();
	}
	
	private void updateCredential(String email, String password) {
 		try {
 			BufferedWriter writer = new BufferedWriter(new FileWriter("credential.txt", true));
 			writer.write(email + "#" + password + "\n");
 			writer.close();
 		} catch (IOException e) {
 			System.out.println("Writing to file credential.txt failed");
 		}
	}
	
	private void searchItem(String string) {
		String[] items = string.split("-");
		for(String i : items) {
			String[] whichItem = i.split("@");
			for(Item j : game.itemList) {
				if(whichItem[0].equals(j.getID())) {
					game.itemBought.add(j);
					if(j instanceof Offensive) {
						((Offensive) game.itemBought.get(game.itemBought.size()-1)).setUseLeft(Integer.parseInt(whichItem[1]));
					}
					else if(j instanceof Defensive) {
						((Defensive) game.itemBought.get(game.itemBought.size()-1)).setUseLeft(Integer.parseInt(whichItem[1]));
					}
					else if(j instanceof Spell) {
						((Spell) game.itemBought.get(game.itemBought.size()-1)).setMana(Double.parseDouble(whichItem[1]));
					}
				}
			}
		}
	}

	private void readItem() {
		Scanner scan = null;
		try {
			File items = new File("item.txt");
			scan = new Scanner(items);
		} catch (Exception e) {
			System.out.printf("File item.txt not found!");
			// e.printStackTrace();
		}
		try {
			while(scan.hasNextLine()) {
				String row = scan.nextLine();
				String[] split = row.split("#");
				
				if(split[2].equals("offensive")) {
					Offensive offensive = new Offensive(split[0], split[1], Integer.parseInt(split[3]), Double.parseDouble(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[5]));
					game.itemList.add(offensive);
				}
				else if(split[2].equals("defensive")) {
					Defensive defensive = new Defensive(split[0], split[1], Integer.parseInt(split[3]), Double.parseDouble(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[5]));
					game.itemList.add(defensive);
				}
				else if(split[2].equals("spell")) {
					Spell spell = new Spell(split[0], split[1], Integer.parseInt(split[3]), Double.parseDouble(split[4]), Double.parseDouble(split[5]));
					game.itemList.add(spell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.close();
	}
	
	private void readMonster() {
		Scanner scan = null;
		try {
			File items = new File("monster.txt");
			scan = new Scanner(items);
		} catch (Exception e) {
			System.out.printf("File monster.txt not found!");
			// e.printStackTrace();
		}
		try {
			for(int i = 0; i < 3; i++) {
				storeMonster(i, scan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		scan.close();
	}

	private void storeMonster(Integer i, Scanner scan) {
		String row;
		String[] splitString;
		row = scan.nextLine();
		splitString = row.split("#");
		for (String string : splitString) {
			if(i == 0) {
				// Strength
				double damage = Randomizer.random(20, 30);
				double health = Randomizer.random(200, 210) - damage;
				double armor = Randomizer.random(20, 40);
				Strength strength = new Strength(string, damage, health);
				strength.setArmor(armor);
				game.monsterList.add(strength);
			}
			else if(i == 1) {
				// Intelligence
				double damage = Randomizer.random(10, 20);
				double health = Randomizer.random(100, 110);
				Intelligence intelligence = new Intelligence(string, damage, health);
				game.monsterList.add(intelligence);
			}
			else {
				// Agility
				double damage = Randomizer.random(40, 50);
				double health = Randomizer.random(100, 110) - damage;
				double critical = Randomizer.random(1, 3);
				Agility agility = new Agility(string, damage, health);
				agility.setCritical(critical);
				game.monsterList.add(agility);
			}
		}
	}
	
}
