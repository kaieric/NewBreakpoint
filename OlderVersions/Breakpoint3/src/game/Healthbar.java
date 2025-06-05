package game;
import java.awt.*;
/**
 * @author Kai
 * 
 * CLASS: healthBar
 * DESCRIPTION: A health bar of various lengths, consisting of a chain of circles
 * USAGE: Extended by YourGameName.
 * NOTE: You don't need to understand the details here, no fiddling neccessary.
 */

class Healthbar extends Polygon {
    
    Point[][] bars;
	static int radius = 10;

    public Healthbar(Point inPosition, double inRotation) {
        
        super(createDodecagonArray(), inPosition, 0);
	}

    /**
     * @param brush passes the brush through to this class
     * This class's purpose is to draw the unique, multi-shape health bar.
     */
    public void paint(Graphics brush) {
		//this.points = this.getPoints(); //moved to move() in slider, but keep in paint for ball(possibly).

        
        for (int i = 0; i < bars.length; i++) {
            /* makes them right to left to account fo the number of them.
            Afterwards, if i+1 < bars.length, add in the line connection.
            */
            
        }

		int[] xpos = new int[collisionMatrix.length];
		int[] ypos = new int[collisionMatrix.length];
		for (int i = 0; i < collisionMatrix.length; i++) {
			xpos[i] = (int) collisionMatrix[i].getX();
			ypos[i] = (int) collisionMatrix[i].getY();
		}
		brush.setColor(Color.white);
		brush.fillPolygon(xpos, ypos, collisionMatrix.length);
	}

    private static Point[] createDodecagonArray() {
		Point[] dodecaPoints = new Point[12];
			for (int i = 0; i < dodecaPoints.length; i++) {
				dodecaPoints[i] =
						new Point(Math.cos((Math.PI*i*30/180))*radius, Math.sin((Math.PI*i*30/180))*radius);
			}
		return dodecaPoints;
	}
}
