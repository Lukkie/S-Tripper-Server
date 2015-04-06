/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import Core.Gebruiker;
import Core.UserRating;
import DAO.GebruikerFacade;
import DAO.UserRatingFacade;
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
public class AddUserRating extends HttpServlet {

    @EJB
    UserRatingFacade userRatingFacade;
    
    @EJB
    GebruikerFacade userFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("raterid") != null && request.getParameter("wordtgerateid") != null && request.getParameter("score") != null) {
            
                Long raterid = Long.parseLong(request.getParameter("raterid")); 
                Long wordtgerateid = Long.parseLong(request.getParameter("wordtgerateid")); 
                int score = Integer.parseInt(request.getParameter("score"));
                
                Gebruiker rater = userFacade.find(raterid);
                Gebruiker wordtGerate = userFacade.find(wordtgerateid);
                if (rater == null) out.print("error: rater niet gevonden");
                else if (wordtGerate == null) out.print("error: wordtGerate niet gevonden");
                else if (rater.equals(wordtGerate)) out.print("error: Je kan jezelf niet raten");
                else {
                    // je kan maar 1 rating aan 1 persoon geven
                    for (UserRating u: userRatingFacade.findAll()) {
                        if (u.getRater().equals(rater) && u.getWordtGerate().equals(wordtGerate)) {
                            userRatingFacade.remove(u);
                        }
                    }                                      
                    
                    UserRating userRating = new UserRating();
                    userRating.setRater(rater);
                    userRating.setWordtGerate(wordtGerate);
                    userRating.setScore(score);
                    userRatingFacade.create(userRating);
                    out.print("Rating toegevoegd");
                }
                out.flush();
            
            }
            else {
                out.print("error: leeg veld");
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
