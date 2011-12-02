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

import java.util.ArrayList;
import util.Dessinable;
import util.Vecteur;

/**
 * Contient tous les paramètres du jeu.
 * Les paramètres propres aux objets ne doivent pas être placés ici, ils seront
 * sérialisé avec l'ArrayList. Il sert aussi à regrouper les variables afin de
 * faciliter l'implémentation de mode de jeu.
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public final class GameValues {

    public long remainingTime = 100;

    /**
     * Retourne le temps restants sous forme d'un String formaté.
     * @return le temps restants sous forme d'un String formaté.
     */
    public String getRemainingTime() {


// TODO formater le temps en minutes secondes.


        return null;

    }
    /**
     * Si true, les highscores sont affichés.
     */
    public boolean showHighscores = false;
    /**
     * Variable définissant si le programme est en exécution afin d'avertir les threads
     * dans le programme en cas de fermeture.
     */
    public boolean isRunning = true;
    /**
     * Variable définissant la durée entre chaque frame. Elle peut être diminué
     * si le système est rapide, c'est-à-dire qu'il n'a pas besoin d'autant de 
     * latence pour dessiner l'image. Dans le cas ou le système "time out", la 
     * latence devrait être augmentée.
     */
    public double latency = 15;
    /**
     * Variable définissant si le mode débogage est activé.
     */
    public long tempsDuRendu = 0;
    /**
     * Si true, le jeu est en pause, tous les threads sont en attente.
     */
    public boolean isPaused = false;
    /**
     * Est true quand le rendu est fini, false quand le rendu est en cours.
     */
    public boolean paintDone = false;
    /**
     * Variable qui contient le temps de jeu.
     */
    public long time;
    /**
     * Timer qui donne le temps depuis le début du jeu.
     */
    public long timerSeconds = 15;
    /**
     * ArrayList des composantes dessinables.
     */
    public boolean isDebugEnabled = false;
    /**
     * ArrayList contenant les objets dessinables.
     */
    public ArrayList<Dessinable> composantesDessinables = new ArrayList<Dessinable>();
    /**
     * La variable points contient les points du/des joueur/s.
     */
    public int points = 0;
    /**
     * Cette variable définit le niveau du jeu.
     */
    public int level = 1;
     /**
     * Ce vecteur est le vecteur dimension du canvas ou les composants et
     * graphics sont dessinés.
     */
    public Vecteur canvasSize = new Vecteur(1024, 768);
    ////////////////////////////////////////////////////////////////////////////
    /* Variables pour les objets de valeurs.
     * Devrait être transféré dans les objets éventuellement.
     */    
    ////////////////////////////////////////////////////////////////////////////
    /**
     * 
     */
    public DecorFlottantValues decorFlottant = new DecorFlottantValues();
    
    /**
     * 
     */
    public ProjectileValues projectile = new ProjectileValues();
    /**
     * 
     */
    public OvniValues ovni = new OvniValues();
    /**
     * 
     */
    public ProjectileEnnemiValues projectileEnnemi = new ProjectileEnnemiValues();
   

    /**
     * 
     */
    public class DecorFlottantValues {

        /**
         * 
         */
        public int PROBABILITE_APPARITION_NUAGE = 1000;
    }

   

    /**
     * 
     */
    public class ProjectileValues {

        /**
         * 
         */
        public double GRAVITY = 0.8;
    }

    /**
     * 
     */
    public class ProjectileEnnemiValues {

        /**
         *
         */
        public final int MISSILE_0 = 0;
    }

    /**
     * 
     */
    public class OvniValues {

        
    }
}
