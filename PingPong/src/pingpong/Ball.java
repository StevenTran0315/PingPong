/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingpong;

import java.util.Random;

/**
 *
 * @author Steven
 */
public class Ball extends Thread {

    static int bally = 170;
    static int ballx = 350;
    int xVel = 1;
    int yVel = 1;
    Player player = new Player();
    Random random = new Random();
    boolean dir = random.nextBoolean();
    int score1 = 0;
    int score2 = 0;

    public void s1() {
        score2++;
    }

    public void s2() {
        score1++;
    }

    public void run() {
        try {
            while (true) {
                if (dir == false) {
                    ballx += xVel;
                    bally += yVel;
                } else {
                    ballx -= xVel;
                    bally += yVel;
                }

                if (ballx < 0) {
                    Restart();
                    s1();
                } else if (ballx > 750) {
                    Restart();
                    s2();
                }

                if (bally <= 0) {
                    yVel = -yVel;
                }

                if (bally >= player.p1y && bally <= player.p1y + 100 && ballx >= 15 && ballx <= 35) {
                    dir = !dir;
                }

                if (bally >= player.p2y && bally <= player.p2y + 100 && ballx >= 665 && ballx <= 685) {
                    dir = !dir;
                }

                if (bally >= 550) {
                    yVel = -yVel;
                }
                Thread.sleep(4);
            }
        } catch (InterruptedException e) {
        }

    }

    public void Restart() {
        bally = 170;
        ballx = 350;
        dir = random.nextBoolean();
    }
}
