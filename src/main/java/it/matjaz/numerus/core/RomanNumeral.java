package it.matjaz.numerus.core;

/**
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeral {

    private String symbols;

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

    private static void checkRomanSyntax(String symbols) {
        if (symbols.isEmpty()) {
            throw new NumberFormatException("Empty roman numeral.");
        }
        if (symbols.length() >= 20) {
            throw new NumberFormatException("Impossibly long roman numeral.");
        }
    }

}
