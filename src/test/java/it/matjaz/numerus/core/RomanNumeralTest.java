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

    public RomanNumeralTest() {
    }

    private RomanNumeral roman;

    @Before
    public void defaultRomanConstructor() {
        roman = new RomanNumeral();
    }

    @Test
    public void whenDefaultConstructorIsCalledThenSymbolsAreEmptyString() {
        assertEquals(roman.getSymbols(), "");
    }
    /*
     @Test
     public void whenCorrectStringIsGivenThenNoExceptionIsThrown() {
     roman.setNumerals("XLII");
     assertEquals("XLII", roman.getNumerals());
     }

     @Test(expected = NumberFormatException.class)
     public void whenEmptyStringIsGivenThenExceptionIsThrown() {
     roman.setNumerals("");
     }

     @Test(expected = NumberFormatException.class)
     public void whenWhitespaceStringIsGivenThenExceptionIsThrown() {
     roman.setNumerals("  \t\n\r  ");
     }

     @Test
     public void givenStringGetsStrippedOfWhitespaceCharsAndUpcased() {
     roman.setNumerals("  \t\n\r   xl iI ");
     assertEquals("XLII", roman.getNumerals());
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringContainsNotRomanCharactersThenExceptionIsThrown() {
     roman.setNumerals("pFXC- ");
     }

     @Test(expected = NullPointerException.class)
     public void whenNullStringIsGivenThenExceptionIsThrown() {
     roman.setNumerals(null);
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringWithIncorrectTomanSyntaxIsGivenThenExceptionIsThrown() {
     roman.setNumerals("MMCMIIIX");
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringWithMoreThanThreeConsecutiveTenLikeIsGivenThenExceptionIsThrown() {
     roman.setNumerals("CCCC");
     }

     @Test(expected = NumberFormatException.class)
     public void whenStringWithMoreThanOneFiveLikeCharIsGivenThenExceptionIsThrown() {
     roman.setNumerals("DD");
     }

     @Test
     public void whenStringWithNonRomanCharIsGivenThenAllNonRomanCharsAreListedInException() {
     try {
     roman.setNumerals("MMCZCCFUV-II=");
     } catch (NumberFormatException ex) {
     assertEquals("Not roman characters: [Z, F, U, -, =]", ex.getMessage());
     }
     }

     @Test
     public void whenStringWithMoreThanThreeConsecutiveTenLikeCharsIsGivenThenTheyAreListenInException() {
     try {
     roman.setNumerals("MMCCCCVI");
     } catch (NumberFormatException ex) {
     assertEquals("Four consecutive [CCCC] in numeral.", ex.getMessage());
     }
     }

     @Test
     public void whenStringWithMoreThanOneFiveLikeCharsIsGivenThenTheyAreListenInException() {
     try {
     roman.setNumerals("LXVVILL");
     } catch (NumberFormatException ex) {
     assertEquals("Two consecutive [VV, LL] in numeral.", ex.getMessage());
     }
     }

     // test with all roman numbers

     */
}
