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

import ca.qc.bdeb.guillaumepoiriermorency.tp1.graphical.Interface;

import ca.qc.bdeb.guillaumepoiriermorency.tp1.exception.IllegalGridSizeException;
import java.util.Random;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Le fichier Root contient tout ce qui trait aux methodes de calcul utilises
 * dans ce logiciel. Il permet d'alléger le code interface.
 * @author Guillaume Poirier-Morency
 */
public final class Root {

    /**
     * Cet algorithme génère une lettre de l'alphabet français aléatoire en 
     * fonction des statistique d'utilisation dans la langue courante.
     * @return une lettre de l'alphabet.
     */
    public static String getStatisticallyRandomizedAlphabetCharacter() {
        Random rnd = new Random();
        float i = rnd.nextFloat() * 100.0f;
        if (i >= 0.0 && i < 8.1) {
            return "A";
        } else if (i >= 8.1 && i < 9.0) {
            return "B";
        } else if (i >= 9.0 && i < 11.4) {
            return "C";
        } else if (i >= 11.4 && i < 15.1) {
            return "D";
        } else if (i >= 15.1 && i < 32.2) {
            return "E";
        } else if (i >= 32.2 && i < 33.3) {
            return "F";
        } else if (i >= 33.3 && i < 34.2) {
            return "G";
        } else if (i >= 34.2 && i < 34.9) {
            return "H";
        } else if (i >= 34.9 && i < 42.7) {
            return "I";
        } else if (i >= 42.7 && i < 43.2) {
            return "J";
        } else if (i >= 43.2 && i < 43.3) {
            return "K";
        } else if (i >= 43.3 && i < 48.8) {
            return "L";
        } else if (i >= 48.8 && i < 51.8) {
            return "M";
        } else if (i >= 51.8 && i < 58.8) {
            return "N";
        } else if (i >= 58.8 && i < 62.9) {
            return "O";
        } else if (i >= 62.9 && i < 68.2) {
            return "P";
        } else if (i >= 68.2 && i < 69.6) {
            return "QU";
        } else if (i >= 69.6 && i < 76.2) {
            return "R";
        } else if (i >= 76.2 && i < 84.0) {
            return "S";
        } else if (i >= 84.0 && i < 91.2) {
            return "T";
        } else if (i >= 91.2 && i < 97.5) {
            return "U";
        } else if (i >= 97.5 && i < 99.1) {
            return "V";
        } else if (i >= 99.1 && i < 99.2) {
            return "W";
        } else if (i >= 99.2 && i < 99.6) {
            return "X";
        } else if (i >= 99.6 && i < 99.9) {
            return "Y";
        } else if (i >= 99.9 && i < 100.0) {
            return "Z";
        } else {
            return null;
        }
    }

