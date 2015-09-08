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

import java.util.Locale;
import java.util.ResourceBundle;

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

    public static RomanInteger sum(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() + two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooBigSum"));
        }
    }

    public static RomanInteger subtract(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() - two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooSmallSubtraction"));
        }
    }

    public static RomanInteger product(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() * two.getValue());
        } catch (IllegalArabicValueException ex) {
            throw new IllegalArabicValueException(romanBundle.getString("TooBigProduct"));
        }
    }

    public static RomanInteger intDivision(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() / two.getValue());
        } catch (ArithmeticException ex) {
            throw new ArithmeticException(romanBundle.getString("DivisionByZero"));
        }
    }

    public static RomanInteger remainder(RomanInteger one, RomanInteger two) throws IllegalArabicValueException {
        try {
            return new RomanInteger(one.getValue() % two.getValue());
        } catch (ArithmeticException ex) {
            throw new ArithmeticException(romanBundle.getString("DivisionByZero"));
        }
    }
}
