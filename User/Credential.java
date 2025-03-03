package User;

public class Credential {

	private String email;
	private String password;
	
	private String itemSaved;
	
	public String getItemSaved() {
		return itemSaved;
	}
	public void setItemSaved(String itemSaved) {
		this.itemSaved = itemSaved;
	}
	
	private int money;
	private double health;
	private double mana;
	
	private double damage;
	
	public Credential(String email, String password, int money, double health, double mana, double damage) {
		super();
		this.email = email;
		this.password = password;
		this.money = money;
		this.mana = mana;
		this.health = health;
		this.damage = damage;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
}
