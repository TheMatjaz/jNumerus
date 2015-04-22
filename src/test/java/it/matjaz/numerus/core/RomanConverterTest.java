/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project Numerus, a roman numerals
 * library for Java. The library and its source code may be found on:
 * https://github.com/MatjazDev/Numerus and http://matjaz.it/numerus
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.numerus.core;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test of {@link RomanConverter} which is converting roman numerals to
 * integers and vice-versa by using RomanCharMapFactory generated structures.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanConverterTest {

    private RomanConverter converter;

    @Before
    public void constructConverter() {
        this.converter = new RomanConverter();
    }

    @Test
    public void romanNumeralsMayBeConvertedToIntegers() {
        assertEquals(21, converter.romanNumeralToInteger(new RomanNumeral("XXI")));
    }

    @Test
    public void romanNumeralsAreReturnedFromIntConversion() {
        assertEquals(new RomanNumeral("LXI"), converter.integerToRomanNumeral(61));
    }

    @Test
    public void whenRomanWithMultipleSameCharsIsGivenThenArabicIsReturned() {
        assertEquals(3212, converter.romanNumeralToInteger(new RomanNumeral("MMMCCXII")));
    }

    @Test
    public void whenRomanWithSubtractiveFormIsGivenThenArabicIsreturned() {
        assertEquals(1940, converter.romanNumeralToInteger(new RomanNumeral("MCMXL")));
    }

    @Test
    public void whenCorrectRomanIsGivenThenArabicIsReturned() {
        assertEquals(1100, converter.romanNumeralToInteger(new RomanNumeral("MC")));
    }

    @Test
    public void whenIntegerIsGivenThenRomanStringIsReturned() {
        assertEquals(new RomanNumeral("CXII"), converter.integerToRomanNumeral(112));
    }

    @Test
    public void whenIntegerForRomanWithSubtractiveFormIsGivenThenRomanStringIsReturned() {
        assertEquals(new RomanNumeral("XCVI"), converter.integerToRomanNumeral(96));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeIntegerIsGivenThenExceptionIsThrown() {
        converter.integerToRomanNumeral(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooBigIntegerIsGivenThenExceptionIsThrown() {
        converter.integerToRomanNumeral(4001);
    }

    @Test
    public void whenUninitializedRomanNumeralIsConvertedReturnsZero() {
        assertEquals(0, converter.romanNumeralToInteger(new RomanNumeral()));
    }

    @Test
    public void everyIntegerIsConvertedToASyntacticallyCorrectRomanNumeral() {
        for (int i = 1; i <= 3999; i++) {
            try {
                converter.integerToRomanNumeral(i);
            } catch (NumberFormatException ex) {
                System.out.println(ex.getMessage());
                fail("Failed to convert " + i);
            }
        }
    }

    @Test
    public void conversionIsBijective() {
        HashMap<Integer, RomanNumeral> intsAndNumerals = new HashMap<>();
        for (int i = 1; i <= 3999; i++) {
            intsAndNumerals.put(i, converter.integerToRomanNumeral(i));
        }
        intsAndNumerals.keySet().stream().forEach((i) -> {
            assertTrue(i == converter.romanNumeralToInteger(intsAndNumerals.get(i)));
        });
    }

}
