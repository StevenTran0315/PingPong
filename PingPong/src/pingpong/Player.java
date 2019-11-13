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

    static int p1y = 250;
    static int p2y = 250;
    boolean p1up;
    boolean p2up;
    boolean p1down;
    boolean p2down;

    public void paddleUp(int player) {

        if (player == 1) {
            p1y -= 2;
            if (p1y < 0) {
                p1y = 0;
            }
        }
        if (player == 2) {
            p2y -= 2;
            if (p2y < 0) {
                p2y = 0;
            }
        }
    }

    public void paddleDown(int player) {
        if (player == 1) {
            p1y += 2;
            if (p1y > 470) {
                p1y = 470;
            }
        }
        if (player == 2) {
            p2y += 2;
            if (p2y > 470) {
                p2y = 470;

            }
        }
    }

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
