package display;
import javax.swing.*;

import controller.Input;
import game.*;
import game.state.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame { //just lets us put everything in the constructor what would normally be in a main method. Solves some problems with instance vars extending Jframe.

    private Canvas canvas;
    private Renderer renderer;

    public Display(int width, int height, Input input) {
        setTitle("Breakpoint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        this.renderer = new Renderer();

        canvas = new Canvas(); //initializes canvas
        canvas.setPreferredSize(new Dimension(width, height)); //sets the size
        canvas.setFocusable(false); //stops canvas from seeing input from keyboard, even if keylistener is later added
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        add(canvas); //adds canvas component to display
        addKeyListener(input); //JFrame display is what generates all of the events, and therefore needs the keylistener.
        pack(); //calculates the size of the JFrame depending on its children

        //canvas.createBufferStrategy(3); //stops flickering by drawing a backup image in between paints

        setLocationRelativeTo(null); //causes the window to pop up centered rather than top left
        setVisible(true); //default is not visible for some raisin.

        canvas.createBufferStrategy(3); //stops flickering by drawing a backup image in between paints
    }

    public void render(State state) { //is an alternative to each of the objects drawing themselves
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics(); //this object is the brush

        //lets you know display is working
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //renders the state eacha frame.
        renderer.render(state, graphics);

        graphics.dispose(); //destroys the graphics object for memory saving reasons. this is because we are creating a new graphics object for each render call, rather than just passing one around.
        bufferStrategy.show(); //effectively pushes the buffer to the top, making it visible at the end of this render method.
    }
}