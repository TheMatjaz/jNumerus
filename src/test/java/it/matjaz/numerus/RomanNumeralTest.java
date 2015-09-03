/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project Numerus, a roman numerals
 * library for Java. The library and its source code may be found on:
 * https://github.com/TheMatjaz/Numerus and http://matjaz.it/numerus/
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.numerus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test of {@link RomanNumeral} which is a container for syntactically
 * correct roman numerals.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
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
    public void whenCorrectStringIsGivenThenNoExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("XLII");
        assertEquals("XLII", roman.getNumeral());
    }

    @Test(expected = NullPointerException.class)
    public void whenNullStringIsGivenThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral(null);
    }

    @Test
    public void givenStringGetsStrippedAndUpcased() throws IllegalNumeralSyntaxException {
        roman.setNumeral("  \t\n\r   xliI ");
        assertEquals("XLII", roman.getNumeral());
    }

    @Test
    public void givenStringGetsStrippedOfInnerWhiteSpaceChars() throws IllegalNumeralSyntaxException {
        roman.setNumeral("  XL I  II");
        assertEquals("XLIII", roman.getNumeral());
    }

    @Test(expected = IllegalNumeralSyntaxException.class)
    public void whenEmptyStringIsGivenThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("");
    }

    @Test(expected = IllegalNumeralSyntaxException.class)
    public void whenWhitespaceStringIsGivenThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("  \t\n\r  ");
    }

    @Test(expected = IllegalNumeralSyntaxException.class)
    public void whenImpossiblyLongStringIsGivenThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }

    @Test(expected = IllegalNumeralSyntaxException.class)
    public void whenStringContainsNonRomanCharactersThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("pFXC-");
    }

    @Test
    public void whenStringContainsNonRomanCharactersThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("pPFXC-");
        } catch (IllegalNumeralSyntaxException ex) {
            assertTrue(ex.getMessage().contains("pPF-".toUpperCase()));
        }
    }

    @Test(expected = IllegalNumeralSyntaxException.class)
    public void whenStringWithIncorrectTomanSyntaxIsGivenThenExceptionIsThrown() throws IllegalNumeralSyntaxException {
        roman.setNumeral("MMCMIIIX");
    }

    @Test
    public void whenStringContainsMoreThanThreeConsecutiveTenLikeSymbolsThenThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("CCCC");
        } catch (IllegalNumeralSyntaxException ex) {
            assertTrue(ex.getMessage().contains("CCCC"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem() {
        try {
            roman.setNumeral("DDXII");
        } catch (IllegalNumeralSyntaxException ex) {
            assertTrue(ex.getMessage().contains("DD"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem2() {
        try {
            roman.setNumeral("DXID");
        } catch (IllegalNumeralSyntaxException ex) {
            assertTrue(ex.getMessage().contains("DXID"));
        }
    }

    @Test
    public void nullaStringIsAcceptedByParamtericConstructor() throws IllegalNumeralSyntaxException {
        roman = new RomanNumeral("NULLA");
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void nullaStringIsAcceptedByGetter() throws IllegalNumeralSyntaxException {
        roman.setNumeral("NULLA");
        assertEquals(roman.getNumeral(), "NULLA");
    }

    @Test
    public void nullaStringGetsUpcased() throws IllegalNumeralSyntaxException {
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
    public void toStringDelegatesGetter() throws IllegalNumeralSyntaxException {
        roman.setNumeral("MCMLXIV");
        assertEquals(roman.getNumeral(), roman.toString());
    }

    @Test
    public void equalsMethodWorksh() throws IllegalNumeralSyntaxException {
        RomanNumeral numeral1 = new RomanNumeral("MCMLXIV");
        RomanNumeral numeral2 = new RomanNumeral("MCMLXIV");
        assertTrue(numeral1.equals(numeral2));
    }

    @Test
    public void defaultConstructorAndSetterIsTheSameAsInitializingConstructor() throws IllegalNumeralSyntaxException {
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
    public void isNullaReturnsFalseIfIsInitialized() throws IllegalNumeralSyntaxException {
        roman.setNumeral("C");
        assertFalse(roman.isNulla());
    }

    @Test
    public void serializabilityWorksBothWays() throws IllegalNumeralSyntaxException {
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
    public void romanNumeralIsCloneable() throws IllegalNumeralSyntaxException {
        roman.setNumeral("DXI");
        try {
            RomanNumeral otherRoman = roman.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void clonedRomanNumeralEqualsOriginalOne() throws IllegalNumeralSyntaxException {
        roman.setNumeral("DXI");
        RomanNumeral otherRoman;
        try {
            otherRoman = roman.clone();
            assertEquals(roman, otherRoman);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(RomanNumeralTest.class.getName()).log(Level.SEVERE, null, ex);
            fail();
        }
    }

    @Test
    public void charAtReturnsCorrectNumeral() throws IllegalNumeralSyntaxException {
        roman.setNumeral("DLIV");
        assertEquals('I', roman.charAt(2));
    }

    @Test
    public void lengthReturnsSymbolsLenghts() throws IllegalNumeralSyntaxException {
        roman.setNumeral("III");
        assertEquals(3, roman.length());
    }

    @Test
    public void subSequenceWorks() throws IllegalNumeralSyntaxException {
        roman.setNumeral("CCCXXXIII");
        assertEquals("XXX", roman.subSequence(3, 6));
    }
    
}
