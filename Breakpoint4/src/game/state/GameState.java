package game.state;

import controller.*;
import entities.*;

import java.awt.Color;
import java.util.*;
import utilities.*;

public class GameState extends State {

    private int stage;
    private boolean paused;
    private int cooldown = 0;
    private Paddle paddle;
    private ArrayList<Brick> brickList;
    private ArrayList<Brick> brickLegend;
    private ArrayList<Ball> ballList;
    private int lives = 3;
    public static int score;
    

    public GameState(Input input) {
        super(input);
        stage = 0;
        score = 0;
        paddle = new Paddle(new PlayerController(input));
        brickList = initializeStage01(); //fills out the bricklist arraylist
        brickLegend = initializeStage01Legend();
        ballList = initializeBalls();
        //gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    }

    public void update() {
        if (cooldown > 0) {
            cooldown--;
        }

        if (!paused) {
            if (controller.isSpace() && cooldown <= 0) {
                paused = true;
                cooldown += 10;
            }
            paddle.update();
            if (ballList.size() == 0 && lives > 0) {
                lives--;
                if (lives == 0) {
                    //gameOverScreen
                } else {
                    ballList = initializeBalls();
                }
            }
            for (int i = 0; i < ballList.size(); i++) {
                ballList.get(i).update(paddle, brickList);
            }
        } else {
            if (controller.isSpace() && cooldown <= 0) {
                paused = false;
                cooldown += 10;
            }
        }
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public ArrayList<Brick> getBricks() {
        return brickList;
    }

    public ArrayList<Brick> getLegend() {
        return brickLegend;
    }

    public ArrayList<Ball> getBalls() {
        return ballList;
    }

    public int getLives() {
        return lives;
    }

    public boolean isPaused() {
        return paused;
    }

    public ArrayList<Brick> initializeStage01() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 3; row++) {
                //1st gen
                //double randomGenHolder = Math.random();
                //BRICKLIST.add(new Brick(Brick.feederBrickShapeArray, new Point((67.5*col)+200,(37.5*row)+100), 0, synthwaveLightBlue));
                //second gen
                //BRICKLIST.add(new Brick(30, 60, new Point((67.5*col)+215,(37.5*row)+100), 0, synthwaveLightBlue));
                //third gen
                if (col == row) {
                    bricks.add(new FastBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                } else if (col == row + 3) {
                    bricks.add(new SlowBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                } else {
                    bricks.add(new Brick(new Position((67.5*col)+230,(37.5*row)+100)));
                }
            }
        }
        return bricks;
    }

    public ArrayList<Brick> initializeStage01Legend() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick(new Position(100, 100)));
        bricks.add(new SlowBrick(new Position(100, 200)));
        bricks.add(new FastBrick(new Position(100, 300)));
        return bricks;
    }

    public ArrayList<Ball> initializeBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(new Position(300, 300), null, balls, new PlayerController(input)));
        return balls;
    }

}
