/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Gebruiker;
import Core.Journey;
import DAO.GebruikerFacade;
import DAO.JourneyFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lennart
 */
public class getJourneyFromUser extends HttpServlet {

    
    @EJB
    GebruikerFacade gebruikerFacade;
    
    @EJB
    JourneyFacade journeyFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            out.flush();

            String jsonArray = new String();
            String username = request.getParameter("username");

            Gebruiker gebruiker;
            gebruiker = gebruikerFacade.findByUsername(username);
            if(gebruiker != null){
                jsonArray += "\"journeys\":[";

                for(Journey journey : journeyFacade.findAll()){
                    out.print(journey.toString());
                    if(journey.getMaker().getId() == gebruiker.getId()){
                        // json nog niet compleet
                        jsonArray += "{\"makerName\":" + gebruiker.getUsername() + ", \"journeyName\":\""
                                + journey.getName() + "\", \"id\":" + journey.getId() + "},";
                    }
                }
                jsonArray += "]";
                out.print(jsonArray);
                out.flush();
             } else{
                out.println("De gebruiker is niet gevonden");
                out.flush();
            }
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
