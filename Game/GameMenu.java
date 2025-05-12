package Game;

import Util.*;
import Util.Randomizer;

public class GameMenu {

    private Game game;

    protected GameMenu(Game game) {
        this.game = game;
        printGamePage();
    }

    private void printGamePage() {
		int menuInput;
		do {
			System.out.println("Welcome to the Game");
			System.out.println("Please choose an option");
			System.out.println("1. Start Game");
			System.out.println("2. Game Guide");
			System.out.println("3. Exit Game");
			System.out.print(">> ");
			menuInput = IO.scan.nextInt(); IO.scan.nextLine();

			IO.CLEAR_CONSOLE();
			
			switch(menuInput) {
				case 1:
					start();
					break;
				case 2:
					howToPlayPage();
					IO.CLEAR_CONSOLE();
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
		System.out.println("As you roam, you'll find coins (O) waiting to be collected.");
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
        
        IO.PRESS_ENTER();
	}
	
	public void start() {
		Move move = new Move();
		Map map = new Map();

		// 300x300 big game map
		char[][] bigMap = map.generateFilledBigMap(); 

		// move user's player/character to random position
		do {
			move.setX(Randomizer.randomInt(0, 299-35));
			move.setY(Randomizer.randomInt(0, 299-15));
//			move.setX(265);
//			move.setY(285);
		} while(bigMap[move.getY()][move.getX()] != ' ');

		Gameplay play = new Gameplay(game, move, bigMap);
		play.play();
	}

}