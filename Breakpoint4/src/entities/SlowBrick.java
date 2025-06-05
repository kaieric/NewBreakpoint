package entities;

import utilities.Polygon;
import utilities.*;
import java.awt.*;

public class SlowBrick extends Brick {

    public SlowBrick(Position position) {
        super(position);
        this.pixelSprite = initializePixelSprite(position);
        desc = "Low-Speed Brick\nTimer:";
    }
    /*
    private static Polygon initializeFeederPolygon(Position position) {
        Position[] identityMatrix = new Position[] {new Position(0,0), new Position(60,0), new Position(60, 30), new Position(0,30)};
        return new Polygon(identityMatrix, position, 0);
    }
    */

    private static PixelSprite initializePixelSprite(Position position) {
        Color[] sprtPallete = {Color.red, Color.yellow};
        Integer[][] pxIntegers = new Integer[15][30];
        for (int row = 0; row < pxIntegers.length; row++) {
            for (int col = 0; col < pxIntegers[row].length; col++) {
                if ((row < pxIntegers.length / 2 && (row + col + 12) / 4/*arrow width*/ % 3/*arrow spacing*/ == 0) &&
                        (row > 3 && row < pxIntegers.length + 4 && col > 3 && col < pxIntegers[row].length - 4)) {
                    pxIntegers[row][col] = 1;
                } else if ((row >= pxIntegers.length / 2 && (row - col + 37) / 4 % 3 == 0) &&
                (row > 3 && row < pxIntegers.length - 4 && col > 3 && col < pxIntegers[row].length - 4)) {
                    pxIntegers[row][col] = 1;
                } else {
                    pxIntegers[row][col] = 0;
                }
                if (row < 2 || row > pxIntegers.length - 3 || col < 2 || col > pxIntegers[row].length - 3) {
                    pxIntegers[row][col] = 1;
                }
            }
        }
        return new PixelSprite(pxIntegers, 2, position, sprtPallete);
    }

    
    
}
