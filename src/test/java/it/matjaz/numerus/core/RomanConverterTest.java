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
    public void whenRomanWithMultipleSameCharsIsGivenThenArabicIsReturned() {
        assertEquals(3212, converter.romanStringToInteger("MMMCCXII"));
    }

    @Test
    public void whenRomanWithSubtractiveFormIsGivenThenArabicIsreturned() {
        assertEquals(1940, converter.romanStringToInteger("MCMXL"));
    }

    @Test
    public void whenCorrectRomanIsGivenThenArabicIsReturned() {
        assertEquals(1100, converter.romanStringToInteger("MC"));
    }
    
    @Test
    public void whenIntegerIsGivenThenRomanStringIsReturned() {
        assertEquals("CXII", converter.integerToRomanString(112));
    }

    @Test
    public void whenIntegerForRomanWithSubtractiveFormIsGivenThenRomanStringIsReturned() {
        assertEquals("XCVI", converter.integerToRomanString(96));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNegativeIntegerIsGivenThenExceptionIsThrown() {
        converter.integerToRomanString(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTooBigIntegerIsGivenThenExceptionIsThrown() {
        converter.integerToRomanString(4001);
    }

}
