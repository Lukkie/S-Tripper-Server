/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Core.ToDo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JelmerMAC
 */
@Stateless
public class ToDoFacade extends AbstractFacade<ToDo> {
    @PersistenceContext(unitName = "Project_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ToDoFacade() {
        super(ToDo.class);
    }
    
}
