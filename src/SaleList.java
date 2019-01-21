
import java.util.ArrayList;

public class SaleList {
	public static ArrayList<Sale>salelist = new ArrayList<>();
	
	public static ArrayList<Sale> getSale(String ID){
		ArrayList<Sale>list = new ArrayList<>();
		for(int i=0;i<salelist.size();i++){
			if(salelist.get(i).getBook().getID().equals(ID)){
				list.add(salelist.get(i));
			}
		}
		return list;
	}
	
	public static void addSale(Sale sale){
		salelist.add(sale);
	}
}
