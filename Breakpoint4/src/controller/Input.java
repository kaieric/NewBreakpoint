package controller;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utilities.Position;

import java.awt.event.KeyEvent;

public class Input implements KeyListener, MouseListener, MouseMotionListener {

    private Position pointerPosition;
    boolean mouseClicked;
    boolean mousePressed;

    private boolean[] pressed;

    public Input() {
        pressed = new boolean[255];
        pointerPosition = new Position(0,0);
    }

    public boolean isPressed(int keyCode) {
        return pressed[keyCode];
    }

    public void clearMouseClick() {
        mouseClicked = false;
    }

    public Position getPointerPosition() {
        return pointerPosition;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
        mouseClicked = true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        pointerPosition.setX(e.getX());
        pointerPosition.setY(e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        pointerPosition.setX(e.getX());
        pointerPosition.setY(e.getY());
    }

}