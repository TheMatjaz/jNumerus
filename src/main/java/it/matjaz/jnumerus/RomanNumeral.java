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

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
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
 * anyhow incorrect syntax gets refused with
 * {@link IllegalNumeralSyntaxException}, which may contain some indication of
 * the syntax error in the Exception message.
 * <p>
 * The structure of a syntactically roman numeral is composed of the following
 * characters <i>in this order</i>:
 * <ol>
 * <ul>0-3 <b>M</b></ul>
 * <ul>0-1 <b>CM</b> or 0-1 <b>CD</b> or ( 0-1 <b>D</b> and 0-3 <b>C</b> )</ul>
 * <ul>0-1 <b>XC</b> or 0-1 <b>XL</b> or ( 0-1 <b>L</b> and 0-3 <b>X</b> )</ul>
 * <ul>0-1 <b>IX</b> or 0-1 <b>IV</b> or ( 0-1 <b>V</b> and 0-3 <b>I</b> )</ul>
 * <ul>only <b>NULLA_STRING</b> instead of any other symbol.</ul>
 * </ol>
 * <p>
 * For the integer values of the symbols, see {@link RomanCharMapFactory}.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanNumeral implements Serializable, Cloneable, CharSequence {

    /**
     * The passed string representing the roman numeral.
     *
     * It is a serializable field.
     */
    private String numeral;

    /**
     * Big regex matching all syntactically correct roman numerals.
     * <p>
     * Contains all possible cases of roman numerals. If a string does not match
     * this regex, then is not a roman numeral. The structure of a syntactically
     * roman numeral is composed of the following numeral <i>in this order</i>:
     * <ol>
     * <ul>0-3 <b>M</b></ul>
     * <ul>0-1 <b>CM</b> or 0-1 <b>CD</b> or ( 0-1 <b>D</b> and 0-3 <b>C</b>
     * )</ul>
     * <ul>0-1 <b>XC</b> or 0-1 <b>XL</b> or ( 0-1 <b>L</b> and 0-3 <b>X</b>
     * )</ul>
     * <ul>0-1 <b>IX</b> or 0-1 <b>IV</b> or ( 0-1 <b>V</b> and 0-3 <b>I</b>
     * )</ul>
     * <ul>only <b>NULLA_STRING</b> instead of any other symbol.</ul>
     * </ol>
     * <p>
     * <a href="http://stackoverflow.com/a/267405">Source of the idea</a> of
     * this regex with a great explanation.
     */
    public static final String CORRECT_ROMAN_SYNTAX_REGEX = "^-?(NULLA)|-?((M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3}))$";

    /**
     * Regex matching any non roman characters.
     * <p>
     * If a string contains any character matching with this regex, then is not
     * a roman numeral, because contains illegal characters.
     */
    public static final String NON_ROMAN_CHARS_REGEX = "[^-MDCLXVI]";

    /**
     * Regex matching four consecutive characters M or C or X or I.
     */
    private static final String FOUR_CONSECUTIVE_TEN_LIKE_CHARS_REGEX = "(MMMM|CCCC|XXXX|IIII)";

    /**
     * Regex matching two characters D or L or V in the same string.
     */
    private static final String TWO_SAME_FIVE_LIKE_CHARS_REGEX = "(D.*D|L.*L|V.*V)";

    /**
     * String indicating the roman numeral with value zero, 0.
     */
    public static final String NULLA_STRING = "NULLA";

    /**
     * Default ResourceBundle containing english strings.
     */
    private static final ResourceBundle romanBundle = ResourceBundle.getBundle("RomanBundle", Locale.US);

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
    private static final long serialVersionUID = 20150908L;

    /**
     * Constructs a RomanNumeral initialized to NULLA_STRING.
     * <p>
     * Contains <b>"NULLA_STRING"</b> numerals indicating zero value.
     */
    public RomanNumeral() {
        this.numeral = NULLA_STRING;
    }

    /**
     * Constructs a RomanNumeral initialized with the given value.
     * <p>
     * The passed string gets checked for syntax correctness. If the syntax is
     * illegal, then a {@link IllegalNumeralSyntaxException} is thrown.
     * <p>
     * Whitespace charactes in the passed String are removed and the characters
     * are upcased.
     *
     * @param symbols the initial roman numeral to be stored.
     * @throws IllegalNumeralSyntaxException when string has illegal roman
     * syntax.
     */
    public RomanNumeral(String symbols) throws IllegalNumeralSyntaxException {
        this.numeral = cleanUpcaseAndSyntaxCheckString(symbols);
    }

    /**
     * Getter of the roman numerals String.
     *
     * @return a String containing the roman numeral.
     */
    public String getNumeral() {
        return numeral;
    }

    /**
     * Checks if this RomanNumeral contains a numeral other than NULLA_STRING.
     * <p>
     * Returns <code>true</code> if this RomanNumeal has a roman numeral stored
     * in it that indicates a non-zero value, that is a non-NULLA_STRING
     * numeral. Else
     * <code>false</code> if contains a NULLA_STRING string.
     * <p>
     * Immediatly after calling the {@link #RomanNumeral() default constructor}
     * (the one without parameters), this method will return <code>true</code>.
     *
     * @return <code>true</code> has a NULLA_STRING numeral stored in it, else
     * <code>false</code>.
     */
    public boolean isNulla() {
        return NULLA_STRING.equals(getNumeral());
    }

    /**
     * Setter of the roman numerals String.
     * <p>
     * The passed string gets checked for syntax correctness. If the syntax is
     * illegal, then a {@link IllegalNumeralSyntaxException} is thrown.
     * <p>
     * Whitespace charactes in the passed String are removed and the characters
     * are upcased.
     *
     * @param numeral the new roman numeral to be stored.
     * @throws IllegalNumeralSyntaxException when the passed string has illegal
     * roman syntax.
     */
    public void setNumeral(String numeral) throws IllegalNumeralSyntaxException {
        this.numeral = cleanUpcaseAndSyntaxCheckString(numeral);
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
     * {@link #setNumeral(java.lang.String) setter}.
     *
     * @param numeralsToCheck the String to check the roman syntax on.
     * @return <code>true</code> if the passed String is a roman numeral; else
     * <code>false</code>.
     */
    public static boolean isCorrectRomanSyntax(String numeralsToCheck) {
        try {
            new RomanNumeral().cleanUpcaseAndSyntaxCheckString(numeralsToCheck);
            return true;
        } catch (IllegalNumeralSyntaxException ex) {
            return false;
        }
    }

    /**
     * Removes all whitespace characters, upcases the String and verifies the
     * roman syntax.
     * <p>
     * If the syntax does not match, a {@link IllegalNumeralSyntaxException} is
     * thrown.
     *
     * @param symbols string to be cleaned, upcased and checked.
     * @return given string without whitespaces and upcased.
     */
    private String cleanUpcaseAndSyntaxCheckString(String symbols) throws IllegalNumeralSyntaxException {
        String cleanSymbols = symbols.replaceAll("\\s+", "").toUpperCase();
        throwExceptionIfIllegalRomanSyntax(cleanSymbols);
        if (cleanSymbols.equals("-" + NULLA_STRING)) {
            return NULLA_STRING;
        } else {
            return cleanSymbols;
        }
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
    private static String findAllRegexMatchingSubstrings(String textToParse, String regex) {
        StringBuilder matches = new StringBuilder();
        Matcher matcher = Pattern.compile(regex).matcher(textToParse);
        while (matcher.find()) {
            matches.append(matcher.group());
        }
        return matches.toString();
    }

    /**
     * Performs a check of the roman syntax of the given string.
     *
     * If the syntax is not correct, a {@link IllegalNumeralSyntaxException} is
     * thrown with some specification about the error. The syntax is indicated
     * in {@link RomanNumeral#CORRECT_ROMAN_SYNTAX_REGEX}.
     *
     * @param symbols
     */
    private void throwExceptionIfIllegalRomanSyntax(String symbols) throws IllegalNumeralSyntaxException {
        if (symbols.isEmpty()) {
            String message = romanBundle.getString("NonRomanChars");
            throw new IllegalNumeralSyntaxException(romanBundle.getString("EmptyRomanNumeral"));
        }
        if (symbols.length() >= 20) {
            String message = romanBundle.getString("NonRomanChars");
            throw new IllegalNumeralSyntaxException(romanBundle.getString("TooLongRomanNumeral"));
        }
        if (!symbols.matches(CORRECT_ROMAN_SYNTAX_REGEX)) {
            String illegalChars;
            illegalChars = findAllRegexMatchingSubstrings(symbols, NON_ROMAN_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                String message = MessageFormat.format(romanBundle.getString("NonRomanChars"), illegalChars);
                throw new IllegalNumeralSyntaxException(message);
            }
            illegalChars = findAllRegexMatchingSubstrings(symbols, FOUR_CONSECUTIVE_TEN_LIKE_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                String message = MessageFormat.format(romanBundle.getString("FourConsecutiveChars"), illegalChars);
                throw new IllegalNumeralSyntaxException(message);
            }
            illegalChars = findAllRegexMatchingSubstrings(symbols, TWO_SAME_FIVE_LIKE_CHARS_REGEX);
            if (!illegalChars.isEmpty()) {
                String message = MessageFormat.format(romanBundle.getString("TwoDLVChars"), illegalChars);
                throw new IllegalNumeralSyntaxException(message);
            }
            String message = romanBundle.getString("GenericRomanSyntaxError");
            throw new IllegalNumeralSyntaxException(message);
        }
    }

    /**
     * Returns the hash of this RomanNumeral.
     * <p>
     * The hashcode is created using only the roman numeral String in this
     * RomanNumeral. Uses {@link Objects#hashCode(java.lang.Object)} and
     * overrides {@link Object#hashCode()}.
     *
     * @return the hash of this RomanNumeral.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.numeral);
        return hash;
    }

    /**
     * Verifies if the passed Object is equal to this.
     * <p>
     * Returns <code>true</code> if the passed Object is a RomanNumeral and
     * contains the same roman numeral as this one, else <code>false</code>.
     *
     * @param otherRomanNumeral to compare with this.
     * @return a boolean telling if the two RomanNumerals are equal.
     * @see Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherRomanNumeral) {
        if (otherRomanNumeral == null) {
            return false;
        }
        if (getClass() != otherRomanNumeral.getClass()) {
            return false;
        }
        final RomanNumeral other = (RomanNumeral) otherRomanNumeral;
        return Objects.equals(this.numeral, other.getNumeral());
    }

    /**
     * Returns a String representation of this RomanNumeral, which is the roman
     * numeral String stored in it.
     * <p>
     * This method is a delegate method and just calls {@link #getNumeral()} so
     * the returned string is exactly the same for both. This method is here for
     * compatibility reasons.
     *
     * @return a String containing the roman numeral.
     * @see #getNumeral()
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return getNumeral();
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
     * <p>
     * Delegates {@link Object#clone()}.
     *
     * @return a RomanNumeral with the same numeral.
     * @throws CloneNotSupportedException when super object is not cloneable.
     * @see Object#clone()
     */
    @Override
    public RomanNumeral clone() throws CloneNotSupportedException {
        return (RomanNumeral) super.clone();
    }

    /**
     * Returns the lenght of the roman numeral contained in this RomanNumeral
     * expressed as number of characters.
     * <p>
     * Delegates {@link String#length()}.
     *
     * @return number of characters in the roman numeral.
     * @see String#length()
     */
    @Override
    public int length() {
        return numeral.length();
    }

    /**
     * Returns the character of the roman numeral at the given index.
     * <p>
     * Delegates {@link String#charAt(int)}.
     *
     * @param index of the wanted character in the roman numeral.
     * @return the character at the given index.
     * @see String#charAt(int)
     */
    @Override
    public char charAt(int index) {
        return numeral.charAt(index);
    }

    /**
     * Returns a CharSequence containing a part of this RomanNumeral.
     * <p>
     * The returned CharSequence contains the characters of the roman numeral
     * from the start index (included) to the end index (exluded).
     * <p>
     * Delegates {@link String#subSequence(int, int)}.
     *
     * @param startIncluded index of the first character to be included.
     * @param endNotIncluded index of the first character after the end of the
     * wanted part.
     * @return a part of the roman numeral as CharSequence.
     * @see String#subSequence(int, int)
     */
    @Override
    public CharSequence subSequence(int startIncluded, int endNotIncluded) {
        return numeral.subSequence(startIncluded, endNotIncluded);
    }
}
