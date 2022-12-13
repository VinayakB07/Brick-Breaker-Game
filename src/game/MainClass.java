package game;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args){

        // creating a frame
        JFrame f=new JFrame();
        f.setTitle("Brick Breaker");
        f.setSize(700,600);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setResizable(false);

        //creating object of gameplay class
        Gameplay gameplay=new Gameplay();
        //adding gameplay to the frame
        f.add(gameplay);

    }
}
