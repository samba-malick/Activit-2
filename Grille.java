package com.example.sudoku;

import java.util.Set;

/**
 * API d'une grille de Sudoku.
 */
public interface Grille {

    /**
     * @return la dimension (ex: 9 pour une grille 9x9).
     */
    int getDimension();

    /**
     * @return l'ensemble des valeurs autorisées.
     */
    Set<ValeurDeCase> getValeursPossibles();

    /**
     * @param x colonne (0..dimension-1)
     * @param y ligne (0..dimension-1)
     * @return la valeur courante ou null si la case est vide
     * @throws GrilleException si coordonnées hors bornes
     */
    ValeurDeCase getValeur(int x, int y) throws GrilleException;

    /**
     * Place une valeur dans la case ou vide si value==null.
     * @param x colonne (0..dimension-1)
     * @param y ligne (0..dimension-1)
     * @param value valeur à placer (ou null pour vider)
     * @throws GrilleException si coord. hors bornes, valeur non autorisée,
     *                         ou règle de Sudoku violée (ligne/colonne/sous-grille).
     */
    void setValeur(int x, int y, ValeurDeCase value) throws GrilleException;

    /**
     * Indique si l'on peut placer value en (x,y) sans violer les règles.
     * @param x colonne
     * @param y ligne
     * @param value valeur candidate (non null)
     * @return true si possible
     * @throws GrilleException si coord. hors bornes, valeur non autorisée
     */
    boolean possible(int x, int y, ValeurDeCase value) throws GrilleException;

    /**
     * @return true si toutes les cases sont remplies (ne valide pas la solution).
     */
    boolean complete();

    /** Vide toutes les cases. */
    void vider();
}
