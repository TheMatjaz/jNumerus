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
import it.matjaz.jnumerus.IllegalNumeralSyntaxException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * JUnit test of {@link IllegalNumeralSyntaxException} which is an exception
 * thrown if a string is not matching the roman numeral syntax.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class IllegalNumeralSyntaxExceptionTest {

    private IllegalNumeralSyntaxException ex;

    @Test
    public void whenExIsCreatedStringIsStoredInTheMessage() {
        ex = new IllegalNumeralSyntaxException("Test message");
        assertEquals("Test message", ex.getMessage());
    }

    @Test
    public void IllegalNumeralSyntaxExceptionExtendsRomanException() {
        ex = new IllegalNumeralSyntaxException("Hello!");
        boolean catched = false;
        try {
            throw ex;
        } catch (RomanException e) {
            catched = true;
        }
        assertTrue(catched);
    }
}
