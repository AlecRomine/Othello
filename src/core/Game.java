/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEC
 */
public class Game {
    
    private ArrayList<Player> players;
    private Board board;
    private Player currentPlayer;
    
    
    
    public Game() 
    {
        initObjects();
    }
   
    private void initObjects()
    {
        board = new Board();
        
        createPlayers();
        
        board.setPlayers(players);
        
        currentPlayer = players.get(Constants.PLAYER_ONE);
        printPlayers();
    }
    //Prompts UI for players names and assignes them disc color
    private void createPlayers()
    {
        players = new ArrayList<>();
        
        
        for(int i = 0; i < Constants.MAX_PLAYERS ; i++)
        {
             String name = JOptionPane.showInputDialog(null,"Enter player's name");
             
             Player player = new Player();
             
             player.setName(name);
             
             if(i == Constants.PLAYER_ONE)
             {
             player.setDiscolor(Constants.DARK);
             } 
             else if(i == Constants.PLAYER_TWO)
             {
                 player.setDiscolor(Constants.LIGHT);
             }
             
             players.add(player); 
             
        }
        
    }
    //Prints name and color of players to the screen
    private void printPlayers()
    {
        System.out.println("The game has the following players:");
        
         for(Player player:players)
         {
             System.out.println("Player " + player.name + " is playing disc color " + player.discolor);
         }
    
    }
    
    public void calcaulateScore()
    {
        board.calculateScore();
        
        players.get(Constants.PLAYER_ONE).setScore(board.getDarkColor());
        
        players.get(Constants.PLAYER_TWO).setScore(board.getLightColor());
    }
    
    
    /**
     * @return the players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * @return the currentPlayer
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    
    
}
