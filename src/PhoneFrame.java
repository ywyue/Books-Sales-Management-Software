import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*; 

public class PhoneFrame extends JFrame implements ActionListener
{
	private JButton btOk;
	private JPanel panel;
	public JTextArea ta;
	private JScrollPane sp;
	ArrayList<String> list;
	public void go(){
		this.setSize(400, 300);
		this.setLocation(500, 250);
		this.setTitle("ø‚¥Ê");
		btOk = new JButton("OK");
		btOk.setBounds(170,220,60, 30);;
		btOk.addActionListener(this);
		ta = new JTextArea("Õº È±‡∫≈\t√˚≥∆\tø‚¥Ê",10,10);
		list = BookList.getStockInfo();
		for(int i=0;i<list.size();i++){
			ta.setText(ta.getText()+"\r\n"+list.get(i));
		}
		ta.setEditable(false);
		sp = new JScrollPane();
		sp.setViewportView(ta);
		sp.setBounds(70, 30, 250, 160);
		panel = new JPanel();
		panel.setLayout(null);
		panel.add(sp);
		panel.add(btOk);
		this.add(panel);
		this.setVisible(true);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btOk){
			this.dispose();
		}
	}

}
