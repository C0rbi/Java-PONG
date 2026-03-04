package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    KeyHandler keyH;
    GamePanel gp;
    int team;

    // Dimensions des barres
    int playerWidth = 20;    // Largeur de la barre
    int playerHeight = 100;  // Hauteur de la barre


    public Player(GamePanel gp, KeyHandler keyH, int team) {
        this.gp = gp;
        this.keyH = keyH;
        this.team = team;
        setDefaultValues();
    }

    public void setDefaultValues() {
        switch (team) {
            case 1:  // Joueur gauche
                x = 50;
                y = (gp.getScreenHeight() / 2) - (playerHeight / 2);  // Centré
                speed = 10;
                velocityY = 0;
                break;
            case 2:  // Joueur droit
                x = gp.getScreenWidth() - 50 - playerWidth;
                y = (gp.getScreenHeight() / 2) - (playerHeight / 2);  // Centré
                speed = 10;
                velocityY = 0;
                break;
        }
    }

    // GETTER
    public void update() {
        if (team == 1) {
            if (keyH.upPressed && y >= 0) {
                y -= speed;
                velocityY = -speed;
            } else {
                velocityY = 0;
            }
            if (keyH.downPressed && y + playerHeight < gp.getScreenHeight()) {
                y += speed;
                velocityY = speed;
            } else {
                velocityY = 0;
            }
        } else {
            if (keyH.upPressed2 && y >= 0) {
                y -= speed;
                velocityY = -speed;
            } else {
                velocityY = 0;
            }
            if (keyH.downPressed2 && y + playerHeight < gp.getScreenHeight()) {
                y += speed;
                velocityY = speed;
            } else {
                velocityY = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, playerWidth, playerHeight);
    }
}