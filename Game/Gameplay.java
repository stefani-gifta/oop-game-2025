package Game;

public class Gameplay {

    Move move;
    char[][] bigMap;

    public Gameplay(Move move, char[] bigMap) {
        this.move = move;
        this.bigMap = bigMap;
    }

	public void play() {
		char inputMove = '0';

        // will only be printing small portions of the big map
		do {
            if(playerNow.getHealth() <= 0) {
                losePage();
            }

			showPortionofMap();
			System.out.println();
            showPlayerInformation();
			System.out.print(">> ");
			
//			System.out.println("\n" + move.getX() + " " + move.getY());
			
			inputMove = Util.scan.next().charAt(0); IO.scan.nextLine();
			
			switch(String.valueOf(inputMove).toLowerCase()) {
				case "i":
					printItemBought();
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
			
//			System.out.println("\n" + move.getX() + " " + move.getY());

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

	private void losePage() {
		IO.CLEAR_CONSOLE();
		System.out.println("Sorry, you've lost:( Better luck next time\n");
		IO.PRESS_ENTER();
	}

    private void showPlayerInformation() {
        System.out.println("Player " + playerNow.getEmail() + " Information");
        System.out.println("Health\t\t: " + playerNow.getHealth());
        System.out.println("Money\t\t: " + playerNow.getMoney());
        System.out.println("Mana\t\t: " + playerNow.getMana());
        System.out.println("Base Damage\t: " + playerNow.getDamage());
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
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("\nYou've reached the end of the world ...");
					j -= 2; // forces player to go back
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
		showPlayerInformation();
		System.out.println();
		showMonsterInformation(monster);
		System.out.println();
	}

    private void showMonsterInformation(Monster monster) {
        System.out.println("Monster " + monster.getName() + " Information");
		System.out.println("Health\t\t: " + monster.getHealth());
		System.out.println("Base Damage\t: " + monster.getDamage());
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

	private void whatsInTheGrass() {
		int monster = Randomizer.randomInt(0, 100);
		if(monster < 30) {
			IO.CLEAR_CONSOLE();
			System.out.println("Welcome to the Fight Arena");
			IO.PRESS_ENTER();
			new Battlefield().goToArena();
		}
	}

}
