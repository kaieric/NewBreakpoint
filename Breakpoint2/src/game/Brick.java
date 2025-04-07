package game;
import java.awt.*;
/**
*
* @author Samara
* @author Kai
*
*
* Class: Brick
* Brick is an outer class of the super-bricks, which refer to the Lethargic and Hasty brick variants,
* and which have some special properties in terms of their coloring, and their interaction with ball on impact.
*/
public class Brick extends Polygon {	
	Color color;
    //can add accessory point[] arrays to store more details. Use shape to just
	static Point[] feederBrickShapeArray = {new Point(0,0), new Point(40,0), new Point(40, 20), new Point(0,20)};
	
	/**
	 * Calls super constructor of Polygon to initialize shape, center, and rotation of the Brick
	 * @param inShape		See Polygon documentation
	 * @param inPosition	See Polygon documentation
	 * @param inRotation	See Polygon documentation
	 * @param color			Holds the color of the brick at initialization. May be liable to change.
	 */
	public Brick(Point[] inShape, Point inPosition, double inRotation, Color color) {
		super(inShape, inPosition, 0);
		this.color = color;
	}
	
	/**
	* Getter method for color.
	* @return Color Returns color of brick
	*/
	public Color getColor() {
		return color;
	}
	
	/**
	* allows the compiler to recognize updateColor method for super-bricks
	*/
	public void paint() {
		
	}

}
