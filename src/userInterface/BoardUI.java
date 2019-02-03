/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Board;
import core.Constants;
import core.Disc;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author ALEC
 */
class BoardUI extends JPanel{
    
    private JButton[][] board;
    private BoardListener listener;
    private final Game game;
    private final GameUI gameUI;
    
    
    BoardUI(Game newgame, GameUI newgameUI)
    {
        this.game = newgame;
        this.gameUI = newgameUI;
        initComponents();
        listener.updateUi();
    }

    private void initComponents() {
        
        //set board sizes
        this.setPreferredSize(new Dimension(600,600));
        this.setMinimumSize(new Dimension(400,400));
        
        //set to gridlayout
        this.setLayout(new GridLayout(Constants.ROWS,Constants.COLUMNS));
        
        board = new JButton[Constants.ROWS][Constants.COLUMNS];
        listener = new BoardListener();
        
        for(int row = 0; row < Constants.ROWS; row++)
        {
            for(int col = 0; col<Constants.COLUMNS; col++)
            {
                board[row][col] = new JButton(); 
                board[row][col].putClientProperty("row", row);
                board[row][col].putClientProperty("col", col);
                board[row][col].putClientProperty("color", Constants.EMPTY);
                board[row][col].setBackground(Color.green);
                board[row][col].addActionListener(listener);
                
                this.add(board[row][col]);                
            }
        }        
    }

    private class BoardListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            if(ae.getSource() instanceof JButton)
            {
                JButton button = (JButton)ae.getSource();
                int row = (int)button.getClientProperty("row");
                int col = (int)button.getClientProperty("col");
                Color color = (Color)button.getClientProperty("color");
                
               
               if(isMoveValid(row,col,game.getCurrentPlayer().getDiscolor(),true)) 
               {
                   
                   changePlayer(); 
                   updateUi();
                   
                   if(isGameOver())
                   {                       
                       endGame();
                   }
                   else if(!hasMove(game.getCurrentPlayer().getDiscolor()))
                   {
                       JOptionPane.showMessageDialog(button,"There are no moves for this player ,so we're switching turns.");
                       changePlayer();
                       updateUi();
                   }
                }
                else
                {
                        JOptionPane.showMessageDialog(button,"Not a valid move.");
                }
            }
        }
        private void endGame()
        {
            if(game.getPlayers().get(Constants.PLAYER_ONE).getScore() > game.getPlayers().get(Constants.PLAYER_TWO).getScore())
                        {
                            
                            JOptionPane.showMessageDialog(null,game.getPlayers().get(Constants.PLAYER_ONE).getName()+" wins!!   "+
                                    game.getPlayers().get(Constants.PLAYER_ONE).getScore()+" Discs");
                            System.exit(1);
                        }
                        else if(game.getPlayers().get(Constants.PLAYER_ONE).getScore() < game.getPlayers().get(Constants.PLAYER_TWO).getScore())
                        {
                            JOptionPane.showMessageDialog(null,game.getPlayers().get(Constants.PLAYER_TWO).getName()+" wins!!   "+
                                    game.getPlayers().get(Constants.PLAYER_TWO).getScore()+" Discs");
                            System.exit(1);                            
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"It's a draw!");
                            System.exit(1);
                        }                      
                            
                    }
        
        //returns true if move is valid
        private boolean isMoveValid(int row, int col,Color color, boolean flip) 
        {   
            Disc[][] tempboard = game.getBoard().getBoard();
            
            if(tempboard[row][col].getDiscolor() != Constants.EMPTY)
            {
               return false;
            }
            else if(game.getBoard().isValidMove(row, col, color,flip))
            {
               return true;
            }
            
            return false;
        }

        private void changePlayer() {
            
            if(game.getCurrentPlayer() == game.getPlayers().get(Constants.PLAYER_ONE))
            {
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_TWO));
               
            }
            else
            {
                game.setCurrentPlayer(game.getPlayers().get(Constants.PLAYER_ONE));                
            }
             gameUI.setWhosturn(game.getCurrentPlayer().getName());
        }

        private void updateUi() 
        {
        //get the array of discs
        Disc[][] discs = game.getBoard().getBoard();
        ImageIcon disc = null;
        
        for(int row = 0; row < Constants.ROWS;row++)
        {
            for(int col = 0; col < Constants.COLUMNS; col++)
            {
                if(discs[row][col].getDiscolor() == Constants.DARK)
                {
                    disc = new ImageIcon( getClass().getResource("../images/BlackwitGreen.png"));
                    disc = imageResize(disc);
                    board[row][col].setIcon(disc);
                }
                else if(discs[row][col].getDiscolor() == Constants.LIGHT)
                {
                    disc = new ImageIcon(getClass().getResource("../images/GoldwitGreen.png"));
                    disc = imageResize(disc);
                    board[row][col].setIcon(disc);
                }
            }
        }
        
        gameUI.getScoreOne().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getScore()));
        gameUI.getScoreTwo().setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_TWO).getScore()));
        }
        
        private ImageIcon imageResize(ImageIcon icon)
        {
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(60,60,java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImage);
            return icon;
        }
        //returns true if player of color still has a move
        private boolean hasMove(Color color) 
        {
            Disc[][] tempboard = game.getBoard().getBoard();
            
            for(int i = 0;i < 8;i++)
            {
                for(int j = 0;j < 8;j++)
                {   
                    if(tempboard[i][j].getDiscolor() == Constants.EMPTY && isMoveValid(i,j,color,false))
                    {//System.out.println("move is valid ["+i+"]["+j+"}");
                        return true;                    
                    }
                }                    
            }            
            return false; 
        }
//returns true if game is over
        private boolean isGameOver() {        
            
            if((game.getPlayers().get(Constants.PLAYER_ONE).getScore()+game.getPlayers().get(Constants.PLAYER_TWO).getScore()) == 64)
            {
                return true;            
            }
            else if(!hasMove(Constants.DARK) && !hasMove(Constants.LIGHT))
            {
                return true;
            }
            
            return false;
        }
    }
}
    



