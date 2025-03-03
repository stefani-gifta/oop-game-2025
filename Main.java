import java.util.ArrayList;
import java.util.Arrays;

import Util.Randomizer;
import Util.Util;
import Game.Game;
import Item.Defensive;
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
		addItem();
		addMonster();
		
		int menuInput;
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Update Info");
			System.out.println("4. Exit");
			System.out.print(">> ");
			menuInput = Util.scan.nextInt(); Util.scan.nextLine();

			Util.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					login();
					Util.CLEAR_CONSOLE();
					break;
				case 2:
					register();
					Util.CLEAR_CONSOLE();
					break;
				case 3:
					view();
					Util.CLEAR_CONSOLE();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while(menuInput != 4);
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
	        
	        System.out.println("Email or password unregistered\n");
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
	                Credential newCredential = new Credential(email, password, 999, 0, 0, 0);
	                game.playerList.add(newCredential);
	                System.out.println("Registration successful. You can login now.");
	    	        Util.PRESS_ENTER();
	                break;
	            } else {
	                System.out.println("Password length must be between 8 and 30 characters");
	            }
	        } else {
	            System.out.println("Email must be in the format 'email@domain.domain' (Example: hi@email.com)");
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

	private void view() {
		if(game.playerList.isEmpty()) {
			System.out.println("No account registered yet!");
			Util.PRESS_ENTER();
			return;
		}
		
		System.out.println("Pick the number of your account (0 to cancel):");
		for(int i = 0; i < game.playerList.size(); i++) {
			System.out.println(i+1 + ". " + game.playerList.get(i).getEmail());
		}
		int accountNumber = -1;
		do {
			System.out.print(">> ");
			accountNumber = Util.scan.nextInt(); Util.scan.nextLine();
			if(accountNumber < 0 || accountNumber > game.playerList.size()) {
				System.out.println("Invalid input!");
			} else if(accountNumber == 0) {
				return;
			}
		} while(accountNumber < 0 || accountNumber > game.playerList.size());
		
		Credential accountPicked = game.playerList.get(accountNumber - 1);
		String password;
		do {
			System.out.print("Enter password: ");
			password = Util.scan.nextLine();
			if(password.equals(accountPicked.getPassword())) {
				do {
					System.out.print("Enter new password: ");
					password = Util.scan.nextLine();
					if(isValidPassword(password)) {
						accountPicked.setPassword(password);
						System.out.println("Account updated!");
						Util.PRESS_ENTER();
						return;
					} else {
						System.out.println("Password length must be between 8 and 30 characters");
					}
				} while(!isValidPassword(password));
			} else {
				System.out.print("Wrong password! Forget password? Type 0 to exit (or 1 to retry): ");
				accountNumber = Util.scan.nextInt(); Util.scan.nextLine();
				if(accountNumber == 0 ) {
					return;
				}
			}
		} while(!password.equals(accountPicked.getPassword()));
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
		System.out.println("Welcome to the adventure!");
		System.out.println("The rules are simple. Simply move around using the usual controls (W, A, S, D).");
		System.out.println("As you roam, you’ll find coins (O) waiting to be collected.");
		System.out.println("But watch out! Lurking in the grass might be monsters ready to challenge you.");
		System.out.println("Defeat those monsters, and you'll earn some coins which you can use to buy cool items!");
		System.out.println("So, are you ready to dive in?\n");
		
		System.out.println("Symbols Information\n");
		
        System.out.println("  Coin       : O");
        System.out.println("  Grass      : v | V");
        System.out.println("  Character  : X");
        System.out.println("  Wall       : #\n");

        System.out.println("Key Information\n");

        System.out.println("  w          : Move character up");
        System.out.println("  a          : Move character left");
        System.out.println("  s          : Move character down");
        System.out.println("  d          : Move character right");
        System.out.println("  i          : Show items you've bought");
        System.out.println("  z          : Open shop to buy items");
        System.out.println("  e          : Exit game");
        
        Util.PRESS_ENTER();
	}

	private void addItem() {
        game.itemList.add(new Offensive("BTLR", "Battle Fury", 91, 100, 6, 6));
        game.itemList.add(new Offensive("UTMTOB", "Ultimate Orb", 77, 50, 8, 8));
        game.itemList.add(new Offensive("WRHBND", "Wraith Band", 5, 3, 9, 9));
        game.itemList.add(new Offensive("VNDCT", "Vindicator's", 20, 15, 2, 2));
        game.itemList.add(new Offensive("SHDWBLD", "Shadow Blade", 21, 15, 5, 5));
        
        game.itemList.add(new Defensive("STNIC", "Satanic", 42, 30, 3, 3));
        game.itemList.add(new Defensive("SCRC", "Sacred Relic", 18, 15, 4, 4));
        game.itemList.add(new Defensive("URNSHDW", "Urn Of Shadows", 27, 21, 6, 6));
        game.itemList.add(new Defensive("STTSHLD", "Stout Shield", 23, 24, 2, 2));
        game.itemList.add(new Defensive("VNGRD", "Vanguard", 27, 20, 4, 4));
        
        game.itemList.add(new Spell("RBOOCH", "Revenants Brooch", 64, 50, 7));
        game.itemList.add(new Spell("MYSTF", "Mystic Staff", 36, 30, 5));
        game.itemList.add(new Spell("WCTHBLD", "Witch Blade", 7, 5, 2));
        game.itemList.add(new Spell("TMLRLC", "Timeless Relic", 30, 20, 3));
        game.itemList.add(new Spell("TRDNT", "Trident", 22, 15, 1));
	}
	
	private void addMonster() {
		ArrayList<String> Strength = new ArrayList<>(Arrays.asList("Earthshaker", "Tiny", "Biggy", "Beastmaster", "Dragon Knight"));
		for (String string : Strength) {
			double damage = Randomizer.random(20, 30);
			double health = Randomizer.random(200, 210);
			double armor = Randomizer.random(20, 40);
			Strength strength = new Strength(string, damage, health);
			strength.setArmor(armor);
			game.monsterList.add(strength);
		}
		
		ArrayList<String> Intelligence = new ArrayList<>(Arrays.asList("Enchantress", "Tinker", "Zeus", "Nature's Prophet", "Crystal Maiden"));
		for (String string : Intelligence) {
			double damage = Randomizer.random(10, 20);
			double health = Randomizer.random(100, 110);
			Intelligence intelligence = new Intelligence(string, damage, health);
			game.monsterList.add(intelligence);
		}
		
		ArrayList<String> Agility = new ArrayList<>(Arrays.asList("Juggernaut", "Sniper", "Vengeful Spirit", "Phantom Lancer", "Firanha"));
		for (String string : Agility) {
			double damage = Randomizer.random(40, 50);
			double health = Randomizer.random(100, 110) - damage;
			double critical = Randomizer.random(1, 3);
			Agility agility = new Agility(string, damage, health);
			agility.setCritical(critical);
			game.monsterList.add(agility);
		}
	}
	
}
