
public class Sale {
	private Book book;
	private String seller;
	private int count;
	private int order;
	public Sale(Book book,String seller,int count,int order){
		this.book = book;
		this.seller = seller;
		this.count = count;
		this.order = order;
	}
	public Book getBook(){
		return book;
	}
	public String getSeller(){
		return seller;
	}
	public int getCount(){
		return count;
	}
	public int getOrder(){
		return order;
	}
	public double getTlPrice(){
		return book.getPrice()*count;
	}
}
