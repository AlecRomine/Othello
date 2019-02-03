/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import userInterface.OthelloUI;
import core.Game;

/**
 *
 * @author ALEC
 */
public class Othello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Game game = new Game();
        OthelloUI othelloui = new OthelloUI(game);
    }
    
}
