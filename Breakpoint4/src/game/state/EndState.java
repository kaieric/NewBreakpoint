package game.state;

import java.util.ArrayList;

import controller.*;
import entities.*;
import game.*;
//import map.GameMap;
//import utilities.*;
import utilities.Position;

public class EndState extends State {

    Game game;
    ArrayList<Button> buttons;

    public EndState(Input input, Game game) {
        super(input);
        this.game = game;
        //gameObjects.add(new Player(new PlayerController(input), spriteLibrary));
        //gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    }
    
}
