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
 * Numerus main class.
 *
 * Performs no other action than creating and starting a RomanRepl.
 *
 * Full path: {@code src/main/java/it/matjaz/numerus/RomanMain.java} or
 * {@code it.matjaz.numerus.RomanMain}
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanMain {

    /**
     * Starts the Numerus project.
     *
     * At the moment accepts no arguments and simply starts a REPL to interact
     * with the conversion tools.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RomanRepl repl = new RomanRepl();
        repl.start();
    }

}
