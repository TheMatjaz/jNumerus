package it.matjaz.numerus.core;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test of {@link RomanConverter} which is converting roman numerals to
 * integers and vice-versa by using RomanCharMapFactory generated structures.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanConverterTest {

    private RomanConverter converter;

    @Before
    public void constructConverter() {
        this.converter = new RomanConverter();
    }

    @Test
    public void whenDefaultConstructorIsCalledThenNonNullArrayOfPairsIsConstructed() {
        assertNotNull(converter.getChars());
    }

    @Test
    public void whenDefaultConstructorIsCalledThenRomanCharPairsArrayIsConstructed() {
        assertArrayEquals(converter.getChars(), RomanCharMapFactory.generateCharPairsArray());
    }

}
