package main;

import content.ImageBank;
import graphique.InterfaceGraphique;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public class Main {

    public static ImageBank imageBank;
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public static double latency = 10;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public static long tempsDuRendu = 0;
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public static boolean isRunning = true;
    /**
     * 
     */
    public static boolean isPaused = false;
    private static InterfaceGraphique tp2 = new InterfaceGraphique();
    /**
     * @param args the command line arguments
     */
    public static Thread threadRenduGraphique;

    public static void main(String[] args) {
        
        try {
        imageBank = new ImageBank();
        } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Thread pour le rendu
        threadRenduGraphique = new Thread() {

            @Override
            public void run() {
                while (isRunning) {
                    long startedTime = System.currentTimeMillis();
                    // On peint l'interface, ce qui oblige les composantes à calculer leurs animations.
                    tp2.mainCanvas.repaint();
                    try {
                        /* currentTime vaut le temps en millisecondes prit pour faire un rendu.
                         * En quelque sorte, si le rendu est trop long, on attendra moins 
                         * longtemps avant le suivant afin de ne pas causer d'accélération 
                         * subites en cours de jeu. Si le temps de redraw est plus grand que 10 ms,
                         * soit 100 fps, on passe directement au prochain frame.
                         */
                        tempsDuRendu = (System.currentTimeMillis() - startedTime);
                        if (tempsDuRendu > 10.0) {
                            Thread.sleep(0);
                        } else {
                            Thread.sleep((int) (latency - tempsDuRendu));
                        }
                        while (isPaused) {
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        threadRenduGraphique.start();
    }
}
