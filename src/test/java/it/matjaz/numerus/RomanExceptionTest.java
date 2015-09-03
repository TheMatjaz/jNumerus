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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * JUnit test of {@link RomanException} which is a generic exception for the
 * Numerus classes.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanExceptionTest {

    private RomanException ex;

    @Test
    public void whenExIsCreatedStringIsStoredInTheMessage() {
        ex = new RomanException("Test message");
        assertEquals("Test message", ex.getMessage());
    }

    @Test
    public void RomanExceptionNotExtendsRuntimeException() {
        ex = new RomanException("Hello!");
        boolean catched = false;
        boolean isRuntime = false;
        try {
            throw ex;
        } catch (RuntimeException e) {
            isRuntime = true;
        } catch (RomanException e) {
            catched = true;
        }
        assertTrue(catched);
        assertFalse(isRuntime);
    }

}
