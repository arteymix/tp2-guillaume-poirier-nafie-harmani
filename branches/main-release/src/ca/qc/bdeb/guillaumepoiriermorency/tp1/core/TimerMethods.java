/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.guillaumepoiriermorency.tp1.core;

/**
 *
 * @author guillaume
 */
public class TimerMethods {
    /**
     * Convertit un entier de secondes en String de temps.
     * @param i est le nombre de secondes en entier.
     * @return le temps en String écrit "x minutes y secondes".
     */
    public static String convertIntegerToTime(int i) {
        int nbrSeconds = i % 60;
        int nbrMinutes = (i - nbrSeconds) / 60;
        return nbrMinutes + " minutes " + nbrSeconds + " secondes";
    }

    /**
     * Convertit un String de temps en entier de secondes.
     * @param s est le temps et doit être écrit comme "x minutes y secondes".
     * @return la valeur en seconde et en entier du temps entré.
     */
    public static int convertTimeToInteger(String s) {
        String[] SplittedString = s.split(" ");
        int nbrMinutes = Integer.parseInt(SplittedString[0]);
        int nbrSeconds = Integer.parseInt(SplittedString[2]);
        return nbrMinutes * 60 + nbrSeconds;
    }
}
