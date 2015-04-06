/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Locatie;
import Core.ToDo;
import DAO.LocatieFacade;
import DAO.ToDoFacade;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author JelmerMAC
 */
@ManagedBean
@RequestScoped
public class ToDoView {

    @EJB
    private ToDoFacade toDoFacade;
    private ToDo toDo;
    
    @EJB
    private LocatieFacade locatieFacade;
    private Locatie locatie;
    
    public ToDoView() {
        toDo=new ToDo();
        locatie=new Locatie();
    }
    
    public ToDo getToDo(){
        return toDo;
    }
    
    public Locatie getLocatie() {
        return locatie;
    }
    
    public int getAantalToDos(){
        return toDoFacade.findAll().size();
    }
    
    public List<ToDo> getAlleToDos(){
        return toDoFacade.findAll();
    }
    
    public void maakNieuweToDo() throws IOException{
        locatieFacade.create(locatie);
        toDo.setLocatie(locatie);
        toDoFacade.create(toDo);
        
        //reload page
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderToDo(ToDo toDo) throws IOException {
        toDoFacade.remove(toDo);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
