/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Tussenstop;
import DAO.JourneyFacade;
import DAO.TussenstopFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lennart
 */
public class GetTussenstopsJourney extends HttpServlet {
    
    @EJB
    TussenstopFacade tussenstopFacade;
    
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
            
            StringBuilder jsonArray = new StringBuilder();
            Long journeyId = Long.parseLong(request.getParameter("journeyId"));
            out.print(journeyId);
            out.flush();
            List <Tussenstop> tussenstops = tussenstopFacade.getTussenstopsByJourney(journeyId);
            if(tussenstops != null){
                jsonArray.append("\"tussenstops\":[");
                for(Tussenstop t : tussenstops){
                    jsonArray.append("{\"tussenstopId\":");
                    jsonArray.append(t.getId());
                    jsonArray.append(", \"tussenstopName\":\"");
                    jsonArray.append(t.getTitle());
                    System.out.println(t.getTitle());
                    jsonArray.append(", \"toDoName\":\"");
                    jsonArray.append(t.getTodo().getTitle());
                    jsonArray.append("},");
                }
                jsonArray.setCharAt(jsonArray.length()-1, ']');
                out.print(jsonArray.toString());
                out.flush();
            } else {
                out.println("Geen tussenstop gevonden");
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
