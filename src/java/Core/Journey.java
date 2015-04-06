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
public class Journey implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @Id
    private Long id;

    public Journey(Gebruiker maker, String name) {
        this.maker = maker;
        this.name = name;
    }
       
    public Journey() {
        this.maker = null;
        this.name = null;
    }
   
    @ManyToOne
    private Gebruiker maker;
    
    private String name;
    
    public Gebruiker getMaker() {
        return maker;
    }

    public void setMaker(Gebruiker maker) {
        this.maker = maker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Journey)) {
            return false;
        }
        Journey other = (Journey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.Journey[ id=" + id + " ]";
    }
    
}
