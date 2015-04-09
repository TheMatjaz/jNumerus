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
            roman.setSymbols("pFXC-");
        } catch (NumberFormatException ex) {
            assertTrue(ex.getMessage().contains("pF-".toUpperCase()));
        }
    }
    /*

     @Test(expected = NumberFormatException.class)
     public void whenStringWithIncorrectTomanSyntaxIsGivenThenExceptionIsThrown() {
     roman.setSymbols("MMCMIIIX");
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringWithMoreThanThreeConsecutiveTenLikeIsGivenThenExceptionIsThrown() {
     roman.setSymbols("CCCC");
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringWithMoreThanOneFiveLikeCharIsGivenThenExceptionIsThrown() {
     roman.setSymbols("DD");
     }

     @Test
     public void whenStringWithNonRomanCharIsGivenThenAllNonRomanCharsAreListedInException() {
     try {
     roman.setSymbols("MMCZCCFUV-II=");
     } catch (NumberFormatException ex) {
     assertEquals("Not roman characters: [Z, F, U, -, =]", ex.getMessage());
     }
     }

     @Test
     public void whenStringWithMoreThanThreeConsecutiveTenLikeCharsIsGivenThenTheyAreListenInException() {
     try {
     roman.setSymbols("MMCCCCVI");
     } catch (NumberFormatException ex) {
     assertEquals("Four consecutive [CCCC] in numeral.", ex.getMessage());
     }
     }

     @Test
     public void whenStringWithMoreThanOneFiveLikeCharsIsGivenThenTheyAreListenInException() {
     try {
     roman.setSymbols("LXVVILL");
     } catch (NumberFormatException ex) {
     assertEquals("Two consecutive [VV, LL] in numeral.", ex.getMessage());
     }
     }

     // test with all roman numbers

     */
}
