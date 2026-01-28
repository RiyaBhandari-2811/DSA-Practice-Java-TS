import java.util.Stack;

public class SortAStack {

    /**
     * Problem:
     * Given a stack of integers, sort it in descending order using recursion.
     * Top of the stack should contain the greatest element.
     * Only stack operations are allowed.
     */

    /* ============================================================
     * Approach 1: Brute Force (Using Extra Stack)
     *
     * Idea:
     * - Pop elements from original stack
     * - Insert them into a temporary stack in sorted order
     * - Finally move elements back to original stack
     *
     * Note:
     * - This approach is intuitive but violates recursion-only constraint
     * - Included for conceptual completeness
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Stable: No
     * ============================================================
     */
    public static void sortStackBrute(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();

        while (!stack.isEmpty()) {
            int curr = stack.pop();

            while (!temp.isEmpty() && temp.peek() < curr) {
                stack.push(temp.pop());
            }
            temp.push(curr);
        }

        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    /* ============================================================
     * Approach 2: Recursive Sorting (Core Recursive Idea)
     *
     * Idea:
     * - Recursively pop all elements until stack becomes empty
     * - Insert each popped element back in sorted position
     * - Use another recursive function to insert correctly
     *
     * This fully satisfies the problem constraints.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)  // recursion stack
     *
     * Stable: No
     * ============================================================
     */
    public static void sortStackRecursive(Stack<Integer> stack) {
        if (stack.isEmpty()) return;

        int top = stack.pop();
        sortStackRecursive(stack);
        insertInSortedOrder(stack, top);
    }

    private static void insertInSortedOrder(Stack<Integer> stack, int value) {
        if (stack.isEmpty() || stack.peek() <= value) {
            stack.push(value);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, value);
        stack.push(top);
    }

    /* ============================================================
     * Approach 3: Tail-Recursive Style (Optimized Readability)
     *
     * Idea:
     * - Same logic as Approach 2
     * - Slightly clearer separation of responsibilities
     * - Still recursive and constraint-compliant
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Stable: No
     * ============================================================
     */
    public static void sortStackOptimal(Stack<Integer> stack) {
        sort(stack);
    }

    private static void sort(Stack<Integer> stack) {
        if (stack.size() <= 1) return;

        int x = stack.pop();
        sort(stack);
        sortedInsert(stack, x);
    }

    private static void sortedInsert(Stack<Integer> stack, int x) {
        if (stack.isEmpty() || stack.peek() <= x) {
            stack.push(x);
            return;
        }

        int temp = stack.pop();
        sortedInsert(stack, x);
        stack.push(temp);
    }

    /* ============================================================
     * Utility Method
     * ============================================================
     */
    public static void printStack(Stack<Integer> stack) {
        System.out.println(stack);
    }

    public static void main(String[] args) {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(3);
        stack1.push(1);
        stack1.push(4);
        stack1.push(2);

        Stack<Integer> stack2 = (Stack<Integer>) stack1.clone();
        Stack<Integer> stack3 = (Stack<Integer>) stack1.clone();

        sortStackBrute(stack1);
        printStack(stack1);

        sortStackRecursive(stack2);
        printStack(stack2);

        sortStackOptimal(stack3);
        printStack(stack3);
    }
}
