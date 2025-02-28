package Monster;

import java.util.Random;

public class Intelligence extends Monster {

	public Intelligence(String name, double damage, double health) {
		super(name, damage, health);
		// TODO Auto-generated constructor stub
	}

	public double addDamage() {
		Random r = new Random();
		double damageAddition = Double.valueOf(30 + r.nextInt(19));
		return damageAddition;
	}
	
}
