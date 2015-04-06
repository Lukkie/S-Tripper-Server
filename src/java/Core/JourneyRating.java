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
import javax.persistence.Inheritance;
import static javax.persistence.InheritanceType.JOINED;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author Lukas
 */
@Inheritance(strategy=JOINED)
@Entity
public class JourneyRating implements Serializable {
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
    
    
    
    private int score;
    
    @ManyToOne
    private Gebruiker rater;
    
    @ManyToOne
    private Journey journey;
    
    public JourneyRating(Gebruiker g, int s) {
        this.score = s;
        this.rater = g;
    }
    
    public JourneyRating() {
        this.score = 0;
        this.rater = null;
    }
    
    public JourneyRating(JourneyRating j) {
        this.id = j.getId();
        this.journey = j.getJourney();
        this.rater = j.getRater();
        this.score = j.getScore();
    }
    
    public void setScore(int s) {
        this.score = s;
    }
    
    public int getScore() {
        return score;
    }
    

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public Gebruiker getRater() {
        return rater;
    }

    public void setRater(Gebruiker nieuw){
        this.rater=nieuw;
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
        if (!(object instanceof JourneyRating)) {
            return false;
        }
        JourneyRating other = (JourneyRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.JourneyRating[ id=" + id + " ]";
    }
    
}
