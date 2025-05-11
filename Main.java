import java.util.ArrayList;
import java.util.Arrays;

import Util.*;
import Game.Game;
import Item.*;
import Monster.*;
import User.*;

public class Main {
	
	public static void main(String[] args) {
		printMainMenu();
	}

	private static void printMainMenu() {
		int menuInput;
		do {
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Change Password");
			System.out.println("4. Exit");
			System.out.print(">> ");
			menuInput = IO.scan.nextInt(); IO.scan.nextLine();

			IO.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					Authorization.login();
					IO.CLEAR_CONSOLE();
					break;
				case 2:
					Authorization.register();
					IO.CLEAR_CONSOLE();
					break;
				case 3:
					Authorization.changePassword();
					IO.CLEAR_CONSOLE();
					break;
				case 4:
					break;
				default:
					System.out.println("Invalid input!");
					break;
			}
		} while(menuInput != 4);
	}

}
