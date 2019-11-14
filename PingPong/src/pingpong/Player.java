/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Steven
 */
public class Player {
    //declaring initial paddle positions
    static int p1y = 250;
    static int p2y = 250;
    
    
    boolean p1up;
    boolean p2up;
    boolean p1down;
    boolean p2down;

    //move paddle upwards
    public void paddleUp(int player) {

        //player 1
        if (player == 1) {
            p1y -= 2;
            if (p1y < 0) {     //ensures paddle can not leave bottom of map area
                p1y = 0;
            }
        }
        //player 2
        if (player == 2) {
            p2y -= 2;
            if (p2y < 0) {    //ensures paddle can not leave bottom of map area
                p2y = 0;
            }
        }
    }
    
    //moves paddle downwards
    public void paddleDown(int player) {
        //player 1
        if (player == 1) {
            p1y += 2;
            if (p1y > 470) {     //ensures paddle can not leave top of map area
                p1y = 470;
            }
        }
        //player 2
        if (player == 2) {
            p2y += 2;
            if (p2y > 470) {    //ensures paddle can not leave top of map area
                p2y = 470;

            }
        }
    }
    //sets variable to true if a key is pressed
    //variables are used in PingPong class to determine whether paddleUp/paddleDown methods are called
    ////if the key is pressed then the method is called and the paddle(s) move
    Action upAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p1up = true;
        }
    };
    Action downAction = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p1down = true;

        }
    };
    Action up2Action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p2up = true;
        }
    };
    Action down2Action = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p2down = true;
        }
    };
    
    //sets variable to false if a key is released
    //variables are used in PingPong class to determine whether paddleUp/paddleDown methods are called
    //if the key is released then the method is not called and the paddle(s) do not move
    Action stopUp = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p1up = false;
        }
    };
    Action stop2Up = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p2up = false;
        }
    };
    Action stopDown = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p1down = false;
        }
    };
    Action stop2Down = new AbstractAction() {
        public void actionPerformed(ActionEvent e) {
            p2down = false;
        }
    };
}
