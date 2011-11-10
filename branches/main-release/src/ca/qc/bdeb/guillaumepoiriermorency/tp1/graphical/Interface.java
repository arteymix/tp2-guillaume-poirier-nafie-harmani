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

import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.Dictionary;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.SaveFile;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.Highscores;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.Root;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.core.TimerMethods;
import ca.qc.bdeb.guillaumepoiriermorency.tp1.translation.Translateable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 * Ceci est le fichier principal du programme de jeu de Boogle : l'éternel 
 * combat contre le tentacule mauve. Le programme est codé en anglais, mais tous
 * les commentaires  et l'interface est construit en bon français! 
 * @author Guillaume Poirier-Morency
 */
public final class Interface extends JFrame implements Translateable {

    /**
     * @param args contient les arguments en ligne de commande. 
     * Il peut prendre les valeurs suivantes :
     * --highscores affiche les meilleurs scores à ce jour ;
     * --rules affiche les règlements de Boggle ;
     * --license affiche les informations sur la license de ce logiciel.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
        } else if (args[0].equals("--highscores")) {
            // Ne montrer que les meilleurs scores et quitter
            Highscores hs = Highscores.unSerialize();
            String messageToRender = "";
            for (Entry e : Highscores.unSerialize().entrySet()) {
                messageToRender += e.getKey() + " : " + e.getValue() + "\n";
            }
            JOptionPane.showMessageDialog(null, (messageToRender.equals("") ? "Aucuns scores trouvés!" : ""),
                    messageToRender, (messageToRender.equals("") ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE));
            System.exit(0);
        } else if (args[0].equals("--license")) {
            JOptionPane.showMessageDialog(null, "Sauver [...] est un logiciel libre"
                    + "\n\nCe logiciel distribué sous license GPLv3"
                    + "\nVous trouverez plus amples informations"
                    + "\nsur <http://www.gnu.org/licenses/> ainsi"
                    + "\nqu'une copie numérique de la GPLv3dans "
                    + "\nles sources de ce logiciel."
                    + "\n\nCréé par Guillaume Poirier-Morency."
                    + "\nRemis le : ", "À propos", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (args[0].equals("--rules")) {
            JOptionPane.showMessageDialog(null, "Les règles du jeu sont simples :"
                    + "\n\n    Formez des mots de 3 lettres ou plus à l'aide"
                    + "\n    des cubes du jeu. Les lettres doivent être "
                    + "\n    adjacente et utilisées une seule fois. Un mot ne"
                    + "\n    peut pas être formé plus d'une fois et plus long ils"
                    + "\n    seront, plus de points vous ammasserez."
                    + "\n\n    3 ou 4 lettres : 1 point"
                    + "\n    5 lettres : 2 points"
                    + "\n    6 lettres : 3 points"
                    + "\n    7 lettres : 5 points"
                    + "\n    8 lettres et plus : 11 points", "Règles du jeu",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Please check your argument spelling :"
                    + "\n--highscores shows highscores then quit ;"
                    + "\n--license shows license details then quit ;"
                    + "\n--rules shows rules then quit.", "À propos", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            if (JOptionPane.showOptionDialog(null, "Il existe des problèmes avec l'implémentation"
                    + "\nde GTK dans java, voulez-vous utiliser un autre look?",
                    "Problème de compatibilité de l'interface", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println("Le look dégeulasse de Swing sera utilisé.");
            }
        }
        new Interface(4, 4);
    }
    //<editor-fold defaultstate="collapsed" desc="Variables">      
    /**
     * Couleur de l'interface.
     */
    public Color colorInterface;
    /**
     * Dimension en y de la grille de Boggle.
     */
    /**
     * Dimension en x de la grille de Boggle
     */
    public final int HEIGH, LENGTH; // TODO Jeu de test sur ces variables
    /**
     * Bouton de vérification.
     */
    private JButton btnVerify = new JButton("Vérifier");
    /**
     * Timer ppur le temps restant.
     */
    private Timer timerRemainingTime;
    /**
     * Référence de la table de Boggle en utilisation.
     */
    public BoggleGrid pnlBoggleGrid;
    private JScrollPane jspFoundWords = new JScrollPane();
    private DefaultListModel dlmFoundWords = new DefaultListModel();
    /**
     * Liste des mots trouvés.
     */
    public JList listFoundWords = new JList(dlmFoundWords);
    /**
     * Panel EAST contenant la liste de mots trouvé, le buffer de réponse et le temps restant.
     */
    /**
     * Panel contenant le pointage.
     */
    /**
     * Panel contenant les boutons de vérification et d'annulation.
     */
    private JPanel pnlEastBorder = new JPanel(new BorderLayout()),
            pnlStatusBar = new JPanel(),
            pnlNorthBorder = new JPanel();
    /**
     * Position du dernier cube cliqué.
     */
    public int lastClickedCubeNumber = -1; // TODO Jeu de test sur cette variable
    /**
     * Minuteur pour le temps restant.
     */
    /**
     * Label pour afficher les points.
     */
    public JLabel lblTimer = new JLabel("3 minutes 0 secondes"), // TODO Jeu de test sur ces variables
            lblPoints = new JLabel("0");
    /**
     * Buffer pour les réponses.
     */
    private JLabel lblAnswerBuffer = new JLabel(""); // TODO Jeu de test sur cette variable
    private JMenuBar menuBarMainMenu = new JMenuBar();
    private JMenu menuFile = new JMenu("Fichier"),
            menuHelp = new JMenu("Aide");
    /**
     * Composantes des menus
     */
    private JMenuItem menuItemSave = new JMenuItem("Sauvegarder"),
            menuItemQuit = new JMenuItem("Quitter"),
            menuItemNewGame = new JMenuItem("Nouvelle partie..."),
            menuItemRules = new JMenuItem("Règlements..."),
            menuItemAbout = new JMenuItem("À propos de..."),
            menuItemLoad = new JMenuItem("Charger la derniere partie..."),
            menuItemHighscores = new JMenuItem("Meilleurs scores..."),
            menuItemParameters = new JMenuItem("Paramètres..."),
            menuItemPause = new JMenuItem("Pause...");
    /**
     * Bouton pour annuler la sélection.
     */
    private JButton btnCancel;
    /**
     * Variable contenant la version du jeu.
     */
    private final double VERSION = 1.0;
    /**
     * JFrame de paramètres pour changer la couleur et la langue.
     */
    private Parameters parameters = new Parameters(this);
    /**
     * Dictionnaire de mots.
     */
    private Dictionary dictionnary = new Dictionary();
    /**
     * Highscores, pour les meilleurs!
     */
    private Highscores highscores = Highscores.unSerialize();
    //</editor-fold>

