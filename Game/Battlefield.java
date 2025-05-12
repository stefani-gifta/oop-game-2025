package Game;

import java.util.ArrayList;
import Item.*;
import Monster.*;
import User.Credential;
import Util.*;

public class Battlefield {

	private Credential playerNow;
	private double damage;
	private double monsterDamage;
	private ArrayList<Item> itemBought;

	private Monster monster;

    private void showMonsterInformation() {
        System.out.println("Monster " + monster.getName() + " Information");
		System.out.println("Health\t\t: " + monster.getHealth());
		System.out.println("Base Damage\t: " + monster.getDamage());
    }

	public Battlefield(Game game) {
		playerNow = game.playerNow;
		itemBought = playerNow.getItemBought();

		// generate random monster
		monster = game.monsterList.get(Randomizer.randomInt(0, game.monsterList.size()-1));
	}

    public void goToArena() {
		IO.CLEAR_CONSOLE();
		System.out.println("Welcome to the Fight Arena");
		IO.PRESS_ENTER();

		// randomize turn
		int turn = Randomizer.randomBoolean() ? 1 : 2;
		switch(turn) {
			case 1:
				playerFirst();
				break;
			case 2:
				monsterFirst();
				break;
		}

		IO.PRESS_ENTER();
	}

	private void playerFirst() {
		System.out.println("You are first to attack");
		while(playerNow.getHealth() > 0 && monster.getHealth() > 0) {
			checkPlayerHealth();
			checkMonsterHealth();
			showAttributesInformation();
			if(playerNow.getHealth() > 0) playerTurntoFight();
			else return;
			showAttributesInformation();
			if(monster.getHealth() > 0) monsterTurntoFight();
		}
	}

	private void monsterFirst() {
		System.out.println("Monster is first to attack");
		while(playerNow.getHealth() > 0 && monster.getHealth() > 0) {
			checkPlayerHealth();
			checkMonsterHealth();
			showAttributesInformation();
			if(monster.getHealth() > 0) monsterTurntoFight();
			else return;
			showAttributesInformation();
			if(playerNow.getHealth() > 0) playerTurntoFight();
		}
	}

	private void checkPlayerHealth() {
		if(playerNow.hasDied()) {
			losePage();
		}
	}

	private void losePage() {
		IO.CLEAR_CONSOLE();
		System.out.println("Sorry, you've lost. Try again. Wish you better luck!\n");
		IO.PRESS_ENTER();
	}

	private void checkMonsterHealth() {
		if(monster.hasDied()) {
			System.out.println("Monster killed!");
			getMoneyFromWinning();
		}
	}

	private void getMoneyFromWinning() {
		int moneyGotten = Randomizer.randomInt(0, 50);
		playerNow.setMoney(playerNow.getMoney() + moneyGotten);
	}

	private void showAttributesInformation() {
		playerNow.showPlayerInformation();
		System.out.println();
		showMonsterInformation();
		System.out.println();
	}

	private void playerTurntoFight() {
		damage = playerNow.getDamage(); // reset damage every turn
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
				pureAttack();
				break;
			case 2:
				attackWithItem();
				break;
			case 3:
				storeMana();
				break;
		}
		IO.PRESS_ENTER();
	}

    private void pureAttack() {
        System.out.println("Attacking " + monster.getName());
		System.out.println(monster.getName() + " got damage " + playerNow.getDamage());
		monster.setHealth(monster.getHealth() - playerNow.getDamage());
    }

	private void attackMonster() {
        monster.setHealth(monster.getHealth() - damage);
        System.out.println(monster.getName() + " got " + damage + " of damage");
        monster.setHealth(monster.getHealth() - damage);
	}

    private void attackWithItem() {
		if(playerNow.hasItemBought()) {
			playerNow.showOffensivesAndSpellsBought();
			chooseAnItem();
		}
    }

    private void chooseAnItem() {
        System.out.println("Please choose an item");
        Item use = inputItemIDtoUsetoAttack();
        if(use != null) {
            checkItemType(use);
            checkMonsterSpecialAbility();
			attackMonster();
        }
    }

	private Item inputItemIDtoUsetoAttack() {
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
		return null;
	}

    private void checkItemType(Item use) {
        if(use instanceof Offensive) {
            if(((Offensive)use).checkValidity()) {
                System.out.println("Attacking " + monster.getName() + " with " + use.getName());
                ((Offensive)use).setUseLeft(((Offensive)use).getUseLeft() - 1);
                System.out.println(use.getName() + " used. " + ((Offensive)use).getUseLeft() + " left to use this item.\n");
                
                damage = playerNow.getDamage() + ((Offensive)use).getDamage();
            }
            else {
				// use left is 0
                System.out.println("No use left! Use pure attack instead");
				pureAttack();
            }
        }
        else if(use instanceof Spell) {
            if(playerNow.getMana() >= ((Spell)use).getMana()) {
                System.out.println("Attacking " + monster.getName() + " with " + use.getName());
                System.out.println(use.getName() + " used.\n");
                
                damage = playerNow.getDamage() + ((Spell)use).getDamage();
                playerNow.setMana(playerNow.getMana() - ((Spell)use).getMana());
            }
            else {
				// not enough mana
                System.out.println("Not enough mana to use item! Use pure attack instead");
				pureAttack();
            }
        } else if(use instanceof Defensive) {
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

    private void checkMonsterSpecialAbility() {
		System.out.print(monster.getName() + " is a type of " + monster.getClass().getSimpleName() + " hero, ");

        if(monster instanceof Strength) {
            damage = ((Strength)monster).deflectDamage(damage);
            System.out.println(monster.getName() + " has armor to deflect your damage");
        }
		else if(monster instanceof Intelligence) {
			double addition = ((Intelligence)monster).addDamage();
			System.out.println("using skill gave bonus damage " + addition);
			monsterDamage += addition;
		}
		else if(monster instanceof Agility) {
			System.out.println("it has a critical damage of " + ((Agility)monster).getCritical());
			monsterDamage += ((Agility)monster).getCritical();
		}
		else {
			System.out.println("it has no special ability");
		}
    }

    private void storeMana() {
        System.out.println("Storing mana ...");
        playerNow.setMana(playerNow.getMana() + 10);
        System.out.println("Added 10.00 mana");
    }

	private void monsterTurntoFight() {
		monsterDamage = monster.getDamage(); // reset damage every turn
		System.out.println("Monster is going to attack");

		Item use = askingToUseDefensiveItem();
		
		System.out.println(monster.getName() + " is attacking with a base damage of " + monster.getDamage());
		checkMonsterSpecialAbility();

		System.out.println();

		// check item bought
		if(use != null) {
			checkItemType(use);
		} else {
			System.out.println("Received damage " + damage);
		}

		playerNow.setHealth(playerNow.getHealth() - monsterDamage);
		IO.PRESS_ENTER();
	}

	private Item askingToUseDefensiveItem() {
		for(Item bought : itemBought) {
			if(bought instanceof Defensive) {
				System.out.println("Do you want to use your defensive item?");
				playerNow.showDefensivesBought();
				String yesno = new String();
				System.out.print("Yes | No [Case Insensitive]: ");
				do {
					yesno = IO.scan.nextLine();
					if(yesno.equalsIgnoreCase("yes")) {
						Item use = inputItemIDtoUsetoDeflect();
						return use;
					}
					else if(!yesno.equalsIgnoreCase("no")) {
						System.out.print("Please input Yes | No [Case Insensitive]: ");
					}
				} while(!yesno.equalsIgnoreCase("no"));
			}
		}
		return null;
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
