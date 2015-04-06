/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
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
public class Gebruiker implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
    @Id
    private Long id;

    private String paswoord;
    
    //TODO: Username unique maken + validator toevoegen (Primefaces)
    @Column(name = "USERNAME", unique=true)  
    private String username;
    private String voornaam, achternaam;
    private String email;
    private String biografie;

    public String getBiografie() {
        return biografie;
    }

    public void setBiografie(String biografie) {
        this.biografie = biografie;
    }

    @ManyToMany
    private List<Gebruiker> following; // gebruiker volgt (cf. Instagram) een aantal mensen en hun trips
    // Let op bij JSON: stack overflow als je dit queried
    
    @ManyToMany
    private List<Interesse> interesses;
    



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
        if (!(object instanceof Gebruiker)) {
            return false;
        }
        Gebruiker other = (Gebruiker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Core.User[ id=" + id + " ]";
    }


    public Gebruiker() {
    }

    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Gebruiker> getFollowing() {
        return following;
    }

    public void setFollowing(List<Gebruiker> following) {
        this.following = following;
    }

    public List<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(List<Interesse> interesses) {
        this.interesses = interesses;
    }
    
    public boolean removeFollower(Gebruiker g) {
        return following.remove(g);
    }   
    
    public boolean removeInteresse(Interesse i) {
        return interesses.remove(i);
    }
    
    public void addFollower(Gebruiker g) {
        following.add(g);
    }
    
    public void addInteresse(Interesse i) {
        interesses.add(i);
    }
    
    public boolean hasFollowing(Gebruiker g) {
        for (Gebruiker follower: following) {
            if (follower.equals(g)) {
                return true;
            }
        }
        return false;
    }
     
    
}
