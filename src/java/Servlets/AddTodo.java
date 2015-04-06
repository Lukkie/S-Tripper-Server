/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Interesse;
import Core.Locatie;
import Core.ToDo;
import DAO.InteresseFacade;
import DAO.LocatieFacade;
import DAO.ToDoFacade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lukas
 */
public class AddTodo extends HttpServlet {

    @EJB
    ToDoFacade toDoFacade;
    
    @EJB
    LocatieFacade locatieFacade;
    
    @EJB
    InteresseFacade interesseFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(request.getReader());
            
            ToDo todo = new ToDo();

            String description = (String) jsonObject.get("description");
            String title = (String) jsonObject.get("title");
            todo.setDescription(description);
            todo.setTitle(title);
            
            
            Locatie l = new Locatie();
            JSONObject locatie = (JSONObject)jsonObject.get("locatie");
            String naamLocatie = (String) locatie.get("naam");
            double xLocatie = (double) locatie.get("x");
            double yLocatie = (double) locatie.get("y");
            l.setNaam(naamLocatie);
            l.setXPos(xLocatie);
            l.setYPos(yLocatie);
            locatieFacade.create(l);
            todo.setLocatie(l);
            
            // Enkel het ID van de tags worden verstuurd
            List<Interesse> tags = new ArrayList<Interesse>();
            JSONArray todoTags = (JSONArray) jsonObject.get("tags");
            Iterator<Long> iterator = todoTags.iterator();
            while (iterator.hasNext()) {
                tags.add(interesseFacade.find(iterator.next()));
            }
            todo.setTags(tags);
            
            
            toDoFacade.create(todo);
            out.print("todo aangemaakt");
            out.flush();
            
            
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
