/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Core.Gebruiker;
import Core.Interesse;
import Core.ToDo;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lukas
 */
@Stateless
public class InteresseFacade extends AbstractFacade<Interesse> {
    @PersistenceContext(unitName = "Project_1PU")
    private EntityManager em;
    
    @EJB
    private GebruikerFacade userFacade;
    
    @EJB
    private ToDoFacade toDoFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public void remove(Interesse i) {
        
        for (Gebruiker g: userFacade.findAll()) {
            if (g.removeInteresse(i)) {
                userFacade.edit(g);
            }
        }
        
        for (ToDo t: toDoFacade.findAll()) {
            if (t.removeTags(i)) {
                toDoFacade.edit(t);
            }
        }
        
        
        
        getEntityManager().remove(getEntityManager().merge(i));
    }

    public InteresseFacade() {
        super(Interesse.class);
    }
    
}
