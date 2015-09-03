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

/**
 * Checked exception thrown to indicate that the arabic Integer values to to be
 * converted into roman numerals is out of range of possible roman numbers'
 * values.
 *
 * Using standard roman syntax, the values should be Integers withing [0, 3999].
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 * @see RomanException
 * @see RomanConverter
 * @see RomanInteger
 */
public class IllegalArabicValueException extends RomanException {

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
    public IllegalArabicValueException(String message) {
        super(message);
    }

    /**
     * Simply delegates the correspondent {@link RomanException} constructor.
     *
     * @param message
     * @param cause
     * @see RomanException#RomanException(java.lang.String, java.lang.Throwable)
     */
    public IllegalArabicValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Simply delegates the correspondent {@link RomanException} constructor.
     *
     * @param cause
     * @see RomanException#RomanException(java.lang.String, java.lang.Throwable)
     */
    public IllegalArabicValueException(Throwable cause) {
        super(cause);
    }

}
