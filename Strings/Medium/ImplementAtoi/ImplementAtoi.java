public class ImplementAtoi {

    /**
     * Problem:
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
     *
     * Rules:
     * 1. Ignore leading whitespaces
     * 2. Handle optional '+' or '-' sign
     * 3. Read digits until non-digit is found
     * 4. Clamp result within Integer range [-2^31, 2^31 - 1]
     */

    /**
     * Approach 1: Brute Force (Using Long Parsing)
     *
     * Idea:
     * - Trim leading spaces
     * - Extract sign
     * - Read digits into a StringBuilder
     * - Parse using Long to avoid overflow
     * - Clamp result manually
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static int myAtoiBrute(String s) {
        if (s == null || s.length() == 0) return 0;

        s = s.trim();
        if (s.length() == 0) return 0;

        int index = 0;
        boolean negative = false;

        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            negative = s.charAt(index) == '-';
            index++;
        }

        StringBuilder num = new StringBuilder();

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            num.append(s.charAt(index));
            index++;
        }

        if (num.length() == 0) return 0;

        long value = Long.parseLong(num.toString());
        if (negative) value = -value;

        if (value > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (value < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) value;
    }

    /**
     * Approach 2: Iterative Parsing with Overflow Check
     *
     * Idea:
     * - Traverse character by character
     * - Build number using integer arithmetic
     * - Check overflow before multiplying by 10
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int myAtoiBetter(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0;
        int n = s.length();

        while (i < n && s.charAt(i) == ' ') i++;

        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        int result = 0;

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    /**
     * Approach 3: Optimal (Single Pass, No Extra Space)
     *
     * Idea:
     * - Single traversal
     * - Inline overflow detection
     * - Stop immediately on invalid character
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int myAtoiOptimal(String s) {
        int i = 0;
        int n = s.length();
        int sign = 1;
        int result = 0;

        while (i < n && s.charAt(i) == ' ') i++;

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i) == '-' ? -1 : 1;
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        System.out.println(myAtoiBrute("42"));
        System.out.println(myAtoiBetter("   -42"));
        System.out.println(myAtoiOptimal("4193 with words"));

        System.out.println(myAtoiOptimal("2147483648"));
        System.out.println(myAtoiOptimal("-91283472332"));
    }
}
