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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        assertEquals("MMXV", roman.getNumeral().getNumeral());
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

    @Test
    public void numeralsConstructorAcceptsNullaRomanNumerals() {
        roman = new RomanInteger(new RomanNumeral());
        assertEquals(new RomanNumeral("NULLA"), roman.getNumeral());
    }

    @Test
    public void numeralsConstructorKeepsACorrectCoversionOfInt() {
        roman = new RomanInteger(new RomanNumeral("MMXV"));
        assertEquals(2015, roman.getValue());
        assertEquals("MMXV", roman.getNumeral().getNumeral());
    }

    @Test
    public void intSetterUpdatesInt() {
        roman = new RomanInteger();
        roman.setValue(123);
        assertEquals(123, roman.getValue());
    }

    @Test
    public void intSetterUpdatesRomanNumeral() {
        roman = new RomanInteger();
        roman.setValue(42);
        assertEquals(new RomanNumeral("XLII"), roman.getNumeral());
    }

    @Test(expected = IllegalArgumentException.class)
    public void intSetterRejectIllegalValues() {
        roman = new RomanInteger();
        roman.setValue(900000);
    }

    @Test
    public void intSetterWithIllegalIntDoesNotSetRomanNumeral() {
        try {
            roman = new RomanInteger(40);
            roman.setValue(20150853);
        } catch (IllegalArgumentException ex) {
            assertEquals(40, roman.getValue());
            assertEquals(new RomanNumeral("XL"), roman.getNumeral());
        }
    }

    @Test
    public void romanNumeralSetterUpdatesRomanNumeral() {
        roman = new RomanInteger(2345);
        roman.setNumeral(new RomanNumeral("MMM"));
        assertEquals(new RomanNumeral("MMM"), roman.getNumeral());
    }

    @Test
    public void romanNumeralSetterUpdatesInt() {
        roman = new RomanInteger(2345);
        roman.setNumeral(new RomanNumeral("CC"));
        assertEquals(200, roman.getValue());
    }

    @Test
    public void numeralSetterWithIllegalNumeralDoesNotSetInt() {
        // kind of stupid test, because the exception is thrown by RomanNumeral
        // not by RomanInteger and a RomanNumeral with illegal syntax can not be
        // even constructed.
        try {
            roman = new RomanInteger(40);
            roman.setNumeral(new RomanNumeral("ABCD"));
        } catch (NumberFormatException ex) {
            assertEquals(40, roman.getValue());
            assertEquals(new RomanNumeral("XL"), roman.getNumeral());
        }
    }

    @Test
    public void numeralSetterAcceptsNullaRomanNumerals() {
        roman = new RomanInteger(10);
        roman.setNumeral(new RomanNumeral());
        assertEquals(0, roman.getValue());
    }

    @Test
    public void equalsChecksBothFields() {
        roman = new RomanInteger(20);
        RomanInteger otherRoman = new RomanInteger(new RomanNumeral("XX"));
        assertTrue(roman.equals(otherRoman));
    }

    @Test
    public void toStringReturnsBothFields() {
        roman = new RomanInteger(14);
        assertTrue(roman.toString().contains("XIV"));
        assertTrue(roman.toString().contains("14"));
    }

    @Test
    public void romanIntegerIsCloneable() {
        roman = new RomanInteger(101);
        try {
            RomanInteger otherRoman = roman.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void clonedRomanNumeralEqualsOriginalOne() {
        roman = new RomanInteger(101);
        RomanInteger otherRoman;
        try {
            otherRoman = roman.clone();
            assertEquals(roman, otherRoman);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void serializabilityWorksBothWays() {
        FileOutputStream outputFile = null;
        try {
            roman = new RomanInteger(new RomanNumeral("MMXV"));
            File tempFile = new File("/tmp/romaninteger.ser");
            outputFile = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(roman);
            outputStream.close();
            outputFile.close();
            FileInputStream inputFile = new FileInputStream(tempFile);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            RomanInteger deserializedRoman = (RomanInteger) inputStream.readObject();
            inputStream.close();
            inputFile.close();
            assertEquals(roman, deserializedRoman);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void deserializedRomanIntegerHasAConverter() {
        FileOutputStream outputFile = null;
        try {
            roman = new RomanInteger(new RomanNumeral("MMXV"));
            File tempFile = new File("/tmp/romaninteger.ser");
            outputFile = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(roman);
            outputStream.close();
            outputFile.close();
            FileInputStream inputFile = new FileInputStream(tempFile);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            RomanInteger deserializedRoman = (RomanInteger) inputStream.readObject();
            inputStream.close();
            inputFile.close();
            deserializedRoman.setValue(4);
            assertEquals(deserializedRoman.getNumeral(), new RomanNumeral("IV"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputFile.close();
            } catch (IOException ex) {
                Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Test
    public void isConvertableToInt() {
        roman = new RomanInteger(300);
        assertEquals(300, roman.intValue());
    }

    @Test
    public void isConvertableToLong() {
        roman = new RomanInteger(123);
        assertEquals(123L, roman.longValue());
    }

    @Test
    public void isConvertableToFloat() {
        roman = new RomanInteger(42);
        assertEquals(42.0, roman.floatValue(), 0.000001);
    }

    @Test
    public void isConvertableToDouble() {
        roman = new RomanInteger(42);
        assertEquals(42.0, roman.doubleValue(), 0.000001);
    }

    @Test
    public void compareWithBigger() {
        roman = new RomanInteger(10);
        double other = 42.0;
        assertTrue(roman.compareTo(other) < 0);
    }

    @Test
    public void compareWithLesser() {
        roman = new RomanInteger(10);
        float other = (float) -4.24;
        assertTrue(roman.compareTo(other) > 0);
    }

    @Test
    public void compareWithEqual() {
        roman = new RomanInteger(10);
        long other = 10L;
        assertTrue(roman.compareTo(other) == 0);
    }

    @Test
    public void compareAndEqualsAreCompatibile() {
        roman = new RomanInteger(10);
        long other = 10L;
        assertTrue(roman.equals(new RomanInteger((int) other)) == true);
    }

}
