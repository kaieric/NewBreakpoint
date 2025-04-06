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
	public void updateColor() {
		
	}
	
	/**
	* LethargicBrick is an inner class of Brick
	* when it is impacted, the ball increases a timer which slows down the ball when is is descending
	* and below a certain level vertically, and only runs the timer when the ball is slowed down
	*/
	class LethargicBrick extends Brick {
		boolean colorDescending;
		/**
		* calls Brick super constructor to initialize lethargic brick
		*/
		public LethargicBrick(Point[] inShape, Point inPosition, double inRotation) {
			super(inShape, inPosition, inRotation, new Color(42, 0, 255));
		}
		/**
		* Increments the green value of brick every time it is called.
		*/
		public void updateColor() {
			if (color.getGreen() > 254) {
				colorDescending = true;
			} else if (color.getGreen() < 1) {
				colorDescending = false;
			}
			if(colorDescending ) {
				color = new Color(color.getRed(), color.getGreen() - 1, color.getBlue());
			} else {
				color = new Color(color.getRed(), color.getGreen() + 1, color.getBlue());
			}
		}
			
	}
	
	/**
	* HastyBrick is an inner class of Brick
	* when it is impacted, the ball increases a timer which speeds up the ball when is is ascending
	* and only runs the timer when the ball is sped up.
	*/
	class HastyBrick extends Brick {
		boolean colorDescending;
		/**
		* calls Brick super constructor to initialize hasty brick
		*/
		public HastyBrick(Point[] inShape, Point inPosition, double inRotation) {
			super(inShape, inPosition, inRotation, new Color(0, 0, 255));
		}
		/**
		* Increments the green value of brick every time it is called.
		*/
		public void updateColor() {
			if (color.getGreen() > 254) {
				colorDescending = true;
			} else if (color.getGreen() < 1) {
				colorDescending = false;
			}
			if(colorDescending ) {
				color = new Color(color.getRed(), color.getGreen() - 1, color.getBlue());
			} else {
				color = new Color(color.getRed(), color.getGreen() + 1, color.getBlue());
			}
		}
	}
}
