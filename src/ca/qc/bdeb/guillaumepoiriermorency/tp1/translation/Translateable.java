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
package ca.qc.bdeb.guillaumepoiriermorency.tp1.translation;

/**
 * Interface pour la traduction. Il permet l'implémentation du TranslationManager.
 * @author Guillaume Poirier-Morency
 */
public interface Translateable {  
    
    /**
     * Quand le TranslationManager est appelé, il utilisera cette méthode pour
     * renvoyer son résultat. Celle-ci doit interpréter le tableau et retransmettre
     * les traductions dans le programme.
     * @param s 
     */
    public void translate(String[] s);
    
    /**
     * Obtenir les String à traduire localement.
     * @return un tableau de String à traduire localement.
     */
    public String[] getLocalStringToTranslate();
    
    /**
     * Obtenir les sous-objets à traduire.
     * @return un tableau de sous-objets à traduire.
     */
    public Translateable[] getTranslateableObjects();
    
    
}
