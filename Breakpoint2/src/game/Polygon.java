package game;
import java.util.ArrayList;
/*
CLASS: Polygon
DESCRIPTION: A polygon is a sequence of points in space defined by a set of
             such points, an offset, and a rotation. The offset is the
             distance between the origin and the center of the collisionMatrix.
             The rotation is measured in degrees, 0-360.
USAGE: You are intended to instantiate this class with a set of points that
       forever defines its collisionMatrix, and then modify it by repositioning and
       rotating that collisionMatrix. In defining the collisionMatrix, the relative positions
       of the points you provide are used, in other words: {(0,1),(1,1),(1,0)}
       is the same collisionMatrix as {(9,10),(10,10),(10,9)}.
NOTE: You don't need to worry about the "magic math" details.

*/

public class Polygon {
	public Point[] collisionMatrix;   // An array of points.
	public ArrayList<Point[]> animationMatrices = new ArrayList<Point[]>(); //an array list of matrices that serve as layers in animation.
	public Point position;   // The offset mentioned above.
	public double rotation; // Zero degrees is due east.
	/**
 	* 
 	* @param incollisionMatrix		Stores the geometric identity of the polygon in the form of an array of constituent points.
 	* @param inPosition	Stores the geometric location of the polygon in the form of a point, representing the vector
 	* between the average center point of the polygon, and the center (top left) of the grid.	
 	* @param inRotation	Stores the angle at which the point array, collisionMatrix, is to be rotated before being pulled for painting
 	* or collision checking.
 	*/
	public Polygon(Point[] incollisionMatrix, Point inPosition, double inRotation) {
		collisionMatrix = incollisionMatrix;
		position = inPosition;
		rotation = inRotation;

	//	First, we find the collisionMatrix's top-most left-most boundary, its origin.
		Point origin = collisionMatrix[0].clone();
		for (Point p : collisionMatrix) {
			if (p.x < origin.x) origin.x = p.x;
			if (p.y < origin.y) origin.y = p.y;
		}
		
	//	Then, we orient all of its points relative to the real origin.
		for (Point p : collisionMatrix) {
			p.x -= origin.x;
			p.y -= origin.y;
		}
  	}

	//"getPoints" applies the rotation and offset to the collisionMatrix of the polygon.
	/**
	 * Returns the actual position of the points of the polygon after rotation and displacement from origin.
	 * Origin is defined as wherever the average center of the collisionMatrix array is.
	 * @return Returns the array of points that define the polygon, rotated and displaced from the origin and from
	 * due east position to the current state, as defined in the position and rotation instance variables.
	 */
  	public Point[] getPoints() {
		Point center = findCenter();
		Point[] points = new Point[collisionMatrix.length];
		for (int i = 0; i < collisionMatrix.length; i++) {
	//	for (Point p : collisionMatrix) {
		Point p = collisionMatrix[i];
		double x = ((p.x-center.x) * Math.cos(Math.toRadians(rotation)))
				- ((p.y-center.y) * Math.sin(Math.toRadians(rotation)))
				+ center.x/2 + position.x;
		double y = ((p.x-center.x) * Math.sin(Math.toRadians(rotation)))
				+ ((p.y-center.y) * Math.cos(Math.toRadians(rotation)))
				+ center.y/2 + position.y;
		points[i] = new Point(x,y);
		}
		return points;
  	}

	/**
	 * Getter method for point "position"
	 * @return	Returns the point representing the displacement in vector form from (0,0) to current position.
	 */
	public Point getPosition() {
		return this.position;
   }
  
	// "contains" implements some magical math (i.e. the ray-casting algorithm).
	/**
	 * Checks if this polygon contains a point.
	 * @param point	The point that is to be tested as to whether it is within the bounds of this polygon.
	 * @return		A boolean representing whether or not the given point is within the area of this polygon.
	 */
	public boolean contains(Point point) {
		Point[] points = getPoints();
		double crossingNumber = 0;
		for (int i = 0, j = 1; i < collisionMatrix.length; i++, j=(j+1)%collisionMatrix.length) {
			if ((((points[i].x < point.x) && (point.x <= points[j].x)) ||
				((points[j].x < point.x) && (point.x <= points[i].x))) &&
				(point.y > points[i].y + (points[j].y-points[i].y)/
				(points[j].x - points[i].x) * (point.x - points[i].x))) {
			crossingNumber++;
			}
		}
		return crossingNumber%2 == 1;
	}

	/**
	 * Setter method for the position point, with a defined x and y displacement.
	 * @param horizontal	Is the amount by which the X position of the polygon is displaced by.
	 * @param vertical		Is the amount by which the Y position of the polygon is displaced by.
	 */
	public void changePosition(double horizontal, double vertical) {
		position.setX(position.getX() + horizontal);
		position.setY(position.getY() + vertical);
	}

	/**
	 * 
	 * @param horizontal	Sets the current X position of the polygon.
	 * @param vertical		Sets the current Y position of the polygon.
	 */
	public void setPosition(double horizontal, double vertical) {
		position.setX(horizontal);
		position.setY(vertical);
	}
  
	/**
	 * Rotates current orientation clockwise (+y is downwards) by the given degree amount.
	 * @param degrees		Is the amount that the rotation instance variable is changed by.
	 */
  	public void rotate(int degrees) {rotation = (rotation+degrees)%360;}
  
	/*
	The following methods are private access restricted because, as this access
	level always implies, they are intended for use only as helpers of the
	methods in this class that are not private. They can't be used anywhere else.
	*/
  
  	// "findArea" implements some more magic math.
	/**
	 * 	Returns the Area in units squared of the given polygon based on the identity array of Points.
	 * @return Returns the Area as a double of the given polygon based on the identity array of Points.
	 */
	private double findArea() {
		double sum = 0;
		for (int i = 0, j = 1; i < collisionMatrix.length; i++, j=(j+1)%collisionMatrix.length) {
			sum += collisionMatrix[i].x*collisionMatrix[j].y-collisionMatrix[j].x*collisionMatrix[i].y;
		}
		return Math.abs(sum/2);
	}
  
  	//"findCenter" implements another bit of math.
	/**
		 * Finds the average point of all of the points.
		 * @return Returns the point that is in the average position of all of the points in the collisionMatrix array combined.
		 */
	private Point findCenter() {
		Point sum = new Point(0,0);
		for (int i = 0, j = 1; i < collisionMatrix.length; i++, j=(j+1)%collisionMatrix.length) {
			sum.x += (collisionMatrix[i].x + collisionMatrix[j].x)
					* (collisionMatrix[i].x * collisionMatrix[j].y - collisionMatrix[j].x * collisionMatrix[i].y);
			sum.y += (collisionMatrix[i].y + collisionMatrix[j].y)
					* (collisionMatrix[i].x * collisionMatrix[j].y - collisionMatrix[j].x * collisionMatrix[i].y);
		}
		double area = findArea();
		return new Point(Math.abs(sum.x/(6*area)),Math.abs(sum.y/(6*area)));
	}

	/**
	 * Tests if two polygons overlap by testing if any points of the parameter polygon are within this Polygon's bounds.
	 * @param other 	Is a Polygon to be checked for collision with this.
	 * @return A boolean representing if any points of the other Polygon are within the area of this Polygon.
	 */
	public boolean collides(Polygon other) {
		Point[] otherPoints = other.getPoints();
		for (int i = 0; i < otherPoints.length; i++) {
			if (this.contains(otherPoints[i])) {
				return true;
			}
		}
		return false;
   }

}