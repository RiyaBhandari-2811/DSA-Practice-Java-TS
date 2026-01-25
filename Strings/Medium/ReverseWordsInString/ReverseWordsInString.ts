class ReverseWordsInStringTS {
  /**
   * Problem:
   * Given a string s containing letters, digits, and spaces,
   * reverse the order of words.
   *
   * - Remove leading and trailing spaces
   * - Ensure only one space between words
   *
   * Example:
   * Input:  "  hello   world  "
   * Output: "world hello"
   */

  /**
   * ------------------------------------------------------------
   * Approach 1: Brute Force (Split + Reverse)
   *
   * Idea:
   * - Trim the string
   * - Split using regex to handle multiple spaces
   * - Traverse array from end and rebuild string
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static reverseWordsBrute(s: string): string {
    if (!s || s.length === 0) return "";

    const parts = s.trim().split(/\s+/);
    let result: string[] = [];

    for (let i = parts.length - 1; i >= 0; i--) {
      result.push(parts[i]);
    }

    return result.join(" ");
  }

  /**
   * ------------------------------------------------------------
   * Approach 2: Better (Two Pointers, Right to Left)
   *
   * Idea:
   * - Traverse string from right to left
   * - Skip spaces
   * - Identify word boundaries using pointers
   * - Append words to result
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1) (excluding output)
   *
   * Stable: Yes
   */
  static reverseWordsTwoPointers(s: string): string {
    if (!s || s.length === 0) return "";

    let result: string[] = [];
    let i = s.length - 1;

    while (i >= 0) {
      // Skip spaces
      while (i >= 0 && s[i] === " ") i--;

      if (i < 0) break;

      let end = i;

      // Find start of word
      while (i >= 0 && s[i] !== " ") i--;

      let start = i + 1;

      result.push(s.substring(start, end + 1));
    }

    return result.join(" ");
  }

  /**
   * ------------------------------------------------------------
   * Approach 3: Stack Based
   *
   * Idea:
   * - Traverse string left to right
   * - Extract words and push into stack
   * - Pop words to reverse order
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static reverseWordsStack(s: string): string {
    if (!s || s.length === 0) return "";

    let stack: string[] = [];
    let n = s.length;
    let i = 0;

    while (i < n) {
      // Skip spaces
      while (i < n && s[i] === " ") i++;

      if (i >= n) break;

      let start = i;

      // Capture word
      while (i < n && s[i] !== " ") i++;

      stack.push(s.substring(start, i));
    }

    let result: string[] = [];

    while (stack.length > 0) {
      result.push(stack.pop()!);
    }

    return result.join(" ");
  }
}

/**
 * ------------------------------------------------------------
 * Testing
 */
const input = "  hello   world  ";

console.log(ReverseWordsInStringTS.reverseWordsBrute(input));
console.log(ReverseWordsInStringTS.reverseWordsTwoPointers(input));
console.log(ReverseWordsInStringTS.reverseWordsStack(input));
