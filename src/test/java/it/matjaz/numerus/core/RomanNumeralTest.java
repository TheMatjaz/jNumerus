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
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test of {@link RomanNumeral} which is a container for syntactically
 * correct roman numerals.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeralTest {

    private RomanNumeral roman;

    @Before
    public void defaultRomanConstructor() {
        roman = new RomanNumeral();
    }

    @Test
    public void whenDefaultConstructorIsCalledThenSymbolsAreNullaString() {
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void whenCorrectStringIsGivenThenNoExceptionIsThrown() {
        roman.setNumeral("XLII");
        assertEquals("XLII", roman.getNumeral());
    }

    @Test(expected = NullPointerException.class)
    public void whenNullStringIsGivenThenExceptionIsThrown() {
        roman.setNumeral(null);
    }

    @Test
    public void givenStringGetsStrippedAndUpcased() {
        roman.setNumeral("  \t\n\r   xliI ");
        assertEquals("XLII", roman.getNumeral());
    }

    @Test
    public void givenStringGetsStrippedOfInnerWhiteSpaceChars() {
        roman.setNumeral("  XL I  II");
        assertEquals("XLIII", roman.getNumeral());
    }

    @Test(expected = NumberFormatException.class)
    public void whenEmptyStringIsGivenThenExceptionIsThrown() {
        roman.setNumeral("");
    }

    @Test(expected = NumberFormatException.class)
    public void whenWhitespaceStringIsGivenThenExceptionIsThrown() {
        roman.setNumeral("  \t\n\r  ");
    }

    @Test(expected = NumberFormatException.class)
    public void whenImpossiblyLongStringIsGivenThenExceptionIsThrown() {
        roman.setNumeral("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }

    @Test(expected = NumberFormatException.class)
    public void whenStringContainsNonRomanCharactersThenExceptionIsThrown() {
        roman.setNumeral("pFXC-");
    }

    @Test
    public void whenStringContainsNonRomanCharactersThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("pPFXC-");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("pPF-".toUpperCase()));
        }
    }

    @Test(expected = NumberFormatException.class)
    public void whenStringWithIncorrectTomanSyntaxIsGivenThenExceptionIsThrown() {
        roman.setNumeral("MMCMIIIX");
    }

    @Test
    public void whenStringContainsMoreThanThreeConsecutiveTenLikeSymbolsThenThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("CCCC");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("CCCC"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("DDXII");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("DD"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem2() {
        try {
            roman.setNumeral("DXID");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("DXID"));
        }
    }

    @Test
    public void nullaStringIsAcceptedByParamtericConstructor() {
        roman = new RomanNumeral("NULLA");
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void nullaStringIsAcceptedByGetter() {
        roman.setNumeral("NULLA");
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void nullaStringGetsUpcased() {
        roman.setNumeral("nullA");
        assertEquals(roman.getNumeral(), "NULLA");
        roman = new RomanNumeral("nUlla");
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void syntaxCheckCanBePerformedWithoutInstantiation() {
        assertTrue(RomanNumeral.isCorrectRomanSyntax("CLXXII"));
        assertFalse(RomanNumeral.isCorrectRomanSyntax("LINUX RULES!"));
    }

    @Test
    public void toStringDelegatesGetter() {
        roman.setNumeral("MCMLXIV");
        assertEquals(roman.getNumeral(), roman.toString());
    }

    @Test
    public void equalsMethodWorksh() {
        RomanNumeral numeral1 = new RomanNumeral("MCMLXIV");
        RomanNumeral numeral2 = new RomanNumeral("MCMLXIV");
        assertTrue(numeral1.equals(numeral2));
    }

    @Test
    public void defaultConstructorAndSetterIsTheSameAsInitializingConstructor() {
        RomanNumeral numeral1 = new RomanNumeral();
        numeral1.setNumeral("MCMLXIV");
        RomanNumeral numeral2 = new RomanNumeral("MCMLXIV");
        assertTrue(numeral1.equals(numeral2));
    }

    @Test
    public void setterAfterDefaultConstructorReturnsEmptyString() {
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void isNullaReturnsTrueIfIsNotInitialized() {
        assertTrue(roman.isNulla());
    }

    @Test
    public void isNullaReturnsFalseIfIsInitialized() {
        roman.setNumeral("C");
        assertFalse(roman.isNulla());
    }

    @Test
    public void serializabilityWorksBothWays() {
        FileOutputStream outputFile = null;
        try {
            roman.setNumeral("MMXV");
            File tempFile = new File("/tmp/numerals.ser");
            outputFile = new FileOutputStream(tempFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
            outputStream.writeObject(roman);
            outputStream.close();
            outputFile.close();
            FileInputStream inputFile = new FileInputStream(tempFile);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            RomanNumeral deserializedRoman = (RomanNumeral) inputStream.readObject();
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
    public void romanNumeralIsCloneable() {
        roman.setNumeral("DXI");
        try {
            RomanNumeral otherRoman = (RomanNumeral) roman.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void clonedRomanNumeralEqualsOriginalOne() {
        roman.setNumeral("DXI");
        RomanNumeral otherRoman;
        try {
            otherRoman = (RomanNumeral) roman.clone();
            assertEquals(roman, otherRoman);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void charAtReturnsCorrectNumeral() {
        roman.setNumeral("DLIV");
        assertEquals('I', roman.charAt(2));
    }

    @Test
    public void lengthReturnsSymbolsLenghts() {
        roman.setNumeral("III");
        assertEquals(3, roman.length());
    }

    @Test
    public void subSequenceWorks() {
        roman.setNumeral("CCCXXXIII");
        assertEquals("XXX", roman.subSequence(3, 6));
    }
}
