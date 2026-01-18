
public class LargestOddNumberInString {

    /**
     * Problem: Return the largest-valued odd integer substring from the given
     * numeric string.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Generate all substrings - Check if substring represents an odd
     * number - Track the largest valid substring
     *
     * Time Complexity: O(n^3) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String largestOddBrute(String s) {
        String ans = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);

                // Skip leading zeros
                int k = 0;
                while (k < sub.length() && sub.charAt(k) == '0') {
                    k++;
                }
                if (k == sub.length()) {
                    continue;
                }

                sub = sub.substring(k);
                int lastDigit = sub.charAt(sub.length() - 1) - '0';

                if (lastDigit % 2 == 1) {
                    if (ans.equals("") || sub.length() > ans.length()) {
                        ans = sub;
                    }
                }
            }
        }

        return ans;
    }

    /**
     * Approach 2: Optimal (Greedy)
     *
     * Idea: - The largest odd number must end at the rightmost odd digit - Take
     * substring from start to that index - Remove leading zeros
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String largestOddOptimal(String s) {
        int idx = -1;

        for (int i = s.length() - 1; i >= 0; i--) {
            int digit = s.charAt(i) - '0';
            if (digit % 2 == 1) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            return "";
        }

        String res = s.substring(0, idx + 1);

        int k = 0;
        while (k < res.length() && res.charAt(k) == '0') {
            k++;
        }

        return k == res.length() ? "" : res.substring(k);
    }

    public static void main(String[] args) {
        System.out.println(largestOddBrute("35427"));
        System.out.println(largestOddOptimal("35427"));

        System.out.println(largestOddBrute("4206"));
        System.out.println(largestOddOptimal("4206"));
    }
}
