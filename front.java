package finalproject;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class mainframe
{
    mainframe()
    {
     JFrame frame = new JFrame();

frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setBounds(100,100,400,400);
    Container c = frame.getContentPane();
    c.setLayout(null);
/*
    frame.setLocationRelativeTo(null); // step 1
            
       ImageIcon img ;
     //   JLabel back ;
    img= new ImageIcon("image1.png");
    Image img1 = img.getImage();
    Image temp_img = img1.getScaledInstance(900,600,Image.SCALE_SMOOTH);
    img= new ImageIcon(temp_img);
    
    JLabel back = new JLabel("",img,JLabel.CENTER);
    back.setBounds(50,50,200,200);
    c.add(back);
  */

    
    JLabel l1 = new JLabel();
    l1.setText("CMS ID:");
    l1.setBounds(70,60,80,50);
    Font font = new Font("Arial",Font.BOLD,20);
    l1.setFont(font);
    c.add(l1);
    
    JTextField txt = new JTextField();
    txt.setBounds(170,70,130,30);
    //txt.PlaceHolder();
    txt.setFont(font);
    txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    c.add(txt);
    
    JLabel l2 = new JLabel();
    l2.setText("STATUS:");
    l2.setBounds(70,120,100,50);
    l2.setFont(font);
    c.add(l2);
    
    
        String s1[] = { "Admin", "Student", "Employee"}; 
        JComboBox c1 = new JComboBox(s1); 
        c1.setBounds(170,130,130,30);
        c1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.add(c1);
        
   JButton btn1 = new JButton("SIGN IN");       
        btn1.setSize(100,30);
    btn1.setLocation(130,200);
   Cursor cur = new Cursor(Cursor.HAND_CURSOR);
    btn1.setCursor(cur);
    btn1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    c.add(btn1);  
    // thehandler handler = new thehandler();
     btn1.addActionListener(new thehandler());
   // btn1.addActionListener(this);
/*  JLabel back1  = new JLabel();
  back1.setIcon(new ImageIcon("image1.png"));
  back1.setBounds(20,20,400,400);
  */
   c.setBackground(Color.WHITE);
  // c.add(back1);
    frame.setVisible(true);


}
   class thehandler  implements ActionListener
 {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFrame f = new JFrame("Clicked");
            f.setVisible(true);
            f.setSize(200,200);
            JLabel label = new JLabel();
            label.setText("this is second frame:");
           JPanel panel  = new JPanel();
           f.add(panel);
           panel.add(label);
        }
 }   
}



 public class front  
{
        public static void main(String args[])
{
  mainframe m = new mainframe();
  

}
 }
 

