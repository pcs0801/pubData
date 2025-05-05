package kh.Stoko;

public class itemData extends Object {
	String name;
	int price;
	int qty;
	String inDate;
	
	public itemData() {
		this(null,0,0,null);
	}

	public itemData(String name, int price, int qty, String inDate) {
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.inDate = inDate;
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

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	@Override
	public String toString() {
	    return String.format("[%-6s] | 가격: %,6d원 | 수량: %3d개 | 입고일자: %s\n",
                name, price, qty, inDate);
	}

}