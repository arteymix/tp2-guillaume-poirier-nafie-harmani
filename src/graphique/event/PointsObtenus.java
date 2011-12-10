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

import graphique.window.MainCanvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import main.Main;
import util.Dessinable;

/**
 *
 * @author Guillaume Poirier-Morency et Nafie Hamrani
 */
public final class PointsObtenus extends Dessinable {

    ////////////////////////////////////////////////////////////////////////////
    // Variables locales
    /**
     * TODO Javadoc ici
     */
    private int size = 15;
    /**
     * TODO Javadoc ici
     */
    private int delay = 30;
    /**
     * TODO Javadoc ici
     */
    private final int POINTS;
    /**
     * TODO Javadoc ici
     */
    private Font font = new Font("Comic sans ms", Font.BOLD, size);
    /**
     * TODO Javadoc ici
     */
    /**
     * TODO Javadoc ici
     */
    private final int POSITION_X, POSITION_Y;
    ////////////////////////////////////////////////////////////////////////////

    /**
     * TODO Javadoc ici
     * @param x TODO Javadoc ici
     * @param y TODO Javadoc ici
     * @param points TODO Javadoc ici
     */
    public PointsObtenus(int x, int y, int points) {
        this.POINTS = points;
        this.POSITION_X = x;
        this.POSITION_Y = y;
    }

    @Override
    public void dessiner(Graphics g) {
        action();
        g.setFont(font);
        if ((POINTS) > 0) {
            g.setColor(Color.BLUE);
            g.drawString(POINTS + " points", POSITION_X, POSITION_Y);
        } else {
            g.setColor(Color.RED);
            g.drawString(POINTS + " points", (Main.getCanvasSizeX() / 2) - 60, 45);
        }
        g.setFont(MainCanvas.FONT);
        g.setColor(Color.BLACK);
    }

    @Override
    public void dessinerDeboguage(Graphics g) {
        action();
        g.setFont(font);
        if ((POINTS) > 0) {
            g.setColor(Color.BLUE);
            g.drawString(POINTS + " points", POSITION_X, POSITION_Y);
        } else {
            g.setColor(Color.RED);
            g.drawString(POINTS + " points", (Main.getCanvasSizeX() / 2) - 60, 45);
        }
        g.setFont(MainCanvas.FONT);
        g.setColor(Color.BLACK);
    }

    private void action() {
        delay--;
        font = new Font("Comic sans ms", Font.BOLD, size++);
        if (delay <= 0) {

            isDessinable = false;
        }
    }
}
