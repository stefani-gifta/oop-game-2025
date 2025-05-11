import java.util.ArrayList;
import java.util.Arrays;

import Util.*;
import Game.Game;
import Item.*;
import Monster.*;
import User.Credential;

public class Main {
	
	Game game = new Game();
	
	public static void main(String[] args) {
		// register items and monsters
		AddingItem();
		AddingMonster();
		
		// print main menu
		printMainMenu();
	}

	private static void printMainMenu() {
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

	private static void login() {
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

	private static void register() {
		while(true) {
			System.out.print("Enter new email: ");
	        String email = Util.scan.nextLine();
	
	        if(RegisterValidation.isValidEmail(email)) {
	            System.out.print("Enter new password: ");
	            String password = Util.scan.nextLine();
	
	            if(RegisterValidation.isValidPassword(password)) {
	                Credential newCredential = new Credential(email, password, 999, 0, 0, 0);
	                game.playerList.add(newCredential);
	                System.out.println("Registration successful. You can login now.");
	    	        Util.PRESS_ENTER();
	                break;
	            }
	        }
	        
	        Util.PRESS_ENTER();
		}
	}

	private static void view() {
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
					if(RegisterValidation.isValidPassword(password)) {
						accountPicked.setPassword(password);
						System.out.println("Account updated!");
						Util.PRESS_ENTER();
						return;
					}
				} while(!RegisterValidation.isValidPassword(password));
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
		System.out.println("As you roam, you�ll find coins (O) waiting to be collected.");
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

}
