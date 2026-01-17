/**
 * Problem:
 * Reverse the order of words in a string.
 */

class ReverseWordsInString {

  /**
   * Approach 1: Brute Force (Split + Reverse)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static reverseWordsBrute(s: string): string {
    const words = s.trim().split(/\s+/);
    return words.reverse().join(" ");
  }

  /**
   * Approach 2: Two Pointer Scan (Optimal)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1) (excluding output)
   *
   * Stable: Yes
   */
  static reverseWordsOptimal(s: string): string {
    let result = "";
    let i = s.length - 1;

    while (i >= 0) {
      while (i >= 0 && s[i] === " ") i--;
      if (i < 0) break;

      let j = i;
      while (j >= 0 && s[j] !== " ") j--;

      if (result.length > 0) result += " ";
      result += s.substring(j + 1, i + 1);

      i = j - 1;
    }

    return result;
  }
}

// Test
console.log(ReverseWordsInString.reverseWordsBrute("  hello   world  "));
console.log(ReverseWordsInString.reverseWordsOptimal("  hello   world  "));
