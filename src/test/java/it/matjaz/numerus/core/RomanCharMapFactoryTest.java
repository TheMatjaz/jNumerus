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
    private Map<Integer, String> inverseCharMap;

    @Before
    public void generateCharMap() {
        this.charMap = RomanCharMapFactory.generateCharMap();
        this.inverseCharMap = RomanCharMapFactory.generateInverseCharMap();
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
}
