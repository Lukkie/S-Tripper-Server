/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Hulp;


/**
 *
 * @author Lukas
 */
public class Stat implements Comparable<Stat> {
    
    private int aantalRatings;
    private int totaal;
    
    public Stat() {
        aantalRatings = 0;
        totaal = 0;
    }
    
    public void addScore(int rating) {
        aantalRatings++;
        totaal+=rating;
    }
    
    public double getScore() {
        return (double)totaal / (double)aantalRatings;
    }

    
    @Override
    public int compareTo(Stat t) {
        if (this.getScore() - t.getScore() < 0) return -1;
        else if (this.getScore() - t.getScore() > 0) return 1;
        else return 0;
                
    }
    
}
