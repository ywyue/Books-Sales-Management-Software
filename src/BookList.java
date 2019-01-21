import java.io.*;  
import java.util.*;  

public class BookList {
	public static ArrayList<Pair<Book,Integer>>booklist;
	static {
		try{
			booklist = new ArrayList<>();
			File filename = new File(System.getProperty("user.dir")+"\\Books.txt");
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filename));
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			while(true){
				String[] putInfo;
				line = br.readLine();
				if(line == null){
					break;
				}
				putInfo = line.split("\t",3);
				Book book = new Book(putInfo[0],putInfo[1], Double.parseDouble(putInfo[2]));
				booklist.add(new Pair<>(book,100));
			}
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<String> getStockInfo(){
		ArrayList<String> list = new ArrayList<>();
		for(int i =0;i<booklist.size();i++){
			list.add(booklist.get(i).getBook().getID()+"\t"+booklist.get(i).getBook().getName()+"\t"+booklist.get(i).getNum());
		}
		return list;
	}
}
