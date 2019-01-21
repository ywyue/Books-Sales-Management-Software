import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class ChartFrame extends JFrame {
    public ChartFrame(){
        setTitle("销售统计表");
        setBounds(100, 100, 600, 300);
        setLocation(500,250);
        setResizable(false);
    }
    @Override
    public void paint(Graphics g) {
        int Width = getWidth();
        int Height = getHeight();
        int leftMargin = 20;
        int topMargin = 50;
        Graphics2D g2 = (Graphics2D) g;
        int ruler = Height - topMargin - 5;
        int rulerStep = ruler / 10;
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, Width, Height);
        g2.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) {
            g2.drawString((100 - 10 * i) +"", 5, topMargin + rulerStep * i);
            g2.drawLine(5, topMargin + rulerStep * i, Width, topMargin + rulerStep * i);
        }
        g2.setColor(Color.BLUE);
        ArrayList<Pair<Book,Integer>> pairArrayList=getSaleInfo();
        for (int i = 0; i < pairArrayList.size(); i++) {
            Book book=pairArrayList.get(i).getBook();
            int value =  new Double(pairArrayList.get(i).getNum()/100.0*220).intValue();
            int step = (i + 1) * 40;
            g2.fillRoundRect(leftMargin + step * 2, Height-value-10, 40, value, 40, 10);
            g2.drawString(book.getID(), leftMargin + step * 2+5, Height - value - 13);
        }
    }

   
    private ArrayList<Pair<Book,Integer>> getSaleInfo(){
        ArrayList<Pair<Book,Integer>> salelist=new ArrayList<>();
        for (int i=0;i<BookList.booklist.size();i++){
            int count=0;
            for (int j=0;j< SaleList.salelist.size();j++){
                if (SaleList.salelist.get(j).getBook().getID().equals(BookList.booklist.get(i).getBook().getID())){
                    count+=SaleList.salelist.get(j).getCount();
                }
            }
            salelist.add(new Pair<>(BookList.booklist.get(i).getBook(),count));
        }
        return salelist;
    }
}