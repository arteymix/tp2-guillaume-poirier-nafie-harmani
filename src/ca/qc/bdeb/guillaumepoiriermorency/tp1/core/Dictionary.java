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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Objet pour construire un dictionnaire.
 * @author Guillaume Poirier-Morency
 */
public final class Dictionary extends ArrayList<String> {

    private ArrayList<String> newWords = new ArrayList<String>();

    /**
     * Constructeur pour le dictionnaire.
     */
    public Dictionary() {
        super();
        init();

    }

    /**
     * Ajoute un mot au dictionnaire.
     * @param s est le mot à ajouter.
     */
    public void addWord(String s) {

        newWords.add(s);

    }

    /**
     * Initialise le dictionnaire lors de sa création.
     */
    private void init() {
        String currentLine;
        try {
            BufferedReader stdin = new BufferedReader(new InputStreamReader(new FileInputStream("liste_francais.txt")));
            while ((currentLine = stdin.readLine()) != null) {
                this.add(currentLine.toUpperCase());
            }

        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Le dictionnaire de mots n'a\n"
                    + " pas été trouvé! Ce dictionnaire se trouve"
                    + "\nhabituellement dans le répertoire personnel"
                    + "\nde l'utilisateur et devrait être inclus\ndans les fichiers du jeu.",
                    "Pas de dictionnaire trouvé", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            System.exit(0);
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Probleme de i/o");
            ioe.printStackTrace();
        }
    }

    /**
     * Détermine si de nouveaux mots ont été ajoutés au dictionnaire.
     * @return true si c'est le cas, false autrement.
     */
    public boolean isNewWords() {
        return this.newWords.size() > 0;
    }

    /**
     * Sauvegarde le dictionnaire.
     */
    public void save() {
        try {
            BufferedWriter stdout = new BufferedWriter(new FileWriter("liste_francais.txt"));
            for (String s : newWords) {
                stdout.append(s);
            }
            stdout.flush();
            stdout.close();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null, "Problèmes lors de la sauvegarde du dictionnaire\nce dernier ne sera pas sauvegardé si vous\navez ajouté de nouveaux mots!", "Problème de sauvegarde", JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    }
}
