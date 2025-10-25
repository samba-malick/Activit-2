package com.example;

/**
 * Méthodes de calcul simples.
 * Classe utilitaire non instanciable.
 */
public final class Calcul {

    /** Constructeur privé pour empêcher l'instanciation. */
    private Calcul() {
        throw new IllegalStateException("Classe utilitaire - ne pas instancier");
    }

    /**
     * Calcule la somme de deux entiers.
     *
     * @param a premier opérande
     * @param b second opérande
     * @return a + b
     */
    public static int somme(final int a, final int b) {
        return a + b;
    }

    /**
     * Renvoie la note bornée par {@code min} et {@code max}.
     *
     * @param note valeur à borner
     * @param min  borne inférieure (incluse)
     * @param max  borne supérieure (incluse)
     * @return la note si {@code min <= note <= max}, sinon la borne atteinte
     * @throws IllegalArgumentException si {@code min > max} (bornes invalides)
     */
    public static double noteBornee(final double note, final double min, final double max) {
        if (min > max) {
            throw new IllegalArgumentException("min doit être <= max");
        }
        if (note >= max) {
            return max;
        }
        if (note <= min) {
            return min;
        }
        return note;
    }

    /**
     * Effectue la division entière de {@code a} par {@code b}.
     *
     * @param a dividende
     * @param b diviseur (non nul)
     * @return le quotient entier {@code a / b}
     * @throws IllegalArgumentException si {@code b == 0}
     */
    public static int division(final int a, final int b) {
        if (b == 0) {
            throw new IllegalArgumentException("b ne doit pas être 0");
        }
        return a / b;
    }
}
