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
 * Container for a RomanNumeral and its Integer value tied toghether.
 * <p>
 * Upon initialization or modification of one of the two members, the other gets
 * immediatly updated. The roman numeral is always syntactically correct since
 * it's a RomanNumeral.
 * <p>
 * Uses a private RomanConverter for the conversion of fields.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanInteger {

    private int value;
    private RomanNumeral numeral;
    private final RomanConverter converter;

    public RomanInteger() {
        this.converter = new RomanConverter();
        this.value = 0;
        this.numeral = new RomanNumeral();
    }

    public RomanInteger(int value) {
        this.converter = new RomanConverter();
        this.numeral = converter.integerToRomanNumeral(value);
        this.value = value;
    }

    public RomanInteger(RomanNumeral numeral) {
        if (!numeral.isInitialized()) {
            throw new IllegalArgumentException("Could not create a RomanInteger with uninitialized RomanNumeral.");
        }
        this.converter = new RomanConverter();
        this.value = converter.romanNumeralToInteger(numeral);
        this.numeral = numeral;
    }

    public int getValue() {
        return value;
    }

    public RomanNumeral getNumeral() {
        return numeral;
    }

    public void setValue(int value) {
        this.numeral = converter.integerToRomanNumeral(value);
        this.value = value;
    }

    public void setNumeral(RomanNumeral numeral) {
        if (!numeral.isInitialized()) {
            throw new IllegalArgumentException("Could not set a RomanInteger with uninitialized RomanNumeral.");
        }
        this.numeral = numeral;
        this.value = converter.romanNumeralToInteger(numeral);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.value;
        hash = 29 * hash + Objects.hashCode(this.numeral);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RomanInteger other = (RomanInteger) obj;
        if (this.value != other.getValue()) {
            return false;
        }
        return this.numeral.equals(other.getNumeral());
    }

    @Override
    public String toString() {
        return value + " " + numeral.toString();
    }

}
