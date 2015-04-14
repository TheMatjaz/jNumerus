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
 * JUnit test of {@link RomanInteger} which is a container for a RomanNumeral
 * and its Integer value tied toghether.
 * <p>
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanIntegerTest {

    private RomanInteger roman;

    @Test
    public void defaultConstructorSetsRomanNumeralToDefault() {
        roman = new RomanInteger();
        assertEquals(roman.getNumeral(), new RomanNumeral());
    }

    @Test
    public void defaultConstructorSetsIntToZero() {
        roman = new RomanInteger();
        assertEquals(roman.getValue(), 0);
    }

    @Test
    public void intConstructorInitializedBothFields() {
        roman = new RomanInteger(42);
        assertNotEquals(0, roman.getValue());
        assertNotNull(roman.getNumeral());
    }

    @Test(expected = IllegalArgumentException.class)
    public void intConstructorRejectTooBigInts() {
        roman = new RomanInteger(90909090);
    }

    @Test(expected = IllegalArgumentException.class)
    public void intConstructorRejectNegativeInts() {
        roman = new RomanInteger(-1);
    }

    @Test
    public void intConstructorKeepsCorrectConversionOfNumerals() {
        roman = new RomanInteger(2015);
        assertEquals(2015, roman.getValue());
        assertEquals("MMXV", roman.getNumeral().getSymbols());
    }

    @Test
    public void intConstructorWithIllegalIntDoesNotSetRomanNumeral() {
        try {
            roman = new RomanInteger(40);
            roman = new RomanInteger(20150853);
        } catch (IllegalArgumentException ex) {
            assertEquals(40, roman.getValue());
            assertEquals(new RomanNumeral("XL"), roman.getNumeral());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void numeralsConstructorRejectsUninitializedRomanNumerals() {
        roman = new RomanInteger(new RomanNumeral());
    }

    @Test
    public void numeralsConstructorKeepsACorrectCoversionOfInt() {
        roman = new RomanInteger(new RomanNumeral("MMXV"));
        assertEquals(2015, roman.getValue());
        assertEquals("MMXV", roman.getNumeral().getSymbols());
    }
    
     @Test(expected = IllegalArgumentException.class)
     public void costructorWithBothParametersRejectsIncorrectPair() {
         roman = new RomanInteger(2345, new RomanNumeral("C"));
     }

     @Test
     public void constructorWithBothParametersAcceptsCorrectPairsAndStoresThem() {
         roman = new RomanInteger(2345, new RomanNumeral("MMCCCXLV"));
         assertEquals(2345, roman.getValue());
         assertEquals(new RomanNumeral("MMCCCXLV"), roman.getNumeral());
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void constructorWithBothParametersRejectsIllegalInts() {
         // TODO: this works because the Exception is about the non-matching pair.
         // At this point (2015-04-14, v0.3.0) we don't support negative or large
         // Integers yet.
         roman = new RomanInteger(-4, new RomanNumeral("III"));
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void constructorWithBothParametersRejectsUninitializedRomanNumerals() {
         roman = new RomanInteger(0, new RomanNumeral());
     }
     
     /*
     @Test
     public void intSetterUpdatesInt() {
     roman.setValue(123);
     assertEquals(123, roman.getValue());
     }

     @Test
     public void intSetterUpdatesRomanNumeral() {
     roman.setValue(42);
     assertEquals(new RomanNumeral("XLII"), roman.getNumeral());
     }

     @Test(expected = IllegalArgumentException.class)
     public void intSetterRejectIllegalValues() {
     roman.setValue(900000);
     }

     @Test
     public void intSetterWithIllegalIntDoesNotSetRomanNumeral() {
     roman.setValue(value);
     }

     @Test
     public void romanNumeralSetterUpdatesRomanNumeral() {
     roman.setNumeral(new RomanNumeral("MMM"));
     assertEquals(new RomanNumeral("MMM"), roman.getNumeral());
     }

     @Test
     public void romanNumeralSetterUpdatesInt() {
     roman.setNumeral(new RomanNumeral("CC"));
     assertEquals(200, roman.getValue());
     }

     /*
     @Test
     public void numeralStringSetterUpdatesInt() {
     fail();
     }
    
     @Test
     public void numeralStringSetterUpdatesRomanNumeral() {
     fail();
     }
    
     @Test(expected = NumberFormatException.class)
     public void numeralsStringSetterRejectsWrongSyntax() {
     roman.setNumeral("LaTeX rules");
     }
     */
}
