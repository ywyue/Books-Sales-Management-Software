import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.*;


class BookSalesFrame implements ActionListener
{
	private JFrame mainframe;
	public  JButton btStock;
	private JButton btSales;
	private JButton btExit;
	private JButton btSubmit;
	private JButton btClear;
	private JLabel lQuantity;
	private JLabel lBookid;
	private JLabel lSeller;
	private JTextField tQuantity;
	private JTextField tBookid;
	private JTextField tSeller;
	public  JTextArea textarea;
	private JScrollPane scrollpane;
	private JPanel panel1;
	private JPanel panel2;
	private Toolkit toolkit;
	private Dimension screen;
	private static int Width;
    private static int Height;
	static int salecount = 0;
	public void go(){
		mainframe = new JFrame("图书销售");
		btStock = new JButton("显示库存 ");
		btSales = new JButton("销售图");
		btExit = new JButton("保存并退出");
		btSubmit = new JButton("提交");
		btClear = new JButton("清除");
		lQuantity = new JLabel("数量");
		lBookid = new JLabel("图书编号");
		lSeller = new JLabel("销售员");
		tQuantity = new JTextField("",15);
		tBookid = new JTextField("",15);
		tSeller = new JTextField("",15);
		scrollpane = new JScrollPane();
		textarea = new JTextArea("序号\t编号\t书名\t销售员\t数量\t单价\t总价\t",20,70);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		scrollpane.setViewportView(textarea);
		panel1 = new JPanel();
		panel2 = new JPanel();
		toolkit = Toolkit.getDefaultToolkit();
	    screen = toolkit.getScreenSize();
	    Width = screen.width/2;
        Height = (screen.height/3)*2;
		btStock.addActionListener(this);
		btSales.addActionListener(this);
		btExit.addActionListener(this);
		btSubmit.addActionListener(this);
		btClear.addActionListener(this);
		
	
		mainframe.setSize(Width,Height);
        mainframe.setLayout(null);
        panel1.setSize(Width,Height/5);
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER,Width/10,Height/20));
        panel1.add(btStock);
        panel1.add(btSales);
        panel1.add(btExit);
        scrollpane.setBounds(10,10, Width-20, Height/3+20);
        panel2.setLayout(null);
        panel2.add(scrollpane);
        panel2.setLocation(0,Height/5);
        panel2.setSize(Width,Height/2);
        
        btSubmit.setLocation(Width/5,Height/4*3);
        btSubmit.setSize(new Dimension(70,30));
        btClear.setBounds(Width/5,Height/4*3+50,70,30);
        lQuantity.setBounds(Width/5*3,Height/4*3,50,25);
        tQuantity.setBounds(Width/5*3+50,Height/4*3,150,25);
        lBookid.setBounds(Width/5*3-25,Height/4*3+30,60,25);
        tBookid.setBounds(Width/5*3+50,Height/4*3+30,150,25);
        lSeller.setBounds(Width/5*3-13,Height/4*3+60,60,25);
        tSeller.setBounds(Width/5*3+50,Height/4*3+60,150,25);
        
        mainframe.add(panel1);
        mainframe.add(panel2);
        mainframe.add(btSubmit);
        mainframe.add(btClear);
        mainframe.add(tQuantity);
        mainframe.add(lQuantity);
        mainframe.add(lBookid);
        mainframe.add(tBookid);
        mainframe.add(lSeller);
        mainframe.add(tSeller);
        mainframe.setTitle("图书销售管理系统");
        mainframe.setLocation(300,150);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setResizable(false);
        mainframe.setVisible(true);
	}
	
	private String saleInfo(String count,String seller,String ID){
		String saleInfo = "";
		for(int i=0;i<BookList.booklist.size();i++){
			Book book = BookList.booklist.get(i).getBook();
			if(book.getID().equals(ID)){
				int num = BookList.booklist.get(i).getNum()-Integer.parseInt(count);
				if(Integer.parseInt(count)<0){
					JOptionPane.showMessageDialog(mainframe, "图书数量不能为负！");
				}
				else{
				if(num>=0){
					saleInfo = "\r\n";
					BookList.booklist.set(i, new Pair<>(book,num));
					saleInfo +=(++salecount)+"\t"+ ID +"\t"+ book.getName()+"\t"+ seller +"\t" +count +"\t"+book.getPrice()+"\t"+book.getPrice()*Integer.parseInt(count);
					SaleList.addSale(new Sale(book,seller,Integer.parseInt(count),salecount));
				}
				else {
                    JOptionPane.showMessageDialog(mainframe,"库存不足!");
                }
				}
				break;
			}
			else if(!book.getID().equals(ID)&&i==BookList.booklist.size()-1){
				JOptionPane.showMessageDialog(mainframe,"您输入的图书不存在！");
			}
		}
		return saleInfo;
	}
	private void exit(String filename){
		try{
			FileOutputStream fos = new FileOutputStream(filename);
			PrintStream ps = new PrintStream(fos);
			ps.println("\t\t图书销售统计\t\t");
		    ps.println("\t============================");
		    for(int i=0;i<BookList.booklist.size();i++){
		    	int tlCount = 0;
		    	double tlPrice = 0;
		    	Book book = BookList.booklist.get(i).getBook();
		    	ps.println(book.getID()+" 号  "+book.getName()+": 单价  "+book.getPrice());
		    	ArrayList<Sale>saleInfo = SaleList.getSale(book.getID());
		    	for(int j=0;j<saleInfo.size();j++){
		    		Sale sale = saleInfo.get(j);
		    		tlCount += sale.getCount();
		    		tlPrice += sale.getTlPrice();
		    		ps.println("C00"+sale.getOrder()+" "+sale.getSeller()+"\t\t"+sale.getCount()+" @ ￥ "+book.getPrice()+" = ￥"+sale.getTlPrice());
		    	}
		    	ps.println("============================================");
		    	ps.println("总销量："+tlCount+"\t\t\t￥"+tlPrice);
		    	ps.println("============================================");
		    }
		    ps.close();
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(mainframe, "请保存文件！");
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btStock){
			PhoneFrame phoneframe = new PhoneFrame();
			phoneframe.go();
			mainframe.setResizable(false);
		}
		
		if(e.getSource()==btSales){
			ChartFrame chartframe = new ChartFrame();
			chartframe.setVisible(true);
		}
		
		if(e.getSource()==btExit){
			FileDialog savefile = new FileDialog(mainframe,"Save File As",FileDialog.SAVE);
			savefile.setVisible(true);
            FilenameFilter fnf = new FilenameFilter(){

				@Override
				public boolean accept(File dir, String name) {
					if(name!=null&&name.toLowerCase().endsWith(".txt")){
						return true;
					}
					return false;
				}
            };		
            savefile.setFilenameFilter(fnf);
            String filename = savefile.getDirectory()+savefile.getFile();
            if(!filename.equals(savefile.getDirectory()+null)){
            	exit(filename);
            	System.exit(0);
            }else{
            	JOptionPane.showMessageDialog(mainframe, "您未保存文件！");
            }
		}
		
		if(e.getSource()==btSubmit){
			if(tQuantity.getText().equals("")||tBookid.getText().equals("")||tSeller.getText().equals(""))
			{
				JOptionPane.showMessageDialog(mainframe, "请您将信息填写完整！");
			}
			else{
					textarea.setText(textarea.getText()+saleInfo(tQuantity.getText(),tSeller.getText(),tBookid.getText()));
				}
			
		}
		
		if(e.getSource()==btClear){
			tQuantity.setText("");
			tBookid.setText("");
			tSeller.setText("");
		}
	}
	
}
