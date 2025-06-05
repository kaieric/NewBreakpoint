package game.state;

import controller.*;
import entities.*;
import game.*;
//import map.GameMap;
//import utilities.*;
import utilities.Position;

public class MenuState extends State {

    Game game;
    Button button; //could make button abstract later, and make this an arraylist.

    public MenuState(Input input, Game game) {
        super(input);
        this.game = game;
        button = new Button(new Position(400,300), "Start");
        //gameObjects.add(new Player(new PlayerController(input), spriteLibrary));
        //gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    }

    public Button getButton() {
        return button;
    }

    public void update(){
        //rectangle.setLocation((int) rectangle.getX() + 1, (int) rectangle.getY());
        //gameObjects.forEach(gameObject -> gameObject.update());
        handleMouseInput(); //must go at end
    }

    public void handleMouseInput() {
        if (input.isMouseClicked()) {
            //System.out.println("(" + input.getPointerPosition().getX() + ", " + input.getPointerPosition().getY() + ")");
            if (button.getCollisionMatrix().contains(input.getPointerPosition())) {
                game.changeState();
            }
        }
        input.clearMouseClick();
    }
    
}
