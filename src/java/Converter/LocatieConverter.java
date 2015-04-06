/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Core.Locatie;
import DAO.LocatieFacade;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author JelmerMAC
 */
@ManagedBean
@RequestScoped
public class LocatieConverter implements Converter{

    @EJB
    private LocatieFacade locatieFacade;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return locatieFacade.find(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is geen geldige Locatie-ID", submittedValue)), e);
        }
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Locatie) {
            return String.valueOf(((Locatie) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is geen geldige Locatie", modelValue)));
        }
    }
    
}
