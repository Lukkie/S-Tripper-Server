/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Gebruiker;
import Core.Interesse;
import DAO.GebruikerFacade;
import DAO.InteresseFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Lukas
 */
@ManagedBean
@RequestScoped
public class InteresseView {

    @EJB
    private InteresseFacade interesseFacade;
    private Interesse interesse;
    
    @EJB
    private GebruikerFacade userFacade;
    
    
    private PieChartModel pieModel;
       
    public InteresseView() {
        interesse = new Interesse();
    }

    public Interesse getInteresse() {
        return interesse;
    }
    
    public PieChartModel getPieModel() {
        createPieModel();
        return pieModel;
    }

    public int getAantalInteresses() {
        return interesseFacade.findAll().size();
    }
    
    public List<Interesse> getAlleInteresses() {
        return interesseFacade.findAll();
    }
    
    public void maakNieuweInteresse() throws IOException {     
        interesseFacade.create(interesse);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderInteresse(Interesse interesse) throws IOException {
        /*
        for (Gebruiker g: userFacade.findAll()) {
            if (g.removeInteresse(interesse)) {
                userFacade.edit(g);
            }
        }
        
        for (ToDo t: toDoFacade.findAll()) {
            if (t.removeTags(interesse)) {
                toDoFacade.edit(t);
            }
        }
        */
        interesseFacade.remove(interesse);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        /* 
        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);
        */
        
        HashMap<Interesse, Integer> waarden = new HashMap<Interesse, Integer>();
        
        
        for (Gebruiker g: userFacade.findAll()) {
            for (Interesse i: g.getInteresses()) {
                if (waarden.containsKey(i)) {
                    waarden.put(i, waarden.get(i)+1);
                }
                else waarden.put(i, 1);
            }
        }
        
        for (Entry<Interesse, Integer> e: waarden.entrySet()) {
            pieModel.set(e.getKey().getInteresse(), e.getValue());
        }
        
        
        pieModel.setTitle("Interesse spreiding bij gebruikers");
        pieModel.setLegendPosition("e");
        pieModel.setShowDataLabels(true);
    }
    
}
