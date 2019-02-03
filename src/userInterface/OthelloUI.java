                 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Game;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author ALEC
 */
public class OthelloUI extends JFrame{

   Game game;
   GameUI gameUI;
   BoardUI boardUI;

      
   public OthelloUI(Game newgame)
   {
       game = newgame;
       initComponents(game);
   }

    private void initComponents(Game game) {
        GameUI gameUi = new GameUI(game);
        BoardUI boardUi = new BoardUI(game,gameUi);
        
        //need to set dimensions for board still
        this.setPreferredSize(new Dimension(600,600));
        this.setMinimumSize(new Dimension(600,600));
        
        //App close on window close
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set UI to board sections
        this.add(gameUi, BorderLayout.NORTH);
        this.add(boardUi, BorderLayout.CENTER);
        
        //if put before layout it will look blank
        this.setVisible(true);
            
    }
    
}