    /**
     * Permet de transférer l'interface en référence pour les ActionListener.
     * @return l'interface actuel.
     */
    private Interface getInterface() {
        return this;
    }

    /**
     * Instancie un interface de Boggle.
     * @param heigh est la hauteur de la grille.
     * @param length  est la longeur de la grille.
     */
    private Interface(int heigh, int length) {
        super();
        HEIGH = heigh;
        LENGTH = length;
        pnlBoggleGrid = new BoggleGrid(HEIGH, LENGTH, lblAnswerBuffer, this);
        generalInterfaceCalls();
    }

    /**
     * Génère une partie à partir d'un fichier de configuration.
     * @param sf est le fichier de configuration en question.
     */
    private Interface(SaveFile sf) {
        super();
        HEIGH = sf.HEIGH;
        LENGTH = sf.LENGTH;
        lblTimer = new JLabel(sf.REMAINING_TIME);
        listFoundWords = sf.FOUND_WORD_LIST;
        lblPoints.setText(sf.POINTS);
        pnlBoggleGrid = sf.GRID;
        lastClickedCubeNumber = sf.LAST_CLICKED_CUBE_NUMBER;
        generalInterfaceCalls();
        // On rajoute la référence du JFrame dans les composantes
        for (Component c : sf.GRID.getComponents()) {
            ((Cube) c).setAnswerBuffer(this, lblAnswerBuffer);
        }
        setGameColor(sf.COLOR);
    }

    /**
     * Détruit tout forme de sélection dans la grille.
     */
    private void cleanGrid() {
        lblAnswerBuffer.setText("");
        lastClickedCubeNumber = -1;
        for (int i = 0; i < pnlBoggleGrid.HEIGH * pnlBoggleGrid.LENGTH; i++) {
            pnlBoggleGrid.getComponent(i).setEnabled(true);
        }
    }

