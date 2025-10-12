package entity;

import main.GamePanel;

import java.awt.*;

public class Ball extends Entity{

    GamePanel gp;
    Player playerR;
    Player playerL;

    // Dimensions de la ball
    int ballWidth = 20;
    int ballHeight = 20;

    // Vecteur de déplacement
    int velocityX = 5;  // Vitesse horizontale
    int velocityY = 3;  // Vitesse verticale

    public Ball(GamePanel gp, Player playerR, Player playerL) {
        this.gp = gp;
        this.playerR = playerR;
        this.playerL = playerL;

        setDefaultValues();
    }

    public void setDefaultValues() {
        x = gp.getScreenWidth() / 2 - ballWidth / 2; // centré au milieu de l'écran
        y = gp.getScreenHeight() / 2 - ballHeight / 2; // centré
        velocityX = 5;  // Part vers la droite
        velocityY = 3;  // Part légèrement vers le bas
    }

    public void update() {
        // Déplacement de la balle
        x += velocityX;
        y += velocityY;

        // Collision avec le haut et le bas de l'écran
        if (y <= 0 || y >= gp.getScreenHeight() - ballHeight) {
            velocityY = -velocityY;  // Inverse la direction verticale
        }

        // Collision avec le joueur gauche
        checkPaddleCollision(playerL);

        // Collision avec le joueur droit
        checkPaddleCollision(playerR);

        // Si la balle sort à gauche (point pour joueur 2).
        if (x < 0) {
            // TODO: Ajouter un point au joueur 2
            resetBall();
        }

        // Si la balle sort à droite (point pour joueur 1).
        if (x > gp.getScreenWidth()) {
            // TODO: Ajouter un point au joueur 1
            resetBall();
        }
    }

    private void checkPaddleCollision(Player player) {
        // Vérifie si les rectangles se chevauchent
        if (x < player.x + player.playerWidth &&           // Balle touche le côté droit de la raquette
                x + ballWidth > player.x &&                     // Balle touche le côté gauche de la raquette
                y < player.y + player.playerHeight &&           // Balle touche le bas de la raquette
                y + ballHeight > player.y) {                    // Balle touche le haut de la raquette

            // Inverse la direction horizontale
            velocityX = -velocityX;

            // BONUS : Calcule l'angle selon où ça tape sur la raquette
            int paddleCenter = player.y + player.playerHeight / 2;
            int ballCenter = y + ballHeight / 2;
            int hitOffset = ballCenter - paddleCenter;

            // Plus c'est loin du centre, plus l'angle est prononcé
            velocityY += hitOffset / 15;

            // Limite la vitesse verticale pour éviter que ça parte en vrille
            if (velocityY > 8) velocityY = 8;
            if (velocityY < -8) velocityY = -8;

            // Empêche la balle de rester coincée dans la raquette
            if (player.team == 1) {
                x = player.x + player.playerWidth;  // Pousse la balle à droite de la raquette gauche
            } else {
                x = player.x - ballWidth;  // Pousse la balle à gauche de la raquette droite
            }
        }
    }

    private void resetBall() {
        // Remet la balle au centre
        x = gp.getScreenWidth() / 2 - ballWidth / 2;
        y = gp.getScreenHeight() / 2 - ballHeight / 2;

        // Change la direction de départ (aléatoire)
        velocityX = (Math.random() > 0.5) ? 5 : -5;
        velocityY = (int)(Math.random() * 6 - 3);  // Entre -3 et +3
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.fillRect(x, y, ballWidth, ballHeight);
    }
}