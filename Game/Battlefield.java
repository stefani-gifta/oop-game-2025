package Game;

import java.util.ArrayList;
import Item.*;
import Monster.*;
import User.Credential;
import Util.*;

public class Battlefield {

    public void goToArena() {
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

	private void showAttributesInformation(Monster monster) {
		playerNow.showPlayerInformation();
		System.out.println();
		showMonsterInformation(monster);
		System.out.println();
	}

    private void showMonsterInformation(Monster monster) {
        System.out.println("Monster " + monster.getName() + " Information");
		System.out.println("Health\t\t: " + monster.getHealth());
		System.out.println("Base Damage\t: " + monster.getDamage());
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
				monsterPureAttack();
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

    private void monsterPureAttack() {
        System.out.println("Attacking " + monster.getName());
		System.out.println(monster.getName() + " got damage " + playerNow.getDamage());
		monster.setHealth(monster.getHealth() - playerNow.getDamage());
    }

    private void attackWithItem() {
        playerNow.showOffensivesAndSpellsBought();
        chooseAnItem();
    }

    private void chooseAnItem() {
        System.out.println("Please choose an item");
        Item use = inputItemIDtoUsetoAttack();
        double damage = playerNow.getDamage();
        if(use != null) {
            checkItemType();
            checkMonsterSpecialAbility();
        }
    }

    private void checkItemType() {
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
    }

    private void checkMonsterSpecialAbility() {
        if(monster instanceof Strength) {
            damage = ((Strength)monster).deflectDamage(damage);
            System.out.println(monster.getName() + " has armor to deflect your damage");
        }
        monster.setHealth(monster.getHealth() - damage);
        System.out.println(monster.getName() + " got " + damage + " of damage");
        monster.setHealth(monster.getHealth() - damage);
    }

    private void storeMana() {
        System.out.println("Storing mana ...");
        playerNow.setMana(playerNow.getMana() + 10);
        System.out.println("Added 10.00 mana");
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
				playerNow.showDefensivesBought();
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
