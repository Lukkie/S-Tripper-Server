/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Lukas
 * 
 * 
 */

/* TODO:
 * WERKT NOG NIET!
 * 
 * -- Entity class kan niet afgeleid worden van een andere entity class --
 * -- Abstract class maken? --
*/


@Entity
public class Review extends JourneyRating {
    String reviewTekst;
    
    public Review(Gebruiker g, int s, String tekst) {
        super(g,s);
        this.reviewTekst = tekst;
    }
    
    public Review(JourneyRating j, String tekst) {
        super(j);
        this.reviewTekst = tekst;
    }
    
    public Review() {
        super(null, 0);
        this.reviewTekst = null;
    }

    public String getReviewTekst() {
        return reviewTekst;
    }

    public void setReviewTekst(String reviewTekst) {
        this.reviewTekst = reviewTekst;
    }
    
    
    
}
