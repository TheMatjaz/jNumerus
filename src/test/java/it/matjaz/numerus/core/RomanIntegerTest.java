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
    public void constructorWithIntParameterInitializedBothFields() {
        roman = new RomanInteger(42);
        assertNotEquals(roman.getValue(), 0);
        assertNotNull(roman.getNumeral());
    }
    
    
    

}
