import java.util.Stack;

public class ReverseAStack {

    /**
     * Problem:
     * Given a stack of integers, reverse it using recursion.
     * Only stack operations are allowed.
     * No loops or extra data structures for the optimal solution.
     */

    /* ============================================================
     * Approach 1: Brute Force (Using Extra Stack)
     *
     * Idea:
     * - Pop elements from original stack
     * - Push them into a temporary stack
     * - This reverses the order
     *
     * Note:
     * - Uses extra stack and loops
     * - Violates recursion-only constraint
     * - Included for understanding
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     * ============================================================
     */
    public static void reverseStackBrute(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();

        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    /* ============================================================
     * Approach 2: Recursive Reversal (Core Recursive Solution)
     *
     * Idea:
     * - Pop the top element
     * - Recursively reverse remaining stack
     * - Insert popped element at the bottom using recursion
     *
     * Fully satisfies the problem constraints.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)  // recursion stack
     *
     * Stable: Yes
     * ============================================================
     */
    public static void reverseStackRecursive(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop();
        reverseStackRecursive(stack);
        insertAtBottom(stack, top);
    }

    private static void insertAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }

        int top = stack.pop();
        insertAtBottom(stack, value);
        stack.push(top);
    }

    /* ============================================================
     * Approach 3: Optimal Recursive (Cleaner Abstraction)
     *
     * Idea:
     * - Same logic as Approach 2
     * - Clear separation of reverse and insert responsibilities
     * - Commonly expected interview solution
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     * ============================================================
     */
    public static void reverseStackOptimal(Stack<Integer> stack) {
        reverse(stack);
    }

    private static void reverse(Stack<Integer> stack) {
        if (stack.size() <= 1) return;

        int x = stack.pop();
        reverse(stack);
        pushToBottom(stack, x);
    }

    private static void pushToBottom(Stack<Integer> stack, int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            return;
        }

        int temp = stack.pop();
        pushToBottom(stack, x);
        stack.push(temp);
    }

    /* ============================================================
     * Driver Code
     * ============================================================
     */
    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);

        Stack<Integer> stack2 = (Stack<Integer>) stack1.clone();
        Stack<Integer> stack3 = (Stack<Integer>) stack1.clone();

        reverseStackBrute(stack1);
        System.out.println(stack1);

        reverseStackRecursive(stack2);
        System.out.println(stack2);

        reverseStackOptimal(stack3);
        System.out.println(stack3);
    }
}
