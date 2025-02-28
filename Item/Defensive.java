package Item;

public class Defensive extends Item implements maxUse {
	
	public double deflect;
	
	public double deflectDamage(double damage) {
		damage -= deflect;
		damage = Math.max(0, damage); // in case it is <0 after being deflected
		return damage;
	}
	
	public int maxUse;
	public int useLeft;

	@Override
	public boolean checkValidity() {
		if(useLeft > 0) {
			return true;
		}
		return false;
	}

	public Defensive(String iD, String name, int price, double deflect, int maxUse, int useLeft) {
		super(iD, name, price);
		this.deflect = deflect;
		this.maxUse = maxUse;
		this.useLeft = useLeft;
	}
	
	public double getDeflect() {
		return deflect;
	}
	public void setDeflect(double deflect) {
		this.deflect = deflect;
	}

	public int getMaxUse() {
		return maxUse;
	}
	public void setMaxUse(int maxUse) {
		this.maxUse = maxUse;
	}

	public int getUseLeft() {
		return useLeft;
	}
	public void setUseLeft(int useLeft) {
		this.useLeft = useLeft;
	}

}
