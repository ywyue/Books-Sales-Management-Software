
public class Pair<T1,T2> {
	private Book book;
	private int num;
	public Pair(Book book,int num){
		this.book = book;
		this.num = num;
	}
	public Book getBook(){
		return book;
	}
	public int getNum(){
		return num;
	}

}
