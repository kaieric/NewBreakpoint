package game;
import java.awt.*;
/**
*
* @author Samara
* @author Kai
* This interface holds the paint method which is implemented by the lambda expression.
* Simplifies an oft repeated but bulky method used across multiple classes.
*/
public interface PaintedElement {
	public void paint(Graphics brush, Color color, Polygon element);
}