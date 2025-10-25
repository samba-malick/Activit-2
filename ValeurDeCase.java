package com.example.sudoku;

/**
 * Représente une valeur autorisée d'une case de Sudoku.
 */
public interface ValeurDeCase {
    /**
     * @return la représentation caractère de la valeur (ex: '1'..'9').
     */
    char asChar();
}
