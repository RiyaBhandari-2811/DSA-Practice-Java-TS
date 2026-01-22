import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    /**
     * Problem:
     * Convert a Roman numeral string to an integer.
     *
     * Symbols:
     * I = 1, V = 5, X = 10, L = 50,
     * C = 100, D = 500, M = 1000
     *
     * Special subtraction cases:
     * IV = 4, IX = 9
     * XL = 40, XC = 90
     * CD = 400, CM = 900
     */

    /**
     * Approach 1: Brute Force (Explicit Comparison)
     *
     * Idea:
     * - Traverse the string from left to right
     * - Compare current value with next value
     * - If current < next, subtract
     * - Else add
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int romanToIntBrute(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i));
            int next = (i + 1 < s.length()) ? map.get(s.charAt(i + 1)) : 0;

            if (curr < next) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }

    /**
     * Approach 2: Optimised Left-to-Right Traversal
     *
     * Idea:
     * - Same logic as brute but cleaner implementation
     * - Avoid handling subtraction cases explicitly
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int romanToIntBetter(String s) {
        Map<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i < s.length() - 1 &&
                values.get(s.charAt(i)) < values.get(s.charAt(i + 1))) {
                sum -= values.get(s.charAt(i));
            } else {
                sum += values.get(s.charAt(i));
            }
        }
        return sum;
    }

    /**
     * Approach 3: Optimal (Right-to-Left Traversal)
     *
     * Idea:
     * - Traverse from right to left
     * - Maintain previous numeral value
     * - If current < previous, subtract
     * - Else add
     *
     * This is the most preferred interview solution.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int romanToIntOptimal(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int total = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));

            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }
            prev = curr;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(romanToIntBrute("III"));       // 3
        System.out.println(romanToIntBetter("LVIII"));   // 58
        System.out.println(romanToIntOptimal("MCMXCIV"));// 1994
    }
}
