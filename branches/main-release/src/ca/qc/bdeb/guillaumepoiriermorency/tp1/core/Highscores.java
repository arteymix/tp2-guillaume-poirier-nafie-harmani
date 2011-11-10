/*  This file is part of "Sauver l'univers du tentacule mauve en jouant 
 *  au Boggle!".
 *
 *  "Sauver l'univers du tentacule mauve en jouant au Boggle!" is free 
 *  software: you can redistribute it and/or modify it under the terms 
 *  of the GNU General Public License as published by the Free Software 
 *  Foundation, either version 3 of the License, or (at your option) 
 *  any later version.
 * 
 *  "Sauver l'univers du tentacule mauve en jouant au Boggle!" is distributed 
 *  in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even 
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with "Sauver l'univers du tentacule mauve en jouant au Boggle!". 
 *  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.qc.bdeb.guillaumepoiriermorency.tp1.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;

/**
 *
 * @author Guillaume Poirier-Morency
 */
public final class Highscores extends HashMap<String, Integer> {

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     */
    public void serialize() {
        try {
            FileOutputStream fos = new FileOutputStream("highscores.serial");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(this);
                oos.flush();
            } finally {
                //fermeture des flux
                try {
                    oos.close();
                } finally {
                    fos.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Ajoute un score si le nom n'est pas présent et remplace un score si 
     * l'individu l'a battu.
     * @param name est le nom de l'individu et doit être unique.
     * @param score est le score de l'individu.
     */
    public void addScore(String name, int score) {
        if (!containsKey(name)) {
            put(name, score);
        } else if (get(name) < score) {
            put(name, score);
        }
    }

    /**
     * Ce code a ete adapte du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     * L'objet Interface doit etre en mesure de se reconstruire a partir d'un
     * objet de configuration.
     * @return l'objet désérialisé. 
     */
    public static Highscores unSerialize() {
        Object p = new Highscores();
        try {
            FileInputStream fis = new FileInputStream("highscores.serial");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                p = ois.readObject();
            } finally {
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return (Highscores) p;
    }
}
