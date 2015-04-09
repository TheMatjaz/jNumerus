package it.matjaz.numerus.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeral {

    private String symbols;
    public final String NON_ROMAN_CHARS_REGEX = "[^MDCLXVI]";

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
    private static String getAllMatches(String textToParse, String regex) {
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
        String matches;
        matches = getAllMatches(symbols, NON_ROMAN_CHARS_REGEX);
        if (!matches.isEmpty()) {
            throw new NumberFormatException("Non roman characters: " + matches);
        }
    }

}
