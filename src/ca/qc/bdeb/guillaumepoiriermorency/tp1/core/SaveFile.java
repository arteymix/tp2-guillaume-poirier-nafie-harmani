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

import ca.qc.bdeb.guillaumepoiriermorency.tp1.graphical.BoggleGrid;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.graphical.Interface;

import java.awt.Color;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JList;

/**
 * Cette classe permet la sérialisation d'une sauvegarde.
 * @author Guillaume Poirier-Morency
 */
public final class SaveFile implements Serializable {

    /**
     * 
     */
    public Color COLOR;
    /**
     * La hauteur de la grille.
     */
    /**
     * La longeur de la grille.
     */
    public final int HEIGH, LENGTH;
    /**
     * Le temps restant.
     */
    public final String REMAINING_TIME, POINTS;
    /**
     * La grille en soit.
     */
    public final BoggleGrid GRID;
    /**
     * 
     */
    public final JList FOUND_WORD_LIST;
    /**
     * 
     */
    public final int LAST_CLICKED_CUBE_NUMBER;

    /**
     * Le constructeur pour sérialiser une grille de Boggle.
     * @param i est l'interface à sérialiser,
     */
    public SaveFile(Interface i) {
        LAST_CLICKED_CUBE_NUMBER = i.lastClickedCubeNumber;
        COLOR = i.colorInterface;
        HEIGH = i.HEIGH;
        LENGTH = i.LENGTH;
        GRID = i.pnlBoggleGrid;
        REMAINING_TIME = i.lblTimer.getText();
        FOUND_WORD_LIST = i.listFoundWords;
        POINTS = i.lblPoints.getText();
        serialize("save.serial");
    }

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     * @param filename est le nom du fichier de serialisation.
     */
    public void serialize(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(this);
                oos.flush();
            }
            finally {
                //fermeture des flux
                try {
                    oos.close();
                }
                finally {
                    fos.close();
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Ce code a été adapté du site web : http://ydisanto.developpez.com/tutoriels/java/serialisation-binaire/
     * L'objet Interface doit être en mesure de se reconstruire à partir d'un
     * objet de configuration.
     * @param filename est le nom du fichier à désérialiser.
     * @return l'objet désérialisé.
     * @throws IOException est lancée lorsque le fichier d'objet ne peut être récupéré. 
     * @throws ClassNotFoundException est lancée lorsque l'objet trouvé ne correspond pas à ce qui est recherché. 
     */
    public static SaveFile unSerialize(String filename) throws IOException, ClassNotFoundException {
        Object p = null;

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            p = ois.readObject();
        }
        finally {
            try {
                ois.close();
            }
            finally {
                fis.close();
            }
        }
        return (SaveFile) p;
    }
}
