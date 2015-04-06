/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Core.Gebruiker;
import Core.Journey;
import Core.UserRating;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lukas
 */
@Stateless
public class GebruikerFacade extends AbstractFacade<Gebruiker> {
    @PersistenceContext(unitName = "Project_1PU")
    private EntityManager em;
    
    @EJB
    private JourneyFacade journeyFacade;
    
    
    @EJB
    private UserRatingFacade userRatingFacade;
    

    public Gebruiker findByUsername(String username) {
        
        username = username.toLowerCase();
        for (Gebruiker g: findAll()) {
            if (g.getUsername().toLowerCase().equals(username)) return g;
        }
        return null;
        
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
   
    @Override
    public void remove(Gebruiker g) {
        // verwijder al zijn journeys
        for (Journey j: journeyFacade.findAll()) {
            if (j.getMaker().equals(g)) {
                journeyFacade.remove(j);
            }
        }

        for (Gebruiker user: findAll()) {
            if (!g.equals(user)) {
                if (user.removeFollower(g)) {
                    edit(user);
                }                
            }
        }
        
        for (UserRating u: userRatingFacade.findAll()) {
            if (u.getRater().equals(g) || u.getWordtGerate().equals(g)) {
                userRatingFacade.remove(u);
            }
        }
        
        
        getEntityManager().remove(getEntityManager().merge(g));
    }

    public GebruikerFacade() {
        super(Gebruiker.class);
    }
    
}
