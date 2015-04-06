/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Locatie;
import DAO.LocatieFacade;
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
public class LocatieView {

    @EJB
    private LocatieFacade locatieFacade;
    private Locatie locatie;
    
    /**
     * Creates a new instance of LocatieView
     */
    public LocatieView() {
        locatie=new Locatie();
    }

    public LocatieFacade getLocatieFacade() {
        return locatieFacade;
    }

    public Locatie getLocatie() {
        return locatie;
    }
    
    public int getAantalLocaties(){
        return locatieFacade.findAll().size();
    }
    
    public List<Locatie> getAlleLocaties(){
        return locatieFacade.findAll();
    }
    
    public void maakNieuweLocatie() throws IOException{
        locatieFacade.create(locatie);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderLocatie(Locatie locatie) throws IOException {
        
        
        locatieFacade.remove(locatie);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
