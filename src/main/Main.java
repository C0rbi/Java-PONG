package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de quite la fenêtre
        window.setResizable(false); // permet de ne pas pouvoir modifier la taille de la fenêtre
        window.setTitle("2D Game"); // donne un nom à la fenêtre

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // normalement c juste pour applique la class GamePanel a la fenêtre.
        window.pack(); // lui donne la bonne taille

        window.setLocationRelativeTo(null); // met la fenêtre au centre de l'écran
        window.setVisible(true); // permet de voir la fenêtre

        gamePanel.startGameThread();
    }
}