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

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Simple command line REPL shell for conversions from and to roman numerals.
 *
 * Simply write the arabic or roman numeral, press enter and it will be
 * converted to the other form. Write "help" or "?" for other assistance.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 * @see RomanConverter
 */
public class RomanRepl {

    private final Scanner keyboardScanner;
    private final RomanConverter converter;
    private String inputLine;
    private int arabicInput;
    private boolean exitFromRepl;

    /**
     * Default ResourceBundle containing english strings.
     */
    private static final ResourceBundle romanBundle = ResourceBundle.getBundle("RomanBundle", Locale.US);

    /**
     * Constructs a Numerus REPL, a shell in which the numbers could be
     * converted and some other commands can be called.
     *
     * After construction, start the REPL it with the {@code start()} method.
     */
    public RomanRepl() {
        this.keyboardScanner = new Scanner(System.in);
        this.converter = new RomanConverter();
        this.exitFromRepl = false;
    }

    /**
     * Starts the REPL, listening on {@code System.in} and printing to
     * {@code System.out}.
     *
     * For a command reference, type '{@code ?}'.
     */
    public void start() {
        System.out.println(romanBundle.getString("WelcomeText"));
        while (!exitFromRepl) {
            System.out.print(romanBundle.getString("Prompt"));
            this.inputLine = keyboardScanner.nextLine().trim().toLowerCase();
            interpreteCommand();
        }
    }

    private void interpreteCommand() {
        switch (inputLine) {
            case "?":
            case "help": {
                System.out.println(romanBundle.getString("HelpText"));
                break;
            }

            case "moo": {
                System.out.println(romanBundle.getString("MooText"));
                break;
            }
            
            case "ascii": {
                System.out.println(romanBundle.getString("AsciiArtText"));
                break;
            }

            case "about":
            case "info": {
                System.out.println(romanBundle.getString("InfoText"));
                break;
            }

            case "syntax": {
                System.out.println(romanBundle.getString("SyntaxText"));
                break;
            }

            case "quit":
            case "exit": {
                this.exitFromRepl = true;
                System.out.println(romanBundle.getString("ExitText"));
                break;
            }

            case "": {
                break;
            }

            case "all": {
                System.out.println(romanBundle.getString("AllText"));
                break;
            }

            default: {
                convertToOtherForm();
                break;
            }
        }
    }

    /**
     * Verifies if the REPL input is an arabic number that can be parsed to an
     * Integer and stores it into this.arabicInput for further conversion.
     *
     * @return {@code true} if is a correctly formatted arabic integer, else
     * {@code false}.
     */
    private boolean isArabic() {
        boolean isArabic = true;
        try {
            this.arabicInput = Integer.parseInt(inputLine);
        } catch (NumberFormatException notIntegerEx) {
            try {
                this.arabicInput = (int) Double.parseDouble(inputLine);
            } catch (NumberFormatException notArabicEx) {
                isArabic = false;
            }
        }
        return isArabic;
    }

    /**
     * Tries to convert the REPL input from an arabic integer to a roman numeral
     * or vice-versa.
     *
     * Note: the roman to arabic conversion does not not a priori if the input
     * is a roman numeral or only a random string, like a wrong command; the
     * arabic to roman conversion on the other hand has already parsed the input
     * and verified it.
     */
    private void convertToOtherForm() {
        if (isArabic()) {
            try {
                System.out.println(converter.integerToRomanNumeral(arabicInput));
            } catch (IllegalArabicValueException arabicOutOfRangeEx) {
                System.out.println(arabicOutOfRangeEx.getMessage());
            }
        } else {
            try {
                System.out.println(converter.romanNumeralToInteger(new RomanNumeral(inputLine)));
            } catch (IllegalNumeralSyntaxException wrongRomanSyntaxEx) {
                System.out.println(wrongRomanSyntaxEx.getMessage());
            }
        }
    }

}
