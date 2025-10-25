package com.example.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Implementation d'une grille de Sudoku 9x9 (générique dimension carrée). */
public final class GrilleImpl implements Grille {

    private final int dimension;
    private final int tailleBloc; // p.ex. 3 pour 9x9
    private final Set<ValeurDeCase> valeursPossibles;
    private final ValeurDeCase[][] cases;

    /**
     * Construit une grille vide.
     * @param dimension taille de la grille (ex 9)
     * @param valeurs ensemble des valeurs autorisées (taille == dimension)
     * @throws IllegalArgumentException si dimension non carrée (sqrt non entier) ou mismatch valeurs
     */
    public GrilleImpl(final int dimension, final Set<ValeurDeCase> valeurs) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("dimension doit être > 0");
        }
        int root = (int) Math.round(Math.sqrt(dimension));
        if (root * root != dimension) {
            throw new IllegalArgumentException("dimension doit être un carré parfait (4, 9, 16, ...)");
        }
        if (valeurs == null || valeurs.size() != dimension) {
            throw new IllegalArgumentException("valeurs possibles doit contenir exactement " + dimension + " éléments");
        }
        this.dimension = dimension;
        this.tailleBloc = root;
        this.valeursPossibles = Collections.unmodifiableSet(new HashSet<>(valeurs));
        this.cases = new ValeurDeCase[dimension][dimension];
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public Set<ValeurDeCase> getValeursPossibles() {
        return valeursPossibles;
    }

    private void checkCoord(final int x, final int y) throws GrilleException {
        if (x < 0 || x >= dimension || y < 0 || y >= dimension) {
            throw new GrilleException("Coordonnées hors bornes: (" + x + "," + y + ")");
        }
    }

    private void checkValeur(final ValeurDeCase v) throws GrilleException {
        if (v == null) {
            return;
        }
        if (!valeursPossibles.contains(v)) {
            throw new GrilleException("Valeur non autorisée: " + v);
        }
    }

    @Override
    public ValeurDeCase getValeur(final int x, final int y) throws GrilleException {
        checkCoord(x, y);
        return cases[y][x];
    }

    @Override
    public void setValeur(final int x, final int y, final ValeurDeCase value) throws GrilleException {
        checkCoord(x, y);
        checkValeur(value);
        if (value != null && !possible(x, y, value)) {
            throw new GrilleException("Conflit Sudoku pour " + value + " en (" + x + "," + y + ")");
        }
        cases[y][x] = value;
    }

    @Override
    public boolean possible(final int x, final int y, final ValeurDeCase value) throws GrilleException {
        Objects.requireNonNull(value, "value");
        checkCoord(x, y);
        checkValeur(value);

        // même ligne
        for (int cx = 0; cx < dimension; cx++) {
            if (cx != x && value.equals(cases[y][cx])) {
                return false;
            }
        }

        // même colonne
        for (int cy = 0; cy < dimension; cy++) {
            if (cy != y && value.equals(cases[cy][x])) {
                return false;
            }
        }

        // même sous-grille
        int bx = (x / tailleBloc) * tailleBloc;
        int by = (y / tailleBloc) * tailleBloc;
        for (int cy = by; cy < by + tailleBloc; cy++) {
            for (int cx = bx; cx < bx + tailleBloc; cx++) {
                if ((cx != x || cy != y) && value.equals(cases[cy][cx])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean complete() {
        for (int y = 0; y < dimension; y++) {
            for (int x = 0; x < dimension; x++) {
                if (cases[y][x] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void vider() {
        for (int y = 0; y < dimension; y++) {
            Arrays.fill(cases[y], null);
        }
    }
}
