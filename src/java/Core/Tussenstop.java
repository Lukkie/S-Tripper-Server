package Core;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
public class Tussenstop implements Serializable {
    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @Id
    private Long id;
    
    @ManyToOne
    private Journey journey;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    private ToDo todo;
    
    private int tijdsduur; // in minuten
    private String beschrijving, title;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public Tussenstop() {
    }
    
    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
    
    public String getBeschrijving() {
        return beschrijving;
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
        if (!(object instanceof Tussenstop)) {
            return false;
        }
        Tussenstop other = (Tussenstop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.Tussenstop[ id=" + id + " ]";
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public ToDo getTodo() {
        return todo;
    }

    public void setTodo(ToDo todo) {
        this.todo = todo;
    }

    public int getTijdsduur() {
        return tijdsduur;
    }

    public void setTijdsduur(int tijdsduur) {
        this.tijdsduur = tijdsduur;
    }
    
    
    
}
