
import java.util.HashMap;
import java.util.Map;

public class SumOfBeautyOfAllSubstrings {

    /**
     * Problem: Given a string s, return the sum of beauty of all substrings.
     *
     * Beauty of a substring = (maximum frequency of any character) - (minimum
     * frequency of any character, excluding zero frequency)
     */
    /**
     * ------------------------------------------------------------ Approach 1:
     * Brute Force (Substring + HashMap)
     * ------------------------------------------------------------
     *
     * Idea: - Generate all substrings - For each substring: - Build frequency
     * map from scratch - Find max and min frequency - Add (max - min) to result
     *
     * Time Complexity: - Substrings: O(n^2) - Frequency per substring: O(n) -
     * Total: O(n^3)
     *
     * Space Complexity: - HashMap of at most 26 characters: O(1)
     *
     * Stable: Yes
     */
    public static int beautySumBrute(String s) {
        int n = s.length();
        int result = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String sub = s.substring(i, j + 1);
                result += getBeauty(sub);
            }
        }

        return result;
    }

    private static int getBeauty(String str) {
        Map<Character, Integer> freq = new HashMap<>();

        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int count : freq.values()) {
            max = Math.max(max, count);
            min = Math.min(min, count);
        }

        return max - min;
    }

    /**
     * ------------------------------------------------------------ Approach 2:
     * Optimal (Incremental Frequency Expansion)
     * ------------------------------------------------------------
     *
     * Idea: - Fix starting index i - Maintain frequency array - Expand
     * substring by increasing j - Update frequency incrementally - Compute
     * beauty in O(26) time
     *
     * Key Insight: - Each substring differs by only one character from previous
     * - Alphabet size is fixed (26)
     *
     * Time Complexity: - O(n^2 * 26) → O(n^2)
     *
     * Space Complexity: - Frequency array of size 26: O(1)
     *
     * Stable: Yes
     */
    public static int beautySumOptimal(String s) {
        int n = s.length();
        int result = 0;

        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                result += calculateBeauty(freq);
            }
        }

        return result;
    }

    private static int calculateBeauty(int[] freq) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int count : freq) {
            if (count > 0) {
                max = Math.max(max, count);
                min = Math.min(min, count);
            }
        }

        return max - min;
    }

    public static void main(String[] args) {
        String s = "aabcbaa";

        System.out.println(beautySumBrute(s));
        System.out.println(beautySumOptimal(s));
    }
}
