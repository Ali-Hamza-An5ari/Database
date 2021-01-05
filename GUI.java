import net.proteanit.sql.DbUtils;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class GUI implements ActionListener
{
    private JFrame frame;
    JFrame TableFrame;
    JFrame AdFrame;
    Font font;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel copyRight;
    JTextField txt;
    JComboBox c1;
    JButton signbtn;
    Cursor cur;
    JLabel label = new JLabel();
    JTable table ;

    String statusStr[] = { "Student", "Teacher", "Admin"};

    String url = "jdbc:mysql://localhost:3306/hr";
    String user = "root";
    ResultSet rs;
    String sqlQuery = "";
    String loginSql = "";
    String CMS_id = "";
    String s = "";
    String isEmployee = "";
    boolean isEmpInd = false;
    boolean tableFlag;
    JButton cBtn;
    JButton eBtn;
    JButton dBtn;
    JButton rBtn;
    JButton sBtn;

    GUI()
    {
        LoginForm();
    }

 public void actionPerformed(ActionEvent e)
 {

     CMS_id = txt.getText();
     s = c1.getSelectedItem().toString();
     ResultSet LoginRs;
     if(CMS_id.length() == 0)
     {
         JOptionPane.showMessageDialog(null," Please enter a registered Login ID.");
     }
     else {


         if (s.equals("Student")) {
             sqlQuery = "select s.student_id CMS_id, s.std_name Student_name, s.fName Father_name, s.Semester Semester, (select department_name from students join departments using(department_id) where student_id = " + CMS_id + ") Department, cr.course_id Course_ID, cr.name Your_Courses, s.adm_date Adm_date, s.ADDRESS Address from students s join course_reg cr using (student_id) where s.Student_ID = " + CMS_id;
             // loginSql = "select * from students where student_id ="+CMS_id;
             Connector(sqlQuery);
             if (isEmpInd) {

                 newFrame(true);
             } else {
                 JOptionPane.showMessageDialog(null, CMS_id + " not matched with any Students's CMS.");
             }
         } else if (s.equals("Teacher")) {
             sqlQuery = "select employee_id from employees where employee_id =" + CMS_id + " AND course_id <> 0";
             Connector(sqlQuery);
             if (isEmpInd) {
                 sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
                 Connector(sqlQuery);
                 newFrame(true);
             } else {
                 JOptionPane.showMessageDialog(null, CMS_id + " not matched with any Teacher's INS.");
             }

         }
         else
         {
             //TableFrame.setVisible(false);
             sqlQuery = "select employee_id from employees where employee_id =" + CMS_id + " AND course_id = 0";
             Connector(sqlQuery);
             if (isEmpInd)
             {
                 AdminForm();
                 String actionCommand = e.getActionCommand();

                 //checking if any of the adminFrame button is pressed.
                     if(isAdButtonPressed(actionCommand))
                     {
                        sqlQuery = "select * from " + actionCommand;
                        Connector(sqlQuery);
                        newFrame(true);
                    }
             }
             else
                 {
                 JOptionPane.showMessageDialog(null, CMS_id + " not matched with any employee's INS.");

                }
             }

         }
 }
 public void Connector(String executeQuery)
 {
     try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url, user, "");
         PreparedStatement st = conn.prepareStatement(executeQuery);
         rs = st.executeQuery(executeQuery);
         isEmpInd = rs.isBeforeFirst();

         if(rs.isBeforeFirst())
         {
             table = ResultRet(rs);

         }
         conn.close();
         rs.close();
         st.close();

     } catch (ClassNotFoundException classNotFoundException)
     {
         classNotFoundException.printStackTrace();
     } catch (SQLException sqlException)
     {
         sqlException.printStackTrace();
     }
 }
