package Item;

public class Item {

	// final -> cannot be edited anymore
	private final String ID;
	private final String name;
	private int price;
	
	public Item(String id, String name, int price) {
		super();
		ID = id;
		this.name = name;
		this.price = price;
	}
	
	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
