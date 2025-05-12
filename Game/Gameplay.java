package Game;

import User.Credential;
import Util.*;

public class Gameplay {

	protected Game game;
	protected Credential playerNow;

    private Move move;
    private char[][] bigMap;

    public Gameplay(Game game, Move move, char[][] bigMap) {
        this.move = move;
        this.bigMap = bigMap;
		this.game = game;
		playerNow = game.getPlayerNow();
    }

	public void play() {
		char inputMove = '0';

        // will only be printing small portions of the big map
		do {
			showPortionofMap();
			System.out.println();
            playerNow.showPlayerInformation();
			System.out.print(">> ");
			
			System.out.println("\nx: " + move.getX() + ", y: " + move.getY());
			
			inputMove = IO.scan.next().charAt(0); IO.scan.nextLine();
			
			switch(String.valueOf(inputMove).toLowerCase()) {
				case "i":
					playerNow.printItemBought();
					break;
				case "z":
                    new Shop(this).printShopPage();
					break;
				case "e":
                    printExitConfirmation();
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

			IO.CLEAR_CONSOLE();

		} while(inputMove != 'e' || playerNow.getHealth() > 0);
	}

    private void printExitConfirmation() {
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

	private void showPortionofMap() {
		for(int i = move.getY(); i < move.getY()+15; i++) {
			for(int j = move.getX(); j < move.getX()+35; j++) {
				try {
					if(i == move.getY()+7 && j == move.getX()+17) {
						System.out.print('X');
					}
					else System.out.print(bigMap[i][j]);
				} catch (Exception e) {
//					e.printStackTrace();
					System.out.println("\nYou've reached the end of the world ...");
					System.out.println("Jumping back to the center ...");
					moveToCenter();
					i = move.getY();
					j = move.getX();
				}
			}
			System.out.println();
		}
	}

	private void moveToCenter() {
		move.setX(149);
		move.setY(149);
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

	private void whatsInTheGrass() {
		int thereIsMonster = Randomizer.randomInt(0, 100);
		System.out.println("there is a monster: " + thereIsMonster);
		if(thereIsMonster < 30) { // 30% chance of encountering a monster
			new Battlefield(game);
		}
	}

}
