package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements ActionListener, KeyListener {
    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private int ballpointX=120;
    private int ballpointY=350;
    private int ballDirX=-1;
    private int ballDirY=-2;
    private int playerX=350;
    private MapGenerator map;

    // constructor
    public Gameplay(){

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        int delay = 1;
        Timer timer = new Timer(delay, this);
        timer.start();
        map=new MapGenerator(3,7);
    }

    public void paint(Graphics g){

    // setting background
    g.setColor(Color.black);
    g.fillRect(0,0,700,600);

    // creating a platform for ball to land
    g.setColor(Color.GREEN);
    g.fillRect(playerX,550,100,8);

    // creating a ball
    g.setColor(Color.white);
    g.fillOval(ballpointX, ballpointY,20,20);

    // creating bricks
    map.draw((Graphics2D) g);

    // score board
    g.setColor(Color.WHITE);
    g.setFont(new Font("serif",Font.BOLD,20));
    g.drawString("Score : "+score,550,30);

    // when ball does not touches the platform
    if(ballpointY >=570){

            play=false;
            ballDirX =0;
            ballDirY =0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("GAME OVER!!  score : "+score,150,300);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter To Restart.",230,350);
        }

    // when there are no more bricks
    if(totalBricks<=0){

            play=false;
            ballDirX =0;
            ballDirY =0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("* * YOU WON * *  score : "+score,200,300);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString("Press Enter To Restart.",230,350);
        }
    }
    // movement of platform
    private void moveLeft(){
        play=true;
        playerX-=20;
}
    private void moveRight(){
        play=true;
        playerX+=20;
    }
    // movement of ball
    @Override
    public void actionPerformed(ActionEvent e) {
    if(play){
    if(ballpointX <=0){
        ballDirX =-ballDirX;
    }
    if(ballpointY <=0){
        ballDirY =-ballDirY;
    }
    if(ballpointX >=670){
        ballDirX =-ballDirX;
    }
    Rectangle ballRect=new Rectangle(ballpointX, ballpointY,20,20);
    Rectangle paddleRect=new Rectangle(playerX,550,100,8);
    if(ballRect.intersects(paddleRect)){
        ballDirY =-ballDirY;
    }
    // when ball touches the brick
    A:for(int i=0;i<map.map.length;i++){
      for(int j=0;j<map.map[0].length;j++){
            if(map.map[i][j]>0){
                int width=map.brickWidth;
                int height= map.brickHeight;
                int brickXpos=80+(j*width);
                int brickYpos=50+(i*height);
                Rectangle brickRect=new Rectangle(brickXpos,brickYpos,width,height);

            if(ballRect.intersects(brickRect)){
                 map.setBrick(0,i,j);
                 totalBricks--;
                 score+=5;
                 if(ballpointX +19<=brickXpos|| ballpointX +1>=brickXpos+width){
                    ballDirX =-ballDirX;
                 }
                 else{
                    ballDirY =-ballDirY;
                 }
                    break A;
                }
            }
        }
    }
    ballpointX += ballDirX;
    ballpointY += ballDirY;
    }
    repaint();
    }
    // when keys are pressed
    @Override
    public void keyPressed(KeyEvent e) {
    if(e.getKeyCode()==KeyEvent.VK_LEFT){
        if(playerX<=0)playerX=0;
        else moveLeft();
    }
    if(e.getKeyCode()==KeyEvent.VK_RIGHT){
        if(playerX>=600)playerX=600;
        else moveRight();
    }
    if(e.getKeyCode()==KeyEvent.VK_ENTER){
        if(!play){

            score=0;
            totalBricks=21;
            ballpointX =120;
            ballpointY =350;
            ballDirX =-1;
            ballDirY =-2;
            playerX=320;
            map=new MapGenerator(3,7);
        }
    }
    repaint();
    }

    //  not needed
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
