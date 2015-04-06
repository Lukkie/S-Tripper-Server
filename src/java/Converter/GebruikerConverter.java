/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converter;

import Core.Gebruiker;
import DAO.GebruikerFacade;
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
 * @author Lukas
 */
@ManagedBean
@RequestScoped
public class GebruikerConverter implements Converter {

    @EJB
    private GebruikerFacade userFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return userFacade.find(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s is geen geldige gebruiker ID", submittedValue)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Gebruiker) {
            return String.valueOf(((Gebruiker) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s is geen geldige gebruiker", modelValue)));
        }
    }

}