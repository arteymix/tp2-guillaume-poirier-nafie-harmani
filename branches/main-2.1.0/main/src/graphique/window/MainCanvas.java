/*   This file is part of TP2.
 *
 *   TP2 is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   TP2 is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with TP2.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphique.window;

import content.KeySetting;

import graphique.event.DecorFlottant;
import graphique.component.Ovni;
import graphique.component.Canon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

import main.Main;

import util.Collisionable;
import util.Dessinable;
import util.Traductions;

/**
 * Classe contenant le canvas principal où les dessins seront effectués.
 * C'est ici que le code principal se trouve, que les collisions sont calculées
 * et tout et tout!
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class MainCanvas extends JComponent {

    /**
     * Pourcentage de vie pour que les vies soient affichés en rouge.
     */
    /**
     * Pourcentage de vie pour que les vies soient affichés en jaune.
     */
    private static final double PERCENTAGE_RED_LIFE = 0.25,
            PERCENTAGE_YELLOW_LIFE = 0.5;
    /**
     * Font utilisé par défaut.
     */
    /**
     * Font utilisé pour draw le GAME OVER!
     */
    public static final Font FONT = new Font("Comic sans ms", Font.BOLD, 15),
            FONT_GAME_OVER = new Font("Comic sans ms", Font.BOLD, 70);

    /**
     * Thread pour gérer les collisions indépendament du rendu, ce qui
     * améliore considérablement les performances du programme.
     */
    public final class Collision extends Thread {

        /**
         * Constructeur pour le Thread de collisions.
         */
        public Collision() {        
        super("Thread pour gérer les collisions");
        }
        
        /**
         * Le code pour gérer les collisions a simplement été déplacé ici.
         */
        @Override
        public void run() {
            while (Main.isRunning) {
                try {
                    long initTime = System.currentTimeMillis();
                    if (activity.equals(Activity.JEU)) {
                        for (int i = 0; i < Main.composantesDessinables.size(); i++) {
                            Dessinable d = Main.composantesDessinables.get(i);
                            /* Sous-boucle pour les collisions, question de tenter toutes
                             * les collisions possibles. 
                             */
                            if (d instanceof Collisionable) {
                                for (int j = 0; j < Main.composantesDessinables.size(); j++) {
                                    Dessinable e;
                                    // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                                    if (((e = Main.composantesDessinables.get(j)) instanceof Collisionable) && d != e) {
                                        if (((Collisionable) d).getRectangle().intersects(((Collisionable) e).getRectangle())) {
                                            // On provoque une collision entre chacun.
                                            ((Collisionable) d).collision((Collisionable) e);
                                            ((Collisionable) e).collision((Collisionable) d);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    while (Main.isPaused) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainCanvas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    int waitingTime = (int) Main.latency - (int) (System.currentTimeMillis() - initTime);
                    if (waitingTime >= 0) {
                        Thread.sleep((int) Main.latency - (int) (System.currentTimeMillis() - initTime));
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    };
    public Collision collision;

    /**
     * Reset le thread de collision lorsque l'on crée une nouvelle partie.
     */
    public void resetCollision() {
        collision = new Collision();
    }

    /**
     * Constructeur pour le canvas où le rendu est effectué.
     */
    MainCanvas() {
        super();
        //Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - 100, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        setPreferredSize(new Dimension(1024, 768));
        collision = new Collision();
    }
    /**
     * Objet contenant l'activité en cours.
     */
    public Activity activity = Activity.JEU;

    /**
     * Enum contenant les activités possibles (faire le rendu du jeu, afficher
     * les highscores, probablement un menu, etc...).
     */
    public enum Activity {

        /**
         * Activity pour le jeu.
         */
        JEU,
        /**
         * Activité pour montrer les highscores à l'écran.
         */
        HIGHSCORES,
        /**
         * Activité pour montrer l'aide à l'écran.
         */
        HELP,
        /**
         * Activité pour montrer la fin du jeu à l'écran.
         */
        GAME_OVER;
    }

    /**
     * Peint le JComponent avec le rendu du jeu.
     * @param g est l'objet Graphics du JPanel.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setFont(FONT);
        if (Main.isDebugEnabled) {
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.latency + " ms", 5, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.tempsDuRendu + " ms", 5, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (Main.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
            g.drawString(Traductions.get("debug.nbcompo") + " : " + Main.composantesDessinables.size() + " " + Traductions.get("debug.composantes"), 5, 60);
            g.drawString("Points : " + Main.points + " points", 5, 75);
            g.drawString(Traductions.get("debug.vies") + " canon 1 : " + Main.canon1.getVie() + " " + Traductions.get("debug.vies"), 5, 90);
            g.drawString(Traductions.get("debug.vies") + " canon 2 : " + Main.canon2.getVie() + " " + Traductions.get("debug.vies"), 5, 105);
            g.drawString(Traductions.get("debug.tempsjoue") + " : " + Main.timerSeconds, 5, 120);
            g.drawString("Tentacules tués : " + Main.tentaculesKilled + " tentacules", 5, 135);
            g.drawString("Niveau actuel : " + Main.level, 5, 150);
            g.drawString("La partie est-elle finie? " + Main.isGameOver, 5, 165);
            g.drawRect(0, 0, Main.getCanvasSizeX() - 1, Main.getCanvasSizeY() - 1);
        } else {
            // Le background est dessiné ici.
            g.drawImage(Main.imageBank.background, 0, 0, Main.getCanvasSizeX(), Main.getCanvasSizeY(), null);
        }
        switch (activity) {
            case GAME_OVER:
                int k = Main.getCanvasSizeY() / 2;
                g.setColor(Color.WHITE);
                g.setFont(FONT_GAME_OVER);
                g.drawString("GAME OVER!", (Main.getCanvasSizeX() / 2) - 250, Main.getCanvasSizeY() / 2);
                g.setFont(FONT);
                g.drawString(Main.messageDeFermeture, 15, k += 60);
                //////////////////////////////////////////////////////////////// 
                // Achievements
                // Afficher le nombre de parties restantes
                g.drawString("Achievements obtenus :", 15, k += 30);
                if (Main.highscore.noobObtained) {
                    g.drawString("Noob obtenu!", 15, k += 15);
                } else {
                    g.drawString("Noob en cours... (il reste encore " + (1 - Main.highscore.partiesCompletes) + " partie à compléter)", 15, k += 15);
                }
                if (Main.highscore.ownObtained) {
                    g.drawString("Own obtenu!", 15, k += 15);
                } else {
                    g.drawString("Own en cours... (il reste encore " + (10 - Main.highscore.partiesCompletes) + " parties à compléter)", 15, k += 15);
                }
                if (Main.highscore.pwnObtained) {
                    g.drawString("Pwn obtenu!", 15, k += 15);
                } else {
                    g.drawString("Pwn en cours... (il reste encore " + (1000 - Main.highscore.partiesCompletes) + " parties à compléter)", 15, k += 15);
                }
                if (Main.highscore.nukeObtained) {
                    g.drawString("Nuke obtenu!", 15, k += 15);
                } else {
                    g.drawString("Nuke en cours... (il faut tuer au moins 100 tentacules en une partie)", 15, k += 15);
                }
                if (Main.highscore.proObtained) {
                    g.drawString("Pro obtenu!", 15, k += 15);
                } else {
                    g.drawString("Pro en cours (il faut terminer une partie avec plus de 250 points pour l'obtenir!)", 15, k += 15);
                }
                if (Main.highscore.leetObtained) {
                    g.drawString("1337 obtenu!", 15, k += 15);
                } else {
                    g.drawString("1337 en cours (il faut terminer une partie avec plus de 1000 points pour l'obtenir!)", 15, k += 15);

                }
                if (Main.highscore.bazingaObtained) {
                    g.drawString("Bazinga obtenu!", 15, k += 15);
                } else {
                    g.drawString("Bazinga en cours... (terminer une partie sans gagner de points pour l'obtenir!)", 15, k += 15);
                }
                ////////////////////////////////////////////////////////////////
                g.setColor(Color.BLACK);
                break;
            case JEU:
                ////////////////////////////////////////////////////////////////
                // On incrémente le timer de Main.gameValues.latency millisecondes.
                Main.timerSeconds += Main.latency;
                ////////////////////////////////////////////////////////////////
                // Le jeu!
                ArrayList<Dessinable> aEnlever = new ArrayList<Dessinable>();
                // Génération des nuages 
                DecorFlottant.createDecorsFlottants();
                // Génération des Ovnis    
                Ovni.createOvni();
                // END Génération des Ovnis
                for (int i = 0; i < Main.composantesDessinables.size(); i++) {
                    Dessinable d = Main.composantesDessinables.get(i);
                    /* Sous-boucle pour les collisions, question de tenter toutes
                     * les collisions possibles. Les collisions sont désormais
                     * gérées dans un Thread indépendant du rendu!
                     */
//                    if (d instanceof Collisionable) {
//                        for (int j = 0; j < Main.composantesDessinables.size(); j++) {
//                            Dessinable e;
//                            // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
//                            if (((e = Main.composantesDessinables.get(j)) instanceof Collisionable) && d != e) {
//                                if (((Collisionable) d).getRectangle().intersects(((Collisionable) e).getRectangle())) {
//                                    // On provoque une collision entre chacun.
//                                    ((Collisionable) d).collision((Collisionable) e);
//                                    ((Collisionable) e).collision((Collisionable) d);
//                                }
//                            }
//                        }
//                    }
                    if (d.isDessinable) {
                        if (!(d instanceof Canon)) {
                            if (Main.isDebugEnabled) {
                                d.dessinerDeboguage(g);
                            } else {
                                d.dessiner(g);
                            }
                        }
                    } else {
                        Main.composantesDessinables.remove(d);
                    }
                }
                ////////////////////////////////////////////////////////////////
                // Draw des canons
                if (Main.isDebugEnabled) {
                    Main.canon1.dessinerDeboguage(g);
                    if (Canon.isCanon2ValidTarget) {
                        Main.canon2.dessinerDeboguage(g);
                    }
                } else {
                    Main.canon1.dessiner(g);
                    if (Canon.isCanon2ValidTarget) {
                        Main.canon2.dessiner(g);
                    }
                }
                ////////////////////////////////////////////////////////////////
                // L'interface utilisateur est dessiné ici
                if (!Main.isDebugEnabled) {
                    drawUserInterface(g);
                }
                ////////////////////////////////////////////////////////////////
                break;
            case HIGHSCORES:
                /* En cas où c'est le rendu des meilleurs scores qui est activé,
                 * on itère le dictionnaire dans Main.highscores.
                 */
                int x = 15;
                if (Main.isDebugEnabled) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                int positionInit = 440;
                g.drawString("Meilleur scores :", x, positionInit += 15);
                for (String s : Main.highscore.getScores()) {
                    g.drawString(s, x, positionInit += 15);
                }
                g.drawString("Trophées :", x, positionInit += 30);
                g.drawString("Noob " + (Main.highscore.noobObtained ? "complété!" : "en cours... (reste " + (1 - Main.highscore.partiesCompletes) + " partie à terminer)"), x, positionInit += 15);
                g.drawString("Own " + (Main.highscore.ownObtained ? "complété!" : "en cours... (reste " + (10 - Main.highscore.partiesCompletes) + " partie à terminer)"), x, positionInit += 15);
                g.drawString("Pwn " + (Main.highscore.pwnObtained ? "complété!" : "en cours... (reste " + (1000 - Main.highscore.partiesCompletes) + " partie à terminer)"), x, positionInit += 15);
                g.drawString("Nuke " + (Main.highscore.nukeObtained ? "complété!" : "en cours... (éliminez au moints 250 tentacules mauves en une partie"), x, positionInit += 15);
                g.drawString("Pro " + (Main.highscore.proObtained ? "complété!" : "en cours...(obtenir 250 points en une partie)"), x, positionInit += 15);
                g.drawString("1337 " + (Main.highscore.leetObtained ? "complété!" : "en cours...(obtenir 1000 points en une partie)"), x, positionInit += 15);
                g.drawString("Bazinga! " + (Main.highscore.bazingaObtained ? "complété!" : "en cours...(obtenir 0 point en une partie)"), x, positionInit += 15);
                if (Main.isDebugEnabled) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                break;
            case HELP:
                ////////////////////////////////////////////////////////////////
                // Draw pour l'aide
                int y = 15;
                positionInit = 0;
                g.setColor(Color.WHITE);
                g.drawString("Aide", y, positionInit += 15);
                g.drawString("Le but du jeu est simple : survivre le plus longtemps possible afin de faire le maximum de points", y, positionInit += 30);
                g.drawString("et éventuellement mériter sa place dans les highscores ainsi qu'obtenir les prestigieux achievements.", y, positionInit += 15);
                g.drawString("Pour ce faire, il ne faut, mais surtout pas, laisser tentacule mauve (le gros boss méchant) envoyer", y, positionInit += 30);
                g.drawString("quatre de ses tentacules au sol, autrement c'est la fin pour vous et vos compatriotes humains.", y, positionInit += 15);
                g.drawString("L'équipe des développeurs vous invitent à avoir beaucoup de plaisir à jouer à ce jeu, et surtout", y, positionInit += 30);
                g.drawString("nous rapporter les bugs et autre dysfonctionnements. Nous apprécions aussi les idées et nouveaux", y, positionInit += 15);
                g.drawString("concepts révolutionnaires!", y, positionInit += 15);
                KeySetting.drawKeySettingHelp(g);
                g.setColor(Color.BLACK);
                break;
            ////////////////////////////////////////////////////////////////
        }
        /* On met paintDone true, ainsi le programme sais exactement quand le
         * draw est terminé et peut rappeler un redessinage.
         */
        Main.paintDone = true;
    }

    /**
     * Méthode qui fait le rendu de l'interface utilisateur.
     * @param g est le Graphics sur lequel le rendu est effectué.
     */
    private void drawUserInterface(Graphics g) {
        // Vie du canon 1
        for (int i = 0; i < 4; i++) {
            g.drawImage(Main.imageBank.projectileEnnemi, (i * 90) + 30, 30, 45, 45, null);
        }
        for (int i = 0; i < Main.alienAuSol; i++) {
            g.drawImage(Main.imageBank.projectileEnnemi, i * 90, 15, 90, 90, null);
        }
        g.setColor(Color.WHITE);
        g.drawString("POINTS : " + Main.points, Main.getCanvasSizeX() - 150, 15);
        g.drawString("NIVEAU : " + Main.level, Main.getCanvasSizeX() - 150, 30);
        g.setColor(Color.BLACK);
        if (Canon.isCanon2ValidTarget) {
            g.setColor(Color.GREEN);
            if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_RED_LIFE) {
                g.setColor(Color.RED);
            } else if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_YELLOW_LIFE) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(0, (int) Main.getCanvasSizeY() - 15, (int) (((double) Main.canon1.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.getCanvasSizeX()), 15);
            if ((double) Main.canon2.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_RED_LIFE) {
                g.setColor(Color.RED);
            } else if ((double) Main.canon2.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_YELLOW_LIFE) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect((int) (Main.getCanvasSizeX()) - (int) (((double) Main.canon2.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.getCanvasSizeX()), (int) Main.getCanvasSizeY() - 15, (int) (((double) Main.canon2.getVie() / (2.0 * (double) Canon.VIE_INIT_CANON)) * Main.getCanvasSizeX()), 15);
        } else {
            g.setColor(Color.GREEN);
            if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_RED_LIFE) {
                g.setColor(Color.RED);
            } else if ((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON < PERCENTAGE_YELLOW_LIFE) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GREEN);
            }
            g.fillRect(0, (int) Main.getCanvasSizeY() - 15, (int) (((double) Main.canon1.getVie() / (double) Canon.VIE_INIT_CANON) * Main.getCanvasSizeX()), 15);
            // Lorsque tout est terminé, on change la couleur pour noir.
            g.setColor(Color.BLACK);
        }
    }
}
