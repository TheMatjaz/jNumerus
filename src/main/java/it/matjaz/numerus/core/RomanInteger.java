/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project Numerus, a roman numeral
 * library for Java. The library and its source code may be found on:
 * https://github.com/MatjazDev/Numerus and http://matjaz.it/numerus
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.numerus.core;

import java.util.Objects;

/**
 * Container for a {@link RomanNumeral} and its Integer value tied toghether.
 * <p>
 * This class keeps an {@link Integer} value and its representation in roman
 * numerals as a {@link RomanNumeral} always correctly correlated with each
 * other. Upon initialization or modification of one of the two members, the
 * other gets immediatly updated. The roman numeral is always syntactically
 * correct since it's a RomanNumeral.
 * <p>
 * Uses a private {@link RomanConverter} for the conversion of the fields.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanInteger {

    /**
     * The arabic value of the roman numeral.
     */
    private int value;

    /**
     * The representation of the value in roman numerals.
     */
    private RomanNumeral numeral;

    /**
     * The converter used to switch from roman numerals to integers.
     */
    private final RomanConverter converter;

    /**
     * Creates an uninitialized RomanInteger.
     * <p>
     * The int values is set to zero and the roman numeral is costructed with
     * the default {@link RomanNumeral} constructor.
     */
    public RomanInteger() {
        this.converter = new RomanConverter();
        this.value = 0;
        this.numeral = new RomanNumeral();
    }

    /**
     * Creates a RomanInteger using the given int value.
     * <p>
     * The value gets immediatly converted to its equivalent
     * {@link RomanNumeral}. If its out of range, and exception is thrown by the
     * {@link RomanConverter} and the object is not constructed.
     *
     * @param value of the RomanInteger
     */
    public RomanInteger(int value) {
        this.converter = new RomanConverter();
        setValueAndNumeral(value);
    }

    /**
     * Creates a RomanInteger using the given {@link RomanNumeral}.
     * <p>
     * The {@link RomanNumeral} gets immediatly converted to its equivalent int
     * values. If the {@link RomanNumeral} is not initialized, an
     * {@link IllegalArgumentException} is thrown and the object is not
     * constructed.
     *
     * @param numeral in roman numerals of the RomanInteger
     */
    public RomanInteger(RomanNumeral numeral) {
        if (!numeral.isInitialized()) {
            throw new IllegalArgumentException("Could not create a RomanInteger with uninitialized RomanNumeral.");
        }
        this.converter = new RomanConverter();
        setNumeralAndValue(numeral);
    }

    /**
     * Private fields setter with int value used in constructors and setters.
     * <p>
     * Made for code reusal.
     *
     * @param value to be set along with its equivalent RomanNumeral.
     * @see #setValue(int)
     * @see #RomanInteger(int)
     */
    private void setValueAndNumeral(int value) {
        this.numeral = converter.integerToRomanNumeral(value);
        this.value = value;
    }

    /**
     * Private fields setter with RomanNumeral used in constructors and setters.
     * <p>
     * Made for code reusal.
     *
     * @param numeral to be set along with its value.
     * @see #setNumeral(it.matjaz.numerus.core.RomanNumeral)
     * @see #RomanInteger(it.matjaz.numerus.core.RomanNumeral)
     */
    private void setNumeralAndValue(RomanNumeral numeral) {
        this.numeral = numeral;
        this.value = converter.romanNumeralToInteger(numeral);
    }

    /**
     * Getter of the arabic int value.
     *
     * @return the int value of this RomanInteger.
     */
    public int getValue() {
        return value;
    }

    /**
     * Getter of the {@link RomanNumeral}.
     *
     * @return the representation of this RomanInteger in roman numerals.
     */
    public RomanNumeral getNumeral() {
        return numeral;
    }

    /**
     * Setter of this RomanInteger with the int value.
     * <p>
     * The value gets immediatly converted to its equivalent
     * {@link RomanNumeral}. If its out of range, and exception is thrown by the
     * {@link RomanConverter} and the object is not modified.
     *
     * @param value of this RomanInteger.
     */
    public void setValue(int value) {
        setValueAndNumeral(value);
    }

    /**
     * Setter of this RomanInteger using the given {@link RomanNumeral}.
     * <p>
     * The {@link RomanNumeral} gets immediatly converted to its equivalent int
     * values. If the {@link RomanNumeral} is not initialized, an
     * {@link IllegalArgumentException} is thrown and the object is not
     * modified.
     *
     * @param numeral in roman numerals of this RomanInteger.
     */
    public void setNumeral(RomanNumeral numeral) {
        if (!numeral.isInitialized()) {
            throw new IllegalArgumentException("Could not set a RomanInteger with uninitialized RomanNumeral.");
        }
        setNumeralAndValue(numeral);
    }

    /**
     * Returns the hash of this RomanInteger.
     * <p>
     * The hashcode is created using the int value and the RomanNumeral. Uses
     * {@link Objects#hashCode(java.lang.Object)} and overrides
     * {@link Object#hashCode()}.
     *
     * @return the hash of this RomanInteger.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.value;
        hash = 29 * hash + Objects.hashCode(this.numeral);
        return hash;
    }

    /**
     * Verifies if the passed Object is equal to this.
     * <p>
     * Returns <code>true</code> if the passed Object is a RomanInteger and
     * contains the same roman numerals String and Integer as this one, else
     * <code>false</code>.
     *
     * @param otherRomanInteger to compare with this.
     * @return a boolean telling if the two RomanIntegers are equal.
     * @see Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherRomanInteger) {
        if (otherRomanInteger == null) {
            return false;
        }
        if (getClass() != otherRomanInteger.getClass()) {
            return false;
        }
        final RomanInteger other = (RomanInteger) otherRomanInteger;
        if (this.value != other.getValue()) {
            return false;
        }
        return this.numeral.equals(other.getNumeral());
    }

    /**
     * Returns a String representation of this RomanInteger, which is its
     * Integer value followed by the roman numeral String.
     *
     * @return a String containing the value and roman numeral of this
     * RomanInteger.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return value + " " + numeral.toString();
    }

}
