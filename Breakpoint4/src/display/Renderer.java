package display;

import java.awt.*;
import java.util.ArrayList;

import entities.*;
import entities.Button;
import game.*;
import game.state.*;
import utilities.Polygon;
import utilities.Position;

public class Renderer {
    //color pallete
    private static Color dullAzure = new Color(1, 27, 50);

    private Graphics graphics;

    public void render(State state, Graphics graphics) {
        if (state instanceof MenuState) {
            graphics.setColor(new Color(250,248,239));
            graphics.fillRect(0, 0, 800, 600);
            renderButton(graphics, ((MenuState) state).getButton());
        } else if (state instanceof GameState) {
            graphics.setColor(dullAzure); //sets game backgroundcolor
			graphics.fillRect(200,0,600,600); //width and height are params in game super class.

            //renders paddle
            renderPolygon(graphics, Color.WHITE, ((GameState) state).getPaddle());

            //renders bricks
            renderBricks(graphics, ((GameState) state).getBricks());

            //renders legend
            renderBrickLegend(graphics, ((GameState) state).getLegend());

            //renders balls
            renderBalls(graphics, ((GameState)state).getBalls());

            //renders lives
            graphics.setColor(Color.white);
            graphics.drawString("Lives: " + ((GameState)state).getLives(), 700, 20);

            if (((GameState)state).isPaused()) {
                graphics.setColor(new Color(255, 255, 255, 128));
                graphics.fillRect(0,0,800,600);
            }
            
        } //else if(state instanceof endState)
        
        
        /*
        Rectangle rectangle = game.getRectangle();
        graphics.setColor(Color.BLUE);
        graphics.fillRect(
            (int) rectangle.getX(),
            (int) rectangle.getY(),
            (int) rectangle.getWidth(),
            (int) rectangle.getHeight()
            );
        */
        /*
        state.getGameObjects().forEach(gameObject -> graphics.drawImage(
            gameObject.getSprite(),
            gameObject.getPosition().getX(),
            gameObject.getPosition().getY(),
            null
        )); 
        */
    }

    public void renderPolygon(Graphics graphics, Color color, Polygon element) {
		Position[] temp = element.getPoints();
		int[] xPoints = new int[temp.length];
		int[] yPoints = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			xPoints[i] = (int)temp[i].getX();
			yPoints[i] = (int)temp[i].getY();
		}
		graphics.setColor(color);
		graphics.fillPolygon(xPoints, yPoints, temp.length);
	}

    public void renderBricks(Graphics graphics, ArrayList<Brick> brickList) {
        for (int i = 0; i < brickList.size(); i++) {
            brickList.get(i).getPixelSprite().paint(graphics);
            //collision matrix testing
            //renderPolygon(graphics, new Color(255, 255, 255, 128), brickList.get(i).getCollisionMatrix());
        }
    }

    public void renderBrickLegend(Graphics graphics, ArrayList<Brick> brickLegend) {
        for (int i = 0; i < brickLegend.size(); i++) {
            brickLegend.get(i).getPixelSprite().paint(graphics);
            graphics.setColor(Color.white);
            Font font = new Font("Courier", Font.PLAIN, 10); // Or whatever size you want
            graphics.setFont(font);
            
            FontMetrics metrics = graphics.getFontMetrics(font);

            // Get width and height of the string
            int textWidth = metrics.stringWidth(brickLegend.get(i).getDesc());
            int textHeight = metrics.getAscent() - metrics.getDescent();

            // Calculate top-left point so that the center is at (centerX, centerY)
            int x = (int)brickLegend.get(i).getPosition().x - textWidth / 2;
            int y = (int)brickLegend.get(i).getPosition().y + textHeight / 2;

            graphics.drawString(brickLegend.get(i).getDesc(), x, y + 40);
            if (brickLegend.get(i) instanceof FastBrick) {
                graphics.drawString(Integer.toString(Ball.HASTYPOWERTICKER), (int)brickLegend.get(i).getPosition().x, y + 50);
            } else if (brickLegend.get(i) instanceof SlowBrick) {
                graphics.drawString(Integer.toString(Ball.LETHARGICPOWERTICKER), (int)brickLegend.get(i).getPosition().x, y + 50);
            }

            graphics.drawString("Score: " + GameState.score, 50, 500);
        }
    }

    public void renderBalls(Graphics graphics, ArrayList<Ball> ballList) {
        for (int i = 0; i < ballList.size(); i++) {
            //renderPolygon(graphics, new Color(255, 255, 255, 128), ballList.get(i).getCollisionMatrix());
            ballList.get(i).getPixelSprite().paint(graphics);
        }
    }

    public void renderButton(Graphics graphics, Button button) {
        button.getPixelSprite().paint(graphics);

        Font font = new Font("Courier", Font.PLAIN, 20); // Or whatever size you want
        graphics.setFont(font);
        
        FontMetrics metrics = graphics.getFontMetrics(font);

        // Get width and height of the string
        int textWidth = metrics.stringWidth(button.getText());
        int textHeight = metrics.getAscent() - metrics.getDescent();

        // Calculate top-left point so that the center is at (centerX, centerY)
        int x = (int)button.getPosition().x - textWidth / 2;
        int y = (int)button.getPosition().y + textHeight / 2;

        graphics.setColor(Color.black);
        graphics.drawString(button.getText(), x, y);
    }




    /*
    private void renderMap(State state, Graphics graphics) {
        Tile[][] tiles = state.getGameMap().getTiles();
        for(int x = 0; x < tiles.length; x++) {
            for(int y = 0; y < tiles[0].length; y++) {
                graphics.drawImage(
                        tiles[x][y].getSprite(),
                        x * game.SPRITE_SIZE,
                        y * game.SPRITE_SIZE,
                        null
                );
            }
        }
    }
     */
}
