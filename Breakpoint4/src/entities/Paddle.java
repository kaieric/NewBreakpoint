package entities;
import java.awt.*;
import java.awt.image.BufferedImage;

import utilities.*;
import utilities.Polygon;
import controller.*;

public class Paddle extends Polygon {

    private Controller controller;
    private double paddleSpeed = 15;

    public Paddle(Controller controller) {
        //super call creates the polygon
        super(new Position[] {new Position(0,0), new Position(40,0), new Position(40, 10), new Position(0,10)},
        new Position(560,500),
        0);
		this.controller = controller;
		
    }

    public void update() {
        if (!(controller.isRight() && controller.isLeft())) {
            if (controller.isRight() && this.position.getX() < 790 - paddleSpeed) {
                this.position.setX(this.position.getX() + paddleSpeed);
            }
            if (controller.isLeft() && this.position.getX() > 210 + paddleSpeed) {
                this.position.setX(this.position.getX() - paddleSpeed);
            }
        }
    }

    public void render() {

    }

    /*
    @Override
    public Image getSprite() {
        BufferedImage bf = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bf.createGraphics(); 
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, size.getWidth(), size.getHeight());
        
        graphics.dispose();
        return bf;
    }
     */
}
