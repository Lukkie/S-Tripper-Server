/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Core.Foto;
import Core.Tussenstop;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lukas
 */
@Stateless
public class TussenstopFacade extends AbstractFacade<Tussenstop> {
    @PersistenceContext(unitName = "Project_1PU")
    private EntityManager em;

    @EJB
    private FotoFacade fotoFacade;
    
    @EJB
    private JourneyFacade journeyFacade;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List <Tussenstop> getTussenstopsByJourney(Long id){
        List <Tussenstop> tussenstops = new LinkedList <Tussenstop> ();
        for(Tussenstop t : this.findAll()){
            if(t.getJourney().getId() == id){
                tussenstops.add(t);
            }
        }
        return tussenstops;
    }
    
    @Override
    public void remove(Tussenstop t) {
        for (Foto f: fotoFacade.findAll()) {
            if (f.getTussenstop().equals(t)) {
                fotoFacade.remove(f);
            }
        }
        
        
        getEntityManager().remove(getEntityManager().merge(t));
    }

    public TussenstopFacade() {
        super(Tussenstop.class);
    }
    
}
