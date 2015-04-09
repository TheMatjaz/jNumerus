package it.matjaz.numerus.core;

import java.util.Map;
import javafx.util.Pair;
import org.apache.commons.collections4.BidiMap;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanCharMapFactoryTest {

    private Map<String, Integer> charMap;
    private Map<Integer, String> inverseCharMap;
    private BidiMap bidiCharMap;
    private Pair[] charPairs;

    @Before
    public void generateCharMap() {
        this.charMap = RomanCharMapFactory.generateCharMap();
        this.inverseCharMap = RomanCharMapFactory.generateInverseCharMap();
        this.bidiCharMap = RomanCharMapFactory.generateBidiCharMap();
        this.charPairs = RomanCharMapFactory.generateCharPairsArray();
    }

    @Test
    public void staticConstructorCreatesAMap() {
        Assert.assertThat(charMap, instanceOf(Map.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyMap() {
        Assert.assertFalse(charMap.isEmpty());
    }

    @Test
    public void charMapKeysAreStrings() {
        Assert.assertThat(charMap.keySet().iterator().next(), instanceOf(String.class));
    }

    @Test
    public void charMapValuesAreIntegers() {
        Assert.assertThat(charMap.values().iterator().next(), instanceOf(Integer.class));
    }

    @Test
    public void charMapKeysAreOnlyRomanChars() {
        for (String romanChar : charMap.keySet()) {
            Assert.assertTrue(romanChar.matches("[MDCLXVI]+"));
        }
    }

    @Test
    public void charMapKeysAreAllTheRomanChars() {
        Assert.assertTrue(charMap.containsKey("M"));
        Assert.assertTrue(charMap.containsKey("CM"));
        Assert.assertTrue(charMap.containsKey("D"));
        Assert.assertTrue(charMap.containsKey("CD"));
        Assert.assertTrue(charMap.containsKey("C"));
        Assert.assertTrue(charMap.containsKey("XC"));
        Assert.assertTrue(charMap.containsKey("L"));
        Assert.assertTrue(charMap.containsKey("XL"));
        Assert.assertTrue(charMap.containsKey("X"));
        Assert.assertTrue(charMap.containsKey("IX"));
        Assert.assertTrue(charMap.containsKey("V"));
        Assert.assertTrue(charMap.containsKey("IV"));
        Assert.assertTrue(charMap.containsKey("I"));
    }

    @Test
    public void charMapContainsNoOtherKeys() {
        Assert.assertTrue(charMap.size() == 13);
    }

    @Test
    public void charMapValuesMatchRomanChars() {
        Assert.assertTrue(charMap.get("M") == 1000);
        Assert.assertTrue(charMap.get("CM") == 900);
        Assert.assertTrue(charMap.get("D") == 500);
        Assert.assertTrue(charMap.get("CD") == 400);
        Assert.assertTrue(charMap.get("C") == 100);
        Assert.assertTrue(charMap.get("XC") == 90);
        Assert.assertTrue(charMap.get("L") == 50);
        Assert.assertTrue(charMap.get("XL") == 40);
        Assert.assertTrue(charMap.get("X") == 10);
        Assert.assertTrue(charMap.get("IX") == 9);
        Assert.assertTrue(charMap.get("V") == 5);
        Assert.assertTrue(charMap.get("IV") == 4);
        Assert.assertTrue(charMap.get("I") == 1);
    }

    @Test
    public void staticConstructorCreatesAInverseMap() {
        Assert.assertThat(inverseCharMap, instanceOf(Map.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyInverseMap() {
        Assert.assertFalse(inverseCharMap.isEmpty());
    }

    @Test
    public void inverseCharMapKeysAreIntegers() {
        Assert.assertThat(inverseCharMap.keySet().iterator().next(), instanceOf(Integer.class));
    }

    @Test
    public void inverseCharMapValuesAreStrings() {
        Assert.assertThat(inverseCharMap.values().iterator().next(), instanceOf(String.class));
    }
      
    @Test
    public void inverseCharMapContainsNoOtherKeys() {
        Assert.assertTrue(inverseCharMap.size() == 13);
    }
    
    @Test
    public void inverseCharMapIsExactOppositeOfTheCharMap() {
        
        Assert.assertTrue("M".equals(inverseCharMap.get(1000)));
        Assert.assertTrue("CM".equals(inverseCharMap.get(900)));
        Assert.assertTrue("D".equals(inverseCharMap.get(500)));
        Assert.assertTrue("CD".equals(inverseCharMap.get(400)));
        Assert.assertTrue("C".equals(inverseCharMap.get(100)));
        Assert.assertTrue("XC".equals(inverseCharMap.get(90)));
        Assert.assertTrue("L".equals(inverseCharMap.get(50)));
        Assert.assertTrue("XL".equals(inverseCharMap.get(40)));
        Assert.assertTrue("X".equals(inverseCharMap.get(10)));
        Assert.assertTrue("IX".equals(inverseCharMap.get(9)));
        Assert.assertTrue("V".equals(inverseCharMap.get(5)));
        Assert.assertTrue("IV".equals(inverseCharMap.get(4)));
        Assert.assertTrue("I".equals(inverseCharMap.get(1)));
    }

    @Test
    public void staticConstructorCreatesBidiMap() {
        Assert.assertThat(bidiCharMap, instanceOf(BidiMap.class));
    }

    @Test
    public void staticConstructorCreatesNonEmptyBidiMap() {
        Assert.assertFalse(bidiCharMap.isEmpty());
    }

    @Test
    public void bidiCharMapContainsNoOtherEntries() {
        Assert.assertTrue(charMap.size() == 13);
    }

    @Test
    public void bidiCharMapEntriesMatchRomanChars() {
        Assert.assertTrue(bidiCharMap.get("M").equals(1000));
        Assert.assertTrue(bidiCharMap.get("CM").equals(900));
        Assert.assertTrue(bidiCharMap.get("D").equals(500));
        Assert.assertTrue(bidiCharMap.get("CD").equals(400));
        Assert.assertTrue(bidiCharMap.get("C").equals(100));
        Assert.assertTrue(bidiCharMap.get("XC").equals(90));
        Assert.assertTrue(bidiCharMap.get("L").equals(50));
        Assert.assertTrue(bidiCharMap.get("XL").equals(40));
        Assert.assertTrue(bidiCharMap.get("X").equals(10));
        Assert.assertTrue(bidiCharMap.get("IX").equals(9));
        Assert.assertTrue(bidiCharMap.get("V").equals(5));
        Assert.assertTrue(bidiCharMap.get("IV").equals(4));
        Assert.assertTrue(bidiCharMap.get("I").equals(1));
        Assert.assertTrue("M".equals(bidiCharMap.getKey(1000)));
        Assert.assertTrue("CM".equals(bidiCharMap.getKey(900)));
        Assert.assertTrue("D".equals(bidiCharMap.getKey(500)));
        Assert.assertTrue("CD".equals(bidiCharMap.getKey(400)));
        Assert.assertTrue("C".equals(bidiCharMap.getKey(100)));
        Assert.assertTrue("XC".equals(bidiCharMap.getKey(90)));
        Assert.assertTrue("L".equals(bidiCharMap.getKey(50)));
        Assert.assertTrue("XL".equals(bidiCharMap.getKey(40)));
        Assert.assertTrue("X".equals(bidiCharMap.getKey(10)));
        Assert.assertTrue("IX".equals(bidiCharMap.getKey(9)));
        Assert.assertTrue("V".equals(bidiCharMap.getKey(5)));
        Assert.assertTrue("IV".equals(bidiCharMap.getKey(4)));
        Assert.assertTrue("I".equals(bidiCharMap.getKey(1)));
    }

    @Test
    public void staticConstructorCreatesPairsArray() {
        Assert.assertThat(charPairs, instanceOf(Pair[].class));
    }

    @Test
    public void staticConstructorCreatesPairsArrayWithSpecificLenght13() {
        Assert.assertTrue(charPairs.length == 13);
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
        Assert.assertArrayEquals(charPairs, pairsArray);
    }
}