public void LoginForm()
{
    frame = new JFrame("College Management System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(150,100,625,450);
    frame.setLocationRelativeTo(null);

    JLabel background = new JLabel(new ImageIcon("D:\\BSCS-IV\\Database Systems\\test.png"));


    l1 = new JLabel();
    l1.setText("CMS ID:");
    l1.setBounds(310,125,80,50);
    Font font = new Font("Arial",Font.BOLD,15);
    l1.setFont(font);
    background.add(l1);

    txt = new JTextField();
    txt.setBounds(410,125,130,30);
    txt.setFont(font);
    txt.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    l2 = new JLabel();
    l2.setText("STATUS:");
    l2.setBounds(310,180,100,50);
    l2.setFont(font);
    c1 = new JComboBox(statusStr);
    c1.setBounds(410,191,130,30);
    c1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    signbtn = new JButton("SIGN IN");
    signbtn.setFont(font);
    signbtn.setBounds(380,290,100,30);

    cur = new Cursor(Cursor.HAND_CURSOR);
    signbtn.setCursor(cur);
    signbtn.setBackground(Color.CYAN);
    signbtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

    background.add(l2);
    background.add(txt);
    background.add(c1);
    background.add(signbtn);
    frame.add(background);
    frame.setVisible(true);

    signbtn.addActionListener(this);

}


    //Creates JTable of the resultset
 public JTable ResultRet(ResultSet rs) throws SQLException {
     ArrayList columnNames = new ArrayList();
     ArrayList data = new ArrayList();
     ResultSetMetaData md = rs.getMetaData();
     int columns = md.getColumnCount();

     //  Get column names
     for (int i = 1; i <= columns; i++)
     {
         columnNames.add( md.getColumnName(i) );
     }

     //  Get row data
     while (rs.next())
     {
         ArrayList row = new ArrayList(columns);

         for (int i = 1; i <= columns; i++)
         {
             row.add( rs.getObject(i) );
         }

         data.add( row );
     }
     Vector columnNamesVector = new Vector();
     Vector dataVector = new Vector();

     for (int i = 0; i < data.size(); i++)
     {
         ArrayList subArray = (ArrayList)data.get(i);
         Vector subVector = new Vector();
         for (int j = 0; j < subArray.size(); j++)
         {
             subVector.add(subArray.get(j));
         }
         dataVector.add(subVector);
     }

     for (int i = 0; i < columnNames.size(); i++ )
         columnNamesVector.add(columnNames.get(i));
     JTable tableSet = new JTable(dataVector, columnNamesVector);
     return tableSet;
 }
 //create a new GUI form with tables
 public void newFrame(boolean frameFlag)
 {
     TableFrame = new JFrame("College Management System");

     Container tContainer = TableFrame.getContentPane();

     tContainer.setBackground(Color.cyan);
     tContainer.setLayout(null);
     font = new Font("Lemon",Font.BOLD,32);

     l3 = new JLabel("College Management System");
     l3.setFont(font);
     copyRight = new JLabel("<html>Developers: Ali Hamza Ansari and Bilal Younus. <br>        \u00a9 2021 College Management System. All Rights Reserved.</html>",JLabel.CENTER);
     copyRight.setFont(new Font("Calibri",Font.PLAIN,16));
     copyRight.setForeground(Color.WHITE);
     //copyRight.setHorizontalTextPosition(SwingConstants.CENTER);
     copyRight.setBackground(Color.DARK_GRAY);
     copyRight.setOpaque(true);

     l3.setBounds(150,20,600,50);
     copyRight.setBounds(0,350,1110,40);

     table.getTableHeader().setFont(new Font("Segeo UI",Font.BOLD,12));
     table.getTableHeader().setOpaque(false);
     table.getTableHeader().setBackground(new Color(32,136,203));
     table.getTableHeader().setForeground(new Color(255,255,255));
     table.setRowHeight(25);
     table.setAutoCreateColumnsFromModel(true);
     table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

     JScrollPane scrollPane = new JScrollPane(table);
     scrollPane.setBounds(1,80,1110,250);
     try{
         for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
         {
             if("Windows".equals(info.getName()))
             {
                 UIManager.setLookAndFeel(info.getClassName());
                 break;
             }
         }
     }
     catch(Exception ex)
     {
         ex.getMessage();
     }
     tContainer.add(l3);
     tContainer.add(scrollPane);
     tContainer.add(copyRight);
     TableFrame.setVisible(frameFlag);
     TableFrame.setBounds(150,100,1127,430);
 }

 //shows admin controls
 public void AdminForm()
 {

     AdFrame = new JFrame("College Management System");
     AdFrame.setSize(325, 500);
     AdFrame.setLocationRelativeTo(null);
     AdFrame.setLayout(new BorderLayout());

     JLabel background = new JLabel(new ImageIcon("D:\\BSCS-IV\\Database Systems\\back.png"));

     AdFrame.add(background);
     cBtn = new JButton("Courses");
     eBtn = new JButton("Employees");
     dBtn = new JButton("Departments");
     rBtn = new JButton("Registration");
     sBtn = new JButton("Students");

     //l3.setBounds(50,25,150,50);
     cBtn.setBounds(50,100,200,40);
     eBtn.setBounds(50,160,200,40);
     dBtn.setBounds(50,220,200,40);
     rBtn.setBounds(50,280,200,40);
     sBtn.setBounds(50,340,200,40);

     font = new Font("Arial",Font.BOLD,15);
     cur = new Cursor(Cursor.HAND_CURSOR);
     cBtn.setFont(font);

     cBtn.setCursor(cur);
     cBtn.setBackground(Color.CYAN);
     cBtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

     eBtn.setFont(font);
     eBtn.setCursor(cur);
     eBtn.setBackground(Color.CYAN);
     eBtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

     dBtn.setFont(font);
     Cursor cur = new Cursor(Cursor.HAND_CURSOR);
     dBtn.setCursor(cur);
     dBtn.setBackground(Color.CYAN);
     dBtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

     rBtn.setFont(font);
     rBtn.setCursor(cur);
     rBtn.setBackground(Color.CYAN);
     rBtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

     sBtn.setFont(font);
     sBtn.setCursor(cur);
     sBtn.setBackground(Color.CYAN);
     sBtn.setBorder(BorderFactory.createLineBorder(Color.magenta,2));

     background.add(cBtn);
     background.add(eBtn);
     background.add(dBtn);
     background.add(rBtn);
     background.add(sBtn);

     cBtn.addActionListener(this);
     eBtn.addActionListener(this);
     dBtn.addActionListener(this);
     rBtn.addActionListener(this);
     sBtn.addActionListener(this);

     AdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     AdFrame.setVisible(true);

 }

 public boolean isAdButtonPressed(String checker)
 {
     if ((checker.equals("Courses")) || (checker.equals("Employees")) || (checker.equals("Departments")) ||(checker.equals("Registration")) ||(checker.equals("Students")) )
     {
         return true;
     }
     else
     {
         return false;
     }
 }
}

