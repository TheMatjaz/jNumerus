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

import java.util.logging.Level;
import java.util.logging.Logger;
import static it.matjaz.numerus.RomanCalculator.*;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test of the {@link RomanCalculator} static methods to perform
 * arithmetical operations on RomanIntegers.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanCalculatorTest {

    private RomanInteger one;
    private RomanInteger two;
    private RomanInteger result;
    private static final ResourceBundle romanBundle = ResourceBundle.getBundle("RomanBundle", Locale.US);

    private void setRomanIntegers(int one, int two, int result) {
        try {
            this.one = new RomanInteger(one);
            this.two = new RomanInteger(two);
            this.result = new RomanInteger(result);
        } catch (IllegalArabicValueException ex) {
            Logger.getLogger(RomanCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Impossible RomanIntegers: " + ex.getMessage());
        }
    }

    @Test
    public void sumReturnsCorrectSum() throws IllegalArabicValueException {
        setRomanIntegers(3, 4, 7);
        assertEquals(result, sum(one, two));
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenSumIsBiggerThanMaxThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(3999, 30, 0);
        sum(one, two);
    }

    @Test
    public void sumExceptionContainsSpecificMessage() {
        setRomanIntegers(3999, 30, 0);
        try {
            sum(one, two);
        } catch (IllegalArabicValueException ex) {
            assertEquals(romanBundle.getString("TooBigSum"), ex.getMessage());
        }
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenSumIsSmallerThanMinThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(-3999, -30, 0);
        sum(one, two);
    }

    @Test
    public void subtractReturnsCorrectResult() throws IllegalArabicValueException {
        setRomanIntegers(10, 2, 8);
        difference(one, two);
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenSubtractionsIsSmallerThanMinThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(-3999, 100, 0);
        assertEquals(result, difference(one, two));
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenSubtractionsIsBiggerThanMaxThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(3999, -100, 0);
        assertEquals(result, difference(one, two));
    }

    @Test
    public void whenSubtractionsIsSmallerThanZeroThenNothingIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(10, 30, -20);
        assertEquals(result, difference(one, two));
    }

    @Test
    public void subtractionExceptionContainsSpecificMessage() {
        setRomanIntegers(10, 20, 0);
        try {
            difference(one, two);
        } catch (IllegalArabicValueException ex) {
            assertEquals(romanBundle.getString("TooSmallSubtraction"), ex.getMessage());
        }
    }

    @Test
    public void productReturnsCorrectResult() throws IllegalArabicValueException {
        setRomanIntegers(10, 3, 30);
        product(one, two);
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenProductIsBiggerThanMaxThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(10, 1000, 0);
        assertEquals(result, product(one, two));
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenProductIsSmallerThanMinThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(-10, 3000, 0);
        assertEquals(result, product(one, two));
    }

    @Test
    public void productExceptionContainsSpecificMessage() {
        setRomanIntegers(3999, 30, 0);
        try {
            product(one, two);
        } catch (IllegalArabicValueException ex) {
            assertEquals(romanBundle.getString("TooBigProduct"), ex.getMessage());
        }
    }

    @Test
    public void integerDivisionReturnsCorrectResult() throws IllegalArabicValueException {
        setRomanIntegers(11, 2, 5);
        assertEquals(result, intDivision(one, two));
    }

    @Test
    public void integerDivisionDoesNotViolateMinInteger() throws IllegalArabicValueException {
        setRomanIntegers(1, 100, 0);
        assertEquals(result, intDivision(one, two));
    }

    @Test
    public void integerDivisionWithNegativeIsSupported() throws IllegalArabicValueException {
        setRomanIntegers(10, -2, -5);
        assertEquals(result, intDivision(one, two));
    }

    @Test(expected = ArithmeticException.class)
    public void whenDivisionByZeroExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(10, 0, 0);
        intDivision(one, two);
    }

    @Test
    public void intDivisionExceptionContainsSpecificMessage() throws IllegalArabicValueException {
        setRomanIntegers(1, 0, 0);
        try {
            intDivision(one, two);
        } catch (ArithmeticException ex) {
            assertEquals(romanBundle.getString("DivisionByZero"), ex.getMessage());
        }
    }

    @Test
    public void remainderReturnsCorrectValue() throws IllegalArabicValueException {
        setRomanIntegers(5, 2, 1);
        assertEquals(result, remainder(one, two));
    }

    @Test
    public void remainderWithNegativeValuesIsSupported() throws IllegalArabicValueException {
        setRomanIntegers(5, -2, 1);
        assertEquals(result, remainder(one, two));
    }

    @Test
    public void remainderExceptionContainsSpecificMessage() throws IllegalArabicValueException {
        setRomanIntegers(5, 0, 0);
        try {
            remainder(one, two);
        } catch (ArithmeticException ex) {
            assertEquals(romanBundle.getString("DivisionByZero"), ex.getMessage());
        }
    }

    @Test
    public void whenSecondParamterInRemainderIsBiggerThenFirstIsReturned() throws IllegalArabicValueException {
        setRomanIntegers(10, 11, 10 % 11);
        assertEquals(result, remainder(one, two));
    }

    @Test
    public void powerReturnsCorrectValue() throws IllegalArabicValueException {
        setRomanIntegers(10, 2, 100);
        assertEquals(result, power(one, two));
    }

    @Test(expected = IllegalArabicValueException.class)
    public void whenPowerIsBiggerThanMaxThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(10, 1000, 0);
        assertEquals(result, power(one, two));
    }
    
    @Test(expected = IllegalArabicValueException.class)
    public void whenPowerIsSmallerThanMinThenExceptionIsThrown() throws IllegalArabicValueException {
        setRomanIntegers(-10, 1000, 0);
        assertEquals(result, power(one, two));
    }

    @Test
    public void powerExceptionContainsSpecificMessage() {
        setRomanIntegers(3999, 30, 0);
        try {
            power(one, two);
        } catch (IllegalArabicValueException ex) {
            assertEquals(romanBundle.getString("TooBigPower"), ex.getMessage());
        }
    }

}
