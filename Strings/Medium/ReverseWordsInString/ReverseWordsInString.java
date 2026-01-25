import java.util.Stack;

public class ReverseWordsInString {

    /**
     * Problem:
     * Given a string s containing letters, digits, and spaces,
     * reverse the order of words.
     *
     * - Remove leading and trailing spaces
     * - Ensure only one space between words
     *
     * Example:
     * Input:  "  hello   world  "
     * Output: "world hello"
     */

    /**
     * ------------------------------------------------------------
     * Approach 1: Brute Force (Split + Reverse)
     *
     * Idea:
     * - Split string using whitespace
     * - Traverse split array from end
     * - Ignore empty strings caused by extra spaces
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static String reverseWordsBrute(String s) {
        if (s == null || s.length() == 0) return "";

        String[] parts = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = parts.length - 1; i >= 0; i--) {
            result.append(parts[i]);
            if (i != 0) result.append(" ");
        }

        return result.toString();
    }

    /**
     * ------------------------------------------------------------
     * Approach 2: Better (Two Pointers, Right to Left)
     *
     * Idea:
     * - Traverse from end of string
     * - Skip spaces
     * - Identify word boundaries using pointers
     * - Append words in forward direction
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (excluding output)
     *
     * Stable: Yes
     */
    public static String reverseWordsTwoPointers(String s) {
        if (s == null || s.length() == 0) return "";

        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            // Skip spaces
            while (i >= 0 && s.charAt(i) == ' ') i--;

            if (i < 0) break;

            int end = i;

            // Find start of word
            while (i >= 0 && s.charAt(i) != ' ') i--;

            int start = i + 1;

            // Append word
            result.append(s.substring(start, end + 1));
            result.append(" ");
        }

        // Remove trailing space
        return result.toString().trim();
    }

    /**
     * ------------------------------------------------------------
     * Approach 3: Stack Based
     *
     * Idea:
     * - Traverse string left to right
     * - Build words character by character
     * - Push complete words into stack
     * - Pop words to reverse order
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static String reverseWordsStack(String s) {
        if (s == null || s.length() == 0) return "";

        Stack<String> stack = new Stack<>();
        int n = s.length();
        int i = 0;

        while (i < n) {
            // Skip spaces
            while (i < n && s.charAt(i) == ' ') i++;

            if (i >= n) break;

            int start = i;

            // Capture word
            while (i < n && s.charAt(i) != ' ') i++;

            stack.push(s.substring(start, i));
        }

        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            result.append(stack.pop());
            if (!stack.isEmpty()) result.append(" ");
        }

        return result.toString();
    }

    /**
     * ------------------------------------------------------------
     * Main Method (Testing)
     */
    public static void main(String[] args) {
        String s = "  hello   world  ";

        System.out.println(reverseWordsBrute(s));
        System.out.println(reverseWordsTwoPointers(s));
        System.out.println(reverseWordsStack(s));
    }
}
