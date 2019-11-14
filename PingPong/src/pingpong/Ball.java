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
    //declaring intial ball positions and velocities
    int bally = 170;
    int ballx = 350;
    int xVel = 1;
    int yVel = 1;
    
    Player player = new Player();
    Random random = new Random();      //allowing the use of random
    boolean dir = random.nextBoolean();   //setting random direction
    
    //setting initial scores
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

    //respawn ball at different positions depending on the map type
    //to avoid obstacles
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

        dir = random.nextBoolean();    //random initial direction of the ball
    }

    //getting selected map type
    public void GameMode(int MapNum) {
        Map = MapNum;
    }

    //changing directon of ball depending on different conditions
    private void Direction() {
        //changing ball's x-direction (+=/-=) depending on collision with paddles
        //always adding yVel (+=), but setting it to negative, to subtract when necessary 
        if (dir == false) {
            ballx += xVel;
            bally += yVel;
        } else {
            ballx -= xVel;
            bally += yVel;
        }
        
        //repsawn the ball and update score if the ball moves past a player's paddle
        if (ballx < 0) {
            Restart();
            s1();
        } else if (ballx > 750) {
            Restart();
            s2();
        }

        //reverse ball's y-velocity if ball is at the bottom of the map area
        //(causing the ball to bounce)
        if (bally <= 0) {
            yVel = -yVel;
        }
        //reverse ball's y-velocity if ball is at the top of the map area
        if (bally >= 550) {
            yVel = -yVel;
        }
        
        //changing boolean value of dir if ball collides with the paddles hitbox  
        if (bally >= player.p1y && bally <= player.p1y + 100 && ballx >= 15 && ballx <= 35) {
            dir = !dir;
        }

        if (bally >= player.p2y && bally <= player.p2y + 100 && ballx >= 665 && ballx <= 685) {
            dir = !dir;
        }

    }

    //changing ball's x-direction when it collides with obstacles
    //there are different obstacles depending on the selected map type
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
