package it.matjaz.numerus.core;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
/*
 TODO: add inverse Pair[], make maps unmodifiable
 */

/**
 * Factory generating structures of pairs (basic roman character, its integer
 * value) to be used for conversion.
 *
 * This class offers four static constructors of collections keeping a reference
 * between basic roman characters and character pairs that are used for
 * conversions of whole strings of roman numerals. The scructures offered are:
 * <ul>
 * <li>Array of Pairs(String, Integer)</li>
 * <li>HashMap&lt;String, Integer&gt;</li>
 * <li>HashMap&lt;Integer, String&gt;, the exact opposite of the above one</li>
 * <li>DualHashBidiMap (bidirectional map, from Apache Common Collections
 * 4.0)</li>
 * </ul>
 * <p>
 * The array of Pairs is hardcoded, while the other three Maps are filled using
 * its Pairs. The structure of the array is based on the example of roman
 * numerals convertion from
 * <a href="http://www.fredosaurus.com/notes-java/examples/components/romanNumerals/romanNumeral.html">fredosaurus.com</a>.
 * The pairs of values contained are:
 * <p>
 * <table border="0" summary="" frame="box" rules="rows">
 * <tr><th>Roman,</th><th>value</th></tr>
 * <tr><td align="right"><b>M</b></td><td align="right">1000</td></tr>
 * <tr><td align="right"><b>CM</b></td><td align="right">900</td></tr>
 * <tr><td align="right"><b>D</b></td><td align="right">500</td></tr>
 * <tr><td align="right"><b>CD</b></td><td align="right">400</td></tr>
 * <tr><td align="right"><b>C</b></td><td align="right">100</td></tr>
 * <tr><td align="right"><b>XC</b></td><td align="right">90</td></tr>
 * <tr><td align="right"><b>L</b></td><td align="right">50</td></tr>
 * <tr><td align="right"><b>XL</b></td><td align="right">40</td></tr>
 * <tr><td align="right"><b>X</b></td><td align="right">10</td></tr>
 * <tr><td align="right"><b>IX</b></td><td align="right">9</td></tr>
 * <tr><td align="right"><b>V</b></td><td align="right">5</td></tr>
 * <tr><td align="right"><b>IV</b></td><td align="right">4</td></tr>
 * <tr><td align="right"><b>I</b></td><td align="right">1</td></tr>
 * </table>
 *
 * @author Matjaž <a href="mailto:dev@matjaz.it">dev@matjaz.it</a>
 * <a href="http://matjaz.it">www.matjaz.it</a>
 */
public class RomanCharMapFactory {

    /**
     * Private empty constructor to prevent class instantiation, since it's just
     * a container of static methods.
     */
    private RomanCharMapFactory() {
    }

    /**
     * Constructs an array of 13 Pairs(roman character, its integer value).
     * <p>
     * Those characters are used for conversions of whole strings of roman
     * numerals. A representation of the content is the followind table:
     * <pre>
     * i rom  int
     * 0   M 1000
     * 1  CM  900
     * 2   D  500
     * 3  CD  400
     * 4   C  100
     * 5  XC   90
     * 6   L   50
     * 7  XL   40
     * 8   X   10
     * 9  IX    9
     * 10  V    5
     * 11 IV    4
     * 12  I    1
     * </pre>
     *
     * The pairs are <b>hardcoded</b> since they are constants.
     *
     * @return Pair[] containing Pairs of basic roman characters and the
     * respecitve integer values.
     */
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

    /**
     * Constructs a HashMap with 13 entries &lt;roman character, its integer
     * value&gt;.
     * <p>
     * The Map's keys are basic roman characters stored as strings and they
     * point to their integer values. Those entries are used for conversions of
     * whole strings of roman numerals.
     * <p>
     * The map returned by this method is the exact opposite of the one returned
     * by {@link #generateInverseCharMap() } (keys and values are switched). The
     * Map is filled by inserting values from the
     * {@link #generateCharPairsArray() }. A representation of the content is
     * the followind table:
     *
     * <pre>
     * rom int
     * -------
     *  M 1000
     * CM  900
     *  D  500
     * CD  400
     *  C  100
     * XC   90
     *  L   50
     * XL   40
     *  X   10
     * IX    9
     *  V    5
     * IV    4
     *  I    1
     * </pre>
     *
     * @return HashMap&lt;String, Integer&gt; containing basic roman characters
     * as keys and the respective integer values.
     */
    public static Map<String, Integer> generateCharMap() {
        Map<String, Integer> charMap = new HashMap();
        for (Pair charAndValue : generateCharPairsArray()) {
            charMap.put((String) charAndValue.getKey(), (Integer) charAndValue.getValue());
        }
        return charMap;
    }

    /**
     * Constructs a HashMap with 13 entries &lt;integer value, equivalent roman
     * character&gt;.
     * <p>
     * The Map's values are basic roman characters stored as strings and they
     * are references by their integer values. Those entries are used for
     * conversions of whole strings of roman numerals.
     * <p>
     * The map returned by this method is the exact opposite of the one returned
     * by {@link #generateCharMap() } (keys and values are switched). The Map is
     * filled by inserting values from the {@link #generateCharPairsArray() }. A
     * representation of the content is the followind table:
     *
     * <pre>
     * int rom
     * -------
     * 1000  M
     *  900 CM
     *  500  D
     *  400 CD
     *  100  C
     *   90 XC
     *   50  L
     *   40 XL
     *   10  X
     *    9 IX
     *    5  V
     *    4 IV
     *    1  I
     * </pre>
     *
     * @return HashMap&lt;Integer, String&gt; containing basic roman characters
     * as values and the respective integers as keys.
     */
    public static Map<Integer, String> generateInverseCharMap() {
        Map<Integer, String> inverseCharMap = new HashMap();
        for (Pair charAndValue : generateCharPairsArray()) {
            inverseCharMap.put((Integer) charAndValue.getValue(), (String) charAndValue.getKey());
        }
        return inverseCharMap;
    }

    /**
     * Constructs a DualHashBidiMap with 13 entries &lt;roman character, its
     * integer value&gt;.
     * <p>
     * The BidiMap correlates basic roman characters stored as strings and their
     * integer values. Those entries are used for conversions of whole strings
     * of roman numerals.
     * <p>
     * The map returned by this method is a {@link BidiMap} from the
     * <a href="http://commons.apache.org/proper/commons-collections/">Apache
     * Commons Collection 4.0</a>, which means it can be accessed by its keys
     * with <code>get(Object)</code> or by its values with
     * <code>getKey(Object)</code>. The Map is filled by inserting values from
     * the {@link #generateCharMap()
     * }. A representation of the content is the followind table:
     *
     * <pre>
     * rom int
     * -------
     *  M 1000
     * CM  900
     *  D  500
     * CD  400
     *  C  100
     * XC   90
     *  L   50
     * XL   40
     *  X   10
     * IX    9
     *  V    5
     * IV    4
     *  I    1
     * </pre>
     *
     * @return DualHashBidiMap&lt;String, Integer&gt; containing basic roman
     * characters as and the respective integer values.
     */
    public static BidiMap generateBidiCharMap() {
        return new DualHashBidiMap(generateCharMap());
    }

}
