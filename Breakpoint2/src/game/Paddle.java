package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  @author Samara
 *  @author Kai
 * 
* Class: Paddle implements KeyListener because it has keyboard responsiveness
* Paddle is a subclass of Polygon that represents the long rectangle that is the paddle, only visible when not in the menu.
* Paddle's methods handles the painting and rotation of the paddle method on screen.
*/
public class Paddle extends Polygon implements KeyListener {
	/**
	 * Right arrow boolean causes the paddle to move to the right every time move() is called, which is every frame.
	 */
	public boolean rightArrow;
	/**
	 * Left arrow boolean causes the paddle to move to the right every time move() is called, which is every frame.
	 */
	public boolean leftArrow;
	/**
	 * Is the number of pixels traversed every tenth of a second by the paddle when in motion.
	 */
	public int step = 15;
	/**
	 * Is the angle that the paddle is rotated from due east.
	 */
	private double angle = 0;

	/**
	 * Calls super constructor of Polygon to initialize shape, center, and rotation of the Paddle
	 * @param inShape		See Polygon documentation
	 * @param inPosition	See Polygon documentation
	 * @param inRotation	See Polygon documentation
	 */
	public Paddle(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	/**
	* moves the position based on which arrow is pressed and implements rotation,
	* by adjusting the rotation instance variable.
	*/
	public void move() { 
		if (this.rightArrow && this.position.getX() < 760) { //moves to the right.
			this.position.setX(this.position.getX() + step);
        }
        if (this.leftArrow && this.position.getX() > 220) { //moves to the left.
            this.position.setX(this.position.getX() - step); //changes what the correct coordinates are.
        }
    }
	/**
	* moves the position based on which arrow is pressed and implements rotation
	* @return double  gets the angle of the paddle
	*/
	public double getAngle() {
		return angle;
	}
	
	/**
	* updates booleans to true based on which arrow key is pressed, so that such information can be
	* communicated to the move() method, which is called consistently when the frame updates,
	* rather than when a key is pressed.
	*/
	@Override
	public void keyPressed(KeyEvent e) {
		//when a key is pressed down, this method is called. The parameter is the char of the key.
		int keyCode = e.getKeyCode();
		if ((keyCode == KeyEvent.VK_LEFT)) {
			leftArrow = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			rightArrow = true;
		}
	}
	
	/**
	* updates booleans to false based on which arrow key is released, so that such information can be
	* communicated to the move() method, which is called consistently when the frame updates,
	* rather than when a key is released.
	*/
	@Override
	public void keyReleased(KeyEvent e) {
		//when a key is released, this method is called. The parameter is the char of the key.
		int keyCode = e.getKeyCode();
		if ((keyCode == KeyEvent.VK_LEFT)) {
			leftArrow = false;
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			rightArrow = false;
		}
	}
	
	/**
	*  when a C H A R A C T E R is hit, this method is called. The parameter is the char of the key.
	*  Is not used, but is necessary to complete the implementation of KeyListener interface.
	*/
	@Override
	public void keyTyped(KeyEvent e) { 
		
	}
}
