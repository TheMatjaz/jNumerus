package it.matjaz.numerus.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeral {

    private String symbols;

    /**
     * A big regex matching all syntactically correct roman numerals.
     *
     * Contains all possible cases of roman numerals. If a string does not match
     * this regex, then is not a roman numeral.
     */
    public final String CORRECT_ROMAN_SYNTAX_REGEX = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    /**
     * Non roman characters.
     */
    public final String NON_ROMAN_CHARS_REGEX = "[^MDCLXVI]";

    /**
     * Four consecutive characters M or C or X or I.
     */
    private final String FOUR_CONSECUTIVE_TEN_LIKE_CHARS_REGEX = "(MMMM|CCCC|XXXX|IIII)";

    /**
     * Two non necessary consecutive characters D or L or V.
     */
    private final String TWO_SAME_FIVE_LIKE_CHARS_REGEX = "(D.*D|L.*L|V.*V)";

    public RomanNumeral() {
        this.symbols = "";
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        String cleanSymbols = cleanSymbolsString(symbols);
        checkRomanSyntax(cleanSymbols);
        this.symbols = cleanSymbols;
    }

    private static String cleanSymbolsString(String symbols) {
        return symbols.replaceAll("\\s+", "").toUpperCase();
    }

    // from http://stackoverflow.com/questions/5705111/how-to-get-all-substring-for-a-given-regex
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

}
