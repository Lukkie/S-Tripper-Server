/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.JourneyRating;
import DAO.JourneyRatingFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author JelmerMAC
 */
@ManagedBean
@RequestScoped
public class JourneyRatingView {

    @EJB
    private JourneyRatingFacade journeyRatingFacade;
    private JourneyRating journeyRating;
    
    public JourneyRatingView() {
        journeyRating=new JourneyRating();
    }
    
    public JourneyRating getJourneyRating(){
        return journeyRating;
    }
    
    public int getAantalJourneyRatings(){
        return journeyRatingFacade.findAll().size();
    }
    
    public List<JourneyRating> getAlleJourneyRatings(){
        return journeyRatingFacade.findAll();
    }
    
    public void maakNieuweJourneyRating() throws IOException{
        journeyRatingFacade.create(journeyRating);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    public void verwijderJourneyRating(JourneyRating journeyRating) throws IOException {
        journeyRatingFacade.remove(journeyRating);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
}
