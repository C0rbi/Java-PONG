package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed;
    public boolean upPressed2, downPressed2;  // Joueur 2

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // Joueur 1 (Z/S)
        if (code == KeyEvent.VK_Z) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        // Joueur 2 (Flèches)
        if (code == KeyEvent.VK_UP) {
            upPressed2 = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed2 = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // Joueur 1
        if (code == KeyEvent.VK_Z) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        // Joueur 2
        if (code == KeyEvent.VK_UP) {
            upPressed2 = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed2 = false;
        }
    }
}