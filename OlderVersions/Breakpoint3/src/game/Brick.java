package game;
import java.awt.*;
import java.awt.image.BufferedImage; //pixel art
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
	//constructor for default brick dimensions
	public Brick(Point[] inShape, Point inPosition, double inRotation, Color color) {
		super(inShape, inPosition, 0);
		this.color = color;
	}

	//constructor for custom brick dimensions
	public Brick(int height, int width, Point inPosition, double inRotation, Color color) {
		super(new Point[]{new Point(0,0), new Point(width,0), new Point(width, height), new Point(0,height)}, inPosition, 0);
		this.color = color;
	}

	//constructor for custom brick dimensions and pixelsprite
	public Brick(int height, int width, Point inPosition, double inRotation, PixelSprite pixelsprite) {
		super(new Point[]{new Point(0,0), new Point(width,0), new Point(width, height), new Point(0,height)}, pixelsprite, inPosition, 0);
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
	

	public void pixelPaint(Graphics brush) {
		this.pixelsprite.updatePos(position);
		this.pixelsprite.paint(brush);


		/*
		 * Note that in the below, we can freely manipulate the size of the buffered image.
		
		brush.drawImage(this.pixelImage,
				/
				(int)(this.getPosition().getX() - (pixelImage.getWidth()/2)),
				(int)(this.getPosition().getY() - (pixelImage.getHeight()/2)),
				pixelImage.getWidth(),
				pixelImage.getHeight(),
				null);
		*/




		/*
		Point[] temp = element.getPoints();
		int[] xPoints = new int[temp.length];
		int[] yPoints = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			xPoints[i] = (int)temp[i].getX();
			yPoints[i] = (int)temp[i].getY();
		}
		//updateHorizontalandVertical();
		brush.setColor(color);
		brush.fillPolygon(xPoints, yPoints, temp.length);
		*/





	} //IMPLEMENT THIS TO GET UNIQUE BRICK STOFF

    //create sub-colored bricks? or maybe use a decorator, with some more generic color variables to represent the different shades that need to be put into a color!




}
