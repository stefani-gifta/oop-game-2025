package Monster;

public abstract class Monster {

	// final -> cannot be edited anymore
	private final String name;
	private double damage;
	private double health;
	
	public Monster(String name, double damage, double health) {
		super();
		this.name = name;
		this.damage = damage;
		this.health = health;
	}

	public String getName() {
		return name;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage; // damage can increase
	}

	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public boolean hasDied() {
		if(health <= 0) {
			return true;
		}
		return false;
	}

}
