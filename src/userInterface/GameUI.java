/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import core.Constants;
import core.Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author ALEC
 */
class GameUI extends JPanel 
{
    private Game game;
    private JLabel nameOne;
    private JLabel nameTwo;
    private JLabel scoreOne;
    private JLabel scoreTwo;
    private JLabel whosturn;
    
    public GameUI()
    {
        initComponents();
    }

    public GameUI(Game game) {
        this.game = game;
        initComponents();
    }

    private void initComponents() 
    {
        this.setPreferredSize(new Dimension(150,50));
        this.setMinimumSize(new Dimension(150,50));
        this.setBackground(Color.lightGray);
        
        ImageIcon discOne = new ImageIcon( getClass().getResource("../images/Black.png"));
        discOne = imageResize(discOne);
        
        nameOne = new JLabel();
        nameOne.setIcon(discOne);
        nameOne.setText(game.getPlayers().get(Constants.PLAYER_ONE).getName());
        nameOne.setMinimumSize(new Dimension(100,50));
        nameOne.setPreferredSize(new Dimension(150,50));
        nameOne.setBackground(Color.darkGray);
        nameOne.setFont(new Font("Sarif",Font.BOLD,18));
        nameOne.setBorder(new LineBorder(Color.black));
        nameOne.setHorizontalTextPosition(JLabel.RIGHT);
        
        ImageIcon discTwo = new ImageIcon( getClass().getResource("../images/Gold.png"));
        discTwo = imageResize(discTwo);
        
        nameTwo = new JLabel();
        nameTwo.setIcon(discTwo);
        nameTwo.setText(game.getPlayers().get(Constants.PLAYER_TWO).getName());
        nameTwo.setMinimumSize(new Dimension(100,50));
        nameTwo.setPreferredSize(new Dimension(150,50));
        nameTwo.setBackground(Color.darkGray);
        nameTwo.setFont(new Font("Sarif",Font.BOLD,18));
        nameTwo.setBorder(new LineBorder(Color.black));
        nameTwo.setHorizontalTextPosition(JLabel.RIGHT);
        
        scoreOne = new JLabel();
        scoreOne.setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_ONE).getScore()));
        scoreOne.setMinimumSize(new Dimension(50,50));
        scoreOne.setPreferredSize(new Dimension(50,50));
        scoreOne.setBackground(Color.darkGray);
        scoreOne.setBorder(new LineBorder(Color.black));
        scoreOne.setHorizontalTextPosition(JLabel.RIGHT);
        
        scoreTwo = new JLabel();
        scoreTwo.setText(String.valueOf(game.getPlayers().get(Constants.PLAYER_TWO).getScore()));
        scoreTwo.setMinimumSize(new Dimension(50,50));
        scoreTwo.setPreferredSize(new Dimension(50,50));
        scoreTwo.setBackground(Color.darkGray);
        scoreTwo.setBorder(new LineBorder(Color.black));
        scoreTwo.setHorizontalTextPosition(JLabel.RIGHT);
        
        whosturn = new JLabel();
        whosturn.setText(game.getCurrentPlayer().getName()+"'s turn");
        whosturn.setMinimumSize(new Dimension(100,50));
        whosturn.setPreferredSize(new Dimension(100,50));
        whosturn.setBackground(Color.darkGray);
        whosturn.setBorder(new LineBorder(Color.black));
        whosturn.setHorizontalTextPosition(JLabel.RIGHT);
        
        //add all four jlabel instances to the Jpanel
        //what ever order you add is the order they're presented on UI
        this.add(nameOne);
        this.add(scoreOne);
        this.add(nameTwo);
        this.add(scoreTwo);
        this.add(whosturn);
        
    }
    
    public JLabel getScoreOne() {
        return scoreOne;
    }

    public JLabel getScoreTwo() {
        return scoreTwo;
    }

    private ImageIcon imageResize(ImageIcon icon) {
    Image image = icon.getImage();
        Image newImage = image.getScaledInstance(60,60,java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);
        return icon;
    }

    /**
     * @return the whosturn
     */
    public JLabel getWhosturn() {
        return whosturn;
    }

    /**
     * @param whosturn the whosturn to set
     */
    public void setWhosturn(String whosturn) {
        this.whosturn.setText(whosturn+"'s turn");
        
    }

}