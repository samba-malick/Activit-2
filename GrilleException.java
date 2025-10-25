package com.example.sudoku;

/**
 * Exception lancée en cas d'erreur de manipulation de la grille.
 */
public class GrilleException extends Exception {
    private static final long serialVersionUID = 1L;

    public GrilleException(final String message) {
        super(message);
    }

    public GrilleException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
