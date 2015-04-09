package it.matjaz.numerus.core;

// @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a> <a href="http://matjaz.it">www.matjaz.it</a>
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class RomanCharMapFactory {

    private RomanCharMapFactory() {
    }

    public static Map<String, Integer> generateCharMap() {
        Map<String, Integer> charMap = new HashMap();
        Pair[] pairsArray = generateCharPairsArray();
        for (Pair charAndValue : pairsArray) {
            charMap.put((String) charAndValue.getKey(), (Integer) charAndValue.getValue());
        }
        return charMap;
    }

    public static Map generateInverseCharMap() {
        Map<Integer, String> inverseCharMap = new HashMap();
        Map<String, Integer> charMap = generateCharMap();
        charMap.keySet().stream().forEach((key) -> {
            inverseCharMap.put(charMap.get(key), key);
        });
        return inverseCharMap;
    }

    public static BidiMap generateBidiCharMap() {
        return new DualHashBidiMap(generateCharMap());
    }

    public static Pair[] generateCharPairsArray() {
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
        return pairsArray;
    }
}
