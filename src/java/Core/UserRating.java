/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Lukas
 */
@Entity
public class UserRating implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne
    private Gebruiker rater;
    
    @ManyToOne
    private Gebruiker wordtGerate;
    
    private int score;

    public Gebruiker getRater() {
        return rater;
    }

    public void setRater(Gebruiker rater) {
        this.rater = rater;
    }

    public Gebruiker getWordtGerate() {
        return wordtGerate;
    }

    public void setWordtGerate(Gebruiker wordtGerate) {
        this.wordtGerate = wordtGerate;
    }
    
        
    public UserRating() {
        this.rater = null;
        this.score = 0;
        this.wordtGerate = null;
    }
    
    public UserRating(Gebruiker g, int s, Gebruiker w) {
        this.rater = g;
        this.score = s;
        this.wordtGerate = w;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRating)) {
            return false;
        }
        UserRating other = (UserRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.UserRating[ id=" + id + " ]";
    }
    
}
