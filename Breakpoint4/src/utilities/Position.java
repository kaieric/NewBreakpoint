package utilities;
public class Position implements Cloneable {
    public double x, y;

    public Position(double x, double y) {this.y=y; this.x=x;}

    public double getX() {return x;}
    public double getY() {return y;}
    public void setX(double x){ this.x = x;}
	public void setY(double y){ this.y = y;}

    public Position clone() {
		return new Position(x, y);
	}
}