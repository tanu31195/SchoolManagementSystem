/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolmanagementsystem;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbOperations {

    ResultSet rs = null;

    private Connection DbOperations;

    public Connection connect() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connection Successesful");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection Failed" + e);
        }

        String url = "jdbc:mysql://localhost:3306/school";
        try {
            DbOperations = (Connection) DriverManager.getConnection(url, "root", "");
            System.out.println("Database Connected");
        } catch (SQLException se) {
            System.out.println("No Database" + se);
        }

        return DbOperations;
    }

    boolean addStudent(StudentDetails sd) {
        try {
            Connection con = new DbOperations().connect();;

            String query = "INSERT INTO student_details (stdid,firstname,lastname,age,degree,email,username,password)VALUES (?,?,?,?,?,?,?,?)";

            PreparedStatement pst = con.prepareStatement(query);

            pst.setInt(1, sd.getStdid());
            pst.setString(2, sd.getFirstname());
            pst.setString(3, sd.getLastname());
            pst.setInt(4, sd.getAge());
            pst.setString(5, sd.getDegree());
            pst.setString(6, sd.getEmail());
            pst.setString(7, sd.getUsername());
            pst.setString(8, sd.getPassword());

            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        }

    }

    int checkUsername(String username) {
        try {
            Connection con = new DbOperations().connect();

            String query = "SELECT username from student_details";

            PreparedStatement pst = con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                if (username.equals(rs.getString(1))) {
                    return 0;
                }
            }
            return 1;

        } catch (Exception e) {
            System.out.print(e);
            return 2;
        }

    }
}
