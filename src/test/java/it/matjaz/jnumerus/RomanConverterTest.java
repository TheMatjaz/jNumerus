/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project jNumerus, a roman numerals
 * library for Java. The library and its source code may be found on:
 * https://github.com/TheMatjaz/jNumerus/
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.jnumerus;

import it.matjaz.jnumerus.RomanConverter;
import it.matjaz.jnumerus.RomanNumeral;
import it.matjaz.jnumerus.IllegalNumeralSyntaxException;
import it.matjaz.jnumerus.IllegalArabicValueException;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test of {@link RomanConverter} which is converting roman numerals to
 * integers and vice-versa by using RomanCharMapFactory generated structures.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanConverterTest {

    private RomanConverter converter;

    @Before
    public void constructConverter() {
        this.converter = new RomanConverter();
    }

    @Test
    public void romanNumeralsMayBeConvertedToIntegers() throws IllegalNumeralSyntaxException {
        assertEquals(21, converter.romanNumeralToInteger(new RomanNumeral("XXI")));
    }

    @Test
    public void romanNumeralsAreReturnedFromIntConversion() throws IllegalNumeralSyntaxException, IllegalArabicValueException {
        assertEquals(new RomanNumeral("LXI"), converter.integerToRomanNumeral(61));
    }

    @Test
    public void whenRomanWithMultipleSameCharsIsGivenThenArabicIsReturned() throws IllegalNumeralSyntaxException {
        assertEquals(3212, converter.romanNumeralToInteger(new RomanNumeral("MMMCCXII")));
    }

    @Test
    public void whenRomanWithSubtractiveFormIsGivenThenArabicIsreturned() throws IllegalNumeralSyntaxException {
        assertEquals(1940, converter.romanNumeralToInteger(new RomanNumeral("MCMXL")));
    }

    @Test
    public void whenCorrectRomanIsGivenThenArabicIsReturned() throws IllegalNumeralSyntaxException {
        assertEquals(1100, converter.romanNumeralToInteger(new RomanNumeral("MC")));
    }

    @Test
    public void whenIntegerIsGivenThenRomanStringIsReturned() throws IllegalNumeralSyntaxException, IllegalArabicValueException {
        assertEquals(new RomanNumeral("CXII"), converter.integerToRomanNumeral(112));
    }

    @Test
    public void whenIntegerForRomanWithSubtractiveFormIsGivenThenRomanStringIsReturned() throws IllegalNumeralSyntaxException, IllegalArabicValueException {
        assertEquals(new RomanNumeral("XCVI"), converter.integerToRomanNumeral(96));
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenTooBigIntegerIsGivenThenExceptionIsThrown() throws IllegalArabicValueException {
        converter.integerToRomanNumeral(4001);
    }

    @Test
    public void whenNullaRomanNumeralIsConvertedReturnsZero() {
        assertEquals(0, converter.romanNumeralToInteger(new RomanNumeral()));
    }
    
    @Test
    public void whenZeroIsConvertedReturnsNullaRomanNumeral() throws IllegalArabicValueException {
        assertEquals(new RomanNumeral(), converter.integerToRomanNumeral(0));
    }

    @Test
    public void everyIntegerIsConvertedToASyntacticallyCorrectRomanNumeral() throws IllegalArabicValueException {
        for (int i = -3999; i <= 3999; i++) {
            try {
                converter.integerToRomanNumeral(i);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
                fail("Failed to convert " + i);
            }
        }
    }

    @Test
    public void conversionIsBijective() throws IllegalArabicValueException {
        HashMap<Integer, RomanNumeral> intsAndNumerals = new HashMap<>();
        for (int i = -3999; i <= 3999; i++) {
            intsAndNumerals.put(i, converter.integerToRomanNumeral(i));
        }
        intsAndNumerals.keySet().stream().forEach((i) -> {
            assertTrue(i == converter.romanNumeralToInteger(intsAndNumerals.get(i)));
        });
    }
    
    @Test
    public void negativeIntegerGetsConvertedToNegativeRoman() throws IllegalNumeralSyntaxException, IllegalArabicValueException {
        assertEquals(new RomanNumeral("-LXI"), converter.integerToRomanNumeral(-61));
    }
    
    @Test
    public void negativeRomanGetsConvertedToNegativeInteger() throws IllegalNumeralSyntaxException {
        assertEquals(-21, converter.romanNumeralToInteger(new RomanNumeral("-XXI")));
    }
    
    @Test(expected = IllegalArabicValueException.class)
    public void whenTooSmallNegativeIntegerIsGivenThenExceptionIsThrown() throws IllegalArabicValueException {
        converter.integerToRomanNumeral(-5000);
    }
    
    @Test
    public void negativeZeroGetsConvertedToPositiveNulla() throws IllegalArabicValueException {
        assertEquals(new RomanNumeral(), converter.integerToRomanNumeral(-0));
    }
    
    @Test
    public void negativeNullaGetsConvertedToPositiveZero() throws IllegalNumeralSyntaxException {
        assertEquals(0, converter.romanNumeralToInteger(new RomanNumeral("-NULLA")));
    }
    
}
