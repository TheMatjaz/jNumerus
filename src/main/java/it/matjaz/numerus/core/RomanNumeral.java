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

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A container for syntactically correct roman numerals saved as strings.
 * <p>
 * This class saves a string passed though the constructor or the setter if and
 * only if is a roman number with a correct syntax, which happens if the string
 * matches the {@link #CORRECT_ROMAN_SYNTAX_REGEX}.
 * <p>
 * Any string with other characters, different order, too many characters or
 * anyhow incorrect syntax gets refused with {@link NumberFormatException},
 * which may contain some indication of the syntax error in the Exception
 * message.
 * <p>
 * The structure of a syntactically roman numeral is composed of the following
 * symbols <i>in this order</i>:
 * <ol>
 * <ul>0-3 <b>M</b></ul>
 * <ul>0-1 <b>CM</b> or 0-1 <b>CD</b> or ( 0-1 <b>D</b> and 0-3 <b>C</b> )</ul>
 * <ul>0-1 <b>XC</b> or 0-1 <b>XL</b> or ( 0-1 <b>L</b> and 0-3 <b>X</b> )</ul>
 * <ul>0-1 <b>IX</b> or 0-1 <b>IV</b> or ( 0-1 <b>V</b> and 0-3 <b>I</b> )</ul>
 * </ol>
 * <p>
 * For the integer values of the symbols, see {@link RomanCharMapFactory}.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeral implements Serializable, Cloneable {

    /**
     * The passed string representing the roman numeral with roman symbols.
     * 
     * It is a serializable field.
     */
    private String symbols;

    /**
     * Big regex matching all syntactically correct roman numerals.
     * <p>
     * Contains all possible cases of roman numerals. If a string does not match
     * this regex, then is not a roman numeral. The structure of a syntactically
     * roman numeral is composed of the following symbols <i>in this order</i>:
     * <ol>
     * <ul>0-3 <b>M</b></ul>
     * <ul>0-1 <b>CM</b> or 0-1 <b>CD</b> or ( 0-1 <b>D</b> and 0-3 <b>C</b>
     * )</ul>
     * <ul>0-1 <b>XC</b> or 0-1 <b>XL</b> or ( 0-1 <b>L</b> and 0-3 <b>X</b>
     * )</ul>
     * <ul>0-1 <b>IX</b> or 0-1 <b>IV</b> or ( 0-1 <b>V</b> and 0-3 <b>I</b>
     * )</ul>
     * </ol>
     * <p>
     * <a href="http://stackoverflow.com/a/267405">Source of the idea</a> of
     * this regex with a great explanation.
     */
    public final String CORRECT_ROMAN_SYNTAX_REGEX = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    /**
     * Regex matching any non roman characters.
     * <p>
     * If a string contains any character matching with this regex, then is not
     * a roman numeral, because contains illegal characters.
     */
    public final String NON_ROMAN_CHARS_REGEX = "[^MDCLXVI]";

    /**
     * Regex matching four consecutive characters M or C or X or I.
     */
    private final String FOUR_CONSECUTIVE_TEN_LIKE_CHARS_REGEX = "(MMMM|CCCC|XXXX|IIII)";

    /**
     * Regex matching two characters D or L or V in the same string.
     */
    private final String TWO_SAME_FIVE_LIKE_CHARS_REGEX = "(D.*D|L.*L|V.*V)";

    /**
     * Constructs an empty roman numeral.
     * <p>
     * Contains no value so {@link #setSymbols(java.lang.String) the setter}
     * needs to be used before using the RomanNumeral. Calling this method and
     * then the setter leads to the same result.
     */
    public RomanNumeral() {
        this.symbols = "";
    }

    /**
     * Constructs a roman numeral with initialized value.
     * <p>
     * The passed string gets checked for syntax correctness. If the syntax is
     * illegal, then a {@link NumberFormatException} is thrown.
     * <p>
     * Whitespace charactes in the passed String are removed and the characters
     * are upcased.
     *
     * @param symbols the initial roman numeral to be stored.
     */
    public RomanNumeral(String symbols) {
        String cleanSymbols = cleanSymbolsString(symbols);
        checkRomanSyntax(cleanSymbols);
        this.symbols = cleanSymbols;
    }

    /**
     * Getter of the roman numerals String.
     * <p>
     * If RomanNumeral is not initialized, the returned String is <b>empty</b>.
     *
     * @return a String containing the roman numeral.
     */
    public String getSymbols() {
        return symbols;
    }

    /**
     * Checks if the roman numeal is empty or not.
     * <p>
     * Returns <code>true</code> if this RomanNumeal has a roman numeral stored
     * in it, else <code>false</code> if contains just an empty string. The
     * verification is done by confronting an empty String with the result of
     * the {@link #getSymbols() getter}. The only way it can contain an empty
     * string is to be initialized with the
     * {@link #RomanNumeral() default constructor} (the one without parameters)
     * without setting the value after that with the
     * {@link #setSymbols(java.lang.String) setter}.
     *
     * @return <code>true</code> has a roman numeral stored in it, else
     * <code>false</code> if it's empty.
     */
    public boolean isInitialized() {
        return !"".equals(getSymbols());
    }

    /**
     * Setter of the roman numerals String.
     * <p>
     * The passed string gets checked for syntax correctness. If the syntax is
     * illegal, then a {@link NumberFormatException} is thrown.
     * <p>
     * Whitespace charactes in the passed String are removed and the characters
     * are upcased.
     *
     * @param symbols the new roman numerals to be stored
     */
    public void setSymbols(String symbols) {
        String cleanSymbols = cleanSymbolsString(symbols);
        checkRomanSyntax(cleanSymbols);
        this.symbols = cleanSymbols;
    }

    /**
     * Performs a check of the syntax of the given roman numeral without storing
     * it in a RomanNumeral.
     * <p>
     * Returns <code>true</code> if the passed String matches the syntax of
     * roman numerals; else <code>false</code>. Useful to just quickly check if
     * a String is a roman numeral when an instance of it is not needed. If the
     * result is <code>true</code>, then the passed String can be successfully
     * stored in a RomanNumeral by
     * {@link #RomanNumeral(java.lang.String) constructor} or
     * {@link #setSymbols(java.lang.String) setter}.
     *
     * @param numeralsToCheck the String to check the roman syntax on.
     * @return <code>true</code> if the passed String is a roman numeral; else
     * <code>false</code>.
     */
    public static boolean isCorrectRomanSyntax(String numeralsToCheck) {
        try {
            String cleanNumerals = cleanSymbolsString(numeralsToCheck);
            new RomanNumeral().checkRomanSyntax(cleanNumerals);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Removes all whitespace characters from the given string and transforms
     * all characters to uppercase.
     *
     * @param symbols string to be cleaned and upcased.
     * @return given string without whitespaces and upcased.
     */
    private static String cleanSymbolsString(String symbols) {
        return symbols.replaceAll("\\s+", "").toUpperCase();
    }

    /**
     * Finds all the substrings of the given string matching the regex.
     * <p>
     * Check for all substring of <code>textToParse</code> matching the
     * <code>regex</code>, appends them sequentially to a String and returns it.
     * <p>
     * <a href="http://stackoverflow.com/a/5705591">Source of the idea</a> of
     * the algorithm.
     *
     * @param textToParse String to be checked for matches.
     * @param regex to be searched in <code>textToParse</code>
     * @return a String containing all the matches.
     */
    private static String findAllInternalMatches(String textToParse, String regex) {
        StringBuilder matches = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(textToParse);
        while (matcher.find()) {
            matches.append(matcher.group());
        }
        return matches.toString();
    }

    private void checkRomanSyntax(String symbols) {
        if (symbols.isEmpty()) {
            throw new NumberFormatException("Empty roman numeral.");
        }
        if (symbols.length() >= 20) {
            throw new NumberFormatException("Impossibly long roman numeral.");
        }
        if (!symbols.matches(CORRECT_ROMAN_SYNTAX_REGEX)) {
            String illegalChars;
            illegalChars = findAllInternalMatches(symbols, NON_ROMAN_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                throw new NumberFormatException("Non roman characters: " + illegalChars);
            }
            illegalChars = findAllInternalMatches(symbols, FOUR_CONSECUTIVE_TEN_LIKE_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                throw new NumberFormatException("Four consecutive: " + illegalChars);
            }
            illegalChars = findAllInternalMatches(symbols, TWO_SAME_FIVE_LIKE_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                throw new NumberFormatException("Two D, L or V same characters: " + illegalChars);
            }
            throw new NumberFormatException("Generic roman numeral syntax error.");
        }
    }

    /**
     * Returns the hash of this object.
     * <p>
     * Uses {@link Objects#hashCode(java.lang.Object)} and overrides
     * {@link Object#hashCode()}.
     *
     * @return the hash of this object.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.symbols);
        return hash;
    }

    /**
     * Verifies if the passed Object is equal to this.
     *
     * Returns <code>true</code> if the passed Object is the same as this, else
     * <code>false</code>.
     *
     * @return a boolean telling if the two objects are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RomanNumeral other = (RomanNumeral) obj;
        return Objects.equals(this.symbols, other.getSymbols());
    }

    /**
     * Returns a string representation of this object, which is the roman
     * numeral stored in it.
     * <p>
     * This method is a delegate method and just calls {@link #getSymbols()} so
     * the returned string is exactly the same for both. This method is here for
     * compatibility reasons.
     *
     * @return a String containing the roman numeral.
     */
    @Override
    public String toString() {
        return getSymbols();
    }

    /**
     * Returns a {@link Object#clone() clone} of this object with the same
     * numeral.
     * <p>
     * The original and the RomanNumeral store an equal roman numeral and
     * applying an {@link #equals(java.lang.Object) equals() } method to them,
     * will result <code>true</code>.
     * <p>
     * Since the only field of RomanNumeral is a String, the
     * CloneNotSupportedException should never raise.
     *
     * @return a RomanNumeral with the same numeral.
     * @throws CloneNotSupportedException when super object is not cloneable.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return (RomanNumeral) super.clone();
    }
}
