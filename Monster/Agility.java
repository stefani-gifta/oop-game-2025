package Monster;

public class Agility extends Monster {

	public Agility(String name, double damage, double health) {
		super(name, damage, health);
		// TODO Auto-generated constructor stub
	}

	private double critical;

	public double getCritical() {
		return critical;
	}
	public void setCritical(double critical) {
		this.critical = critical;
	}

}
