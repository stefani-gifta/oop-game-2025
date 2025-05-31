package Item;

public class Offensive extends Item implements maxUse {
	
	public double damage; // damage that will be added when this item type is used
	
	public int maxUse;
	public int useLeft;

	@Override
	public boolean checkValidity() {
		if(useLeft > 0) {
			return true;
		}
		System.out.println("Offensive item has no use left");
		return false;
	}

	public Offensive(String iD, String name, int price, double damage, int maxUse, int useLeft) {
		super(iD, name, price);
		this.damage = damage;
		this.maxUse = maxUse;
		this.useLeft = useLeft;
	}

	public double getDamage() {
		return damage;
	}
	public void setDamage(double damage) {
		this.damage = damage;
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
