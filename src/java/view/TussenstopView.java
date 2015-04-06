/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Tussenstop;
import DAO.TussenstopFacade;
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
public class TussenstopView {

    @EJB
    private TussenstopFacade tussenstopFacade;
    private Tussenstop tussenstop;
    

       
    public TussenstopView() {
        tussenstop = new Tussenstop();
    }

    public Tussenstop getTussenstop() {
        return tussenstop;
    }

    public int getAantalTussenstops() {
        return tussenstopFacade.findAll().size();
    }
    
    public List<Tussenstop> getAlleTussenstops() {
        return tussenstopFacade.findAll();
    }
    
    public void maakNieuweTussenstop() throws IOException {     
        tussenstopFacade.create(tussenstop);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderTussenstop(Tussenstop tussenstop) throws IOException {   
        tussenstopFacade.remove(tussenstop);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
