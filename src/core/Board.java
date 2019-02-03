package core;

import core.Game;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JOptionPane;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ALEC
 */
public class Board {
    
    private Disc[][] board;
    private int darkColor;
    private int lightColor;
    private ArrayList <Player> players;
    
    public Board()
    {
        initObjects();
    }

    /**
     * @return the board
     */
    public Disc[][] getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Disc[][] board) {
        this.board = board;
    }
    //Creates 2D array to rep board and constructs
    //Disc instance on each board tile
    private void initObjects()
    {
        board = new Disc[Constants.ROWS][Constants.COLUMNS];
        
        //Initilie each disk in the board
        for(int row = 0; row < Constants.ROWS; row++)
        {
            for(int col = 0; col < Constants.COLUMNS; col++ )
            {
                board[row][col] = new Disc();
                board[row][col].setDiscolor(Constants.EMPTY);
            }
        } 
        
        //Sets the board for the start of game
        board[3][3].setDiscolor(Constants.LIGHT);
        board[3][4].setDiscolor(Constants.DARK);
        board[4][3].setDiscolor(Constants.DARK);
        board[4][4].setDiscolor(Constants.LIGHT);
        
    }
    
    public void calculateScore()
    {
        darkColor = 0;
        lightColor = 0;        
    for(int row = 0; row < Constants.ROWS; row++)
        {
            for(int col = 0; col < Constants.COLUMNS; col++ )
            {
                if (board[row][col].getDiscolor() == Constants.DARK)
                {
                this.darkColor++;
                }
                else if(board[row][col].getDiscolor() == Constants.LIGHT)
                {
                this.lightColor++;
                }                
            }
        }
    players.get(Constants.PLAYER_ONE).setScore(darkColor);
    players.get(Constants.PLAYER_TWO).setScore(lightColor);
    }
    
    public boolean isValidMove(int row, int col, Color color, boolean flip){
    boolean isvalid = false;
        
    if(checkUp(row,col,color,flip))
        isvalid = true;
    
    if(checkUpLeft(row,col,color,flip))
        isvalid = true;
    
    if(checkLeft(row,col,color,flip))
       isvalid = true;
    
    if(checkLeftDown(row,col,color,flip))
        isvalid = true;
    
    if(checkDown(row,col,color,flip))
       isvalid = true;
    
    if (checkDownRight(row,col,color,flip))
        isvalid = true;
    
    if (checkRight(row,col,color,flip))
        isvalid = true;
    
    if (checkUpRight(row,col,color,flip))
        isvalid = true;
    
    
    calculateScore();
       
    return isvalid;
    }
    /**
     * @return the darkColor
     */
    public int getDarkColor() {
        return darkColor;
    }

    /**
     * @param darkColor the darkColor to set
     */
    public void setDarkColor(int darkColor) {
        this.darkColor = darkColor;
    }

    /**
     * @return the lightColor
     */
    public int getLightColor() {
        return lightColor;
    }

    /**
     * @param lightColor the lightColor to set
     */
    public void setLightColor(int lightColor) {
        this.lightColor = lightColor;
    }

    /**
     * @return the players
     */
    public ArrayList <Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players to set
     */
    public void setPlayers(ArrayList <Player> players) {
        this.players = players;
    }

    private boolean checkUp(int row, int col, Color color, boolean flip)
    {
    int cRow = row-1;
    int cCol = col;
    boolean isvalid = false;
    boolean matchfound = false;
    int discsFlipped = 0;
    
    if(row > 0)
    {
        while(cRow >= 0 && !matchfound)
        {
            if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
            {
                return false;
            }
            else if(board[cRow][cCol].getDiscolor() == color)
            {
                matchfound = true;
            }
            else
            {
            cRow--;
            discsFlipped++;
            }
        }
        
        if(matchfound && cRow != row-1)
        {   
            isvalid = true;
            if(flip)
            {
                do
                {   cRow++;
                    discsFlipped--;
                    board[cRow][cCol].setDiscolor(color);
                }while(discsFlipped >= 0);
            }
        }
            
    }
    
    return isvalid;
    }

    private boolean checkUpLeft(int row, int col, Color color, boolean flip)
    {
        int cRow = row-1;
        int cCol = col-1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(row > 0 && col > 0)
        {
            while(cRow >= 0 && cCol >= 0 && !matchfound)
            {   
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;                    
                }
                else
                {
                cRow--;
                cCol--;
                discsFlipped++;
                }
            }
            
            if(matchfound && cRow != row-1 && cCol != col-1)
            {
                if(flip)
                {    
                    isvalid = true;
                    do
                    {   cRow++;
                        cCol++;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }
            
        }
        
        return isvalid;
    }

    private boolean checkLeft(int row, int col, Color color, boolean flip) 
    {
        int cRow = row;
        int cCol = col-1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(col > 0)
        {
            while(cCol >= 0 && !matchfound)
            {
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;                    
                }
                else
                {
                cCol--;
                discsFlipped++;
                }
            }
            
            if(matchfound && cCol != col-1)
            {   
                if(flip)
                {
                    isvalid = true;
                    do
                    {   
                        cCol++;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }            
        }        
    return isvalid;
    }

    private boolean checkLeftDown(int row, int col, Color color, boolean flip) 
    {
        int cRow = row+1;
        int cCol = col-1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(row < 7 && col > 0)
        {
            while(cRow <= 7 && cCol >=0 && !matchfound)
            {
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;
                }
                else
                {
                cCol--;    
                cRow++;
                discsFlipped++;
                }
            }
            
            if(matchfound && cRow != row+1 && cCol != col-1)
            {
                isvalid = true;
                if(flip)
                {
                    do
                    {   
                        cRow--;
                        cCol++;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }            
        }        
    return isvalid;
    }

    private boolean checkDown(int row, int col, Color color , boolean flip) 
    {
        int cRow = row+1;
        int cCol = col;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(row < 7)
        {
            while(cRow <= 7 && !matchfound)
            {   
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;
                }
                else
                {
                cRow++;
                discsFlipped++;
                }
            }
            
            if(matchfound && cRow != row+1)
            {
                isvalid = true;        
                if(flip)
                {
                    do
                    {   cRow--;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }            
        }        
    return isvalid;
    }

    private boolean checkDownRight(int row, int col, Color color , boolean flip) 
    {
        int cRow = row+1;
        int cCol = col+1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(row < 7 && col < 7)
        {
            while(cRow <= 7 && cCol <= 7 && !matchfound)
            {   
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;
                }
                else
                {
                cRow++;
                cCol++;
                discsFlipped++;
                }
            }
            
            if(matchfound && cRow != row+1 && cCol != col+1)
            {
                isvalid = true;      
                if(flip)
                {
                    do
                    {   cRow--;
                        cCol--;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }            
        }        
    return isvalid;
    }

    private boolean checkRight(int row, int col, Color color, boolean flip) 
    {
        int cRow = row;
        int cCol = col+1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(col < 7)
        {
            while(cCol <= 7 && !matchfound)
            {   
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;
                }
                else
                {
                cCol++;
                discsFlipped++;
                }
            }
            
            if(matchfound && cCol != col+1)
            {
                isvalid = true;                                
                if(flip)
                {
                    do
                    {   
                        cCol--;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }            
        }        
    return isvalid;
    }

    private boolean checkUpRight(int row, int col, Color color , boolean flip)
    {
        int cRow = row-1;
        int cCol = col+1;
        boolean isvalid = false;
        boolean matchfound = false;
        int discsFlipped = 0;
        
        if(row > 0 && col < 7)
        {
            while(cRow >= 0 && cCol <= 7 && !matchfound)
            {   
                if(board[cRow][cCol].getDiscolor() == Constants.EMPTY)
                {
                    return false;
                }
                else if(board[cRow][cCol].getDiscolor() == color)
                {
                    matchfound = true;
                }
                else
                {
                cCol++;    
                cRow--;
                discsFlipped++;
                }
            }
            
            if(matchfound && cRow != row-1)
            {
                isvalid = true;
                if(flip)
                {
                    do
                    {   
                        cRow++;
                        cCol--;
                        discsFlipped--;
                        board[cRow][cCol].setDiscolor(color);
                    }while(discsFlipped >= 0);
                }
            }       
    }    
    return isvalid;
    }
}