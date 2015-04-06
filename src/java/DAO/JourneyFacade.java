/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Core.Journey;
import Core.JourneyRating;
import Core.Tussenstop;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lukas
 */
@Stateless
public class JourneyFacade extends AbstractFacade<Journey> {
    @PersistenceContext(unitName = "Project_1PU")
    private EntityManager em;

    @EJB
    private TussenstopFacade tussenstopFacade;
    
    @EJB
    private JourneyRatingFacade journeyRatingFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void remove(Journey j) {
        // verwijder al zijn journeys
        for (Tussenstop t: tussenstopFacade.findAll()) {
            if (t.getJourney().equals(j)) {
                tussenstopFacade.remove(t);
            }
        }
        
        for (JourneyRating jr: journeyRatingFacade.findAll()) {
           if (jr.getJourney().equals(j)) {
               journeyRatingFacade.remove(jr);
           }
        }
        
        getEntityManager().remove(getEntityManager().merge(j));
    }

    public JourneyFacade() {
        super(Journey.class);
    }
    
}
