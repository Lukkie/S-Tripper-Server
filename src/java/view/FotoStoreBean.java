/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
 
import Core.Foto;
import DAO.FotoFacade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.ejb.EJB;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.UploadedFile;
 
@ManagedBean
@RequestScoped
public class FotoStoreBean {
 
    
    private UploadedFile file;
    private String descr;
   
    
    // POST (store) file in the database
    public void storeImage() {
        // Create connection
        try {
            // Load driver
            Class.forName("com.mysql.jdbc.Driver");
            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Project_1?user=root&password=root");
            // Set autocommit to false to manage it by hand
            connection.setAutoCommit(false);
             
            // Create the statement object
            
            //TODO: Add description insertion to DB
            PreparedStatement statement = connection.prepareStatement("INSERT INTO foto (beschrijving, image) VALUES (?, ?)");
            //Set description
            statement.setString(1, descr);
            // Set file image
            statement.setBinaryStream(2, file.getInputstream());
             
            // Insert image to the database
            statement.executeUpdate();
            
            
            // Commit & close
            connection.commit();    // when autocommit=false
            connection.close();
             
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Upload success", file.getFileName() + " is uploaded."); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
             
        } catch (Exception e) {
            e.printStackTrace();
             
            // Add error message
            FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Upload error", e.getMessage()); 
            FacesContext.getCurrentInstance().addMessage(null, errorMsg);
        }
         
    }
 
    // Getter method
    public UploadedFile getFile() {
        return file;
    }
 
    // Setter method
    public void setFile(UploadedFile file) {
        this.file = file;
    }  

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    
    
}
