package Item;

public class Item {

	private String ID;
	private String name;
	private int price;
	
	public Item(String iD, String name, int price) {
		super();
		ID = iD;
		this.name = name;
		this.price = price;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
