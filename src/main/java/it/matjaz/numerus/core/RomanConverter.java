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

    private final Pair[] charValues;

    public RomanConverter() {
        this.charValues = RomanCharMapFactory.generateCharPairsArray();
    }

    public Pair[] getCharValues() {
        return charValues;
    }
    
    public int romanStringToInteger(String roman) {
        int arabic = 0;
        int i = 0;
        while (!roman.isEmpty()) {
            Pair romanChar = charValues[i];
            if (roman.startsWith((String) romanChar.getKey())) {
                arabic += (Integer) romanChar.getValue();
                roman = roman.replaceFirst((String) romanChar.getKey(), "");
            } else {
                i++;
            }
        }
        return arabic;
    }
    
    /**
     * 
     * Based on            
     * http://www.fredosaurus.com/notes-java/examples/components/romanNumerals/romanNumeral.html
     * @param arabic
     * @return 
     */
    public String integerToRomanString(int arabic) {
        if (arabic < 1 || arabic > 3999) {
            throw new IllegalArgumentException("Arabic numeral should be an integer in [1, 3999].");
        } 
        String romanString = "";
        for (Pair charAndValue : charValues) {
            // Remove as many of this value as possible (maybe none).
            while (arabic >= (int) charAndValue.getValue()) {
                romanString += (String) charAndValue.getKey();
                arabic -= (int) charAndValue.getValue();
            }
        }
        //RomanNumeral roman = new RomanNumeral(romanString);
        return romanString;
    }
}
