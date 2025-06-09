package entities;

import java.awt.*;

import game.*;
import utilities.PixelSprite;
import utilities.Polygon;
import utilities.Position;
import java.awt.event.MouseEvent;

public class StartButton extends Button {

    String text;

    public StartButton(Position position, String text) {
        super(initializeFeederPolygon(position), position, initializePixelSprite(position), text);
        this.text = text;
    }

    private static Polygon initializeFeederPolygon(Position position) {
        Position[] identityMatrix = new Position[] {new Position(0,0), new Position(200,0), new Position(200, 30), new Position(0,30)};
        return new Polygon(identityMatrix, position, 0);
    }

    private static PixelSprite initializePixelSprite(Position position) {
        Color[] sprtPallete = {Color.black, new Color(205,92,91)};
        Integer[][] pxIntegers = new Integer[15][100];
        for (int row = 0; row < pxIntegers.length; row++) {
            for (int col = 0; col < pxIntegers[row].length; col++) {
                if ((row > 1 && row < pxIntegers.length - 2 && col > 1 && col < pxIntegers[row].length - 2)) {
                    pxIntegers[row][col] = 1;
                } else {
                    pxIntegers[row][col] = 0;
                }
            }
        }
        return new PixelSprite(pxIntegers, 2, position, sprtPallete);
    }

    public String getText() {
        return text;
    }
}
