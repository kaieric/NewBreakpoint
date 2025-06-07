package entities;

import utilities.PixelSprite;
import utilities.Polygon;
import utilities.*;
import java.awt.*;

public class HealthBrick extends Brick {

    public HealthBrick(Position position) {
        super(position);
        this.pixelSprite = initializePixelSprite(position);
        desc = "Ball Cloning Brick";
    }
    /*
    private static Polygon initializeFeederPolygon(Position position) {
        Position[] identityMatrix = new Position[] {new Position(0,0), new Position(60,0), new Position(60, 30), new Position(0,30)};
        return new Polygon(identityMatrix, position, 0);
    }
    */

    private static PixelSprite initializePixelSprite(Position position) {
        Color[] sprtPallete = {Color.blue, Color.CYAN};
        Integer[][] pxIntegers = new Integer[15][30];
        for (int row = 0; row < pxIntegers.length; row++) {
            for (int col = 0; col < pxIntegers[row].length; col++) {
                //IMPLEMENT
            }
        }
        return new PixelSprite(pxIntegers, 2, position, sprtPallete);
    }

    
    
}
