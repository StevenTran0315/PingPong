/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.lang.Runnable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Object;
import javax.swing.InputMap;
import java.util.Random;
/**
 *
 * @author S331461152
 */
public class PingPong extends JPanel implements Runnable {

    Thread animate;
    static PingPong panel = new PingPong();
    static JFrame frame = new JFrame("Pong");    
    
    Random random = new Random();
    int p1y = 25;
    int p2y = 25;  
    int bally = 170;
    int ballx = 350;
    boolean dir = random.nextBoolean();
    
    int score1=0;
    int score2=0;
    int increase=1;
    Graphics gg;
    Font scoreFont = new Font ("Impact", 1, 30); 
    
  
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //for updating score
        gg=g;
        //change score font
        g.setFont (scoreFont);
        
        g.setColor(Color.black);
        g.fillRect(25, p1y, 25, 100);
        g.setColor(Color.black);
        g.fillRect(700, p2y, 25, 100);
        g.fillOval(ballx, bally, 20, 20);
        
        
        //Score 
        g.drawString(String.valueOf(score1), getWidth()/4,50);
        g.drawString(String.valueOf(score2), 563,50);
        
    }

    /**
     * @param args the command line arguments
     */
    public PingPong() {
        animate = new Thread(this);
        animate.start();
 
    }
      
    
  
    public void run() {
        Action upAction = new AbstractAction(){
              public void actionPerformed(ActionEvent e) {
                  p1y -=20;
                  if(p1y < 0){
                      p1y = 0;
                  }
                
                  panel.repaint();
              }
          };
        Action downAction = new AbstractAction(){
              public void actionPerformed(ActionEvent e) {
                  p1y +=20;
                 if (p1y >470){
                      p1y = 470;
                  }
                  panel.repaint();
              }
          };
        Action up2Action = new AbstractAction(){
              public void actionPerformed(ActionEvent e) {
                  p2y -=20;
                  if(p2y < 0){
                      p2y = 0;
                  }
  
                  panel.repaint();
              }
          };
        Action down2Action = new AbstractAction(){
              public void actionPerformed(ActionEvent e) {
                  p2y +=20;
               
                  if (p2y >470){
                      p2y = 470;
                  }
                  panel.repaint();
              }
          };

        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

        try {
            while (true) {
              
                inputMap.put(KeyStroke.getKeyStroke("W"), "upAction");
                actionMap.put("upAction", upAction);
                    
                inputMap.put(KeyStroke.getKeyStroke("S"), "downAction");
                actionMap.put("downAction", downAction);

                inputMap.put(KeyStroke.getKeyStroke("UP"), "up2Action");
                actionMap.put("up2Action", up2Action);
                    
                inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down2Action");
                actionMap.put("down2Action", down2Action);
                
                if(dir == false){
                    ballx += 1;
                }
                else{
                    ballx-= 1;
                }
                
                if (bally > p1y && bally < p1y+100 && ballx == 50){
                    dir = !dir;
                }
                
                if (bally > p2y && bally < p2y+100 && ballx == 680){
                    dir = !dir;
                }
                
                if (ballx < 0){
                    Restart();
                    s1();
                }
                
                else if (ballx > 750){
                    Restart();
                    s2();
                }
                panel.repaint();
                animate.sleep(3);

            }
        } catch (InterruptedException e) {

        }
    }
    
    private void Restart(){
        bally =  170;
        ballx = 350;
        dir =  random.nextBoolean();
    }
    
    public void s1(){
        score2=score2+increase;
        gg.drawString(String.valueOf(score2),getWidth()/2,50);
    }
    
    public void s2(){
        score1=score1+increase;
        gg.drawString(String.valueOf(score1),563,70);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        
        //JLabel score = new JLabel("0:0");
        //panel.add(score);

        frame.setSize(750, 600);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
