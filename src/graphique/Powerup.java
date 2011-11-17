/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.Serializable;
import util.Collisionable;
import util.Dessinable;

/**
 * Objet dessinable pour les powerups.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Powerup extends Dessinable implements Collisionable, Serializable {

    @Override
    public void dessiner(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getRectangle() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void collision(Collisionable c) {
        // Un powerup peut peut être détruit par sa collision avec un projectile ennemi ou un ovni
        if (c instanceof ProjectileEnnemi | c instanceof Ovni) {
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        // Un powerup ne cause pas de dommages à son contact, sauf exceptions.
        return 0;
    }

    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        return true;
    }
}
