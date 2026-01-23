public class LongestPalindromicSubstring {

    /**
     * Problem:
     * Given a string s, return the longest palindromic substring in s.
     */

    /**
     * Approach 1: Brute Force (Check All Substrings)
     *
     * Idea:
     * - Generate all substrings
     * - Check each substring if it is a palindrome
     * - Track the longest one
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String longestPalindromeBrute(String s) {
        int n = s.length();
        String ans = "";

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    if (j - i + 1 > ans.length()) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }

    /**
     * Approach 2: Better Brute Force (DP Check Cache)
     *
     * Idea:
     * - Use DP table dp[i][j]
     * - dp[i][j] = true if substring i..j is palindrome
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     *
     * Stable: Yes
     */
    public static String longestPalindromeDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) &&
                        (len <= 2 || dp[i + 1][j - 1])) {

                    dp[i][j] = true;

                    if (len > ans.length()) {
                        ans = s.substring(i, j + 1);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * Approach 3: Expand Around Center
     *
     * Idea:
     * - Every palindrome expands from a center
     * - Handle odd and even length palindromes
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String longestPalindromeExpand(String s) {
        if (s == null || s.length() < 2) return s;

        int start = 0, maxLen = 1;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private static int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    /**
     * Approach 4: Optimal (Manacher’s Algorithm)
     *
     * Idea:
     * - Transform string with separators
     * - Use previously computed palindrome radii
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static String longestPalindromeManacher(String s) {
        StringBuilder t = new StringBuilder("^");
        for (char c : s.toCharArray()) {
            t.append("#").append(c);
        }
        t.append("#$");

        int[] p = new int[t.length()];
        int center = 0, right = 0;

        for (int i = 1; i < t.length() - 1; i++) {
            int mirror = 2 * center - i;

            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            while (t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i])) {
                p[i]++;
            }

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        int maxLen = 0, centerIndex = 0;
        for (int i = 1; i < p.length - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        int start = (centerIndex - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "babad";

        System.out.println(longestPalindromeBrute(s));
        System.out.println(longestPalindromeDP(s));
        System.out.println(longestPalindromeExpand(s));
        System.out.println(longestPalindromeManacher(s));
    }
}
