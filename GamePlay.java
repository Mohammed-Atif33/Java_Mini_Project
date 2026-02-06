import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
// import java.util.*;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay  extends JPanel implements KeyListener , ActionListener{
    private boolean play = false;
    private int score = 0;

    private int totalBricks = 21;

    private Timer timer;
    private int delay = 6;

    private int playerX = 310;

    private int ballposX = 120; 
    private int ballposY = 350;
    
    private int ballXdir = -3;
    private int ballYdir = -3;

    private Bricks map;

    public GamePlay(){
        map = new Bricks(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics graphics){

        //Background
        graphics.setColor(Color.BLACK);
        graphics.fillRect(1,1, 692, 592);
        
        //Drawing Brick
        map.drawBricks((Graphics2D)graphics);

        //Borders
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 5, 592);
        graphics.fillRect(0, 0, 692, 5);
        graphics.fillRect(681, 0, 5, 592);

        //Paddle
        graphics.setColor(Color.WHITE);
        graphics.fillRect(playerX, 550, 120, 8);

        //Ball
        graphics.setColor(Color.RED);
        graphics.fillOval(ballposX, ballposY, 20, 20);

        if(totalBricks <= 0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("serif", Font.BOLD , 30));
            graphics.drawString("YOU WIN", 167, 297);

            graphics.setFont(new Font("serif", Font.BOLD , 30));
            graphics.drawString("PRESS ENTER TO RESTART ", 167, 350);

        }

        if(ballposY > 570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("serif", Font.BOLD , 30));
            graphics.drawString("GAME OVER ", 167, 297);

            graphics.setFont(new Font("serif", Font.BOLD , 30));
            graphics.drawString("PRESS ENTER TO RESTART ", 167, 350);

        }

        
        // Score
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("serif" , Font.BOLD , 25));
        graphics.drawString(""+score, 500, 30);
        
        graphics.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) { 
        timer.start();

        if(play){
            
            if (new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballYdir = -ballYdir;
            }

            for (int i = 0; i < map.map.length; i++) {
                A : for (int j = 0; j < map.map[0].length; j++) {
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle Rectball = new Rectangle(ballposX , ballposY , 20 ,20);
                        Rectangle breakRect = rect;

                        if (Rectball.intersects(breakRect)) {
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score += 5;

                            if (ballposX + 19 <= Rectball.x || ballposX >= Rectball.x + Rectball.width) {
                                ballXdir = - ballXdir;
                            } else {
                                ballYdir = -ballYdir;
                            }

                            break A;
                        }
                    }
                }
            }

            ballposX+= ballXdir;
            ballposY+= ballYdir;

            if(ballposX < 0){
                ballXdir = -ballXdir;
            }

            if(ballposY < 0){
                ballYdir = -ballYdir;
            }

            if(ballposX > 670){
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 535){
                playerX = 535;
            }
            else{
                moveRight();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT){
          if(playerX <= 10){
                playerX = 10;
            }
            else{
                moveLeft();
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if (!play) {
                play = true;
                ballXdir = -3;
                ballYdir = -3;
                ballposX = 121;
                ballposY = 350;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new Bricks(3, 7);

                repaint(); 

            }
        }
    }

    public void moveRight(){
        play = true;
        playerX+=20;
    }

    public void moveLeft(){
        play = true;
        playerX-=20;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}