    /**
     * Regroupe les appels généraux pour créer l'interface.
     */
    private void generalInterfaceCalls() {

        //<editor-fold defaultstate="collapsed" desc="Le AnswerBuffer est défini ici.">
        lblAnswerBuffer.setHorizontalAlignment(JLabel.CENTER);
        lblAnswerBuffer.setPreferredSize(new Dimension(200, 25));
        //</editor-fold>        

        //<editor-fold defaultstate="collapsed" desc="Le panel EAST du JFrame est défini ici.">
        jspFoundWords.add(listFoundWords);
        pnlEastBorder.add(jspFoundWords, BorderLayout.NORTH);
        pnlEastBorder.add(lblTimer, BorderLayout.CENTER);
        pnlEastBorder.add(lblAnswerBuffer, BorderLayout.SOUTH);
        //</editor-fold>
        /* On rajoute la table de Boggle à l'interface. Pour simplifier son 
         * accès, on lui définit une référence locale. 
         */
        //<editor-fold defaultstate="collapsed" desc="Les composants du JFrame sont ajoutés ici">
        add(pnlBoggleGrid, BorderLayout.CENTER);
        // La liste de mots trouvés est générée ici
        lblTimer.setHorizontalAlignment(JLabel.CENTER);
        add(pnlEastBorder, BorderLayout.EAST);
        pnlNorthBorder.add(btnVerify);
        btnCancel = new JButton("Annuler la sélection");
        pnlNorthBorder.add(btnCancel);
        // Le bouton de vérification est ajouté ici
        add(pnlNorthBorder, BorderLayout.NORTH);
        pack();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="L'Action Listener du bouton de vérification est défini ici">
        btnVerify.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                /* On envoit à la classe spécialisée en vérification de réponse
                 * la réponse de l'utilisateur et cette dernière devrait être
                 * en mesure de la vérifier à partir d'un dictionnaire.
                 */
                int i;
                if ((i = Root.checkAnAnswer(dictionnary, lblAnswerBuffer.getText(), dlmFoundWords)) > 0) {
                    dlmFoundWords.add(listFoundWords.getModel().getSize(), lblAnswerBuffer.getText() + " " + i + " points");
                    lblPoints.setText("" + (Integer.parseInt(lblPoints.getText()) + i));
                }
                cleanGrid();
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Le bouton Annuler est defini ici">
        btnCancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cleanGrid();
            }
        });
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="La barre de status est définie ici">
        pnlStatusBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        pnlStatusBar.add(new JLabel("Points"));
        pnlStatusBar.add(lblPoints);
        add(pnlStatusBar, BorderLayout.SOUTH);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="La barre de menus est définie ici">
        menuItemSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                SaveFile i = new SaveFile(getInterface());
                // On mets le Timer en pause pendant la sauvegarde...
                timerRemainingTime.stop();
                Thread threadMessageWhileSaving = new Thread() {

                    @Override
                    public void run() {
                        setVisible(false);
                        JOptionPane.showMessageDialog(null, "Sauvegarde en cours...",
                                "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(true);
                        timerRemainingTime.start();
                    }
                };
                threadMessageWhileSaving.start();
                i.serialize("save.serial");

            }
        });
        menuItemPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timerRemainingTime.stop();
                setVisible(false);
                JOptionPane.showMessageDialog(null, "Le jeu est en pause...", "Pause", JOptionPane.INFORMATION_MESSAGE);
                setVisible(true);
                timerRemainingTime.start();
            }
        });

        menuItemRules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                timerRemainingTime.stop();
                JOptionPane.showMessageDialog(null, "Les règles du jeu sont simples :"
                        + "\n\n    Formez des mots de 3 lettres ou plus à l'aide"
                        + "\n    des cubes du jeu. Les lettres doivent être "
                        + "\n    adjacente et utilisées une seule fois. Un mot ne"
                        + "\n    peut pas être formé plus d'une fois et plus long ils"
                        + "\n    seront, plus de points vous ammasserez."
                        + "\n\n    3 ou 4 lettres : 1 point"
                        + "\n    5 lettres : 2 points"
                        + "\n    6 lettres : 3 points"
                        + "\n    7 lettres : 5 points"
                        + "\n    8 lettres et plus : 11 points", "Règles du jeu",
                        JOptionPane.INFORMATION_MESSAGE);
                setVisible(true);
                timerRemainingTime.start();
            }
        });
        menuItemLoad.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    generalCallsWhenNewGameIsAsked(4, 4, SaveFile.unSerialize("save.serial"));
                    timerRemainingTime.stop();
                } catch (Exception e) {
                    timerRemainingTime.stop();
                    setVisible(false);
                    JOptionPane.showMessageDialog(null, "La dernière partie sauvegardé n'a pas pu être trouvée!",
                            "Problème de sauvegarde", JOptionPane.ERROR_MESSAGE);
                    setVisible(true);
                    timerRemainingTime.start();
                }
            }
        });
        menuItemQuit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                gameEnded();
            }
        });
        menuItemNewGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                /* TODO Rendre invisible les fenetres inactives peut nuire a la
                 * gestion de la memoire. On devrait detruire les ancienne
                 * fenetres. En reinstanciant une nouvelle fenetre, on lui
                 * accorde les privileges de EXIT_ON_CLOSE tandis que la fenetre
                 * courrante sera reduite a HIDE_ON_CLOSE.
                 */
                timerRemainingTime.stop();
                if (JOptionPane.showOptionDialog(null, "Voulez-vous vraiment recommencer une nouvelle partie?",
                        "Sauvegarder la partie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, null, null) == 1) {
                    // On fait pas grand chose mais la structure est présente au besoin.
                    timerRemainingTime.start();
                } else {
                    // Le timer reste stoppé!!
                    generalCallsWhenNewGameIsAsked(4, 4, null);
                }
            }
        });
        menuItemHighscores.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {                               
                setVisible(false);
                timerRemainingTime.stop();
                String messageToRender = "";               
                for (Entry e : Highscores.unSerialize().entrySet()) {
                    messageToRender += e.getKey() + " : " + e.getValue() + "\n";
                }
                JOptionPane.showMessageDialog(null, (messageToRender == "" ? "Aucuns scores trouvés!" : messageToRender),"Meilleurs scores" , (messageToRender.equals("") ? JOptionPane.WARNING_MESSAGE : JOptionPane.INFORMATION_MESSAGE));
                setVisible(true);
                timerRemainingTime.start();
            }
        });
        menuItemAbout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                timerRemainingTime.stop();
                JOptionPane.showMessageDialog(null, "Sauver [...] est un logiciel libre"
                        + "\n\nCe logiciel distribué sous license GPLv3"
                        + "\nVous trouverez plus amples informations"
                        + "\nsur <http://www.gnu.org/licenses/> ainsi"
                        + "\nqu'une copie numérique de la GPLv3dans "
                        + "\nles sources de ce logiciel."
                        + "\n\nCréé par Guillaume Poirier-Morency."
                        + "\nRemis le : ", "À propos", JOptionPane.INFORMATION_MESSAGE);
                setVisible(true);
                timerRemainingTime.start();
            }
        });

        menuItemParameters.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO Jeu indisonible quand l'utilisateur chance ses pref

                parameters.setVisible(true);
            }
        });
        menuFile.add(menuItemNewGame);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemLoad);
        menuFile.addSeparator();
        menuFile.add(menuItemPause);
        menuFile.add(menuItemHighscores);
        menuFile.add(menuItemParameters);
        menuFile.addSeparator();
        menuFile.add(menuItemQuit);
        menuHelp.add(menuItemRules);
        menuHelp.add(menuItemAbout);
        menuBarMainMenu.add(menuFile);
        menuBarMainMenu.add(menuHelp);
        setJMenuBar(menuBarMainMenu);
        //</editor-fold>  

        //<editor-fold defaultstate="collapsed" desc="Les dimensions sont définies ici avec un .pack()">
        setPreferredSize(new Dimension(710, 600));
        listFoundWords.setSize(new Dimension(200, 425));
        jspFoundWords.setPreferredSize(new Dimension(200, 425));
        pnlEastBorder.setPreferredSize(new Dimension(200, 500));
        lblTimer.setPreferredSize(new Dimension(200, 25));
        pack();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Les propriétés du JFrame sont définies ici">
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sauver l'univers du tentacule mauve en jouant au Boggle! " + VERSION);
        //</editor-fold>

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

        //<editor-fold defaultstate="collapsed" desc="Le timer est défini ici">
        timerRemainingTime = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int toFormat = TimerMethods.convertTimeToInteger(lblTimer.getText());
                if (toFormat > 0) {
                    lblTimer.setText(TimerMethods.convertIntegerToTime(toFormat - 1));
                } else {
                    gameEnded();
                }
            }
        });
        timerRemainingTime.start();
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Le window manager est défini ici.">
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                gameEnded();
            }
        });
        //</editor-fold>

        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Méthode appelée lorsque le jeu doit être éteint.
     */
    private void gameEnded() {
        timerRemainingTime.stop();
        String name = null;
        if (Integer.parseInt(this.lblPoints.getText()) > 0) {
            name = JOptionPane.showInputDialog(null, "La partie est finie! Vous avez fait "
                    + this.lblPoints.getText() + " points!"
                    + "\nVeuillez entrer votre nom :", "Partie terminée",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (name != null) {
            highscores.addScore(name, Integer.parseInt(this.lblPoints.getText()));
            highscores.serialize();
        }
        if (dictionnary.isNewWords() && JOptionPane.showOptionDialog(null,
                "Voulez-vous sauvegarder le dictionnaire?",
                "Sauvegarder le dictionnaire", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
            // De nouveaux mots ont été ajouté, on propose une sauvegarde...
            dictionnary.save();
        }
        if (Integer.parseInt(this.lblPoints.getText()) > 0) {
            Highscores hs = Highscores.unSerialize();
            hs.addScore(name, Integer.parseInt(this.lblPoints.getText()));
            hs.serialize();
        }
        if (JOptionPane.showOptionDialog(null, "Voulez-vous rejouer?",
                "Rejouer", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null) == 0) {
            generalCallsWhenNewGameIsAsked(4, 4, null);
        } else {
            System.exit(0);
        }
    }

    /**
     * Change la couleur de l'interface.
     * @param color est la couleur voulue.
     */
    void setGameColor(Color color) {
        colorInterface = color;
        pnlEastBorder.setBackground(color);
        pnlStatusBar.setBackground(color);
        pnlNorthBorder.setBackground(color);
        pnlBoggleGrid.setBackground(color);
        btnVerify.setBackground(color);
        btnCancel.setBackground(color);
    }

    /**
     * Appels de méthodes lorsqu'une nouvelle partie doit être générée.
     * @param length est la longeur de la nouvelle grille.
     * @param heigh est la hauteur de la nouvell grille.
     * @param s est le fichier de sauvegarde au besoin, mettre null si aucuns.
     */
    private void generalCallsWhenNewGameIsAsked(int length, int heigh, SaveFile s) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(false);
        // On nettoye les objets en mémoire...
        colorInterface = null;
        btnVerify = null;
        timerRemainingTime = null;
        pnlBoggleGrid = null;
        dlmFoundWords = null;
        listFoundWords = null;
        pnlEastBorder = null;
        pnlStatusBar = null;
        pnlNorthBorder = null;
        lblTimer = null;
        lblPoints = null;
        lblAnswerBuffer = null;
        menuBarMainMenu = null;
        menuFile = null;
        menuHelp = null;
        menuItemSave = null;
        menuItemQuit = null;
        menuItemNewGame = null;
        menuItemRules = null;
        menuItemAbout = null;
        menuItemLoad = null;
        menuItemHighscores = null;
        menuItemParameters = null;
        menuItemPause = null;
        btnCancel = null;
        parameters = null;
        dictionnary = null;
        highscores = null;
        // On appelle le garebage collector, anyway c'est en train de loader!
// TODO Fixer les exceptions de NullPointer.. ou on s'en fout, l'objet est inutile...
        new Thread() {

            @Override
            public void run() {
                System.gc();
            }
        }.start();
        if (s == null) {
            new Interface(length, heigh);
        } else {
            new Interface(s);
        }
    }

    @Override
    public String[] getLocalStringToTranslate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Translateable[] getTranslateableObjects() {
        Translateable[] t = {pnlBoggleGrid, parameters};
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void translate(String[] s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
