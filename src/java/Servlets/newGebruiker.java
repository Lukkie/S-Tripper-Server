/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Gebruiker;
import DAO.GebruikerFacade;
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
public class newGebruiker extends HttpServlet {

    @EJB
    GebruikerFacade gebruikerFacade;
    
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

            out.print("test");
            out.flush();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");

            Gebruiker gebruiker = new Gebruiker();
            if(username != null){
                gebruiker.setUsername(username);
            } else {
                out.print("Fout bij username");
                out.flush();
            }
            if(password != null){
                gebruiker.setPaswoord(password);
            } else {
                out.print("Fout bij password");
                out.flush();
            }
            if(firstname != null){
                gebruiker.setVoornaam(firstname);
            } else {
                out.print("Fout bij firstname");
                out.flush();
            }
            if(lastname != null){
                gebruiker.setAchternaam(lastname);
            } else {
                out.print("Fout bij lastname");
                out.flush();
            }
            if(email != null){
                gebruiker.setEmail(email);
            } else {
                out.print("Fout bij email");
                out.flush();
            }

            out.println(gebruiker.getUsername() + "; " + gebruiker.getPaswoord() + "; " + gebruiker.getVoornaam() + "; " 
                    + gebruiker.getAchternaam() + "; " + gebruiker.getEmail());

            if(gebruiker != null) {
                gebruikerFacade.create(gebruiker);
                out.print("Gelukt");
                out.flush();
            } else {
                out.print("Gebruiker bestaat niet");
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
