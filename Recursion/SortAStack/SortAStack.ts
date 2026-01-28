class SortAStack {
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
   * - Uses loops, so it violates recursion-only constraint
   * - Included for conceptual completeness
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n)
   *
   * Stable: No
   * ============================================================
   */
  static sortStackBrute(stack: number[]): void {
    const temp: number[] = [];

    while (stack.length > 0) {
      const curr = stack.pop() as number;

      while (temp.length > 0 && temp[temp.length - 1] < curr) {
        stack.push(temp.pop() as number);
      }
      temp.push(curr);
    }

    while (temp.length > 0) {
      stack.push(temp.pop() as number);
    }
  }

  /* ============================================================
   * Approach 2: Recursive Sorting (Core Recursive Solution)
   *
   * Idea:
   * - Recursively pop all elements until stack becomes empty
   * - Insert each element back in sorted position
   * - Uses helper recursive function for insertion
   *
   * This fully satisfies the problem constraints.
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n)  // recursion stack
   *
   * Stable: No
   * ============================================================
   */
  static sortStackRecursive(stack: number[]): void {
    if (stack.length === 0) return;

    const top = stack.pop() as number;
    this.sortStackRecursive(stack);
    this.insertInSortedOrder(stack, top);
  }

  private static insertInSortedOrder(stack: number[], value: number): void {
    if (stack.length === 0 || stack[stack.length - 1] <= value) {
      stack.push(value);
      return;
    }

    const top = stack.pop() as number;
    this.insertInSortedOrder(stack, value);
    stack.push(top);
  }

  /* ============================================================
   * Approach 3: Optimal Recursive (Cleaner Abstraction)
   *
   * Idea:
   * - Same logic as Approach 2
   * - Cleaner separation of sort and insert responsibilities
   * - Most commonly expected in interviews
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n)
   *
   * Stable: No
   * ============================================================
   */
  static sortStackOptimal(stack: number[]): void {
    this.sort(stack);
  }

  private static sort(stack: number[]): void {
    if (stack.length <= 1) return;

    const x = stack.pop() as number;
    this.sort(stack);
    this.sortedInsert(stack, x);
  }

  private static sortedInsert(stack: number[], x: number): void {
    if (stack.length === 0 || stack[stack.length - 1] <= x) {
      stack.push(x);
      return;
    }

    const temp = stack.pop() as number;
    this.sortedInsert(stack, x);
    stack.push(temp);
  }
}

/* ============================================================
 * Driver Code
 * ============================================================
 */
const stack1: number[] = [3, 1, 4, 2];
const stack2: number[] = [...stack1];
const stack3: number[] = [...stack1];

SortAStack.sortStackBrute(stack1);
console.log(stack1);

SortAStack.sortStackRecursive(stack2);
console.log(stack2);

SortAStack.sortStackOptimal(stack3);
console.log(stack3);
