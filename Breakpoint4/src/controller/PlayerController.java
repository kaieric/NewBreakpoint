package controller;
import java.awt.event.KeyEvent;

public class PlayerController implements Controller {

    private Input input;

    public PlayerController(Input input) {
        this.input = input;
    }

    @Override
    public boolean isUp() {
        return input.isPressed(KeyEvent.VK_UP);
    }

    @Override
    public boolean isDown() {
        return input.isPressed(KeyEvent.VK_DOWN);
    }

    @Override
    public boolean isLeft() {
        return input.isPressed(KeyEvent.VK_LEFT);
    }

    @Override
    public boolean isRight() {
        return input.isPressed(KeyEvent.VK_RIGHT);
    }

    @Override
    public boolean isSpace() {
        return input.isPressed(KeyEvent.VK_SPACE);
    }
    
}
