
import java.util.Stack;

public class MaximumNestingDepth {

    /**
     * Problem: Given a valid parentheses string s, return the maximum nesting
     * depth of parentheses.
     *
     * Example: s = "(1+(2*3)+((8)/4))+1" Output = 3
     */
    /**
     * Approach 1: Brute Force (Repeated Scan)
     *
     * Idea: - For each '(' in the string, scan forward - Count how many '(' are
     * opened before they are closed - Track the maximum depth found
     *
     * This approach is inefficient and mostly theoretical, but useful to show
     * brute force thinking.
     *
     * Time Complexity: O(n^2) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int maxDepthBruteForce(String s) {
        int maxDepth = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                int depth = 0;
                for (int j = i; j < s.length(); j++) {
                    if (s.charAt(j) == '(') {
                        depth++; 
                    }else if (s.charAt(j) == ')') {
                        depth--;
                    }

                    maxDepth = Math.max(maxDepth, depth);
                }
            }
        }
        return maxDepth;
    }

    /**
     * Approach 2: Using Stack
     *
     * Idea: - Push '(' onto stack - Pop when ')' is encountered - Maximum size
     * of stack at any time is the nesting depth
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static int maxDepthUsingStack(String s) {
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
                maxDepth = Math.max(maxDepth, stack.size());
            } else if (ch == ')') {
                stack.pop();
            }
        }
        return maxDepth;
    }

    /**
     * Approach 3: Optimised Counter Based Traversal
     *
     * Idea: - Since the string is guaranteed to be valid, we do not need a
     * stack - Maintain a counter for current depth - Increment on '(' and
     * decrement on ')' - Track maximum depth reached
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int maxDepthOptimal(String s) {
        int currentDepth = 0;
        int maxDepth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (ch == ')') {
                currentDepth--;
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        String s = "(1+(2*3)+((8)/4))+1";

        System.out.println(maxDepthBruteForce(s));
        System.out.println(maxDepthUsingStack(s));
        System.out.println(maxDepthOptimal(s));
    }
}
