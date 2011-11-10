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

import java.awt.Dimension;
import java.awt.GridLayout;

import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * La table de jeu définit l'espace sur lequel les dés seront placés.
 * La classe est publique, mais sont constructeur package parce que la
 * classe ConfigFile a besoin de savoir que BoggleTable existe sans avoir à
 * l'instancier.
 * @author Guillaume Poirier-Morency
 */
public final class BoggleTable extends JPanel implements Serializable {

    /**
     * La hauteur de la grille.
     */
    final int HEIGH;
    /**
     * La longeur de la grille.
     */
    final int LENGTH;

    /**
     * Constructeur pour la grille de Boggle.
     * @param heigh est la hauteur de la grille.
     * @param length est la longeur de la grille.
     * @param answerBuffer est le JLabel pour envoyer la lettre du cube.
     * @param jf est l'interface dans lequel la grille se situe.
     */
    BoggleTable(int heigh, int length, JLabel answerBuffer, Interface jf) {
        super();
        // Les dimensions de la table de jeu sont définies ici
        this.HEIGH = heigh;
        this.LENGTH = length;

        this.setLayout(new GridLayout(heigh, length));
        // La table de jeu est remplie ici
        for (int i = 0; i < this.HEIGH * this.LENGTH; i++) {
            this.add(new Cube(answerBuffer, jf, i));
        }
        this.setPreferredSize(new Dimension(500, 500));
    }    
}
