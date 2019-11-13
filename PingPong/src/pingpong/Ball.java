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

    int bally = 170;
    int ballx = 350;
    int xVel = 1;
    int yVel = 1;
    
    Player player = new Player();
    Random random = new Random();
    boolean dir = random.nextBoolean();
    
    int score1 = 0;
    int score2 = 0;
    int Map;

    public void s1() {
        score2++;
    }

    public void s2() {
        score1++;
    }

    public void run() {
        try {
            while (true) {

                Direction();
                Barriers();
                Thread.sleep(2);
            }
        } catch (InterruptedException e) {
        }

    }

    public void Restart() {
        switch (Map) {
            case 1:
                bally = 150;
                ballx = 350;
                break;
            case 2:
                bally = 250;
                ballx = 350;
                break;
            case 3:
                bally = 250;
                ballx = 350;
                break;
            default:
                break;
        }

        dir = random.nextBoolean();
    }

    public void GameMode(int MapNum) {
        Map = MapNum;
    }

    private void Direction() {
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

        if (bally >= 550) {
            yVel = -yVel;
        }
        if (bally >= player.p1y && bally <= player.p1y + 100 && ballx >= 15 && ballx <= 35) {
            dir = !dir;
        }

        if (bally >= player.p2y && bally <= player.p2y + 100 && ballx >= 665 && ballx <= 685) {
            dir = !dir;
        }

    }

    private void Barriers() {
        switch (Map) {
            case 1:
                if (bally >= 250 && bally <= 400 && ballx >= 330 && ballx <= 355) {
                    dir = !dir;
                }
                break;
            case 2:
                break;
            case 3:
                if (bally >= 0 && bally <= 150 && ballx >= 330 && ballx <= 355) {
                    dir = !dir;
                }
                if (bally >= 450 && bally <= 600 && ballx >= 330 && ballx <= 355) {
                    dir = !dir;
                }
                break;
            default:
                break;
        }
    }
}
