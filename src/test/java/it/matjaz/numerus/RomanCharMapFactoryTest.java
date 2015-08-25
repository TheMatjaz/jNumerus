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

import java.util.Map;
import javafx.util.Pair;
import org.apache.commons.collections4.BidiMap;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test of {@link RomanCharMapFactory} which is generating structures of
 * pairs (roman character, its integer value) to be used for conversion.
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">matjaz.it</a>
 */
public class RomanCharMapFactoryTest {

    private Map<String, Integer> charMap;
    private Map<Integer, String> inverseCharMap;
    private BidiMap bidiCharMap;
    private Pair[] charPairs;

    @Before
    public void constructEveryCollectionToBeVerified() {
        this.charMap = RomanCharMapFactory.generateCharMap();
        this.inverseCharMap = RomanCharMapFactory.generateInverseCharMap();
        this.bidiCharMap = RomanCharMapFactory.generateBidiCharMap();
        this.charPairs = RomanCharMapFactory.generateCharPairsArray();
    }

    @Test
    public void staticConstructorCreatesAMap() {
        assertThat(charMap, instanceOf(Map.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyMap() {
        assertFalse(charMap.isEmpty());
    }

    @Test
    public void charMapKeysAreStrings() {
        assertThat(charMap.keySet().iterator().next(), instanceOf(String.class));
    }

    @Test
    public void charMapValuesAreIntegers() {
        assertThat(charMap.values().iterator().next(), instanceOf(Integer.class));
    }

    @Test
    public void charMapKeysAreOnlyRomanChars() {
        charMap.keySet().stream().forEach((romanChar) -> {
            assertTrue(romanChar.matches("[MDCLXVI]+"));
        });
    }

    @Test
    public void charMapKeysAreAllTheRomanChars() {
        assertTrue(charMap.containsKey("M"));
        assertTrue(charMap.containsKey("CM"));
        assertTrue(charMap.containsKey("D"));
        assertTrue(charMap.containsKey("CD"));
        assertTrue(charMap.containsKey("C"));
        assertTrue(charMap.containsKey("XC"));
        assertTrue(charMap.containsKey("L"));
        assertTrue(charMap.containsKey("XL"));
        assertTrue(charMap.containsKey("X"));
        assertTrue(charMap.containsKey("IX"));
        assertTrue(charMap.containsKey("V"));
        assertTrue(charMap.containsKey("IV"));
        assertTrue(charMap.containsKey("I"));
    }

    @Test
    public void charMapContainsNoOtherEntries() {
        assertTrue(charMap.size() == 13);
    }

    @Test
    public void charMapValuesMatchRomanChars() {
        assertTrue(charMap.get("M") == 1000);
        assertTrue(charMap.get("CM") == 900);
        assertTrue(charMap.get("D") == 500);
        assertTrue(charMap.get("CD") == 400);
        assertTrue(charMap.get("C") == 100);
        assertTrue(charMap.get("XC") == 90);
        assertTrue(charMap.get("L") == 50);
        assertTrue(charMap.get("XL") == 40);
        assertTrue(charMap.get("X") == 10);
        assertTrue(charMap.get("IX") == 9);
        assertTrue(charMap.get("V") == 5);
        assertTrue(charMap.get("IV") == 4);
        assertTrue(charMap.get("I") == 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void charMapIsUnmodifiable() {
        charMap.put("bla", 1);
    }

    @Test
    public void staticConstructorCreatesAInverseMap() {
        assertThat(inverseCharMap, instanceOf(Map.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyInverseMap() {
        assertFalse(inverseCharMap.isEmpty());
    }

    @Test
    public void inverseCharMapKeysAreIntegers() {
        assertThat(inverseCharMap.keySet().iterator().next(), instanceOf(Integer.class));
    }

    @Test
    public void inverseCharMapValuesAreStrings() {
        assertThat(inverseCharMap.values().iterator().next(), instanceOf(String.class));
    }

    @Test
    public void inverseCharMapContainsNoOtherEntries() {
        assertTrue(inverseCharMap.size() == 13);
    }

    @Test
    public void inverseCharMapIsExactOppositeOfTheCharMap() {
        assertTrue("M".equals(inverseCharMap.get(1000)));
        assertTrue("CM".equals(inverseCharMap.get(900)));
        assertTrue("D".equals(inverseCharMap.get(500)));
        assertTrue("CD".equals(inverseCharMap.get(400)));
        assertTrue("C".equals(inverseCharMap.get(100)));
        assertTrue("XC".equals(inverseCharMap.get(90)));
        assertTrue("L".equals(inverseCharMap.get(50)));
        assertTrue("XL".equals(inverseCharMap.get(40)));
        assertTrue("X".equals(inverseCharMap.get(10)));
        assertTrue("IX".equals(inverseCharMap.get(9)));
        assertTrue("V".equals(inverseCharMap.get(5)));
        assertTrue("IV".equals(inverseCharMap.get(4)));
        assertTrue("I".equals(inverseCharMap.get(1)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void inverseCharMapIsUnmodifiable() {
        inverseCharMap.put(1, "bla");
    }

    @Test
    public void staticConstructorCreatesBidiMap() {
        assertThat(bidiCharMap, instanceOf(BidiMap.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyBidiMap() {
        assertFalse(bidiCharMap.isEmpty());
    }

    @Test
    public void bidiCharMapContainsNoOtherEntries() {
        assertTrue(charMap.size() == 13);
    }

    @Test
    public void bidiCharMapEntriesMatchRomanChars() {
        assertTrue(bidiCharMap.get("M").equals(1000));
        assertTrue(bidiCharMap.get("CM").equals(900));
        assertTrue(bidiCharMap.get("D").equals(500));
        assertTrue(bidiCharMap.get("CD").equals(400));
        assertTrue(bidiCharMap.get("C").equals(100));
        assertTrue(bidiCharMap.get("XC").equals(90));
        assertTrue(bidiCharMap.get("L").equals(50));
        assertTrue(bidiCharMap.get("XL").equals(40));
        assertTrue(bidiCharMap.get("X").equals(10));
        assertTrue(bidiCharMap.get("IX").equals(9));
        assertTrue(bidiCharMap.get("V").equals(5));
        assertTrue(bidiCharMap.get("IV").equals(4));
        assertTrue(bidiCharMap.get("I").equals(1));
        assertTrue("M".equals(bidiCharMap.getKey(1000)));
        assertTrue("CM".equals(bidiCharMap.getKey(900)));
        assertTrue("D".equals(bidiCharMap.getKey(500)));
        assertTrue("CD".equals(bidiCharMap.getKey(400)));
        assertTrue("C".equals(bidiCharMap.getKey(100)));
        assertTrue("XC".equals(bidiCharMap.getKey(90)));
        assertTrue("L".equals(bidiCharMap.getKey(50)));
        assertTrue("XL".equals(bidiCharMap.getKey(40)));
        assertTrue("X".equals(bidiCharMap.getKey(10)));
        assertTrue("IX".equals(bidiCharMap.getKey(9)));
        assertTrue("V".equals(bidiCharMap.getKey(5)));
        assertTrue("IV".equals(bidiCharMap.getKey(4)));
        assertTrue("I".equals(bidiCharMap.getKey(1)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void bidiCharMapIsUnmodifiable() {
        bidiCharMap.put(1, "bla");
    }

    @Test
    public void staticConstructorCreatesPairsArray() {
        assertThat(charPairs, instanceOf(Pair[].class));
    }

    @Test
    public void staticConstructorCreatesPairsArrayWithSpecificLenght13() {
        assertTrue(charPairs.length == 13);
    }

    @Test
    public void pairsArrayContainsOnlyRomanCharsAndValuesSortedInverselyByChar() {
        Pair[] pairsArray = new Pair[]{
            new Pair("M", 1000),
            new Pair("CM", 900),
            new Pair("D", 500),
            new Pair("CD", 400),
            new Pair("C", 100),
            new Pair("XC", 90),
            new Pair("L", 50),
            new Pair("XL", 40),
            new Pair("X", 10),
            new Pair("IX", 9),
            new Pair("V", 5),
            new Pair("IV", 4),
            new Pair("I", 1)
        };
        assertArrayEquals(charPairs, pairsArray);
    }
}
