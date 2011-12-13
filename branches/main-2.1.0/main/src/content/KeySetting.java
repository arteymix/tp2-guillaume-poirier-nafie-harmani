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
package content;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import main.Main;

/**
 * Classe de configuration pour les touches. Sera objet et sérialisable dans un
 * avenir proche.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class KeySetting {
    ////////////////////////////////////////////////////////////////////////////
    // Canon 1

    /**
     * CANON_1_SHOOT est un identifiant de clavier qui permet au Canon 1 de tirer. 
     */
    public static final int CANON_1_SHOOT = KeyEvent.VK_W;
    /**
     *  CANON_1_LEFT est un identifiant de clavier qui permet au Canon 1 de se 
     *                deplacer à gauche si possible.
     */
    public static final int CANON_1_LEFT = KeyEvent.VK_A;
    /**
     *  CANON_1_RIGHT est un identifiant de clavier qui permet au Canon 1 de se 
     *                 deplacer à droite si possible.
     */
    public static final int CANON_1_RIGHT = KeyEvent.VK_D;
    /**
     *  CANON_1_AIM_LEFT est un identifiant de clavier qui permet au Canon 1 
     *                   d'orienter son tir vers la gauche si possible.
     */
    public static final int CANON_1_AIM_LEFT = KeyEvent.VK_K;
    /**
     * CANON_1_AIM_RIGHT est un identifiant de clavier qui permet au Canon 1 
     *                   d'orienter son tir vers la droite si possible.
     */
    public static final int CANON_1_AIM_RIGHT = KeyEvent.VK_L;
    ////////////////////////////////////////////////////////////////////////////
    // Canon 2
    /**
     * CANON_2_SHOOT est un identifiant de clavier qui permet au Canon 2 de tirer.
     */
    public static final int CANON_2_SHOOT = KeyEvent.VK_UP;
    /**
     * CANON_2_LEFT est un identifiant de clavier qui permet au Canon 2 de se 
     *                deplacer à gauche si possible.
     */
    public static final int CANON_2_LEFT = KeyEvent.VK_LEFT;
    /**
     * CANON_2_RIGHT est un identifiant de clavier qui permet au Canon 2 de se 
     *                 deplacer à droite si possible.
     */
    public static final int CANON_2_RIGHT = KeyEvent.VK_RIGHT;
    /**
     * CANON_2_AIM_LEFT est un identifiant de clavier qui permet au Canon 2 
     *                   d'orienter son tir vers la gauche si possible.
     */
    public static final int CANON_2_AIM_LEFT = KeyEvent.VK_NUMPAD2;
    /**
     *  CANON_2_AIM_RIGHT est un identifiant de clavier qui permet au Canon 2 
     *                   d'orienter son tir vers la droite si possible.
     */
    public static final int CANON_2_AIM_RIGHT = KeyEvent.VK_NUMPAD3;
    ////////////////////////////////////////////////////////////////////////////
    // Other settings
    /**
     * SHOW_HIGHSCORES est un identifiant de clavier qui permet d'afficher 
     *                 le tableau de pointage.
     */
    public static final int SHOW_HIGHSCORES = KeyEvent.VK_H;
    /**
     * PAUSE est un identifiant de clavier qui permet de mettre le programme en pause.
     */
    public static final int PAUSE = KeyEvent.VK_P;
    /**
     * QUIT est un identifiant de clavier qui permet de quitter le programme.
     */
    public static final int QUIT = KeyEvent.VK_ESCAPE;
    /**
     * SHOW_DEBUG est un identifiant de clavier qui permet de mettre le programme en mode debug.
     */
    public static final int SHOW_DEBUG = KeyEvent.VK_F6;
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Dessine la configuration des touches sur un Graphics.
     * @param g est le Graphics sur lequel la configuration des touches sera
     * dessinée.
     */
    public static void drawKeySettingHelp(Graphics g) {
        if (Main.isDebugEnabled) {
            g.setColor(Color.BLACK);
        } else if (Main.level == Main.LEVEL_1) {
            g.setColor(Color.WHITE);
        } else if (Main.level == Main.LEVEL_2) {
            g.setColor(Color.WHITE);
        } else if (Main.level == Main.LEVEL_3) {
            g.setColor(Color.WHITE);
        } else if (Main.level == Main.LEVEL_BONUS) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        int i = 440;
        g.drawString("Agencement du clavier", 15, i += 15);
        ////////////////////////////////////////////////////////////////////////
        // Canon 1
        g.drawString("Canon 1 (celui de gauche) :", 15, i += 30);
        g.drawString("W Tirer", 15, i += 15);
        g.drawString("A Aller à gauche", 15, i += 15);
        g.drawString("D Aller à droite", 15, i += 15);
        g.drawString("K Bouger la tête de canon vers la gauche", 15, i += 15);
        g.drawString("L Bouger la tête de canon vers la droite", 15, i += 15);
        ////////////////////////////////////////////////////////////////////////
        // Canon 2
        g.drawString("Canon 2 (celui de droite) :", 15, i += 30);
        g.drawString("UP Tirer", 15, i += 15);
        g.drawString("LEFT Aller à gauche", 15, i += 15);
        g.drawString("RIGHT Aller à droite", 15, i += 15);
        g.drawString("NUMPAD_2 Bouger la tête de canon vers la gauche", 15, i += 15);
        g.drawString("NUMPAD_3 Bouger la tête de canon vers la droite", 15, i += 15);
        ////////////////////////////////////////////////////////////////////////
        // Autres fonctions
        g.drawString("Autres fonctions :", 15, i += 30);
        g.drawString("ESC Fermer le jeu", 15, i += 15);
        g.drawString("F6 Entrer en mode débogage", 15, i += 15);
        g.drawString("P Mettre le jeu en pause", 15, i += 15);
        g.drawString("H Afficher les highscores", 15, i += 15);
        g.setColor(Color.BLACK);
        ////////////////////////////////////////////////////////////////////////
    }
}
