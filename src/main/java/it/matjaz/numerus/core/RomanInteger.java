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

import java.io.Serializable;
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
public class RomanInteger extends Number implements Cloneable, Comparable<Number>, Serializable {

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
    private static final RomanConverter converter = new RomanConverter();

    /**
     * Serializable class version number.
     * <p>
     * It is used during deserialization to verify that the sender and receiver
     * of a serialized object have loaded classes for that object that are
     * compatible with respect to serialization.
     * <p>
     * This UID is a date and all objects stored before this date won't be
     * compatible with older ones.
     * [<a href="http://c2.com/ppr/wiki/JavaIdioms/AlwaysDeclareSerialVersionUid.html">Source
     * of the idea</a>]
     *
     * @see Serializable
     */
    private static final long serialVersionUID = 20150428L;

    /**
     * Creates an uninitialized RomanInteger.
     * <p>
     * The int values is set to zero and the roman numeral is costructed with
     * the default {@link RomanNumeral} constructor.
     */
    public RomanInteger() {
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
        this.value = converter.romanNumeralToInteger(numeral);
        this.numeral = numeral;
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

    /**
     * Returns a {@link Object#clone() clone} of this object with the same
     * RomanNumeral and int value.
     * <p>
     * Delegates {@link Object#clone()}.
     *
     * @return a RomanInteger with the same numeral and int value.
     * @throws CloneNotSupportedException when super object is not cloneable.
     * @see Object#clone()
     */
    @Override
    public RomanInteger clone() throws CloneNotSupportedException {
        return (RomanInteger) super.clone();
    }

    /**
     * Returns the value of this RomanInteger as an int by delegating the
     * {@link #getValue() getter}.
     * <p>
     * This method is inherited by the abstract class {@link Number}.
     *
     * @return the int value of this RomanInteger.
     */
    @Override
    public int intValue() {
        return getValue();
    }

    /**
     * Returns the value of this RomanInteger as a long after a widening
     * primitive conversion of the {@link #getValue() getter} return value.
     * <p>
     * This method is inherited by the abstract class {@link Number}.
     *
     * @return the long value of this RomanInteger.
     */
    @Override
    public long longValue() {
        return (long) getValue();
    }

    /**
     * Returns the value of this RomanInteger as a float after a widening
     * primitive conversion of the {@link #getValue() getter} return value.
     * <p>
     * This method is inherited by the abstract class {@link Number}.
     *
     * @return the float value of this RomanInteger.
     */
    @Override
    public float floatValue() {
        return (float) getValue();
    }

    /**
     * Returns the value of this RomanInteger as a double after a widening
     * primitive conversion of the {@link #getValue() getter} return value.
     * <p>
     * This method is inherited by the abstract class {@link Number}.
     *
     * @return the double value of this RomanInteger.
     */
    @Override
    public double doubleValue() {
        return (double) getValue();
    }

    /**
     * Compares this {@code RomanInteger} with the passed Number, {@code other}.
     * <p>
     * The returned int value has the following meanings:
     * <ol>
     * <ul>positive if this RomanInteger is greater than {@code other}</ul>
     * <ul>zero if this is equal to {@code other} (and
     * {@link #equals(java.lang.Object) equals()} returns {@code true})</ul>
     * <ul>negative if this is less than {@code other}</ul>
     * </ol>
     *
     * @param other RomanInteger to be compared to this.
     * @return positive in for this &gt; {@code other}, else negative. Zero if
     * equal.
     */
    @Override
    public int compareTo(Number other) {
        if (this.intValue() < other.intValue()) {
            return -1;
        } else if (this.intValue() > other.intValue()) {
            return 1;
        } else {
            return 0;
        }
    }

}
