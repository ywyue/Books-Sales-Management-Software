
public class Book {
	private String ID;
	private String name;
	private double price;
	public  Book(String ID,String name,double price){
		this.ID = ID;
		this.name = name;
		this.price = price;
	}
	public String getID(){
		return ID;
	}
	public String getName(){
		return name;
	}
	public double getPrice(){
		return price;
	}
}
