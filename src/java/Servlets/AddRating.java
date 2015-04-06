/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Gebruiker;
import Core.Journey;
import Core.JourneyRating;
import Core.Review;
import DAO.GebruikerFacade;
import DAO.JourneyFacade;
import DAO.JourneyRatingFacade;
import DAO.ReviewFacade;
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
public class AddRating extends HttpServlet {

    @EJB
    JourneyRatingFacade journeyRatingFacade;
    
    @EJB
    ReviewFacade reviewFacade;
    
    @EJB
    JourneyFacade journeyFacade;
    
    @EJB
    GebruikerFacade userFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            int rating = Integer.parseInt(request.getParameter("rating"));
            String tekst = request.getParameter("review"); // deze parameter niet meegeven als het geen review is!
            Long raterId = Long.parseLong(request.getParameter("raterId"));
            Long journeyId = Long.parseLong(request.getParameter("journeyId"));
            
            //voorlopig geen foutafhandeling, assumptie dat app alles correct doet
            
            Journey journey = journeyFacade.find(journeyId);
            Gebruiker rater = userFacade.find(raterId);
            if (journey.getMaker().equals(rater)) out.print("error: Je kan je eigen journey niet raten");
            else {
                for (JourneyRating j: journeyRatingFacade.findAll()) {
                    if (j.getJourney().equals(journey) && j.getRater().equals(rater)) {
                        journeyRatingFacade.remove(j);
                    }
                }

                JourneyRating journeyRating = new JourneyRating();
                journeyRating.setJourney(journey);
                journeyRating.setRater(rater);
                journeyRating.setScore(rating);

                if (tekst == null) {
                    journeyRatingFacade.create(journeyRating);
                    out.print("Journey Rating aangemaakt");
                }
                else {
                    Review review = new Review(journeyRating, tekst);
                    reviewFacade.create(review);
                    out.print("Journey Review aangemaakt");
                }
            }
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
