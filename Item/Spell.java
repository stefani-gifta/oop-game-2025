package Item;

public class Spell extends Item {
	
	public double damage;
	
	public double mana;

	public Spell(String iD, String name, int price, double damage, double mana) {
		super(iD, name, price);
		this.damage = damage;
		this.mana = mana;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}

}
