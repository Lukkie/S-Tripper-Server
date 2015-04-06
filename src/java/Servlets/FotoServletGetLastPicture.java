package Servlets;

/*
*
*
*GETS LAST PICTURE OUT OF DATABASE
*
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class FotoServletGetLastPicture extends HttpServlet {
 
    private static final long serialVersionUID = -6449908958106497977L;
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET last uploaded image
        try {
            // Image bytes
            byte[] imageBytes = null;
             
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Project_1?user=root&password=root");
             
            // Create the statement
            // This query is specific to MySQL, it returns only one row (using 'LIMIT 1') - the last uploaded file
            PreparedStatement statement = connection.prepareStatement("SELECT id, image FROM foto ORDER BY id DESC LIMIT 1");
             
            ResultSet rs = statement.executeQuery();
             
            while (rs.next()) { // This will run only once
                imageBytes = rs.getBytes("image");
            }
 
            // Close the connection
            connection.close();
            
            resp.getOutputStream().write(imageBytes);
            resp.getOutputStream().close();
             
        } catch (Exception e) {
            // Display error message
            resp.getWriter().write(e.getMessage());
            resp.getWriter().close();
        }
         
    }  
}
