package game;

/*
CLASS: Breakpoint2oids
DESCRIPTION: Extending Game, Breakpoint2 is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class Breakpoint2 extends Game {
	static int counter = 0;

  public Breakpoint2() {
    super("Breakpoint2!",800,600);
    this.setFocusable(true);
	this.requestFocus();
  }
  
	public void paint(Graphics brush) {
    	brush.setColor(Color.black);
    	brush.fillRect(0,0,width,height);
    	
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
}