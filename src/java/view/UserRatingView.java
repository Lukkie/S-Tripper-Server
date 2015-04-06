/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.UserRating;
import DAO.UserRatingFacade;
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
public class UserRatingView {

    @EJB
    private UserRatingFacade userRatingFacade;
    private UserRating userRating;
       
    public UserRatingView() {
        userRating = new UserRating();
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public int getAantalUserRatings() {
        return userRatingFacade.findAll().size();
    }
    
    public List<UserRating> getAlleUserRatings() {
        return userRatingFacade.findAll();
    }
    
    public void maakNieuweUserRating() throws IOException {     
        userRatingFacade.create(userRating);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderUserRating(UserRating userRating) throws IOException {
        userRatingFacade.remove(userRating);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
}
