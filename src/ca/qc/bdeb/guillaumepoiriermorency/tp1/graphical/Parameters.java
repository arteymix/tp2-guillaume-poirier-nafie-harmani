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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Objet pour la fenêtre de paramètres.
 * @author Guillaume Poirier-Morency
 */
public class Parameters extends JFrame implements Translateable {

    /**
     * Boîte déroulante pour les couleurs.
     */
    private JComboBox jcbBackgroundColor = new JComboBox();
    /**
     * Boîte déroulante pour les langues.
     */
    private JComboBox jcbLanguage = new JComboBox();

    /**
     * Constructeur pour le JFrame de paramètres.
     * @param i est l'interface à paramétrer.
     */
    public Parameters(final Interface i) {

        setLayout(new GridLayout(10, 2));
        add(new JLabel("Couleur d'arrière plan"));
        for (Colors c : Colors.values()) {
            this.jcbBackgroundColor.addItem(c);
        }
        jcbBackgroundColor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                i.setGameColor(((Colors) jcbBackgroundColor.getSelectedItem()).COLOR);
            }
        });
        add(jcbBackgroundColor);
        add(new JLabel("Langue"));        
         jcbLanguage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // On update les traductions icitte!
                
            }
        });
        add(jcbLanguage);

        //<editor-fold defaultstate="collapsed" desc="Ce code à été pris sur le site : http://www.java-forums.org/awt-swing/3491-jframe-center-screen.html">
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Determine the new location of the window
        int w = getSize().width;
        int h = getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        // Move the window
        setLocation(x, y);
        //</editor-fold>
        pack();
    }

    @Override
    public void translate(String[] s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String[] getLocalStringToTranslate() {
        String[] s = {};
        return s;        
    }

    @Override
    public Translateable[] getTranslateableObjects() {
        throw new UnsupportedOperationException("Not supported yet.");
    }   

    /**
     * Enum pour les couleurs disponible pour agrémenter l'interface utilisateur.
     */
    public enum Colors {

        /**
         * Aucune couleur.
         */
        DEFAULT(null, "Par défaut"),
        /**
         * Cyan.
         */
        CYAN(Color.CYAN, "Cyan"),
        /**
         * Magenta.
         */
        MAGENTA(Color.MAGENTA, "Magenta"),
        /**
         * Bleu.
         */
        BLUE(Color.BLUE, "Bleu"),       
        /**
         * Jaune.
         */
        YELLOW(Color.YELLOW, "Jaune"),
        /**
         * Rouge.
         */
        RED(Color.RED, "Rouge");
        private final String NAME;
        /**
         * Couleur associé à l'objet.
         */
        public final Color COLOR;

        Colors(Color c, String s) {
            NAME = s;
            COLOR = c;
        }

        /**
         * Retourne la valeur en String de l'objet.
         * @return la valeur en String de l'objet.
         */
        @Override
        public String toString() {
            return this.NAME;
        }
    }
}
