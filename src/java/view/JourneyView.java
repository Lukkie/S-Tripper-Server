/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;


import Core.Journey;
import Core.JourneyRating;
import Core.Tussenstop;
import DAO.JourneyFacade;
import DAO.JourneyRatingFacade;
import DAO.TussenstopFacade;
import Hulp.Stat;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Lukas
 */
@ManagedBean
@RequestScoped
public class JourneyView {

    @EJB
    private JourneyFacade journeyFacade;
    private Journey journey;
    
    @EJB
    private JourneyRatingFacade journeyRatingFacade;
    
    
    private BarChartModel animatedModel;
    
    public JourneyView() {
        journey = new Journey();
    }
    
    public BarChartModel getAnimatedModel() {
        createAnimatedModel();
        return animatedModel;
    }
    
    public Journey getJourney() {
        return journey;
    }

    public int getAantalJourneys() {
        return journeyFacade.findAll().size();
    }
    
    public List<Journey> getAlleJourneys() {
        return journeyFacade.findAll();
    }
    
    public void maakNieuweJourney() throws IOException {     
        journeyFacade.create(journey);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderJourney(Journey journey) throws IOException {
        /*
        for (JourneyRating j: journeyRatingFacade.findAll()) {
            if (j.getJourney().equals(journey)) {
                journeyRatingFacade.remove(j);
            }
        }
        
        for (Tussenstop t: tussenstopFacade.findAll()) {
            if (t.getJourney().equals(journey)) {
                tussenstopFacade.remove(t);
            }
        }
        */
        
        journeyFacade.remove(journey);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    private void createAnimatedModel() {
        animatedModel = new BarChartModel();
        
       HashMap<Journey, Stat> waarden = new HashMap<Journey, Stat>();

        for (JourneyRating jr: journeyRatingFacade.findAll()) {
            Stat stat;
            if (waarden.containsKey(jr.getJourney())) {
                stat = waarden.get(jr.getJourney());               
            }
            else {
                stat = new Stat();
            }
            stat.addScore(jr.getScore());
            waarden.put(jr.getJourney(), stat);
        }
        

        for (Entry<Journey, Stat> e: waarden.entrySet()) {
            ChartSeries journeys = new ChartSeries();
            journeys.setLabel(e.getKey().getName());
            journeys.set("Journey", e.getValue().getScore());
            animatedModel.addSeries(journeys);
        }
        
 
 
        
        animatedModel.setTitle("Journey scores");
        animatedModel.setAnimate(true);
        animatedModel.setLegendPosition("ne");
        
        Axis yAxis = animatedModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);
        yAxis.setLabel("Score");
    }
}
