package User;

import Util.*;

import java.util.ArrayList;

import Game.Game;

public class Authorization {

	private ArrayList<Credential> playerList = Credential.getPlayerList();

    public void login() {
		while(true) {
			System.out.println("Input 'Exit' to quit");
			
			System.out.print("Enter email: ");
	        String email = IO.scan.nextLine();
	        if(email.equalsIgnoreCase("exit")) {
	        	return;
	        }
	        System.out.print("Enter password: ");
	        String password = IO.scan.nextLine();
	        if(password.equalsIgnoreCase("exit")) {
	        	return;
	        }
	        
            // check if email and password are valid
	        for(int i = 0; i < playerList.size(); i++) {
	        	if(email.equals(playerList.get(i).getEmail())) {
	        		if(password.equals(playerList.get(i).getPassword())) {
                        // get into the game
                        new Game(playerList.get(i));
                        return;
	        		}
	        	}
	        }
	        
            // if email and password are not valid
	        System.out.println("Email or password unregistered. Please register first.");
	    	IO.PRESS_ENTER();
		}
	}

	public void register() {
		while(true) {
			System.out.print("Enter new email: ");
	        String email = IO.scan.nextLine();
	
	        if(RegisterValidation.isValidEmail(email)) {
	            System.out.print("Enter new password: ");
	            String password = IO.scan.nextLine();
	
	            if(RegisterValidation.isValidPassword(password)) {
	                Credential newCredential = new Credential(email, password);
	                playerList.add(newCredential);
	                System.out.println("Registration successful. You can login now.");
	    	        IO.PRESS_ENTER();
	                break;
	            }
	        }
	        
	        IO.PRESS_ENTER();
		}
	}

	public void changePassword() {
		if(playerList.isEmpty()) {
			System.out.println("No account registered yet!");
			IO.PRESS_ENTER();
			return;
		}
		
		System.out.println("Pick the number of your account (0 to cancel):");
		for(int i = 0; i < playerList.size(); i++) {
			System.out.println(i+1 + ". " + playerList.get(i).getEmail());
		}
		int accountNumber = -1;
		do {
			System.out.print(">> ");
			accountNumber = IO.scan.nextInt(); IO.scan.nextLine();
			if(accountNumber < 0 || accountNumber > playerList.size()) {
				System.out.println("Invalid input!");
			} else if(accountNumber == 0) {
				return;
			}
		} while(accountNumber < 0 || accountNumber > playerList.size());
		
		Credential accountPicked = playerList.get(accountNumber - 1);
		String password;
		do {
			System.out.print("Enter password: ");
			password = IO.scan.nextLine();
			if(password.equals(accountPicked.getPassword())) {
				do {
					System.out.print("Enter new password: ");
					password = IO.scan.nextLine();
					if(RegisterValidation.isValidPassword(password)) {
						accountPicked.setPassword(password);
						System.out.println("Account updated!");
						IO.PRESS_ENTER();
						return;
					}
				} while(!RegisterValidation.isValidPassword(password));
			} else {
				System.out.print("Wrong password! Forget password? Type 0 to exit (or 1 to retry): ");
				accountNumber = IO.scan.nextInt(); IO.scan.nextLine();
				if(accountNumber == 0) {
					return;
				}
			}
		} while(!password.equals(accountPicked.getPassword()));
	}

}
