class ReverseAStack {
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
     * - Uses extra stack
     * - Uses loop, hence violates constraints
     * - Included for understanding
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     * ============================================================
     */
    static reverseStackBrute(stack: number[]): void {
        const temp: number[] = [];

        while (stack.length > 0) {
            temp.push(stack.pop() as number);
        }

        while (temp.length > 0) {
            stack.push(temp.pop() as number);
        }
    }

    /* ============================================================
     * Approach 2: Recursive Reversal (Core Recursive Solution)
     *
     * Idea:
     * - Pop top element
     * - Recursively reverse remaining stack
     * - Insert popped element at bottom using recursion
     *
     * Fully satisfies problem constraints.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)  // recursion stack
     *
     * Stable: Yes
     * ============================================================
     */
    static reverseStackRecursive(stack: number[]): void {
        if (stack.length === 0) return;

        const top = stack.pop() as number;
        this.reverseStackRecursive(stack);
        this.insertAtBottom(stack, top);
    }

    private static insertAtBottom(stack: number[], value: number): void {
        if (stack.length === 0) {
            stack.push(value);
            return;
        }

        const top = stack.pop() as number;
        this.insertAtBottom(stack, value);
        stack.push(top);
    }

    /* ============================================================
     * Approach 3: Optimal Recursive (Cleaner Abstraction)
     *
     * Idea:
     * - Same as Approach 2
     * - Clear separation of concerns
     * - Most expected interview solution
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     * ============================================================
     */
    static reverseStackOptimal(stack: number[]): void {
        this.reverse(stack);
    }

    private static reverse(stack: number[]): void {
        if (stack.length <= 1) return;

        const x = stack.pop() as number;
        this.reverse(stack);
        this.pushToBottom(stack, x);
    }

    private static pushToBottom(stack: number[], x: number): void {
        if (stack.length === 0) {
            stack.push(x);
            return;
        }

        const temp = stack.pop() as number;
        this.pushToBottom(stack, x);
        stack.push(temp);
    }
}

/* ============================================================
 * Driver Code
 * ============================================================
 */
const stack1: number[] = [1, 2, 3, 4];
const stack2: number[] = [...stack1];
const stack3: number[] = [...stack1];

ReverseAStack.reverseStackBrute(stack1);
console.log(stack1);

ReverseAStack.reverseStackRecursive(stack2);
console.log(stack2);

ReverseAStack.reverseStackOptimal(stack3);
console.log(stack3);
