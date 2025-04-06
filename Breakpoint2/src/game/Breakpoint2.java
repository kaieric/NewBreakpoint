package game;

/*
CLASS: Breakpoint2oids
DESCRIPTION: Extending Game, Breakpoint2 is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*; //allows for interfaces like keylistener. Look into this?
import java.util.ArrayList;

class Breakpoint2 extends Game {
	/*
	 * 
	 * COUNTER >> Records the frame number, delete this
	 * PHASE >> Records in String form what screen we are on.
	 * 
	 */
	static int COUNTER = 0;
	static String PHASE = "menu";
	private static int SPEED = 5;
	private static int LIVES = 3;

	private static Paddle p;
	private static ArrayList<Brick> BRICKLIST = new ArrayList<Brick>();

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

		//sets the black background.
    	//brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height); //width and height are params in game super class.

		//PHASES: menu, stage01, stage02, stage03, void (end screen)

		if (PHASE.equals("menu")) {
			
			
		}

		if (PHASE.length() > 5 && PHASE.substring(0,5).equals("stage")) { //if we are on a stage
			brush.setColor(dullAzure); //sets menu color
			brush.fillRect(200,0,600,600); //width and height are params in game super class.

			p.move();
			paintObjects.paint(brush, Color.white, p);
		}
    	// sample code for printing message for debugging
    	// COUNTER is incremented and this message printed
    	// each time the canvas is repainted
    	COUNTER++;
    	brush.setColor(Color.white);
    	brush.drawString("COUNTER is " + COUNTER,10,10);
  	}
  
	public static void main (String[] args) {
   		Breakpoint2 a = new Breakpoint2();
		a.repaint();
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
					p = spawnPaddle(); //spawns in the paddle. Can be moved elsewhere, like if we desire for this to be called after start is pressed on the MENU
					addKeyListener(p); //this is necessary for the paddle object to detect key presses.
					addKeyListener(new keyHandler()); //adds key handling to the anonymous class inside of breakou- sorry BREAKPOINT.

				} //else { //add this in for pause, and secondary menu implementation
			}
			/* 
			if (keyCode == KeyEvent.VK_SHIFT && Breakpoint.INMOTION == false && Breakpoint.MENU == false) {
				Breakpoint.INMOTION = true;
				Breakpoint.setSpeed(5);
			}
			*/
		}
		/**
		* Is not used, but is necessary to complete the implementation of KeyListener interface.
		*/
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}

	private Paddle spawnPaddle() {
		Point[] temp = {new Point(0,0), new Point(40,0), new Point(40, 10), new Point(0,10)};
			Paddle p = new Paddle(temp, new Point(560,550), 0);
			return p;
	}

  
}