/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.awt.Color;

/**
 *
 * @author ALEC
 */
public class Player {
    
    String name;
    Color discolor;
    private int score = Constants.TWO;
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the discolor
     */
    public Color getDiscolor() {
        return discolor;
    }

    /**
     * @param discolor the discolor to set
     */
    public void setDiscolor(Color discolor) {
        this.discolor = discolor;
    }

    void setScore(int newscore) {
    score = newscore;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    
    
}
