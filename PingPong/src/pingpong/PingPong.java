/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;


import java.awt.*;
import javax.swing.*;
import javax.swing.InputMap;

/**
 *
 * @author S331461152
 */
public class PingPong extends JPanel implements Runnable {

    static Thread animate;
    static PingPong panel = new PingPong();
    static JFrame frame = new JFrame("Pong");
    static Player player = new Player();
    static Ball ball = new Ball();
    static Menu menu = new Menu();
    static Score score = new Score();

    Graphics gg;
    Font scoreFont = new Font("Impact", 1, 30);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //for updating score
        gg = g;
        //change score font
        g.setFont(scoreFont);

        g.setColor(Color.black);
        g.fillRect(25, player.p1y, 10, 100);
        g.setColor(Color.black);
        g.fillRect(685, player.p2y, 10, 100);
        g.fillOval(ball.ballx, ball.bally, 20, 20);
        //Score 
        g.drawString(String.valueOf(ball.score1), getWidth() / 4, 50);
        g.drawString(String.valueOf(ball.score2), 563, 50);

    }

    /**
     * @param args the command line arguments
     */
    public PingPong() {
        animate = new Thread(this);
        animate.start();
    }

    public void run() {
        InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = this.getActionMap();

            while (true) {
                
                inputMap.put(KeyStroke.getKeyStroke("W"), "upAction");
                actionMap.put("upAction", player.upAction);

                inputMap.put(KeyStroke.getKeyStroke("S"), "downAction");
                actionMap.put("downAction", player.downAction);

                inputMap.put(KeyStroke.getKeyStroke("UP"), "up2Action");
                actionMap.put("up2Action", player.up2Action);

                inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down2Action");
                actionMap.put("down2Action", player.down2Action);

                if (ball.bally >= player.p1y && ball.bally <= player.p1y + 100 && ball.ballx >= 15 && ball.ballx <= 35) {
                    ball.dir = !ball.dir;
                }

                if (ball.bally >= player.p2y && ball.bally <= player.p2y + 100 && ball.ballx >= 665 && ball.ballx <= 685) {
                    ball.dir = !ball.dir;
                }

                panel.repaint();
                
            }
  
    }

    public static void main(String[] args) {
        // TODO code application logic here
        while(true){
             menu.setVisible(true);
            if (menu.start) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.add(panel);

                    //JLabel score = new JLabel("0:0");
                    //panel.add(score);
                    frame.setSize(750, 600);
                    frame.setResizable(false);
                    frame.setVisible(true);

                    ball.start();
                    break;
                  
                }
            
        }
    }

}
