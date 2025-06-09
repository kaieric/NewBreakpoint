package game.state;

import controller.*;
import entities.*;
import game.*;
import java.util.*;
//import map.GameMap;
//import utilities.*;
import utilities.Position;

public class MenuState extends State {

    Game game;
    ArrayList<Button> buttons;

    public MenuState(Input input, Game game) {
        super(input);
        this.game = game;
        buttons = new ArrayList<Button>();
        buttons.add(new StartButton(new Position(400,200), "Start"));
        buttons.add(new StartButton(new Position(400,300), "Demo"));
        //gameObjects.add(new Player(new PlayerController(input), spriteLibrary));
        //gameMap = new GameMap(new Size(20, 20), spriteLibrary);
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void update(){
        //rectangle.setLocation((int) rectangle.getX() + 1, (int) rectangle.getY());
        //gameObjects.forEach(gameObject -> gameObject.update());
        handleMouseInput(); //must go at end
    }

    public void handleMouseInput() {
        if (input.isMouseClicked()) {
            //System.out.println("(" + input.getPointerPosition().getX() + ", " + input.getPointerPosition().getY() + ")");
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getCollisionMatrix().contains(input.getPointerPosition())) {
                    if (buttons.get(i).getText().equals("Start")) {
                        GameState.setDemo(false);
                        game.toGameState();
                    } else if (buttons.get(i).getText().equals("Demo")) {
                        GameState.setDemo(true);
                        game.toGameState();
                    }
                }
            }   
        }
        input.clearMouseClick();
    }
    
}
