package game.state;

import controller.*;
import entities.*;
import game.Game;

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
    public int score;

    private static boolean demoMode = false;
    private Game game;
    

    public GameState(Input input, Game game) {
        super(input);
        this.game = game;

        stage = 1;
        score = 0;
        paddle = new Paddle(new PlayerController(input));
        brickList = new ArrayList<Brick>();
        initializeStage(stage); //fills out the bricklist arraylist
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
                if (!demoMode) {
                    lives--; //under demoMode, lives are not decreased.
                }
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

    public static void setDemo(boolean bool) {
        demoMode = bool;
    }

    public static boolean isDemo() {
        return demoMode;
    }

    public void checkLevel(ArrayList<Brick> bricks) {
        if (bricks.size() < 1) {
            stage++;
            initializeStage(stage);
        }
    }

    public void addScore(int add) {
        score+= add;
    }

    public int getScore() {
        return score;
    }

    public void initializeStage(int stage) {
        System.out.println("new stage");
        System.out.println(stage);
        if (stage == 1) {
            for (int col = 0; col < 9; col++) {
                for (int row = 0; row < 3; row++) {
                    if (col == row) {
                        brickList.add(new FastBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                    } else if (col == row + 3) {
                        brickList.add(new SlowBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                    } else {
                        brickList.add(new Brick(new Position((67.5*col)+230,(37.5*row)+100)));
                    }
                }
            }
        } else if (stage == 2) {
            for (int col = 0; col < 9; col++) {
                for (int row = 0; row < 3; row++) {
                    if (col == row) {
                        brickList.add(new FastBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                    } else if (col == row + 3) {
                        brickList.add(new SlowBrick(new Position((67.5*col)+230,(37.5*row)+100)));
                    } else {
                        brickList.add(new Brick(new Position((67.5*col)+230,(37.5*row)+100)));
                    }
                }
            }
        } else {
            //have some victory screen
        }
    }

    public ArrayList<Brick> initializeStage01Legend() {
        ArrayList<Brick> bricks = new ArrayList<Brick>();
        bricks.add(new Brick(new Position(100, 100)));
        bricks.add(new SlowBrick(new Position(100, 180)));
        bricks.add(new FastBrick(new Position(100, 260)));
        return bricks;
    }

    public ArrayList<Ball> initializeBalls() {
        ArrayList<Ball> balls = new ArrayList<Ball>();
        balls.add(new Ball(new Position(300, 300), balls, new PlayerController(input), this));
        return balls;
    }

}
