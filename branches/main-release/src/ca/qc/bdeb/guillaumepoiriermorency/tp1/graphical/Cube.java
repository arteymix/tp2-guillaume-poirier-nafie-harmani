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

import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.Root;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Les cubes composent les lettres que l'utilisateur peut choisir.
 * @author Guillaume Poirier-Morency
 */
final class Cube extends JButton implements Serializable {

    private JLabel lblAnswerBuffer;
    private final int CUBE_NUMBER;
    

    /**
     * Redéfini le JLabel AnswerBuffer en cas de perte de référence (sérialisation).
     * @param i est la référence de l'interface qui contient la grille de Boggle.
     * @param answerBuffer est le nouveau JLabel pour answerBuffer.
     */
    public void setAnswerBuffer(final Interface i, JLabel answerBuffer) {
        lblAnswerBuffer = answerBuffer;
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Root.checkIfCubeCanBeClicked(i, CUBE_NUMBER)) {
                    lblAnswerBuffer.setText(lblAnswerBuffer.getText() + getText());
                    setEnabled(false);
                    i.lastClickedCubeNumber = CUBE_NUMBER;
                }
                i.pack();
            }
        });
    }

    /**
     * Constructeur d'un cube.
     * @param answerBuffer est la référence du JLabel ou sera ajouté la lettre contenue dans le cube.
     * @param i est l'interface qui contient le jeu de Boggle.
     * @param cubeNumber est la position du cube dans la grille.
     */
    Cube(JLabel answerBuffer, final Interface i, final int cubeNumber) {
        super(Root.getStatisticallyRandomizedAlphabetCharacter());
        Font f = new Font("Arial", Font.BOLD, 60);
        CUBE_NUMBER = cubeNumber;
        lblAnswerBuffer = answerBuffer;
        setFont(f);       
        setFocusable(false);
        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Root.checkIfCubeCanBeClicked(i, CUBE_NUMBER)) {
                    lblAnswerBuffer.setText(lblAnswerBuffer.getText() + getText());
                    setEnabled(false);
                    i.lastClickedCubeNumber = CUBE_NUMBER;
                }
                i.pack();
            }
        });
    }
}
