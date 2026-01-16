/**
 * Problem:
 * Remove the outermost parentheses from each
 * primitive valid parentheses substring.
 */

class RemoveOutermostParentheses {

    /**
     * Approach 1: Brute Force (Using Stack)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    static removeOuterBrute(s: string): string {
      let result = "";
      const stack: string[] = [];
  
      for (const ch of s) {
        if (ch === "(") {
          if (stack.length > 0) {
            result += ch;
          }
          stack.push(ch);
        } else {
          stack.pop();
          if (stack.length > 0) {
            result += ch;
          }
        }
      }
  
      return result;
    }
  
    /**
     * Approach 2: Counter Based (Optimal)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static removeOuterOptimal(s: string): string {
      let result = "";
      let depth = 0;
  
      for (const ch of s) {
        if (ch === "(") {
          if (depth > 0) {
            result += ch;
          }
          depth++;
        } else {
          depth--;
          if (depth > 0) {
            result += ch;
          }
        }
      }
  
      return result;
    }
  }
  
  // Test
  console.log(RemoveOutermostParentheses.removeOuterBrute("(()())(())"));
  console.log(RemoveOutermostParentheses.removeOuterOptimal("(()())(())"));
  
  console.log(RemoveOutermostParentheses.removeOuterBrute("()()"));
  console.log(RemoveOutermostParentheses.removeOuterOptimal("()()"));
  