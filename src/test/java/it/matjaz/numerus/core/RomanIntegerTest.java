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

    @Before
    public void defaultRomanConstructor() {
        roman = new RomanInteger();
    }

    @Test
    public void defaultConstructorSetsRomanNumeralToDefault() {
        assertEquals(roman.getNumeral(), new RomanNumeral());
    }

    @Test
    public void defaultConstructorSetsIntToZero() {
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
            roman = new RomanInteger(20150853);
        } catch (IllegalArgumentException ex) {
            assertEquals(0, roman.getValue());
            assertFalse(roman.getNumeral().isInitialized());
        }
    }
    /*
 
     @Test
     public void numeralsConstructorKeepsACorrectCoversionOfInt() {
     fail();
     }

     @Test
     public void costructorWithBothParametersRejectsIncorrectPair() {
     fail();
     }
    
     @Test
     public void constructorWithBothParametersRejectsIllegalInts() {
     fail();
     }

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
