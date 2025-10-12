package main;

import entity.Ball;
import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // extends JPanel permet de fair hériter (c une sub class de JPanel)
    // implements Runnable c pour que Thread puise fonctionner

    // SCREEN SETTINGS
    int screenWidth = 1280; //
    int screenHeight = 720; //

    // FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // la class Thread c la main boucle pour avoir 60fps (refresh plein de foix)
    Player playerL = new Player(this ,keyH, 1);
    Player playerR = new Player(this ,keyH, 2);
    Ball ball = new Ball(this, playerR, playerL);

    // Set player default position
    int playerX = 0;
    int playerY = 100;
    int speed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // permet de donner la taille de l'écran avec sa width et sa height
        this.setBackground(Color.BLACK); // Met la couleur du background en noir
        this.setDoubleBuffered(true); // Les process vont être fait en arrière-plans dans un buffer (augment les performance).
        this.addKeyListener(keyH); // permet à la fenêtre de reconnaitre le clavier
        this.setFocusable(true); // IMPORTANT : autorise le panel à capter le clavier (askip)
    }

    // GETTER
    public int getScreenHeight() {return this.screenHeight;}
    public int getScreenWidth() {return this.screenWidth;}

    public void startGameThread() {

        gameThread = new Thread(this); // donne tout la class actuel dans le constructeur
        gameThread.start(); // Call la méthodes run
    }

    // la Class Thread quand elle est appelé utilise automatiquement les méthodes run
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS; // 1*10^9 c des nanoseconde du coup 1s / 60
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount ++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        playerR.update();
        playerL.update();
        ball.update();

    }

    public void paintComponent(Graphics g) {

        // sur GPanel on utilise paintComponent et Graphics c comme ca.
        super.paintComponent(g); // quand on utilise paintComponent// t, on fait comme ça.

        Graphics2D g2 = (Graphics2D)g;
        // paintComponent c bien, mais avec Graphics2D, tu peux faire plus de truc

        playerR.draw(g2);
        playerL.draw(g2);
        ball.draw(g2);

        g2.dispose(); // permet de moin use des ressources, sa release les resources utiliser
    }
}
