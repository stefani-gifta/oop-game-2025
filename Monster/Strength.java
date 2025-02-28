package Monster;

public class Strength extends Monster {

	public Strength(String name, double damage, double health) {
		super(name, damage, health);
		// TODO Auto-generated constructor stub
	}

	private double armor;

	public double getArmor() {
		return armor;
	}
	public void setArmor(double armor) {
		this.armor = armor;
	}
	
	public double deflectDamage(double damage) {
		damage -= armor;
		damage = Math.max(0, damage); // in case it is <0 after being deflected
		return damage;
	}

}
