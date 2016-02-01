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

/**
 * Checked exception thrown to indicate that the string parsed by
 * {@link RomanNumeral} does not contain a syntactically correct roman numeral.
 *
 * The correct syntax regex may be found at {@link RomanNumeral#CORRECT_ROMAN_SYNTAX_REGEX
 * }.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 * @see RomanNumeral#CORRECT_ROMAN_SYNTAX_REGEX
 * @see RomanException
 */
public class IllegalNumeralSyntaxException extends RomanException {

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
    private static final long serialVersionUID = 20150903L;

    /**
     * Simply delegates the correspondent {@link RomanException} constructor.
     *
     * @param message
     * @see RomanException#RomanException(java.lang.String, java.lang.Throwable)
     */
    public IllegalNumeralSyntaxException(String message) {
        super(message);
    }

    /**
     * Simply delegates the correspondent {@link RomanException} constructor.
     *
     * @param message
     * @param cause
     * @see RomanException#RomanException(java.lang.String, java.lang.Throwable)
     */
    public IllegalNumeralSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Simply delegates the correspondent {@link RomanException} constructor.
     *
     * @param cause
     * @see RomanException#RomanException(java.lang.String, java.lang.Throwable)
     */
    public IllegalNumeralSyntaxException(Throwable cause) {
        super(cause);
    }

}