    /**
     * Méthode pour déterminer si un mot trouvé est valide.
     * @param dict dictionnaire à utiliser pour véridier les réponses.
     * @param s contient le mot à vérifier dans le dictionnaire.
     * @param dlm est la liste des mots trouvés.
     * @return true si le mot est trouve, false autrement.
     */
    public static int checkAnAnswer(Dictionary dict, String s, DefaultListModel dlm) {      
        if (dlm.contains(s+" " + getWordPointsValue(s) + " points") || dlm.contains("<HTML><s>" + s + "</s></HTML>")) {
            JOptionPane.showMessageDialog(null, "Le mot a déjà été trouvé!","Mot déjà trouvé", JOptionPane.WARNING_MESSAGE);
            return 0;
        }

        if (s.length() >= 3) {
            if (dict.contains(s)) {
                return getWordPointsValue(s);
            } else {
                if (JOptionPane.showOptionDialog(null, "Le mot n'existe pas, voulez-vous l'ajouter?",
                        "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
                    dict.addWord(s);
                    return getWordPointsValue(s);
                } else {
                    // On ajoute le mot ici, mais invalidé! Il sera trouvé dans la liste mais pas bon :(
                    dlm.addElement("<HTML><s>" + s + "</s></HTML>");
                    return 0;
                }
            }
        } else {
            return 0;
        }
    }

    /**
     * Retourne la valeur en points d'un mot sans considérer si il est bon.
     * @param currentLine est le mot en question.
     * @return le nombre de points accordés.
     */
    private static int getWordPointsValue(String currentLine) {
        if (currentLine.length() == 1 || currentLine.length() == 2) {
            return 0;
        } else if (currentLine.length() == 3 || currentLine.length() == 4) {
            return 1;
        } else if (currentLine.length() == 5) {
            return 2;
        } else if (currentLine.length() == 6) {
            return 3;
        } else if (currentLine.length() == 7) {
            return 5;
        } else if (currentLine.length() >= 8) {
            return 11;
        } else {
            return 0;
        }
    }

    /**
     * Vérifie si un cube peut être cliqué. Ce code ne peut que gérer une interface
     * en 4x4, autrement il lance un IllegalGridSizeException. L'implémentation des
     * grilles nxm est prévu.
     * @param i est l'interface ou les cubes sont situés.
     * @param clickedCube est la position du cube cliqué.
     * @return true si le cube peut être cliqué, false autrement.
     */
    public static boolean checkIfCubeCanBeClicked(Interface i, int clickedCube) {
        System.out.println(i.lastClickedCubeNumber);
        if (i.HEIGH != 4 || i.LENGTH != 4) {
            throw new IllegalGridSizeException();
        }
        // Si un cube est non cliquable, une verification complete est
        // necessaire...
        if (i.lastClickedCubeNumber == -1) {
            return true;
        }
        switch (clickedCube) {
            // Pour ces cas, au moins un bouton cubeNumber -5 -4 -3 -1 +1 +3 +4 +5 doivent etre enabled
            case 5:
            case 6:
            case 9:
            case 10:
                if (i.lastClickedCubeNumber == clickedCube - 5
                        || i.lastClickedCubeNumber == clickedCube - 4
                        || i.lastClickedCubeNumber == clickedCube - 3
                        || i.lastClickedCubeNumber == clickedCube - 1
                        || i.lastClickedCubeNumber == clickedCube + 1
                        || i.lastClickedCubeNumber == clickedCube + 3
                        || i.lastClickedCubeNumber == clickedCube + 4
                        || i.lastClickedCubeNumber == clickedCube + 5) {
                    return true;
                }
                break;
            // -1 +1 +3 +4 +5
            case 1:
            case 2:
                if (i.lastClickedCubeNumber == clickedCube - 1
                        || i.lastClickedCubeNumber == clickedCube + 1
                        || i.lastClickedCubeNumber == clickedCube + 3
                        || i.lastClickedCubeNumber == clickedCube + 4
                        || i.lastClickedCubeNumber == clickedCube + 5) {
                    return true;
                }
                break;
            // -4 -3 +1 +4 +5
            case 4:
            case 8:
                if (i.lastClickedCubeNumber == clickedCube - 4
                        || i.lastClickedCubeNumber == clickedCube - 3
                        || i.lastClickedCubeNumber == clickedCube + 1
                        || i.lastClickedCubeNumber == clickedCube + 4
                        || i.lastClickedCubeNumber == clickedCube + 5) {
                    return true;
                }
                break;
            // -5 -4 -1 +3 +4
            case 7:
            case 11:
                if (i.lastClickedCubeNumber == clickedCube - 5
                        || i.lastClickedCubeNumber == clickedCube - 4
                        || i.lastClickedCubeNumber == clickedCube - 1
                        || i.lastClickedCubeNumber == clickedCube + 3
                        || i.lastClickedCubeNumber == clickedCube + 4) {
                    return true;
                }
                break;

            // -5 -4 -3  -1 +1
            case 13:
            case 14:
                if (i.lastClickedCubeNumber == clickedCube - 5
                        || i.lastClickedCubeNumber == clickedCube - 4
                        || i.lastClickedCubeNumber == clickedCube - 3
                        || i.lastClickedCubeNumber == clickedCube - 1
                        || i.lastClickedCubeNumber == clickedCube + 1) {
                    return true;
                }

                break;
            // 1 4 5
            case 0:
                if (i.lastClickedCubeNumber == 1
                        || i.lastClickedCubeNumber == 4
                        || i.lastClickedCubeNumber == 5) {
                    return true;
                }
                break;
            // 2 6 7
            case 3:
                if (i.lastClickedCubeNumber == 2
                        || i.lastClickedCubeNumber == 6
                        || i.lastClickedCubeNumber == 7) {
                    return true;
                }
                break;
            // 8 9 13
            case 12:
                if (i.lastClickedCubeNumber == 8
                        || i.lastClickedCubeNumber == 9
                        || i.lastClickedCubeNumber == 13) {
                    return true;
                }
                break;
            // 10 11 14
            case 15:
                if (i.lastClickedCubeNumber == 10
                        || i.lastClickedCubeNumber == 11
                        || i.lastClickedCubeNumber == 14) {
                    return true;
                }
                break;
        }
        return false;
    }
}
