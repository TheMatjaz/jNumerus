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
    
    private Map charMap;
    
    @Before
    public void generateCharMap() {
        this.charMap = RomanCharMapFactory.generateCharMap();
    }

    @Test
    public void staticConstructorCreatesAMap() {
        Assert.assertThat(charMap, instanceOf(Map.class));
    }
    
//    @Test
//    public void staticConstructorCreatesNonEmptyMap() {
//        Assert.assertFalse(charMap.isEmpty());
//    }

//    @Test
//    public void charMapKeysAreStrings() {
//        Map charMap = RomanCharMapFactory
//        Assert.assertThat(RomanCharMapFactory.generateCharMap().keySet()., instanceOf(Set.class));
//    }
//    @Test
//    public void charMapContainsOnlyRomanChars() {
//        Map<String, Integer> charMap = RomanCharMapFactory.generateCharMap();
//        for (String romanChar : charMap.keySet()) {
//            "[^MDCLXVI]";
//        }
//    }

}
