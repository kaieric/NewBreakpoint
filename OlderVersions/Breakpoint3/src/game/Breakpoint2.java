package game;

/*
CLASS: Breakpoint2oids
DESCRIPTION: Extending Game, Breakpoint2 is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.image.BufferedImage; //what is this
import java.awt.event.*; //allows for interfaces like keylistener. Look into this?
import java.util.ArrayList;


class Breakpoint2 extends Game {
	/*
	 * 
	 * FRAME >> Records the frame number, delete this
	 * PHASE >> Records in String form what screen we are on.
	 * 
	 */
	static int FRAME = 0;
	static String PHASE = "menu";
	private static int SPEED = 5;
	private static int LIVES = 3;
	private static int SCORE = 0;

	private static Paddle p;
	private static Ball b;
	private static ArrayList<Brick> BRICKLIST = new ArrayList<Brick>();

	private static int speedballTicker = 0; //the tickers being above zero are what enable power-ups.

	private static boolean INMOTION;

	public Breakpoint2() {
		super("Breakpoint2!",800,600);
		this.setFocusable(true);
		this.requestFocus();
		this.addKeyListener(new keyHandler()); //adds key handling to the anonymous class
  	}

	public void paint(Graphics brush) {
		//battery of colors to be used (saves on memory a little bit)
		Color dullAzure = new Color(1, 27, 50);
		Color synthwavePink = new Color(244, 55, 254);
		Color synthwaveLightBlue = new Color(55, 254, 210);

    	brush.fillRect(0,0,width,height); //width and height are params in game super class. Default color is Color.black

		//PHASES: menu, stage01, stage02, stage03, void (end screen)

		if (PHASE.equals("menu")) {
			
			
		}

		if (PHASE.length() > 5 && PHASE.substring(0,5).equals("stage")) { //if we are on any stage
			brush.setColor(dullAzure); //sets menu color
			brush.fillRect(200,0,600,600); //width and height are params in game super class.
			
			//paints bricks
			for (int i = 0; i < BRICKLIST.size(); i++) {
				//BRICKLIST.get(i).paint();
				if (BRICKLIST.get(i).isRaster()) {
					BRICKLIST.get(i).pixelPaint(brush);
				} else {
					//paintObjects.paint(brush, synthwaveLightBlue, BRICKLIST.get(i));
					BRICKLIST.get(i).pixelPaint(brush);
				}
			}

			//paints paddle
			p.move();
			paintObjects.paint(brush, synthwavePink, p);
			
			if (INMOTION) { //code for moving the ball.
				for (int i = 0; i < SPEED; i++) { //solves frame problems by animating b.move() speed times.
					b.move();
				}
			} else { //code for sticking the ball to the paddle.
				b.ballStuck();
			}
			paintObjects.paint(brush, Color.white, b); //only paints ball after it is moved.
			
			//experimental pixel art. Only run on stage.
			/*
			BufferedImage image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
			image.setRGB(0, 0, Color.RED.getRGB()); //10x10 pixel at (5,5)
			image.setRGB(1, 0, Color.BLUE.getRGB()); //10x10 pixel at 25, 25
			brush.drawImage(image, 0, 0, 100, 100, null); //x, y, width, height
			System.out.println(image.getWidth());
			//Pixelsprite stoff
			Integer[][] sprtInput = {{0, 1, 0, 1},{1, 0, 1, 0},{0, 1, 0, 1},{1, 0, 1, 0}};
			Color[] sprtPallete = {Color.yellow, Color.DARK_GRAY};
			PixelSprite sprt = new PixelSprite(sprtInput, 5, new Point(10, 100), sprtPallete);
			sprt.paint(brush);
 			*/

			//animate all of this at the very end, to make scanlines! Only done on stages.
			brush.setColor(Color.black);
			if (FRAME % 100 < 50) { //cycle lasts 1 second
				for (int i = 0; i < 149; i++) {
					brush.fillRect(200,i*4,600,1);
				}
			} else {
				for (int i = 0; i < 149; i++) {
					brush.fillRect(200,2+i*4,600,1);
				}
			}
			
			/*
			 * Cool idea! have an array list of cosmetic stuff seperate from the collision shape. Then have painObjects loop through this and paint each layer.
			 */
		}
    	// sample code for printing message for debugging
    	// FRAME is incremented and this message printed
    	// each time the canvas is repainted
    	FRAME++;
    	//brush.setColor(Color.white);
    	//brush.drawString("FRAME is " + FRAME,10,10);
  	}
  
	public static void main (String[] args) {
   		Breakpoint2 a = new Breakpoint2();
		a.repaint();
  	}

	public static void initializeStage() {
		Color synthwaveLightBlue = new Color(55, 254, 210);
		Color synthwavePink = new Color(244, 55, 254);
		if (PHASE.equals("stage01")) {
			//initialize the stage 1 bricks?
			Color[] sprtPallete = {Color.yellow, Color.DARK_GRAY};
			Integer[][] stage01Brick = {
				{0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
				{1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1},
				{1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
				{1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1},
				{1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0},
				{0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0},
			};
			PixelSprite sprt = new PixelSprite(stage01Brick, 5, new Point(0, 0), sprtPallete);
			//now make the bricks
			for (int col = 0; col < 9; col++) {
				for (int row = 0; row < 3; row++) {
					//1st gen
					//double randomGenHolder = Math.random();
					//BRICKLIST.add(new Brick(Brick.feederBrickShapeArray, new Point((67.5*col)+200,(37.5*row)+100), 0, synthwaveLightBlue));
					//second gen
					//BRICKLIST.add(new Brick(30, 60, new Point((67.5*col)+215,(37.5*row)+100), 0, synthwaveLightBlue));
					//third gen
					BRICKLIST.add(new Brick(30, 60, new Point((67.5*col)+215,(37.5*row)+100), 0, sprt));
				}
			}
		} else if (PHASE.equals("stage02")) {
			
			
			for (int col = 0; col < 9; col++) {
				for (int row = 0; row < 3; row++) {
					//double randomGenHolder = Math.random();
					//BRICKLIST.add(new Brick(Brick.feederBrickShapeArray, new Point((67.5*col)+200,(37.5*row)+100), 0, synthwaveLightBlue));
					BRICKLIST.add(new Brick(30, 60, new Point((67.5*col)+215,(37.5*row)+100), 0, synthwaveLightBlue));
				}
			}
		}
	}




	/**
	 * Lambda expression that implements PaintedElement interface that paints on the screen
	 */
	PaintedElement paintObjects = (Graphics brush, Color color, Polygon element) -> {
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
	}; //keep this, it's for the lambda expression.

	/**
	 * Setter method for the speed static instance variable, which is the magnitude of the ball's per-frame
	 * displacement vector.
	 * @param newSpeed	Is the new speed that speed is to be set to.
	 */
	public static void setSpeed(int newSpeed) {
		SPEED = newSpeed;
	}

	/**
	 * Anonymous class?? for adding keylistening functionality to the main class.
	 */
	class keyHandler implements KeyListener {
		/**
		* Is not used, but is necessary to complete the implementation of KeyListener interface.
		*/
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		/**
		* updates booleans to true based on which arrow key is pressed, so that such information can be
		* communicated to the move() method, which is called consistently when the frame updates,
		* rather than when a key is pressed.
		*/
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();

			//if space is pressed on the menu, the first stage begins.
			if ((keyCode == KeyEvent.VK_SPACE)) {
				if (PHASE.equals("menu")) {
					PHASE = "stage01";
					initializeStage();
					p = spawnPaddle(); //spawns in the paddle. Can be moved elsewhere, like if we desire for this to be called after start is pressed on the MENU
					b = spawnBall();
					addKeyListener(p); //this is necessary for the paddle object to detect key presses.
					addKeyListener(new keyHandler()); //adds key handling to the anonymous class inside of breakou- sorry BREAKPOINT.

				} //else { //add this in for pause, and secondary menu implementation
			}
			if (keyCode == KeyEvent.VK_UP && Breakpoint2.INMOTION == false && PHASE.length() > 5 && PHASE.substring(0,5).equals("stage")) {
				Breakpoint2.INMOTION = true;
				Breakpoint2.setSpeed(5);
			}
		}
		//Is not used, but is necessary to complete the implementation of KeyListener interface.
		@Override
		public void keyReleased(KeyEvent e) {
			
		}	
	}

	public void checkLevel() {
		if (BRICKLIST.size() == 0) {
			if (PHASE.equals("stage01")) {
				//at stage01 completion:
				Breakpoint2.INMOTION = false;
				b.ballStuck(); //needed to stop the ball's old position from deleting 4 different bricks before moving back to the paddle.
				PHASE = "stage02";
				initializeStage();
			}
		}
	}

	public class Ball extends Polygon {
	
		public int hastyPowerTicker = 0;
		public int lethargicPowerTicker = 0;
		private static int RADIUS = 10;
		private double angle = 225;
		private double horizontalChange = 0;
		private double verticalChange = 0;
		
		/**
		 * Handles the ball on screen, initializes ball's shape, center, and rotation
		 * @param  inPosition keeps track of center of the ball
		 * @param  inRotation keeps track of the ball's rotation (see Polygon documentation)
		 */
		public Ball (Point inPosition, double inRotation) {
			super(createOctagonArray(), inPosition, inRotation);					
		}

		//creates collision matrix
		private static Point[] createOctagonArray() {
			Point[] octagonPoints = new Point[8];
			for (int i = 0; i < 8; i++) {
				octagonPoints[i] =
					new Point(Math.cos((Math.PI*i*45/180))*RADIUS, Math.sin((Math.PI*i*45/180))*RADIUS);
				}
			return octagonPoints;
		}

		public void move() {
			checkPaddle();
			checkWalls();
			checkBricks();
			horizontalChange = Math.cos(Math.toRadians(angle));
			verticalChange = Math.sin(Math.toRadians(angle));
			if (hastyPowerTicker != 0 && verticalChange < 0) {
				SPEED = 10; //if moving up, speed up
				hastyPowerTicker--;
			} else if (lethargicPowerTicker != 0 && verticalChange > 0 && this.getPosition().getY() > 500) {
				SPEED = 2; //if under all bricks and moving towards paddle, slow down.
				lethargicPowerTicker--;
			} else { //if no special conditions, speed is set to 5
				SPEED = 5;
			}
			this.changePosition(horizontalChange, verticalChange);
		}

		/**
	 	 *Checks the walls based on which wall and direction the ball is currently moving in.
	 	 *adjusts the angle accordingly by reversing whichever x or y direction caused the ball to impact that wall.
	 	 */
		private void checkWalls() {		
			
			//left wall negative horizontal direction
			if (this.position.getX() <= 200 && horizontalChange < 0) {
				if (verticalChange < 0) { //quadrant 2
					//angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
					angle = 180 - angle;
				} else { //quadrant 3
					//angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
					angle = 180 - angle;
				}
			}
			//right wall positive horizontal direction
			if (this.position.getX() >= 790 && horizontalChange > 0) {
				if (verticalChange < 0) { //quadrant 1
					//angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
					angle = 180 - angle;
				} else { //quadrant 4
					//angle = Math.acos(-Math.cos(angle))*((360)/Math.PI);
					angle = 180 - angle;
				}
			}
			//top wall positive vertical direction
			if (this.position.getY() <= 0 && verticalChange < 0) {
				if (horizontalChange < 0) {
					angle = 360 - angle;
				} else {
					angle = 360 - angle;
				}
			}
			//bottom wall negative vertical direction
			if (this.position.getY() >= 590 && verticalChange > 0) {
				ballDeath();
			}
			
			if (angle <= -360) {
				angle += 360;
			}
			if (angle >= 360) {
				angle -= 360;
			}
			
		}

		/**
		 * Checks the ball for collision with the paddle.
		 * Adjusts angle based on relative position of impact, including if the ball hits while too low.
		 */
		private void checkPaddle() {
			//get position is assumed to be the center of the brick?

			if (p.collides(b)) {
				if (((p.getPosition().getX()) - this.getPosition().getX() + 20 + RADIUS) <= 40 + RADIUS
				&&  ((p.getPosition().getX()) - this.getPosition().getX() + 20 + RADIUS ) >= 0 - RADIUS
				&& this.getPosition().getY() < p.getPosition().getY()) {
					//target range: 180 to 360, or -180 to 0, with either -180 or 180 corresponding to LEFTWARD movement.
					this.setAngle((((this.getPosition().getX()) - p.getPosition().getX())*4) + 270);
					//maximum angle is 20 * 4 above equation
				} else {
					//hitting the side of the paddle
					if (this.getPosition().getX() - this.getPosition().getX() > 0 && horizontalChange < 0 && verticalChange > 0) {
						angle = 180 - angle;
					}
					if (this.getPosition().getX() - this.getPosition().getX() < 0 && horizontalChange > 0 && verticalChange > 0) {
						angle = 180 - angle;
					}
				}
				
			}
		}

		//Checks collision between this ball and the bricks.
		private void checkBricks() {
			for (int i = 0; i < BRICKLIST.size(); i++) {
				if (BRICKLIST.get(i).collides(b)) {
					this.bounceOffBrick(BRICKLIST.get(i));
					/*
					if (BRICKLIST.get(i) instanceof Brick.LethargicBrick) {
					b.lethargicPowerTicker+=200;
					} else if (BRICKLIST.get(i) instanceof Brick.HastyBrick) {
						b.hastyPowerTicker+=200;
					}
					*/
					BRICKLIST.remove(i);
					SCORE+=10;
					checkLevel();
				}
			}
		}

		/**
		 * Alters the angle of the ball when it makes impact with brick
		 * @param brick The brick that was impacted by the ball.
		 */
		public void bounceOffBrick(Brick brick) {
			Point[] ballPoints = this.getPoints();
			for (int i = 1; i < ballPoints.length-1; i+=2) { //handles diagonal collisions
				if (brick.contains(ballPoints[i])) {
					angle = 180 - angle; //horizontal direction inversion
					angle = 360 - angle; //vertical direction inversion
				}
			}
			if (brick.contains(ballPoints[0]) || brick.contains(ballPoints[4])) {
				angle = 180 - angle; //horizontal direction inversion
			}
			if (brick.contains(ballPoints[2]) || brick.contains(ballPoints[6])) {
				angle = 360 - angle; //horizontal direction inversion
			}
		}

		/**
		 * Getter method for angle.
	 	 * @return double 	The angle of the ball
	 	 */
		public double getAngle() {
			return angle;
		}
		
		/**
	 	 *updates the current angle of the ball
	 	 */
		/**
		 * Setter method for angle.
		 * @param newAngle	is the new Angle (duh).
		 */
		public void setAngle(double newAngle) {
			angle = newAngle;
		}
		
		/**
		 * Reduces the number of lives remaining by one, sets INMOTION to false, which zeros the ball's movement,
		 * resets to a default angle for when the ball is relaunched, and fixes the ball's position to just above the paddle.
	 	 */
		public void ballDeath() {
			Breakpoint2.INMOTION = false;
			//setPosition(p.getPosition().getX(), p.getPosition().getY() - ((radius*2) + 35)); //moved to paint
			Breakpoint2.setSpeed(0);
			angle = 225; //equivalent to 135, but because y is negative, this is really weird.
			Breakpoint2.LIVES--;
		}
		
		/**
	 	 *	Moves the ball to just above the paddle by changing position to the paddle's x position, and a y position just
	 	 *above that of the paddle's. Is called every frame if INMOTION is false.
	 	 */
		public void ballStuck() {
			setPosition(p.getPosition().getX(), p.getPosition().getY() - ((RADIUS*2) + 15));
		}
	}

	/**
	 * Used to initialize paddle object that starts above paddle
	 * @return paddle the singular paddle reference that is used to spawn the one and only paddle in the game.
	 */
	private Paddle spawnPaddle() {
		Point[] temp = {new Point(0,0), new Point(40,0), new Point(40, 10), new Point(0,10)};
			Paddle p = new Paddle(temp, new Point(560,500), 0);
			return p;
	}
	
	/**
	 * Used to initialize ball object that starts at center of screen
	 *@return Ball the singular ball reference that is used to spawn the one and only ball in the game.
	 */
	private Ball spawnBall() {
		b = new Ball(new Point(300, 300), 5);
		return b;
	}

}