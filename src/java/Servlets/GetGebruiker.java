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
 * @author Lukas
 */
public class GetGebruiker extends HttpServlet {

    @EJB
    private GebruikerFacade userFacade;
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
          
        PrintWriter out = response.getWriter();   
        String jsonObject = new String();
        
        // JSON schrijven
        /*jsonObject += "\"journeys\":[";
        for (Gebruiker j: userFacade.findAll()) {
            jsonObject += "{\"makerId\":" + j.getMaker().getId() + ", \"makerName\":\"" 
                    + j.getMaker().getUsername() + "\", \"name\":\""+j.getName()+"\", \"id\":"+j.getId()+"},";
        }
        if (journeyFacade.count() > 0) {
            jsonObject = jsonObject.substring(0,jsonObject.length()-1);
        }
        jsonObject += "]";*/
        
        Long id = Long.parseLong(request.getParameter("id")); // id = 0 als je alle gebruikers wilt.
        
        if (id != 0) {  // 1 gebruiker
            Gebruiker g = userFacade.find(id);
            jsonObject += "\"Gebruiker\":";
            jsonObject += "{\"Id\":" + id + 
                    ", \"Username\":\"" + g.getUsername() + 
                    "\", \"Voornaam\":\""+g.getVoornaam()+
                    "\", \"Achternaam\":\""+g.getAchternaam()+
                    "\", \"Email\":\""+g.getEmail()+
                    "\", \"following\":[";
            
            for (Gebruiker following: g.getFollowing()) {
                jsonObject += following.getId() + ", ";
            }
            if (!g.getFollowing().isEmpty()){
                jsonObject = jsonObject.substring(0,jsonObject.length()-2);
            }
           jsonObject += "]}";       
        }
        else {  // alle gebruikers
            jsonObject += "\"Gebruiker\":[";
            for (Gebruiker g: userFacade.findAll()) {
                
                jsonObject += "{\"Id\":" + id + 
                        ", \"Username\":\"" + g.getUsername() + 
                        "\", \"Voornaam\":\""+g.getVoornaam()+
                        "\", \"Achternaam\":\""+g.getAchternaam()+
                        "\", \"Email\":\""+g.getEmail()+
                        "\", \"Following\":[";

                for (Gebruiker following: g.getFollowing()) {
                    jsonObject += following.getId() + ", ";
                }
                if (!g.getFollowing().isEmpty()){
                    jsonObject = jsonObject.substring(0,jsonObject.length()-2);
                }
               jsonObject += "]}, ";
            }
            if (userFacade.count() > 0) {
                jsonObject = jsonObject.substring(0,jsonObject.length()-2);
            }
            jsonObject+= "]";
        }
        
        out.print(jsonObject);
        out.flush();
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
