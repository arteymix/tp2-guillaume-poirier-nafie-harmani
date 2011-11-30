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

import graphique.event.DecorFlottant;
import graphique.component.Ovni;
import graphique.component.Canon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JComponent;

import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Traductions;

/**
 * Classe contenant le canvas principal où les dessins seront effectués.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class MainCanvas extends JComponent implements Serializable {

    

    /**
     * Constructeur pour le canvas où le rendu est effectué.
     */
    MainCanvas() {
        super();
        setPreferredSize(new Dimension((int) Main.gameValues.canvasSize.x, (int) Main.gameValues.canvasSize.y));
    }
    /**
     * Objet contenant l'activité en cours.
     */
    Activity activity = Activity.JEU;

    /**
     * Enum contenant les activités possibles (faire le rendu du jeu, afficher
     * les highscores, probablement un menu, etc...).
     */
    enum Activity {

        JEU,
        HIGHSCORES;
    }
    private final Font FONT = new Font("Comic sans ms", Font.BOLD, 15);
    
    /**
     * Peint le JPanel avec le rendu du jeu.
     * @param g est l'objet Graphics du JPanel.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setFont(FONT);
        if (Main.gameValues.isDebugEnabled) { // TODO Temporaire le | true, c'est pour avoir des valeurs en mode normal seulement
            // On affiche les variables seulement en mode de débogage...
            g.drawString(Traductions.get("debug.latence") + " : " + Main.gameValues.latency + " ms", 5, 15);
            g.drawString(Traductions.get("debug.tempsdurendu") + " : " + Main.gameValues.tempsDuRendu + " ms", 5, 30);
            g.drawString(Traductions.get("debug.modedebogage") + " : " + (Main.gameValues.isDebugEnabled ? Traductions.get("debug.active") : Traductions.get("debug.desactive")), 5, 45);
            g.drawString("Nombre de composantes dessinable"+" : " + Main.gameValues.composantesDessinables.size() + " composantes", 5, 60);
            g.drawString("Points : " + Main.gameValues.points + " points", 5, 75);
            g.drawString("Vies canon 1 : " + Main.canon1.getVie() + " vies", 5, 90);
            g.drawString("Vies canon 2 : " + Main.canon2.getVie() + " vies", 5, 105);
            g.drawString("Temps restant : " +Main.gameValues.remainingTime, 5, 120);
            g.drawRect(0, 0, (int) Main.gameValues.canvasSize.x - 1, (int) Main.gameValues.canvasSize.y - 1);

        } else {
            // Le background est dessiné ici.
            switch (Main.gameValues.level) {
                case 0:
                    g.drawImage(Main.imageBank.background, 0, 0,(int)Main.gameValues.canvasSize.x,(int)Main.gameValues.canvasSize.y, null);
                    break;
                case 1:
                    g.drawImage(Main.imageBank.background, 0, 0,1024,768, null);
            }
        }
        switch (activity) {
            case JEU:
                // Le jeu!
                ArrayList<Canon> listeDeCanonDessinable = new ArrayList<Canon>();
                ArrayList<Dessinable> aEnlever = new ArrayList<Dessinable>();
                // Génération des nuages 
                DecorFlottant.createNuage();
                // Génération des Ovnis    
                Ovni.createOvni();
                // END Génération des Ovnis
                for (int i = 0; i < Main.gameValues.composantesDessinables.size(); i++) {
                    Dessinable d = Main.gameValues.composantesDessinables.get(i);
                    /* Sous-boucle pour les collisions, question de tenter toutes
                     * les collisions possibles. 
                     */
                    if (d instanceof Collisionable) {
                        for (int j = 0; j < Main.gameValues.composantesDessinables.size(); j++) {
                            Dessinable e;
                            // Il est important de spécifier que d != e pour ne pas provoquer d'intercollision.
                            if (((e = Main.gameValues.composantesDessinables.get(j)) instanceof Collisionable) && d != e) {
                                if (((Collisionable) d).getRectangle().intersects(((Collisionable) e).getRectangle())) {
                                    // On provoque une collision entre chacun.
                                    ((Collisionable) d).collision((Collisionable) e);
                                    ((Collisionable) e).collision((Collisionable) d);
                                }
                            }
                        }
                    }
                    if (d.isDessinable) {
                        if (!(d instanceof Canon)) {
                            if (Main.gameValues.isDebugEnabled) {
                                d.dessinerDeboguage(g);
                            } else {
                                d.dessiner(g);
                            }
                        } else {
                            listeDeCanonDessinable.add((Canon) d);
                        }
                    } else {
                        aEnlever.add(d);

                    }
                }
                //<editor-fold defaultstate="collapsed" desc="Boucle pour supprimer les objets non dessinable. Autrement, il arrive que certains objets ne soient pas dessinés, car la liste de composantes dessinables est dynamique.">
                for (Dessinable d : aEnlever) {
                    Main.gameValues.composantesDessinables.remove(d);
                }
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Les canons sont dessinés à la toute fin (ici), afin de s'assurer qu'ils apparaissent par dessus les missiles.">
                for (Canon c : listeDeCanonDessinable) {
                    if (Main.gameValues.isDebugEnabled) {
                        c.dessinerDeboguage(g);
                    } else {
                        c.dessiner(g);
                    }
                }
                //</editor-fold>
                break;
            case HIGHSCORES:
                /* En cas où c'est le rendu des meilleurs scores qui est activé,
                 * on itère le dictionnaire dans Main.highscores.
                 */
                int x = 100;
                if (Main.gameValues.isDebugEnabled) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.drawString(Main.highscore.toString(), x, 400);
                g.drawString("Noob " + (Main.highscore.NOOB_OBTAINED ? "complété!" : "en cours..."), x, 415);
                g.drawString("Own " + (Main.highscore.OWN_OBTAINED ? "complété!" : "en cours..."), x, 430);
                g.drawString("Pwn " + (Main.highscore.PWN_OBTAINED ? "complété!" : "en cours..."), x, 445);
                g.drawString("Nuke " + (Main.highscore.NUKE_OBTAINED ? "complété!" : "en cours..."), x, 460);
                g.drawString("Pro " + (Main.highscore.PRO_OBTAINED ? "complété!" : "en cours..."), x, 475);
                g.drawString("1337 " + (Main.highscore.LEET_OBTAINED ? "complété!" : "en cours..."), x, 490);
                g.drawString("Bazinga! " + (Main.highscore.BAZINGA_OBTAINED ? "complété!" : "en cours..."), x, 505);
                if (Main.gameValues.isDebugEnabled) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
        }
        Main.gameValues.paintDone = true;
    }
}