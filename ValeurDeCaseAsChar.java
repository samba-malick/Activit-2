package com.example.sudoku;

import java.util.Objects;

/** Implementation d'un élément de grille basé sur un caractère. */
public final class ValeurDeCaseAsChar implements ValeurDeCase {

    private final char c;

    /**
     * @param c caractère représentant la valeur (ex: '1'..'9').
     */
    public ValeurDeCaseAsChar(final char c) {
        this.c = c;
    }

    @Override
    public char asChar() {
        return c;
    }

    @Override
    public String toString() {
        return Character.toString(c);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ValeurDeCaseAsChar)) {
            return false;
        }
        ValeurDeCaseAsChar other = (ValeurDeCaseAsChar) obj;
        return c == other.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c);
    }
}
