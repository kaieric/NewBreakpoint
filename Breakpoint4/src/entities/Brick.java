package entities;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utilities.*;
import utilities.Polygon;

public class Brick extends GameObject{

    String desc;

    public Brick(Position position) {
        super(initializeFeederPolygon(position), position, initializePixelSprite(position));
        desc = "Standard Brick";
    }

    public String getDesc() {
        return desc;
    }

    private static Polygon initializeFeederPolygon(Position position) {
        Position[] identityMatrix = new Position[] {new Position(0,0), new Position(60,0), new Position(60, 30), new Position(0,30)};
        return new Polygon(identityMatrix, position, 0);
    }

    private static PixelSprite initializePixelSprite(Position position) {
        Color[] sprtPallete = {Color.YELLOW, Color.DARK_GRAY};
        Integer[][] pxIntegers = new Integer[15][30];
        for (int row = 0; row < pxIntegers.length; row++) {
            for (int col = 0; col < pxIntegers[row].length; col++) {
                if (((row + col) / 3) % 2 == 0 && (row > 1 && row < pxIntegers.length - 2 && col > 1 && col < pxIntegers[row].length - 2)) {
                    pxIntegers[row][col] = 0;
                } else {
                    pxIntegers[row][col] = 1;
                }
            }
        }
        return new PixelSprite(pxIntegers, 2, position, sprtPallete);
    }

    public void update() {

    }
    public void render() {

    }

    

    //Position[] identityMatrix = new Position[] {new Position(0,0), new Position(40,0), new Position(40, 10), new Position(0,10)};
}

    

    /*
    @Override
    public Image getSprite() {
        BufferedImage bf = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bf.createGraphics(); 
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
        
        graphics.dispose();
        return bf;
    }
     */
