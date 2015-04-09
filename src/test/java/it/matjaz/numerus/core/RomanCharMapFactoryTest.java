package it.matjaz.numerus.core;

import java.util.Map;
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

    @Before
    public void generateCharMap() {
        this.charMap = RomanCharMapFactory.generateCharMap();
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
}
