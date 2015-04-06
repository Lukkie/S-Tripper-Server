/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Interesse;
import Core.ToDo;
import DAO.ToDoFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Lukas
 */
public class FindTodos extends HttpServlet {

    @EJB
    ToDoFacade toDoFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.flush();
            
            JSONArray todos = new JSONArray();
            String searchEntry = request.getParameter("searchEntry");
            /*
                searchEntry = null als we alle todo's in de database willen tonen
                anders houden we rekening met de searchEntry.
            */
            
            //if (searchEntry == null) { // of searchEntry.equals("")??? GEEN ARGUMENT
            for (ToDo t: toDoFacade.findAll()) {
                if (searchEntry == null || t.getTitle().contains(searchEntry) || t.getLocatie().getNaam().contains(searchEntry)|| t.tagsContainString(searchEntry)) {
                    JSONObject json = new JSONObject();
                    json.put("id", t.getId());
                    json.put("title", t.getTitle());
                    json.put("description", t.getDescription());


                    JSONObject locatie = new JSONObject();
                    locatie.put("id",t.getLocatie().getId());
                    locatie.put("naam",t.getLocatie().getNaam());
                    locatie.put("x",t.getLocatie().getXPos());
                    locatie.put("y",t.getLocatie().getYPos());
                    json.put("locatie", locatie);

                    JSONArray tags = new JSONArray();
                    for (Interesse i: t.getTags()) {
                        JSONObject interesse = new JSONObject();
                        interesse.put("id", i.getId());
                        interesse.put("interesse", i.getInteresse());
                        tags.add(interesse);
                    }
                    json.put("tags", tags);

                    todos.add(json);

                }
            }

            
            out.print(todos);
            out.flush();
            
               
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
