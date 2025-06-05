package game.state;

import java.util.ArrayList;
import java.util.List;

import controller.*;
import entities.*;
//import gfx.*;
//import map.GameMap;

public abstract class State {
    //protected GameMap gameMap;
    //protected List<GameObject> gameObjects;
    //protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Controller controller;

    public State(Input input) {
        this.input = input;
        this.controller = new PlayerController(input);
        //gameObjects = new ArrayList<GameObject>();
        //spriteLibrary = new SpriteLibrary();
        //gameObjects.add(new Player(new PlayerController(input), spriteLibrary)); //the input is passed into the playerController.
    }

    public void update(){
        //rectangle.setLocation((int) rectangle.getX() + 1, (int) rectangle.getY());
        //gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render(){

    }
    /*
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
     */
}
