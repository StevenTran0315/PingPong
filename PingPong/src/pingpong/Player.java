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

     static int p1y = 25;
     static int p2y = 25;

    
    public  void paddleUp(int player) {

        if (player == 1) {
            p1y -= 20;
            if (p1y < 0) {
                p1y = 0;
            }
        }
        if (player == 2) {
            p2y -= 20;
            if (p2y < 0) {
                p2y = 0;
            }
        }
    }

    public  void paddleDown(int player) {
        if (player == 1) {
            p1y += 20;
            if (p1y > 470) {
                p1y = 470;
            }
        }
        if (player == 2) {
            p2y += 20;
            if (p2y > 470) {
                p2y = 470;

            }
        }
    }
    
     Action upAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                paddleUp(1);
            }
        };
        Action downAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                paddleDown(1);

            }
        };
        Action up2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                paddleUp(2);
            }
        };
        Action down2Action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                paddleDown(2);
            }
        };
}