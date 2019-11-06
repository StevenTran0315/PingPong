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

/**
 *
 * @author S331461152
 */
public class PingPong extends JPanel implements Runnable {

    Thread animate;
    static PingPong panel = new PingPong();
    static JFrame frame = new JFrame("Pong");     

    int p1y = 25;
    int p2y = 25;  
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(25, p1y, 25, 100);
        g.setColor(Color.black);
        g.fillRect(435, p2y, 25, 100);

        
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
                  p1y -=10;
                  if(p1y < 0){
                      p1y = 0;
                  }
                
                  panel.repaint();
              }
          };
        Action downAction = new AbstractAction(){
              public void actionPerformed(ActionEvent e) {
                  p1y +=10;
                 if (p1y >350){
                      p1y = 350;
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
               
                  if (p2y >350){
                      p2y = 350;
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
                panel.repaint();
                animate.sleep(5);

            }
        } catch (InterruptedException e) {

        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}

