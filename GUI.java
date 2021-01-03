import net.proteanit.sql.DbUtils;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class GUI implements ActionListener
{
    JFrame frame;
    JFrame TableFrame;
    JFrame AdFrame;
    JLabel l1;
    JLabel l2;
    JTextField txt;
    JComboBox c1;
    JButton signbtn;
    JLabel label = new JLabel();
    JTable table = new JTable();
    String s1[] = { "Student", "Teacher", "Admin"};
    String sqlQuery = "";

    ///components for Admin form
    JButton cBtn;
    JButton eBtn;
    JButton dBtn;
    JButton rBtn;
    JButton sBtn;

    GUI()
    {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(150,100,400,400);
        Container c = frame.getContentPane();
        c.setLayout(null);

        l1 = new JLabel();
        l1.setText("CMS ID:");
        l1.setBounds(70,60,80,50);
        Font font = new Font("Arial",Font.BOLD,20);
        l1.setFont(font);
        c.add(l1);

        txt = new JTextField();
        txt.setBounds(170,70,130,30);
        //txt.PlaceHolder();
        txt.setFont(font);
        txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.add(txt);

        l2 = new JLabel();
        l2.setText("STATUS:");
        l2.setBounds(70,120,100,50);
        l2.setFont(font);
        c.add(l2);



        c1 = new JComboBox(s1);
        c1.setBounds(170,130,130,30);
        c1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.add(c1);

        signbtn = new JButton("SIGN IN");
        signbtn.setSize(100,30);
        signbtn.setLocation(130,200);
        Cursor cur = new Cursor(Cursor.HAND_CURSOR);
        signbtn.setCursor(cur);
        signbtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.add(signbtn);
        c.setBackground(Color.WHITE);
        frame.setVisible(true);
        signbtn.addActionListener(this);

    }

 public void actionPerformed(ActionEvent e)
 {

     String CMS_id = txt.getText();
     String s = c1.getSelectedItem().toString();
     String url = "jdbc:mysql://localhost:3306/hr";
     String user = "root";
     ResultSet rs;

                            if (s.equals("Student")) {
                                sqlQuery = "select s.student_id CMS_id, s.std_name Student_name, s.fName Father_name, s.Semester Semester, (select department_name from students join departments using(department_id) where student_id = "+CMS_id+") Department, cr.course_id Course_ID, cr.name Your_Courses, s.adm_date Adm_date, s.ADDRESS Address from students s join course_reg cr using (student_id) where s.Student_ID = "+CMS_id;

                            }
                            else if (s.equals("Teacher"))
                            {
                                sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
                            }
                            else
                            {

                                AdminForm();
                                String actionCommand = e.getActionCommand();
                                sqlQuery = "select * from "+actionCommand;

                            }
                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(url, user, "");
                                PreparedStatement st = conn.prepareStatement(sqlQuery);
                                rs = st.executeQuery(sqlQuery);
                                //ResultSetMetaData rsmd = rs.getMetaData();
                                table.setModel(DbUtils.resultSetToTableModel(rs));
                                

                                if (rs.isBeforeFirst())
                                {
                                    rs.first();

                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"Invalid CMS");

                                    //System.exit(0);
                                }
                            } catch (ClassNotFoundException classNotFoundException)
                            {
                                classNotFoundException.printStackTrace();
                            } catch (SQLException sqlException)
                            {
                                sqlException.printStackTrace();
                            }
     newFrame();

 }
 public void newFrame()
 {
     TableFrame = new JFrame("Clicked");
     Container res = TableFrame.getContentPane();
     res.setLayout(null);

     table.setBounds(150,80,700,250);
     res.add(label);
     res.add(table);
     TableFrame.setVisible(true);
     TableFrame.setBounds(0,100,1000,400);
 }
 public void AdminForm()
 {
     AdFrame = new JFrame();
     Container cAd = AdFrame.getContentPane();
     cAd.setLayout(null);
     AdFrame.setBounds(100,100,400,500);

     AdFrame.setVisible(true);

     JLabel l3 = new JLabel("Tables");
      cBtn = new JButton("courses");
      eBtn = new JButton("employees");
      dBtn = new JButton("departments");
      rBtn = new JButton("registration");
      sBtn = new JButton("students");

      l3.setBounds(50,25,150,50);
     cBtn.setBounds(50,100,200,40);
     eBtn.setBounds(50,160,200,40);
     dBtn.setBounds(50,220,200,40);
     rBtn.setBounds(50,280,200,40);
     sBtn.setBounds(50,340,200,40);

     cBtn.addActionListener(this);
     eBtn.addActionListener(this);
     dBtn.addActionListener(this);
     rBtn.addActionListener(this);
     sBtn.addActionListener(this);

     cAd.add(l1);
     cAd.add(cBtn);
     cAd.add(eBtn);
     cAd.add(dBtn);
     cAd.add(rBtn);
     cAd.add(sBtn);

     AdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(false);
     TableFrame.setVisible(false);


 }


}
