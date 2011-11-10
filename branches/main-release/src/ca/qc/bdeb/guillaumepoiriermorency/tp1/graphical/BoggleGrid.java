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
package ca.qc.bdeb.guillaumepoiriermorency.tp1.graphical;

import ca.qc.bdeb.guillaumepoiriermorency.tp1.translation.Translateable;

import java.awt.Dimension;
import java.awt.GridLayout;

import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La table de jeu définit l'espace sur lequel les dés seront placés.
 * La classe est publique, mais sont constructeur package parce que la
 * classe ConfigFile a besoin de savoir que BoggleGrid existe sans avoir à
 * l'instancier.
 * @author Guillaume Poirier-Morency
 */
public final class BoggleGrid extends JPanel implements Serializable, Translateable {

    /**
     * Hauteur de la grille en cubes.
     */
    final int HEIGH;
    /**
     * Largeur de la grille en cubes.
     */
    final int LENGTH;

    /**
     * Permet de construire une grille de boggle en spécifiant ses dimensions.
     * @param heigh est la hauteur de la grille en cubes.
     * @param length est la largeur de la grille en cubes.
     * @param answerBuffer est le JLabel servant à reçevoir les lettres.
     * @param jf est l'interface où la grille sera construite.
     */
    BoggleGrid(int heigh, int length, JLabel answerBuffer, Interface jf) {
        super();
        // Les dimentions de la table de jeu sont définies ici
        this.HEIGH = heigh;
        this.LENGTH = length;

        this.setLayout(new GridLayout(heigh, length));
        // La table de jeu est remplie ici
        for (int i = 0; i < this.HEIGH * this.LENGTH; i++) {
            this.add(new Cube(answerBuffer, jf, i));
        }
        this.setPreferredSize(new Dimension(500, 500));
    }

    /**
     * Permet de construire une grille de boggle en spécifiant ses dimensions.
     * @param heigh est la hauteur de la grille en cubes.
     * @param length est la longeur de la grille en cubes.
     */
    BoggleGrid(int heigh, int length) {
        super();
        // Les dimentions de la table de jeu sont définies ici
        HEIGH = heigh;
        LENGTH = length;
        setLayout(new GridLayout(heigh, length));
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    public void translate(String[] s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getLocalStringToTranslate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Translateable[] getTranslateableObjects() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

   
}
