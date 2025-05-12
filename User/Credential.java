package User;

import java.util.ArrayList;

import Item.Defensive;
import Item.Item;
import Item.Offensive;
import Item.Spell;
import Util.IO;

public class Credential {

	private static ArrayList<Credential> playerList = new ArrayList<Credential>();

	public static ArrayList<Credential> getPlayerList() {
		return playerList;
	}

	private String email;
	private String password;
	private int money;
	private double health;
	private double mana;
	private double damage;
	
	public Credential(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.money = 100;
		this.mana = 30;
		this.health = 300;
		this.damage = 30;
		playerList.add(this);
	}

	public void showPlayerInformation() {
		System.out.println("Player " + email + " Information");
		System.out.println("Money\t\t: " + money);
		System.out.println("Health\t\t: " + health);
		System.out.println("Mana\t\t: " + mana);
		System.out.println("Damage\t\t: " + damage);
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password; // can change password
	}
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		if(money <= 0) {
			this.money = 0;
		} else {
			this.money = money;
		}
	}
	
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}

	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

	// ITEMS BOUGHT IN GAME

	private static ArrayList<Item> itemBought = new ArrayList<Item>();

	public void printItemBought() {
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

	public void showOffensivesAndSpellsBought() {
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

	public void showDefensivesBought() {
		System.out.printf("| %-10s | %-25s | %-15s | %-10s | %-10s | %-10s | %-10s |\n", "ID", "Name", "Type", "Price", "Damage", "Max Use", "Use Left");
		for(Item bought : itemBought) {
			if(bought instanceof Defensive) {
				System.out.printf("| %-10s | %-25s | %-15s | %-10d | %-10f | %-10d | %-10d |\n",
						bought.getID(), bought.getName(), "Defensive", bought.getPrice(),
						((Defensive) bought).getDeflect(), ((Defensive) bought).getMaxUse(), ((Defensive) bought).getUseLeft());
			}
		}
	}
	
}
