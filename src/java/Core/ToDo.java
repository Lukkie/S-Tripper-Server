/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Lukas
 */
@Entity
public class ToDo implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @Id
    private Long id;
    private String description, title;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Locatie locatie;
    
    @ManyToMany
    private List<Interesse> tags; 

    public List<Interesse> getTags() {
        return tags;
    }

    public void setTags(List<Interesse> tags) {
        this.tags = tags;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Locatie getLocatie(){
        return locatie;
    }
    
    public void setLocatie(Locatie l){
        this.locatie=l;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean tagsContainString(String s) {
        for (Interesse i: tags) {
            if (i.getInteresse().contains(s)) return true;
        }
        return false;
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
        if (!(object instanceof ToDo)) {
            return false;
        }
        ToDo other = (ToDo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.ToDo[ id=" + id + " ]";
    }
    
    public boolean removeTags(Interesse i) {
        return tags.remove(i);
    }
}
