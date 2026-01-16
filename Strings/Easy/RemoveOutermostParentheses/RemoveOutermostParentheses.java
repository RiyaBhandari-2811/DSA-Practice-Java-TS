public class RemoveOutermostParentheses {

    /**
     * Problem:
     * Remove the outermost parentheses from every primitive
     * valid parentheses substring.
     */

    /**
     * Approach 1: Brute Force (Using Stack)
     *
     * Idea:
     * - Use stack to track depth
     * - Skip first '(' and last ')' of each primitive
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static String removeOuterBrute(String s) {
        StringBuilder result = new StringBuilder();
        java.util.Stack<Character> stack = new java.util.Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (!stack.isEmpty()) {
                    result.append(ch);
                }
                stack.push(ch);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

    /**
     * Approach 2: Counter Based (Optimal)
     *
     * Idea:
     * - Maintain a depth counter
     * - Skip '(' when depth == 0
     * - Skip ')' when depth becomes 0
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static String removeOuterOptimal(String s) {
        StringBuilder result = new StringBuilder();
        int depth = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                if (depth > 0) {
                    result.append(ch);
                }
                depth++;
            } else {
                depth--;
                if (depth > 0) {
                    result.append(ch);
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String s1 = "(()())(())";
        String s2 = "()()";

        System.out.println(removeOuterBrute(s1));
        System.out.println(removeOuterOptimal(s1));

        System.out.println(removeOuterBrute(s2));
        System.out.println(removeOuterOptimal(s2));
    }
}
