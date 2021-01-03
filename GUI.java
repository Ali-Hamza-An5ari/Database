import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class GUI implements ActionListener
{
    JFrame frame;
    JLabel l1;
    JLabel l2;
    JTextField txt;
    JComboBox c1;
    JButton signbtn;
    String s1[] = { "Student", "Teacher", "Admin"};
    GUI()
    {
        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100,100,400,400);
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
        // thehandler handler = new thehandler();
        signbtn.addActionListener(this);



                        /*catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();

                        }*/
    }
                        // btn1.addActionListener(this)
                        // //
 public void actionPerformed(ActionEvent e)
 {
     JLabel label = new JLabel();
     String CMS_id = txt.getText();
     String s = c1.getSelectedItem().toString();

     //ArrayList <St_user> stList = new ArrayList<St_user>();
     String url = "jdbc:mysql://localhost:3306/hr";
     String user = "root";
     String sqlQuery = "";
     String[][] rows = {{"a","b","c"},{"x","y","z"}};
     String [] columns = {"p","q","r"};
     ResultSet rs;
     int columnsNumber;

                            if (s.equals("Student")) {
                                sqlQuery = "select s.student_id CMS_id, s.std_name Student_name, s.fName Father_name, s.Semester Semester, (select department_name from students join departments using(department_id) where student_id = "+CMS_id+") Department, cr.course_id Course_ID, cr.name Your_Courses, s.adm_date Adm_date, s.ADDRESS Address from students s join course_reg cr using (student_id) where s.Student_ID = "+CMS_id;
                            }
                            else if (s.equals("Teacher"))
                            {
                                sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
                            }
                            else
                            {
                                sqlQuery = "Select * from employees where employee_id = 1";
                            }

                            //JFrame f = new JFrame("Sukkur IBA University CMS");


                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(url, user, "");
                                // sqlQuery = "Select * from employees where employee_id = 1";
                                //sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
                                PreparedStatement st = conn.prepareStatement(sqlQuery);
                                 rs = st.executeQuery(sqlQuery);
                                ResultSetMetaData rsmd = rs.getMetaData();
                                columnsNumber = rsmd.getColumnCount();
                                label.setText(rs.getString(2));

                                rows = new String[3][columnsNumber];
                                columns = new String[columnsNumber];
                                label.setText(CMS_id+","+rs.getString(2));
                                System.out.println(rs.getString(2));
                                while (rs.next())
                                {
                                    int i = 0;
                                    columns[i] = rsmd.getColumnName(i+1);
                                    i++;
                                }

                           /*
                            //JPanel panel  = new JPanel();
                            Container ct = f.getContentPane();
                            f.setBounds(100,100,800,800);
                            //ct.setSize();
                            ct.setLayout(null);
                            ct.add(table);
                            //f.add(panel);
                            //panel.add(label);
                            //panel.add(table);
                            f.setVisible(true);
                            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
                                //f.setSize(800,600);
                            /*while (rs.next())
                            {
                                for (int i = 1; i <= columnsNumber; i++) {
                                    if (i > 1) System.out.print("  ");
                                    System.out.print(" " + rsmd.getColumnName(i));
                                }
                                System.out.println("\n========================================");
                                for (int i = 1; i <= columnsNumber; i++) {
                                    if (i > 1) System.out.print("    ");
                                    String columnValue = rs.getString(i);
                                    //System.out.print(columnValue + " ");

                                }
                                System.out.println("");*/
            /*while (rs.next())
            {
                System.out.println(rs.getString(""));
            }*/
                            } catch (ClassNotFoundException classNotFoundException)
                            {
                                classNotFoundException.printStackTrace();
                            } catch (SQLException sqlException)
                            {
                                sqlException.printStackTrace();
                            }
     JFrame f = new JFrame("Clicked");
     f.setVisible(true);
     //f.setSize(200,200);
     f.setBounds(100,100,600,800);
     //JLabel label = new JLabel();
     JTable table = new JTable(rows, columns);
     table.setBounds(20,120,600,400);

     //label.setText(CMS_id+","+s);

     JPanel panel  = new JPanel();
     f.add(panel);
     panel.add(label);
     //panel.add(table);
 }


}

/*
class thehandler  implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String CMS_id = "103";
       // String s = c1.getSelectedItem().toString();

        String url = "jdbc:mysql://localhost:3306/hr";
        String user = "root";
        String sqlQuery = "";
        if (s.equals("Student")) {
            sqlQuery = "select s.student_id CMS_id, s.std_name Student_name, s.fName Father_name, s.Semester Semester, (select department_name from students join departments using(department_id) where student_id = "+CMS_id+") Department, cr.course_id Course_ID, cr.name Your_Courses, s.adm_date Adm_date, s.ADDRESS Address from students s join course_reg cr using (student_id) where s.Student_ID = "+CMS_id;
        }
        else if (s.equals("Teacher"))
        {
            sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
        }
        else
        {
            sqlQuery = "Select * from employees where employee_id = 1";
        }

        JFrame f = new JFrame("Sukkur IBA University CMS");
        f.setVisible(true);
        f.setSize(800,600);

        JLabel label = new JLabel();
        label.setText("this is second frame:");
        JPanel panel  = new JPanel();
        f.add(panel);
        panel.add(label);
    }
}*/

