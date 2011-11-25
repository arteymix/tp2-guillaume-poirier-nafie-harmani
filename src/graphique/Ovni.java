package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Random;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Classe pour les ovnis.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Ovni extends Dessinable implements Collisionable, Serializable {

    /**
     * 
     */
    
    double x, y, vitesseX = 1.0;
    int id, vie;
    Image img;
    private static Random r = new Random();
    
    /*
     * 
     * 
     * 
     * 
     * 
     * LES CONSTANTES ONT ÉTÉ TRANSFÉRÉ DANS :
     * 
     * Main.gameValues.ovni.XXXXXXXXXXXXXXX
     * 
     * 
     * 
     * 
     */

    private Ovni(Vecteur v, int id) {

        /* On va pas faire ça compliqué. Il y a une méthode en dessous qui
         * s'appelle create ovni vas-y! 
         */
        if (Main.gameValues.ovni.isBoss) {
            isDessinable = false;
        }
        this.x = v.x;
        this.id = id;
        configurerOvni(id);

    }

    /**
     * 
     */
    public static void createOvni() {
        // Ici tu mets l'algo de génération aléatoire pour le id
        if ((new Random()).nextInt(Main.gameValues.ovni.PROBABILITE_APPARITION_OVNI) == 1) {
            Main.gameValues.composantesDessinables.add(new Ovni(new Vecteur(0, 300), 1));
        }
        /* Bon je te laisse coder l'algorithme de génération en fonction de tes
         * besoins. Le constructeur peut être private maintenant!
         * Comme ça on ne surcharge pas le main avec du code et...
         * TU TE CHARGE DES MISSILES ENNEMIS MUHAHA!
         * C'est pour ça que je te fais une méthode tirer()...
         * 
         */
    }

    /**
     * 
     * @param id est le id du missile à tirer. 
     */
    private void tirer(int id) {
        // Sa devrait être cool maintenant...
        Main.gameValues.composantesDessinables.add(new ProjectileEnnemi(new Vecteur(x,y),ProjectileEnnemi.MISSILE_0));
    }

    private void configurerOvni(int id) {

        switch (id) {
            case 1://img enemi et enemi or et vie
                if (r.nextInt(10000) == 1) {
                    img = Main.imageBank.ENEMIOR;
                    vie = 30;
                } else {
                    img = Main.imageBank.ENEMI;
                    vie = 10;
                }
                break;
            case 2:// img supersonic et supersonicor
                if (r.nextInt(10000) == 1) {
                    img = Main.imageBank.SUPERSONICOR;
                    vie = 200;
                    vitesseX = 3;
                } else {
                    img = Main.imageBank.SUPERSONIC;
                    vitesseX = 2;
                }
                break;
            case 3:// img boss 1
                img = Main.imageBank.BOSS1;
                vie = 1000;
                break;
            case 4:// img boss 2
                img = Main.imageBank.BOSS2;
                vie = 1500;
                break;
            case 5:// img boss 3
                img = Main.imageBank.BOSS3;
                vie = 2000;
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");
        }
    }

    /**
     * 
     */
    private void action() {
        switch (id) {
            case 1:// mouvement d'un enemi normal 
                x += 3 * vitesseX;
                break;
            case 2:// mouvement du supersonic
                x -= 3 * vitesseX;
                break;
            case 3:// mouvement du boss 1
                mouvtBoss12(15, 45);
                break;
            case 4:// mouvement du boss 2
                vitesseX = 3;
                mouvtBoss12(15, 745);
                break;
            case 5:// mouvement du boss 3
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");

        }
        if ((new Random()).nextInt(100) == 1) {
            tirer(0);
        }
    }

    private void mouvtBoss12(int ymin, int ymax) {
        final int XMIN = 75;
        final int XMAX = 800 - 75;
        final int YMIN = ymin;
        final int YMAX = ymax;
        if (x == XMIN) {
            x += 3 * vitesseX;
        } else if (x == XMAX) {
            x -= 3 * vitesseX;
        }
        if (y == YMIN) {
            y += 3 * vitesseX;
        } else if (y == YMAX) {
            y -= 3 * vitesseX;
        }

    }

    /**
     * 
     * @param vitesseX
     */
    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    @Override
    public void dessiner(Graphics g) {
        action();
        g.drawImage(img, (int) x, (int) y, null);
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, vie * 7, 10);
        g.setColor(Color.BLACK);
        if (x > Main.gameValues.CANVAS_SIZE.x) {
            isDessinable = false;

        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        action();
        g.drawOval((int) (x), (int) (y), 100, 100);
        if (x > Main.gameValues.CANVAS_SIZE.x) {
            isDessinable = false;

        }
        
    }

    @Override
    public Rectangle getRectangle() {
        int longeur = 0, hauteur = 0;
        switch (id) {
            case 1://Grandeur du rectangle d'un enemi normal 
                longeur = hauteur = 100;
                break;
            case 2:// Grandeur du rectangle  du supersonic
                longeur = 150;
                hauteur = 100;
                break;
            case 3:// Grandeur du rectangle  du boss 1
                longeur = 450;
                hauteur = 300;
                break;
            case 4:// Grandeur du rectangle  du boss 2
                longeur = hauteur = 450;
                break;
            case 5:// Grandeur du rectangle  du boss 3
                longeur = 500;
                hauteur = 400;
                break;
            default:
                System.out.println("Veuillez entre une identification valide (id) dans le constructuer de l'objet");

        }
        rectangle.x = (int) x;
        rectangle.y = (int) y;
        rectangle.height = hauteur;
        rectangle.width = longeur;
        return rectangle;
    }
    private Rectangle rectangle = new Rectangle();

    @Override
    public void collision(Collisionable c) {
        if (c instanceof Projectile) {

            this.vie -= c.getDommage();
        }
        if (vie <= 0) {
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        return 0;
    }
}
