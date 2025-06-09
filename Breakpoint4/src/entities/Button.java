package entities;

import java.awt.*;

import game.*;
import utilities.PixelSprite;
import utilities.Polygon;
import utilities.Position;
import java.awt.event.MouseEvent;

public class Button extends GameObject {

    String text;

    public Button(Polygon collisionMatrix, Position position, PixelSprite pixelSprite, String text) {
        super(collisionMatrix, position, pixelSprite);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
