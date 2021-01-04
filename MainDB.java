import java.sql.DriverManager;
import java.sql.*;

public class MainDB {

    public static void main(String args[]) {   //Code in the actionPerformed
        GUI gui = new GUI();
/*
        String CMS_id = "1023";
        String s = "Student";
        //String s = combobox1.getSelectedItem().toString();

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
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, "");
           // sqlQuery = "Select * from employees where employee_id = 1";
            //sqlQuery = "select e1.*, (select name from employees join courses USING(course_ID) where EMPLOYEE_ID = " + CMS_id + ") Your_course, CONCAT(e2.first_name,' ',e2.LAST_NAME) Manager_of_dept  from employees e1 join  employees e2 ON e1.manager_id = e2.EMPLOYEE_ID where e1.EMPLOYEE_ID = " + CMS_id;
            PreparedStatement st = conn.prepareStatement(sqlQuery);
            ResultSet rs = st.executeQuery(sqlQuery);
            ResultSetMetaData Rmd = rs.getMetaData();
            int columnsNumber = Rmd.getColumnCount();
            System.out.println("Result: "+rs.isBeforeFirst());
            while (rs.next())
            {
                for (int i = 1; i <= columnsNumber; i++)
                {
                    if (i > 1) System.out.print("  ");
                    System.out.print(" " + Rmd.getColumnName(i));
                }

                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print("    ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " ");
                }
                System.out.println("\n========================================");
            /* while (rs.next())
            {
                System.out.println(rs.getString(""));
            }*
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }*/
    }
}
