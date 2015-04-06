/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Gebruiker;
import DAO.GebruikerFacade;
import DAO.JourneyFacade;
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
 * @author Lukas
 */
@ManagedBean
@RequestScoped
public class GebruikerView {

    @EJB
    private GebruikerFacade userFacade;
    private Gebruiker user;
    
    @EJB
    private JourneyFacade journeyFacade;
    private JourneyView journeyView;
    
       
    public GebruikerView() {
        user = new Gebruiker();
        journeyView = new JourneyView();
    }

    public Gebruiker getGebruiker() {
        return user;
    }

    public int getAantalGebruikers() {
        return userFacade.findAll().size();
    }
    
    public List<Gebruiker> getAlleGebruikers() {
        return userFacade.findAll();
    }
     
    public void maakNieuweGebruiker() throws IOException {  
        userFacade.create(user);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderGebruiker(Gebruiker user) throws IOException {
        /*
        // verwijder al zijn journeys
        for (Journey j: journeyFacade.findAll()) {
            if (j.getMaker().equals(user)) {
                journeyView.verwijderJourney(j);
            }
        }
        
        
        
        // verwijder gebruiker uit followers bij andere gebruikers
        for (Gebruiker g: userFacade.findAll()) {
            if (!g.equals(user)) {
                if (g.removeFollower(user)) {
                    userFacade.edit(g);
                }
            }
        }
        */
        
        userFacade.remove(user);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    
}
