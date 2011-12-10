/*   This file is part of TP2.
 *
 *   TP2 is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   TP2 is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with TP2.  If not, see <http://www.gnu.org/licenses/>.
 */
package graphique.component;

import graphique.event.PowerUp;
import graphique.event.Explosion;
import graphique.event.PointsObtenus;
import java.awt.Rectangle;
import util.Dessinable;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import main.Main;
import util.Collisionable;
import util.Vecteur;

/**
 * Fichier de classe pour un projectile.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class Projectile extends Dessinable implements Collisionable {

    ////////////////////////////////////////////////////////////////////////////
    // Variables propres aux projectiles
    private static final double GRAVITY = 0.8;
    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    private Vecteur position, vitesse = new Vecteur(8, -8);
    private Rectangle rectangle;
    private final int DOMMAGES;
    private double tetha;
    ////////////////////////////////////////////////////////////////////////////

    /**
     * Un Projectile est un objet qui représente un tir de canon.
     * @param point est le point initial d'où le projectile est tiré.
     * @param orientation est l'orientation initiale du projectile.
     * @param id est propre à chaque projectile et définit ses caractéristiquesé
     * @param angle  
     */
    public Projectile(Vecteur point, Vecteur orientation, int id, double angle, int damage) {
        tetha = angle + Math.PI;
        position = point;
        vitesse = orientation;
        image0 = Main.imageBank.projectile;
        rectangle = new Rectangle((int) point.x, (int) point.y, 10, 10);
        DOMMAGES = damage;
    }

    @Override
    public void dessiner(Graphics g) {
        if (position.y > Main.getCanvasSizeY() | position.x < 0 | position.x > Main.getCanvasSizeX()) {
            Main.composantesDessinables.add(new PointsObtenus((int) position.y, (int) position.x, -10));
            Main.points -= 2;
            this.isDessinable = false;
        }
        double x = this.position.x;
        double y = this.position.y;
        AffineTransform at = new AffineTransform();
        at.translate(x - 5, y);
        at.rotate(tetha, 0, 0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image0, at, null);
        at.translate(-x + 5, -y);
        //g.drawImage(image0, (int) ((position.x) -= vitesse.x)-10, (int) (position.y -= vitesse.y) - 10, null);
        position.x -= vitesse.x;
        position.y -= vitesse.y;
        vitesse.y -= GRAVITY;
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        if (position.y > Main.getCanvasSizeY() | position.x < 0 | position.x > Main.getCanvasSizeX()) {
            Main.composantesDessinables.add(new PointsObtenus((int) position.y, (int) position.x, -10));
            Main.points -= 2;
            isDessinable = false;
        }
        g.drawRect((int) ((position.x) -= vitesse.x) - 5, (int) (position.y -= vitesse.y) - 5, 10, 10);
        g.drawString("Projectile", (int) position.x, (int) position.y);
        g.drawString("Dommages : " + DOMMAGES, (int) position.x, (int) position.y + 15);
        vitesse.y -= GRAVITY;
    }

    @Override
    public Rectangle getRectangle() {
        rectangle.x = (int) position.x;
        rectangle.y = (int) position.y;
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        if (c instanceof ProjectileEnnemi) {

            if (((ProjectileEnnemi) c).isInvincible) {
                return;
            }
        }
        if (!(c instanceof Canon) && !(c instanceof Projectile) && !(c instanceof PowerUp)) {
            // Le projectile a frappé quelque chose, il sera détruit!
            isDessinable = false;
            Main.composantesDessinables.add(new Explosion(this.position));
        }
    }

    @Override
    public int getDommage() {
        return DOMMAGES;
    }
}