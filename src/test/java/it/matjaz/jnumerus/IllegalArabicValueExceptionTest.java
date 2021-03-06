/*
 * Copyright (c) 2015, Matjaž <dev@matjaz.it> matjaz.it
 *
 * This Source Code Form is part of the project jNumerus, a roman numerals
 * library for Java. The library and its source code may be found on:
 * https://github.com/TheMatjaz/jNumerus/
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/
 */
package it.matjaz.jnumerus;

import it.matjaz.jnumerus.RomanException;
import it.matjaz.jnumerus.IllegalArabicValueException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * JUnit test of {@link IllegalArabicValueException} which is an exception
 * thrown if an arabic is out of range for conversion to roman numerals.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class IllegalArabicValueExceptionTest {

    private IllegalArabicValueException ex;

    @Test
    public void whenExIsCreatedStringIsStoredInTheMessage() {
        ex = new IllegalArabicValueException("Test message");
        assertEquals("Test message", ex.getMessage());
    }

    @Test
    public void IllegalArabicValueExceptionExtendsRomanException() {
        ex = new IllegalArabicValueException("Hello!");
        boolean catched = false;
        try {
            throw ex;
        } catch (RomanException e) {
            catched = true;
        }
        assertTrue(catched);
    }

}
