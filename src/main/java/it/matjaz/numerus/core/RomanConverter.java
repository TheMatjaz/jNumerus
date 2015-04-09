package it.matjaz.numerus.core;

import javafx.util.Pair;

/**
 * Offers conversion methods from roman numerals to arabic integers and
 * vice-versa.
 *
 * Uses RomanCharMapFactory generated structures as reference for translations
 * of the digits in both numeral systems.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanConverter {

    private final Pair[] chars;

    public RomanConverter() {
        this.chars = RomanCharMapFactory.generateCharPairsArray();
    }

    public Pair[] getChars() {
        return chars;
    }

}
