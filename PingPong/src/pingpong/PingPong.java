/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.InputMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author S331461152
 */
public class PingPong extends JPanel implements Runnable {
    
    //instantiating objects
    static Thread animate;
    static PingPong panel = new PingPong();
    static JFrame frame = new JFrame("Pong");
    static Player player = new Player();
    static Ball ball = new Ball();
    static Menu menu = new Menu();
    static Score score = new Score();
    static Instructions menu2 = new Instructions();
    Timer timer = new Timer();
    JButton jButton1 = new javax.swing.JButton();
    InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = this.getActionMap();
    Graphics gg;
    Font scoreFont = new Font("Impact", 1, 30);

    @Override //setting up graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //for updating score
        gg = g;
        //change score font
        g.setFont(scoreFont);
        
        //changing colour of graphics depending on user selection
        switch (menu.colour) {
            case "Black":
                g.setColor(Color.black);
                g.fillRect(25, player.p1y, 10, 100);
                g.fillRect(685, player.p2y, 10, 100);
                break;
            case "Grey":
                g.setColor(Color.gray);
                g.fillRect(25, player.p1y, 10, 100);
                g.fillRect(685, player.p2y, 10, 100);
                break;
            case "Purple":
                g.setColor(Color.magenta);
                g.fillRect(25, player.p1y, 10, 100);
                g.fillRect(685, player.p2y, 10, 100);
                break;
            case "Orange":
                g.setColor(Color.orange);
                g.fillRect(25, player.p1y, 10, 100);
                g.fillRect(685, player.p2y, 10, 100);
                break;
            case "Blue":
                g.setColor(Color.blue);
                g.fillRect(25, player.p1y, 10, 100);
                g.fillRect(685, player.p2y, 10, 100);
                break;
            default:
                break;
        }
        
        //changing obstacles depending on user map selection
        switch (menu.MapNum) {
            case 1:
                g.fillRect(350, 250, 7, 150);
                break;
            case 2:
                break;
            case 3:
                g.fillRect(350, 0, 7, 150);
                g.fillRect(350, 450, 7, 150);
                break;
            default:
                break;
        }
        
        //drawing ball
        g.fillOval(ball.ballx, ball.bally, 20, 20);

        //Score 
        g.drawString(String.valueOf(ball.score1), getWidth() / 4, 50);
        g.drawString(String.valueOf(ball.score2), 563, 50);
        
        //win conditions 
        if (ball.score1 == 7) {
            g.drawString("Player One Wins", 245, 220); //displays which player won
            ball.stop();                               //stops ball movement
            animate.suspend();
            TimerTask task = new TimerTask() {      
                public void run() {
                    System.exit(0);
                }
            };
            timer.schedule(task, 5000);    //closes the frame 5 seconds after the win condition is met

        } else if (ball.score2 == 7) {
            g.drawString("Player Two Wins", 245, 220);
            ball.stop();
            animate.suspend();
            TimerTask task = new TimerTask() {
                public void run() {
                    System.exit(0);
                }
            };
            timer.schedule(task, 5000);
        }

    }

    /**
     * @param args the command line arguments
     */
    public PingPong() {
        animate = new Thread(this);     //creating thread
        animate.start();             //starting thread     

    }

    public void run() {
        //gets key input from user (W, S, Up arrow, Down arrow)
        while (true) {

            inputMap.put(KeyStroke.getKeyStroke("pressed W"), "upAction");
            actionMap.put("upAction", player.upAction);

            inputMap.put(KeyStroke.getKeyStroke("released W"), "stopUp");
            actionMap.put("stopUp", player.stopUp);

            inputMap.put(KeyStroke.getKeyStroke("pressed S"), "downAction");
            actionMap.put("downAction", player.downAction);

            inputMap.put(KeyStroke.getKeyStroke("released S"), "stopDown");
            actionMap.put("stopDown", player.stopDown);

            inputMap.put(KeyStroke.getKeyStroke("pressed UP"), "up2Action");
            actionMap.put("up2Action", player.up2Action);

            inputMap.put(KeyStroke.getKeyStroke("released UP"), "stop2");
            actionMap.put("stop2", player.stop2Up);

            inputMap.put(KeyStroke.getKeyStroke("pressed DOWN"), "down2Action");
            actionMap.put("down2Action", player.down2Action);

            inputMap.put(KeyStroke.getKeyStroke("released DOWN"), "stop2Down");
            actionMap.put("stop2Down", player.stop2Down);
            
            //if key is pressed call method to move paddle accordingly
            if (player.p1up) {
                player.paddleUp(1);      //calls method with according player number
            }

            if (player.p2up) {
                player.paddleUp(2);
            }

            if (player.p1down) {
                player.paddleDown(1);
            }

            if (player.p2down) {
                player.paddleDown(2);
            }
            ball.GameMode(menu.MapNum);    

            panel.repaint();   //update graphics
            try {
                animate.sleep(4);     
            } catch (InterruptedException ex) {
                Logger.getLogger(PingPong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        
        while (true) {
            menu.setVisible(true);        //opening menu frame
            menu.setResizable(false);    //preventing frame from being resized
            
            //creating play frame if the start button in menu is pressed
            if (menu.start) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.setSize(750, 600);
                frame.setResizable(false);
                frame.setVisible(true);

                ball.start();
                break;

            }

        }
    }

}
