
public class ImplementAtoiRecursive {

    /**
     * Approach: Recursive Parsing
     *
     * Idea: - Skip leading whitespaces - Handle optional '+' or '-' sign -
     * Recursively process digits - Build number using integer arithmetic -
     * Detect overflow before multiplying by 10 - Stop recursion on non-digit
     *
     * Time Complexity: O(n) Space Complexity: O(n) // recursion stack
     *
     * Stable: Yes
     */
    public static int myAtoiRecursive(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int index = 0;
        int n = s.length();

        // Skip leading whitespaces
        while (index < n && s.charAt(index) == ' ') {
            index++;
        }

        if (index == n) {
            return 0;
        }

        int sign = 1;
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            sign = s.charAt(index) == '-' ? -1 : 1;
            index++;
        }

        return parseDigits(s, index, n, 0, sign);
    }

    private static int parseDigits(String s, int index, int n, int result, int sign) {
        // Base case: end of string or non-digit
        if (index >= n || !Character.isDigit(s.charAt(index))) {
            return result * sign;
        }

        int digit = s.charAt(index) - '0';

        // Overflow detection
        if (result > Integer.MAX_VALUE / 10
                || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }

        return parseDigits(
                s,
                index + 1,
                n,
                result * 10 + digit,
                sign
        );
    }
}
