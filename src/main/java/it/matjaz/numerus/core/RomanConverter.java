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
    
    public int romanStringToInteger(String romanString) {
        int arabicValue = 0;
        int romanStringIndex = 0;
        int romanCharIndex = 0;
        while (romanStringIndex < romanString.length()) {
            String romanChar = (String) charValues[romanCharIndex].getKey();
            int romanCharValue = (int) charValues[romanCharIndex].getValue();
            if (romanString.startsWith(romanChar, romanStringIndex)) {
                arabicValue += romanCharValue;
                romanStringIndex += romanChar.length();
            } else {
                romanCharIndex++;
            }
        }
        return arabicValue;
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
