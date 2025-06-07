package entities;

import utilities.PixelSprite;
import utilities.Polygon;
import utilities.Position;
import utilities.*;
import entities.*;

import java.awt.Color;
import java.util.ArrayList;

import controller.*;

public class Ball extends GameObject {
    public static int HASTYPOWERTICKER = 0;
    public static int LETHARGICPOWERTICKER = 0;
    private static int RADIUS = 10;
    private boolean stuck;
    
    private double angle = 225;
    private double speed = 5;
    double horizontalChange;
    double verticalChange;
    
    ArrayList<Ball> balls;
    Controller controller;
    
    public Ball(Position position, ArrayList<Ball> balls, Controller controller) {
        super(new Polygon(createOctagonArray(), position, 0), position, initializePixelSprite(position));
        this.balls = balls;
        this.stuck = true;
        this.controller = controller;
    }
        
    private static Position[] createOctagonArray() {
        Position[] octagonPoints = new Position[8];
        for (int i = 0; i < 8; i++) {
            octagonPoints[i] = new Position(Math.cos((Math.PI*i*45/180))*RADIUS, Math.sin((Math.PI*i*45/180))*RADIUS);
            }
        return octagonPoints;
    }

    private static PixelSprite initializePixelSprite(Position position) {
        Color[] sprtPallete = {Color.white};
        Integer[][] pxIntegers = new Integer[RADIUS * 2][RADIUS * 2];
        for (int row = 0; row < pxIntegers.length; row++) {
            for (int col = 0; col < pxIntegers[row].length; col++) {
                if (RADIUS >= Math.sqrt(Math.pow(row - (RADIUS), 2) + Math.pow(col - (RADIUS), 2))) {
                    pxIntegers[row][col] = 0;
                }
            }
        }
        return new PixelSprite(pxIntegers, 1, position, sprtPallete);
    }

    public void update(Paddle p, ArrayList<Brick> brickList) {
        if (controller != null) {
            if (controller.isUp()) {
                stuck = false;
            }
        }
        if (stuck) {
            setPosition(p.getPosition().getX(), p.getPosition().getY() - ((RADIUS*2) + 15));
        } else {
            for (int i = 0; i < speed; i++) {
                move(p, brickList);
            }
        }
    }

    public void move(Paddle p, ArrayList<Brick> brickList) {
        checkPaddle(p);
        checkWalls();
        checkBricks(brickList);
        horizontalChange = Math.cos(Math.toRadians(angle));
        verticalChange = Math.sin(Math.toRadians(angle));
        if (HASTYPOWERTICKER != 0 && verticalChange < 0) {
            speed = 5; //if moving up, speed up
            HASTYPOWERTICKER--;
        } else if (LETHARGICPOWERTICKER != 0 && verticalChange > 0 && position.getY() > 400) {
            speed = 1; //if under all bricks and moving towards paddle, slow down.
            LETHARGICPOWERTICKER--;
        } else { //if no special conditions, speed is set to 5
            speed = 3;
        }
        this.changePosition(horizontalChange, verticalChange);
    }

    /**
     *Checks the walls based on which wall and direction the ball is currently moving in.
        *adjusts the angle accordingly by reversing whichever x or y direction caused the ball to impact that wall.
        */
    private void checkWalls() {		
        
        //left wall negative horizontal direction
        if (this.position.getX() <= 200 && horizontalChange < 0) {
            if (verticalChange < 0) { //quadrant 2
                //angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
                angle = 180 - angle;
            } else { //quadrant 3
                //angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
                angle = 180 - angle;
            }
        }
        //right wall positive horizontal direction
        if (this.position.getX() >= 790 && horizontalChange > 0) {
            if (verticalChange < 0) { //quadrant 1
                //angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
                angle = 180 - angle;
            } else { //quadrant 4
                //angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
                angle = 180 - angle;
            }
        }
        //top wall positive vertical direction
        if (this.position.getY() <= 0 && verticalChange < 0) {
            if (horizontalChange < 0) {
                angle = 360 - angle;
            } else {
                angle = 360 - angle;
            }
        }
        //bottom wall negative vertical direction
        if (this.position.getY() >= 590 && verticalChange > 0) {
            ballDeath();
        }
        
        if (angle <= -360) {
            angle += 360;
        }
        if (angle >= 360) {
            angle -= 360;
        }
        
    }

    /**
     * Checks the ball for collision with the paddle.
     * Adjusts angle based on relative position of impact, including if the ball hits while too low.
     */
    private void checkPaddle(Paddle p) {
        //get position is assumed to be the center of the brick?

        if (p.collides(collisionMatrix)) {
            //hitting the top of the paddle
            if (((p.getPosition().getX()) - position.getX() + 20 + RADIUS) <= 40 + RADIUS
            &&  ((p.getPosition().getX()) - position.getX() + 20 + RADIUS ) >= 0 - RADIUS
            && position.getY() < p.getPosition().getY()) {
                //target range: 180 to 360, or -180 to 0, with either -180 or 180 corresponding to LEFTWARD movement.
                angle = ((((position.getX()) - p.getPosition().getX())*2.25) + 270);
                //maximum angle is 20 * 4 above equation
            } else {
                //hitting the side of the paddle
                if (position.getX() - position.getX() > 0 && horizontalChange < 0 && verticalChange > 0) {
                    angle = 180 - angle;
                }
                if (position.getX() - position.getX() < 0 && horizontalChange > 0 && verticalChange > 0) {
                    angle = 180 - angle;
                }
            }
            
        }
    }

    //Checks collision between this ball and the bricks.
    private void checkBricks(ArrayList<Brick> brickList) {
        for (int i = 0; i < brickList.size(); i++) {
            if (brickList.get(i).getCollisionMatrix().collides(this.getCollisionMatrix())) {
                this.bounceOffBrick(brickList.get(i));
            
            if (brickList.get(i) instanceof SlowBrick) {
                LETHARGICPOWERTICKER += 200;
            } else if (brickList.get(i) instanceof FastBrick) {
                HASTYPOWERTICKER += 1000;
            }
                brickList.remove(i);
                //SCORE+=10;
                //checkLevel();
            }
        }
    }

    /**
     * Alters the angle of the ball when it makes impact with brick
     * @param brick The brick that was impacted by the ball.
     */
    public void bounceOffBrick(Brick brick) {
        Position[] ballPoints = collisionMatrix.getPoints();
        for (int i = 1; i < ballPoints.length-1; i+=2) { //handles diagonal collisions
            if (brick.getCollisionMatrix().contains(ballPoints[i])) {
                angle = 180 - angle; //horizontal direction inversion
                angle = 360 - angle; //vertical direction inversion
            }
        }
        if (brick.getCollisionMatrix().contains(ballPoints[0]) ||
                brick.getCollisionMatrix().contains(ballPoints[4])) {
            angle = 180 - angle; //horizontal direction inversion
        }
        if (brick.getCollisionMatrix().contains(ballPoints[2]) ||
                brick.getCollisionMatrix().contains(ballPoints[6])) {
            angle = 360 - angle; //horizontal direction inversion
        }
    }
    
    public Polygon getCollisionMatrix() {
        return collisionMatrix;
    }

    public PixelSprite getPixelSprite() {
        return pixelSprite;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double newAngle) {
        angle = newAngle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void ballDeath() {
        balls.remove(this);
    }

}
