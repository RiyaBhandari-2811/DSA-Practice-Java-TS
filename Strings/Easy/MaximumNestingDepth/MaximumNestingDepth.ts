class MaximumNestingDepth {
  /**
   * Problem:
   * Given a valid parentheses string s,
   * return the maximum nesting depth of parentheses.
   *
   * Example:
   * s = "(1+(2*3)+((8)/4))+1"
   * Output = 3
   */

  /**
   * Approach 1: Brute Force (Repeated Scan)
   *
   * Idea:
   * - For each '(' in the string, scan forward
   * - Count how many '(' are opened before they are closed
   * - Track the maximum depth found
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static maxDepthBruteForce(s: string): number {
    let maxDepth = 0;

    for (let i = 0; i < s.length; i++) {
      if (s[i] === "(") {
        let depth = 0;
        for (let j = i; j < s.length; j++) {
          if (s[j] === "(") depth++;
          else if (s[j] === ")") depth--;

          maxDepth = Math.max(maxDepth, depth);
        }
      }
    }
    return maxDepth;
  }

  /**
   * Approach 2: Using Stack
   *
   * Idea:
   * - Push '(' onto stack
   * - Pop when ')' is encountered
   * - Maximum stack size is the nesting depth
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static maxDepthUsingStack(s: string): number {
    const stack: string[] = [];
    let maxDepth = 0;

    for (const ch of s) {
      if (ch === "(") {
        stack.push(ch);
        maxDepth = Math.max(maxDepth, stack.length);
      } else if (ch === ")") {
        stack.pop();
      }
    }
    return maxDepth;
  }

  /**
   * Approach 3: Optimised Counter Based Traversal
   *
   * Idea:
   * - No stack required because string is valid
   * - Increment depth on '('
   * - Decrement depth on ')'
   * - Track maximum depth reached
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static maxDepthOptimal(s: string): number {
    let currentDepth = 0;
    let maxDepth = 0;

    for (const ch of s) {
      if (ch === "(") {
        currentDepth++;
        maxDepth = Math.max(maxDepth, currentDepth);
      } else if (ch === ")") {
        currentDepth--;
      }
    }
    return maxDepth;
  }
}

// Driver code
const s = "(1+(2*3)+((8)/4))+1";

console.log(MaximumNestingDepth.maxDepthBruteForce(s));
console.log(MaximumNestingDepth.maxDepthUsingStack(s));
console.log(MaximumNestingDepth.maxDepthOptimal(s));
