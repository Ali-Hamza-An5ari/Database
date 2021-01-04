package finalproject;
import javax.swing.*;
import java.awt.*;


class button
{
    
    
    
    
}




public class Finalproject {

    public static void main(String[] args)
    {
JFrame frame = new JFrame();

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //frame.setBackground(Color.RED);
  //frame.setBackground(Color.YELLOW);  //Whatever color 
frame.setBounds(100,100,1000,500);
    Container c = frame.getContentPane();
    c.setLayout(null);
    c.setBackground(Color.GRAY);
//ImageIcon img = new ImageIcon("wait.png");
    //JButton btn1 = new JButton(img);
    //btn1.setSize(img.getIconWidth(),img.getIconHeight());
   
    JButton btn1 = new JButton("First Button");
    JLabel l1 = new JLabel();
    JPanel p = new JPanel();
    l1.setText("Email:");
    l1.setBounds(200,50,100,30);
    btn1.setSize(130,40);
    btn1.setLocation(100,100);
    Font font = new Font("Arial",Font.PLAIN,20);
    btn1.setFont(font);
    btn1.setForeground(Color.RED);
    btn1.setBackground(Color.BLACK);
    Cursor cur = new Cursor(Cursor.HAND_CURSOR);
    frame.setLayout(null);
    p.setBounds(50,50,200,200);
    p.setBackground(new Color(200,230,230));
    p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    //p.setLayout(new BorderLayout());
    //p.setVerticalAlignment(JLABEL.BOTTOM);
    //Cursor cur1 = new Cursor(Cursor.CROSSHAIR_CURSOR);
    //Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
    //btn1.setEnabled(false);
    //btn1.setVisible(true); 
    btn1.setCursor(cur);
    c.add(btn1);
    c.add(l1);
    
    JCheckBox chk = new JCheckBox("click this");
    chk.setBounds(80,80,120,120);
    c.add(chk);
   
    
    JCheckBox chk1 = new JCheckBox("click this");
    chk1.setBounds(70,70,120,120);
    c.add(chk1 );
   
    frame.add(p) ;
    
    
    frame.setVisible(true);
        
        
        
    }
    
}
