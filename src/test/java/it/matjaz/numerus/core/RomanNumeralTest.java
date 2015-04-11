package it.matjaz.numerus.core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test of {@link RomanNumeral} which is a container for syntactically
 * correct roman numerals.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanNumeralTest {

    private RomanNumeral roman;

    @Before
    public void defaultRomanConstructor() {
        roman = new RomanNumeral();
    }

    @Test
    public void whenDefaultConstructorIsCalledThenSymbolsAreEmptyString() {
        assertEquals(roman.getSymbols(), "");
    }

    @Test
    public void whenCorrectStringIsGivenThenNoExceptionIsThrown() {
        roman.setSymbols("XLII");
        assertEquals("XLII", roman.getSymbols());
    }

    @Test(expected = NullPointerException.class)
    public void whenNullStringIsGivenThenExceptionIsThrown() {
        roman.setSymbols(null);
    }

    @Test
    public void givenStringGetsStrippedAndUpcased() {
        roman.setSymbols("  \t\n\r   xliI ");
        assertEquals("XLII", roman.getSymbols());
    }

    @Test
    public void givenStringGetsStrippedOfInnerWhiteSpaceChars() {
        roman.setSymbols("  XL I  II");
        assertEquals("XLIII", roman.getSymbols());
    }

    @Test(expected = NumberFormatException.class)
    public void whenEmptyStringIsGivenThenExceptionIsThrown() {
        roman.setSymbols("");
    }

    @Test(expected = NumberFormatException.class)
    public void whenWhitespaceStringIsGivenThenExceptionIsThrown() {
        roman.setSymbols("  \t\n\r  ");
    }

    @Test(expected = NumberFormatException.class)
    public void whenImpossiblyLongStringIsGivenThenExceptionIsThrown() {
        roman.setSymbols("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
    }

    @Test(expected = NumberFormatException.class)
    public void whenStringContainsNonRomanCharactersThenExceptionIsThrown() {
        roman.setSymbols("pFXC-");
    }

    @Test
    public void whenStringContainsNonRomanCharactersThenExceptionMessageShowsThem() {
        try {
            roman.setSymbols("pPFXC-");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("pPF-".toUpperCase()));
        }
    }

    @Test(expected = NumberFormatException.class)
    public void whenStringWithIncorrectTomanSyntaxIsGivenThenExceptionIsThrown() {
        roman.setSymbols("MMCMIIIX");
    }

    @Test
    public void whenStringContainsMoreThanThreeConsecutiveTenLikeSymbolsThenThenExceptionMessageShowsThem() {
        try {
            roman.setSymbols("CCCC");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("CCCC"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem() {
        try {
            roman.setSymbols("DDXII");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("DD"));
        }
    }

    @Test
    public void whenStringContainsMoreThanOneConsecutiveFiveLikeSymbolThenExceptionMessageShowsThem2() {
        try {
            roman.setSymbols("DXID");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("DXID"));
        }
    }
    
    @Test
    public void syntaxCheckCanBePerformedWithoutInstantiation() {
        assertTrue(RomanNumeral.isCorrectRomanSyntax("CLXXII"));
        assertFalse(RomanNumeral.isCorrectRomanSyntax("LINUX RULES!"));
    }

}
