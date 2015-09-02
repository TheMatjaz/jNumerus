/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project Numerus, a roman numerals
 * library for Java. The library and its source code may be found on:
 * https://github.com/MatjazDev/Numerus and http://matjaz.it/numerus/
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.numerus;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * JUnit test of {@link RomanFormatException} which is an exception thrown if a
 * string is not matching the roman numeral syntax.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanFormatExceptionTest {

    private RomanFormatException ex;

    @Test
    public void whenExIsCreatedStringIsStoredInTheMessage() {
        ex = new RomanFormatException("Test message");
        assertEquals("Test message", ex.getMessage());
    }

}
