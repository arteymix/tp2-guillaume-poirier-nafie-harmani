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
package graphique.event;

import graphique.component.Canon;
import java.awt.Graphics;
import java.awt.Rectangle;
import main.Main;
import util.Collisionable;
import util.Dessinable;
import util.Vecteur;

/**
 * Objet dessinable pour les powerups.
 * @author Guillaume Poirier-Morency && Nafie Hamrani
 */
public final class PowerUp extends Dessinable implements Collisionable {

    /**
     * Identificateur du power up du stage 1.
     */
    /**
     * Identificateur du power up du stage 2.
     */
    /**
     * Identificateur du power up du stage 3.
     */
    /**
     * Probabilité d'appartion du power up.
     */
    public static final int POWER_SHOT = 1,
            FAST_SHOT = 2,
            POWER_FAST_SHOT = 3,
            PROBABILITE_APPARITION_POWERUP = 4000;
    /**
     * rectangle qui représente le power up dans le mode débugage.
     */
    private Rectangle rectangle = new Rectangle(0, 0, 500, 500);
    /**
     * id est la variable qui détermine l'identité du power up.
     */
    public final int id;

    /**
     * Constructeur du power up.
     * @param x est la position en x du power up.
     * @param y est la position en y du power up.
     */
    public PowerUp(int x, int y) {
        rectangle.x = x;
        rectangle.y = y;
        switch (Main.level) {
            case Main.LEVEL_1:
                id = POWER_SHOT;
                break;
            case Main.LEVEL_2:
                id = FAST_SHOT;
                break;
            case Main.LEVEL_3:
                id = POWER_FAST_SHOT;
                break;
            case Main.LEVEL_BONUS:
                id = POWER_FAST_SHOT;
                break;
            default:
                id = -1;
        }
        image0 = Main.imageBank.powerup;
        rectangle.width = 187;
        rectangle.height = 140;
    }

    @Override
    public void dessiner(Graphics g) {
        if (rectangle.y >= Main.getCanvasSizeY() - rectangle.height) {
            g.drawImage(image0, rectangle.x, rectangle.y, null);
        } else {
            g.drawImage(image0, rectangle.x, rectangle.y++, null);
        }
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        g.drawString("Powerup", rectangle.x + 15, rectangle.y + 15);
        g.drawString("Type " + this.id + " (1 Power shot, 2 Fast shot, 3 Power & fast shot)", rectangle.x + 15, rectangle.y + 30);
        if (rectangle.y >= Main.getCanvasSizeY() - rectangle.height) {
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        } else {
            g.drawRect(rectangle.x, rectangle.y++, rectangle.width, rectangle.height);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void collision(Collisionable c) {
        // Un powerup est détruit par un canon qui l'absorbe ou un projectile/ovni ennemi
        if (c instanceof Canon) {
            Main.composantesDessinables.add(new Explosion(new Vecteur(rectangle.x, rectangle.y)));
            this.isDessinable = false;
        }
    }

    @Override
    public int getDommage() {
        // Un powerup ne cause pas de dommages à son contact, sauf exceptions.
        return 0;
    }
}