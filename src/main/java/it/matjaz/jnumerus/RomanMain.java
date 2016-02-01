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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * jNumerus main class.
 *
 * Performs no other action than creating and starting a RomanRepl.
 *
 * Full path: {@code src/main/java/it/matjaz/jnumerus/RomanMain.java} or
 * {@code it.matjaz.jnumerus.RomanMain}
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanMain {

    /**
     * Default ResourceBundle containing english strings.
     */
    private static final ResourceBundle romanBundle = ResourceBundle.getBundle("RomanBundle", Locale.US);

    /**
     * Starts the jNumerus project.
     *
     * Starts a REPL to interact with the conversion tools. Alternatively, if
     * any command line arguments are passed, tries to interprete theme without
     * starting an interactive jNumerus shell.
     *
     * @param args the numbers to convert or REPL commands to execute
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            interpreteCommandLineArguments(args);
        } else {
            RomanRepl repl = new RomanRepl();
            repl.start();
        }
    }

    /**
     * Creates a REPL from which calls the input interpreter for each passed
     * argument without creating an interactive shell waiting for input.
     *
     * @param args the numbers to convert or REPL commands to execute
     */
    private static void interpreteCommandLineArguments(String[] args) {
        RomanRepl repl = new RomanRepl();
        for (String command : args) {
            System.out.println(romanBundle.getString("Prompt") + command);
            repl.interpreteSingleCommand(command);
        }
    }

}
