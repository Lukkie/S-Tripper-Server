/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Core.Review;
import DAO.ReviewFacade;
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
public class ReviewView {

    @EJB
    private ReviewFacade reviewFacade;
    private Review review;
    
    public ReviewView() {
        review=new Review();
    }
    
    public Review getReview(){
        return review;
    }
    
    public int getAantalReviews(){
        return reviewFacade.findAll().size();
    }
    
    public List<Review> getAlleReviews(){
        return reviewFacade.findAll();
    }
    
    public void maakNieuweReview() throws IOException{
        reviewFacade.create(review);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
    
    public void verwijderReview(Review review) throws IOException {
        reviewFacade.remove(review);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
