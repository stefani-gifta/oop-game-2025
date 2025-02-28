package Monster;

public abstract class Monster {

	private String name;
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
	public void setName(String name) {
		this.name = name;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
	}

	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}

}
