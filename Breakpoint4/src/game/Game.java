package game;
import java.awt.*;
import java.util.List;

import controller.*;
import display.*;
//import entities.GameObject;
//import entities.Player;
import game.state.*;
//import gfx.*;

import java.util.*;

public class Game {

    //public static int SPRITE_SIZE = 64; //is the number of pixels of each sprite square's height/width.

    private Display display;
    private Input input;
    //private Rectangle rectangle;
    private State currentState;
    private MenuState menuState;
    private GameState gameState;
    private EndState endState;
    

    public Game(int width, int height) {
        input = new Input(); //creates new input. Should be the only input created.
        display = new Display(width, height, input); //passes the input to the display
        //creates all of the states.
        menuState = new MenuState(input, this);
        gameState = new GameState(input, this);
        endState = new EndState(input, this);
        currentState = menuState;

        //rectangle = new Rectangle(0,0,50,50);
    }

    public void update(){
        currentState.update();
    }

    public void toGameState() {
        if (currentState == menuState) {
            currentState = gameState;
        } else if (currentState == gameState) {
            currentState = endState;
        } else if (currentState == endState) {
            gameState = new GameState(input, this); //starts a new run
            currentState = endState;
        }
    }

    public void render(){
        //display.render(this); //legacy from when the game itself held the role of the state
        display.render(currentState);
    }
    //public Rectangle getRectangle() {return rectangle;}
}
