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
    InputMap inputMap = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
    ActionMap actionMap = this.getActionMap();
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

            if (player.p1up) {
                player.paddleUp(1);
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
            ball.Barriers(menu.MapNum);
            /*switch (menu.MapNum) {
                case 1:
                    if (ball.bally >= 250 && ball.bally <= 400 && ball.ballx >= 350 && ball.ballx <= 360) {
                         ball.dir = !ball.dir;
                    }
                    break;
                case 2:
                    break;
                case 3:
                    if (ball.bally >= 0 && ball.bally <= 150 && ball.ballx >= 345 && ball.ballx <= 355) {
                        ball.dir = !ball.dir;
                    }
                    if (ball.bally >= 450 && ball.bally <= 600 && ball.ballx >= 345 && ball.ballx <= 355) {
                        ball.dir = !ball.dir;
                    }
                    break;
                default:
                    break;
            }*/
            panel.repaint();
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
