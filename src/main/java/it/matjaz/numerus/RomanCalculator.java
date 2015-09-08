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

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Container of static methods to perform arithmetic operations between
 * RomanIntegers.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanCalculator {

    /**
     * Private constructor to prevent instantiation.
     */
    private RomanCalculator() {
    }

    /**
     * Default ResourceBundle containing english strings.
     */
    private static final ResourceBundle romanBundle = ResourceBundle.getBundle("RomanBundle", Locale.US);

    /**
     * Returns a RomanInteger containing the sum of two RomanIntegers.
     *
     * Throws an exception if the sum is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     *
     * @param one first RomanInteger to be summed.
     * @param two second RomanInteger to be summed.
     * @return a RomanInteger containing the sum.
     * @throws IllegalArabicValueException if the sum is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     */
    public static RomanInteger sum(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() + two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooBigSum"));
        }
    }

    /**
     * Returns a RomanInteger containing the difference of two RomanIntegers.
     *
     * Throws an exception if the difference is smaller than
     * {@link RomanInteger#MININTEGER}.
     *
     * @param one RomanInteger to be subtracted from.
     * @param two RomanInteger value to be subtracted from {@code one}.
     * @return a RomanInteger containing the difference.
     * @throws IllegalArabicValueException if the difference is smaller than
     * {@link RomanInteger#MININTEGER}.
     */
    public static RomanInteger difference(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() - two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooSmallSubtraction"));
        }
    }

    /**
     * Returns a RomanInteger containing the product of two RomanIntegers.
     *
     * Throws an exception if the product is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     *
     * @param one first RomanInteger of the product.
     * @param two first RomanInteger to be multiplied with the {@code one}.
     * @return a RomanInteger containing the product.
     * @throws IllegalArabicValueException if the product is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     */
    public static RomanInteger product(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() * two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooBigProduct"));
        }
    }

    /**
     * Returns a RomanInteger containing the integer division of two
     * RomanIntegers.
     *
     * It's and integer division so it gets floored. As an example, 5/2 = 2, not
     * 2.5.
     *
     * Throws an exception if the second argument, the divider, is
     * {@link RomanInteger#NULLA}.
     *
     * @param one RomanInteger to be divided.
     * @param two RomanInteger that divides.
     * @return a RomanInteger containing the integer division.
     * @throws ArithmeticException if {@code two} is {@link RomanInteger#NULLA}.
     */
    public static RomanInteger intDivision(RomanInteger one, RomanInteger two) {
        try {
            return new RomanInteger(one.getValue() / two.getValue());
        } catch (ArithmeticException ex) {
            throw new ArithmeticException(romanBundle.getString("DivisionByZero"));
        } catch (IllegalArabicValueException ex) {
            Logger.getLogger(RomanConverter.class.getName()).log(Level.SEVERE, null, ex);
            String message = MessageFormat.format(romanBundle.getString("CalculatorInternalErrorDivision"), one, two, ex.getMessage());
            throw new RuntimeException(message);
        }
    }

    /**
     * Returns a RomanInteger containing the remainder of the division of two
     * RomanIntegers.
     *
     * Throws an exception if the second argument, the divider, is
     * {@link RomanInteger#NULLA}.
     *
     * @param one RomanInteger to be divided.
     * @param two RomanInteger that divides.
     * @return a RomanInteger containing the remainder of the division.
     * @throws ArithmeticException if {@code two} is {@link RomanInteger#NULLA}.
     */
    public static RomanInteger remainder(RomanInteger one, RomanInteger two) {
        try {
            return new RomanInteger(one.getValue() % two.getValue());
        } catch (ArithmeticException ex) {
            throw new ArithmeticException(romanBundle.getString("DivisionByZero"));
        } catch (IllegalArabicValueException ex) {
            Logger.getLogger(RomanConverter.class.getName()).log(Level.SEVERE, null, ex);
            String message = MessageFormat.format(romanBundle.getString("CalculatorInternalErrorRemainder"), one, two, ex.getMessage());
            throw new RuntimeException(message);
        }
    }

    /**
     * Returns a RomanInteger containing the power of two RomanIntegers.
     *
     * Throws an exception if the product is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     *
     * @param one first RomanInteger as the base.
     * @param two first RomanInteger as the exponent.
     * @return a RomanInteger containing the exponantiation.
     * @throws IllegalArabicValueException if the result is bigger than
     * {@link RomanInteger#MAXINTEGER}.
     * @see Math#pow(double, double) on which the method is based.
     */
    public static RomanInteger power(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger((int) Math.pow(one.getValue(), two.getValue()));
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooBigPower"));
        }
    }
}
