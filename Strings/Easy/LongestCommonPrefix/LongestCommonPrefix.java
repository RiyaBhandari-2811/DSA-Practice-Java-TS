
public class LongestCommonPrefix {

    /**
     * Problem: Find the longest common prefix among all strings.
     */
    /**
     * Approach 1: Brute Force (Character by Character)
     *
     * Idea: - Compare characters at same index for all strings - Stop when
     * mismatch occurs
     *
     * Time Complexity: O(n * m) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String longestCommonPrefixBrute(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int index = 0;

        while (true) {
            if (index >= strs[0].length()) {
                return strs[0].substring(0, index);
            }

            char ch = strs[0].charAt(index);

            for (int i = 1; i < strs.length; i++) {
                if (index >= strs[i].length() || strs[i].charAt(index) != ch) {
                    return strs[0].substring(0, index);
                }
            }
            index++;
        }
    }

    /**
     * Approach 2: Sorting (Optimal)
     *
     * Idea: - Sort the array - Only compare first and last strings
     *
     * Time Complexity: O(n log n + m) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String longestCommonPrefixOptimal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        java.util.Arrays.sort(strs);

        String first = strs[0];
        String last = strs[strs.length - 1];

        int i = 0;
        while (i < first.length() && i < last.length()
                && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        return first.substring(0, i);
    }

    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};

        System.out.println(longestCommonPrefixBrute(strs1));
        System.out.println(longestCommonPrefixOptimal(strs1));

        System.out.println(longestCommonPrefixBrute(strs2));
        System.out.println(longestCommonPrefixOptimal(strs2));
    }
}
