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

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Objet dessinable pour les powerups.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public class Powerup extends Dessinable implements Collisionable, Serializable {

    public Powerup(int x, int y) {
        rectangle.x =  x;
        rectangle.y =  y;
        switch (Main.gameValues.level) {
            case 1:
                id = POWER_SHOT;
                break;
            case 2:
                id = FAST_SHOT;
                break;
            case 3:
                id = POWER_FAST_SHOT;
                break;
            default:
                id = -1;
                System.out.println("Illegal id exception!");
        }
        image0 = Main.imageBank.powerup;
    }
    public static final int POWER_SHOT = 0, // Powerup stage 1
            FAST_SHOT = 1, // Powerup stage 2
            POWER_FAST_SHOT = 2,
            PROBABILITE_APPARITION_POWERUP = 1000;
    private Rectangle rectangle;
    public final int id;

    @Override
    public void dessiner(Graphics g) {
        g.drawImage(image0, rectangle.x, rectangle.y, null);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        // Un powerup est détruit par un canon qui l'absorbe ou un projectile/ovni ennemi
        if (c instanceof ProjectileEnnemi | c instanceof Ovni | c instanceof Canon) {
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        // Un powerup ne cause pas de dommages à son contact, sauf exceptions.
        return 0;
    }
}
