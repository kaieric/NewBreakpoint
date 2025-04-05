package game;

/*
CLASS: Breakpoint2oids
DESCRIPTION: Extending Game, Breakpoint2 is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*; //allows for interfaces like keylistener. Look into this?

class Breakpoint2 extends Game {
	/*
	 * 
	 * counter >> Records the frame number, delete this
	 * phase >> Records in String form what screen we are on.
	 * 
	 */
	static int counter = 0;
	static String phase = "menu";

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
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height); //width and height are params in game super class.

		//phases: menu, stage01, stage02, stage03, void (end screen)

		System.out.println(phase);
		if (phase.equals("menu")) {
			
			
		}

		if (phase.length() > 5 && phase.substring(0,5).equals("stage")) { //if we are on a stage
			brush.setColor(dullAzure); //sets menu color
			brush.fillRect(200,0,600,600); //width and height are params in game super class.
		}
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.white);
    	brush.drawString("Counter is " + counter,10,10);
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
				if (phase.equals("menu")) {
					phase = "stage01";
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

  
}