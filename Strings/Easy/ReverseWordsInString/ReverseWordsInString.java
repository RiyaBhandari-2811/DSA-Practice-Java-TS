public class ReverseWordsInString {

    /**
     * Problem:
     * Reverse the order of words in a string.
     */

    /**
     * Approach 1: Brute Force (Split + Reverse)
     *
     * Idea:
     * - Trim the string
     * - Split by spaces
     * - Traverse from end and build result
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static String reverseWordsBrute(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0) result.append(" ");
        }

        return result.toString();
    }

    /**
     * Approach 2: Two Pointer Scan (Optimal)
     *
     * Idea:
     * - Traverse string from end
     * - Extract words manually
     * - Append with single space
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) (excluding output)
     *
     * Stable: Yes
     */
    public static String reverseWordsOptimal(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ') i--;

            if (i < 0) break;

            int j = i;
            while (j >= 0 && s.charAt(j) != ' ') j--;

            if (result.length() > 0) result.append(" ");
            result.append(s.substring(j + 1, i + 1));

            i = j - 1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "  hello   world  ";

        System.out.println(reverseWordsBrute(s));
        System.out.println(reverseWordsOptimal(s));
    }
}
