package entities;
import java.awt.*;
import utilities.*;
import utilities.Polygon; //specificity required to eliminate confusion with my own polygon implementation
//basically like Polygon, is extended by all of the objects
public abstract class GameObject {
    protected Polygon collisionMatrix;
    protected PixelSprite pixelSprite;
    protected Position position; //protected so that the subclasses can use these.

    public GameObject(Polygon collisionMatrix, Position position, PixelSprite pixelSprite) {
        this.collisionMatrix = collisionMatrix;
        this.position = position;
        this.pixelSprite = pixelSprite;
        if (pixelSprite != null) {
            pixelSprite.updatePos(position);
        }
    }

    public Position getPosition() {return position;}

    public void changePosition(double horizontal, double vertical) {
		position.setX(position.getX() + horizontal);
		position.setY(position.getY() + vertical);
        collisionMatrix.changePosition(horizontal, vertical);
	}
    public void setPosition(double horizontal, double vertical) {
		position.setX(horizontal);
		position.setY(vertical);
        collisionMatrix.setPosition(horizontal, vertical);
	}

    public Polygon getCollisionMatrix() {
        return collisionMatrix;
    }

    public PixelSprite getPixelSprite() {
        return pixelSprite;
    }
}
