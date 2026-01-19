import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {

    /**
     * Problem:
     * Determine if two strings are isomorphic.
     */

    /**
     * Approach 1: Brute Force (Two Maps)
     *
     * Idea:
     * - Maintain mapping s -> t and t -> s
     * - Ensure consistency in both directions
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static boolean isIsomorphicBrute(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Character> mapST = new HashMap<>();
        Map<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapST.containsKey(c1)) {
                if (mapST.get(c1) != c2) return false;
            } else {
                mapST.put(c1, c2);
            }

            if (mapTS.containsKey(c2)) {
                if (mapTS.get(c2) != c1) return false;
            } else {
                mapTS.put(c2, c1);
            }
        }

        return true;
    }

    /**
     * Approach 2: Optimised (Last Seen Index)
     *
     * Idea:
     * - Track last seen index for characters in both strings
     * - If last seen positions differ, mapping is invalid
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)  // fixed ASCII size
     *
     * Stable: Yes
     */
    public static boolean isIsomorphicOptimal(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] lastS = new int[256];
        int[] lastT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (lastS[c1] != lastT[c2]) return false;

            lastS[c1] = i + 1;
            lastT[c2] = i + 1;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphicBrute("egg", "add"));
        System.out.println(isIsomorphicOptimal("egg", "add"));

        System.out.println(isIsomorphicBrute("foo", "bar"));
        System.out.println(isIsomorphicOptimal("foo", "bar"));
    }
}
