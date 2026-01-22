public class IntegerToRoman {

    /**
     * Problem:
     * Convert an integer to a Roman numeral.
     *
     * Constraints:
     * 1 <= num <= 3999
     */

    /**
     * Approach 1: Brute Force (Repeated Subtraction)
     *
     * Idea:
     * - Repeatedly subtract the largest possible Roman value
     * - Append corresponding Roman symbol
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String intToRomanBrute(int num) {
        StringBuilder sb = new StringBuilder();

        while (num >= 1000) { sb.append("M"); num -= 1000; }
        while (num >= 900)  { sb.append("CM"); num -= 900; }
        while (num >= 500)  { sb.append("D"); num -= 500; }
        while (num >= 400)  { sb.append("CD"); num -= 400; }
        while (num >= 100)  { sb.append("C"); num -= 100; }
        while (num >= 90)   { sb.append("XC"); num -= 90; }
        while (num >= 50)   { sb.append("L"); num -= 50; }
        while (num >= 40)   { sb.append("XL"); num -= 40; }
        while (num >= 10)   { sb.append("X"); num -= 10; }
        while (num >= 9)    { sb.append("IX"); num -= 9; }
        while (num >= 5)    { sb.append("V"); num -= 5; }
        while (num >= 4)    { sb.append("IV"); num -= 4; }
        while (num >= 1)    { sb.append("I"); num -= 1; }

        return sb.toString();
    }

    /**
     * Approach 2: Better (Predefined Arrays)
     *
     * Idea:
     * - Store Roman values and symbols in arrays
     * - Iterate and subtract greedily
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String intToRomanBetter(int num) {
        int[] values = {
            1000, 900, 500, 400,
            100, 90, 50, 40,
            10, 9, 5, 4, 1
        };

        String[] symbols = {
            "M", "CM", "D", "CD",
            "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                sb.append(symbols[i]);
                num -= values[i];
            }
        }
        return sb.toString();
    }

    /**
     * Approach 3: Optimal (Greedy)
     *
     * Idea:
     * - Greedy subtraction using sorted Roman values
     * - This is the standard interview solution
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String intToRomanOptimal(int num) {
        return intToRomanBetter(num);
    }

    public static void main(String[] args) {
        System.out.println(intToRomanBrute(3));     // III
        System.out.println(intToRomanBetter(58));   // LVIII
        System.out.println(intToRomanOptimal(1994));// MCMXCIV
    }
}
