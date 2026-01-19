/**
 * Problem:
 * Find the longest common prefix among all strings.
 */

class LongestCommonPrefix {
  /**
   * Approach 1: Brute Force (Character by Character)
   *
   * Time Complexity: O(n * m)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static longestCommonPrefixBrute(strs: string[]): string {
    if (strs.length === 0) return "";

    let index = 0;

    while (true) {
      if (index >= strs[0].length) {
        return strs[0].substring(0, index);
      }

      const ch = strs[0][index];

      for (let i = 1; i < strs.length; i++) {
        if (index >= strs[i].length || strs[i][index] !== ch) {
          return strs[0].substring(0, index);
        }
      }

      index++;
    }
  }

  /**
   * Approach 2: Sorting (Optimal)
   *
   * Time Complexity: O(n log n + m)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static longestCommonPrefixOptimal(strs: string[]): string {
    if (strs.length === 0) return "";

    strs.sort();

    const first = strs[0];
    const last = strs[strs.length - 1];

    let i = 0;
    while (i < first.length && i < last.length && first[i] === last[i]) {
      i++;
    }

    return first.substring(0, i);
  }
}

// Test
console.log(
  LongestCommonPrefix.longestCommonPrefixBrute(["flower", "flow", "flight"]),
);
console.log(
  LongestCommonPrefix.longestCommonPrefixOptimal(["flower", "flow", "flight"]),
);

console.log(
  LongestCommonPrefix.longestCommonPrefixBrute(["dog", "racecar", "car"]),
);
console.log(
  LongestCommonPrefix.longestCommonPrefixOptimal(["dog", "racecar", "car"]),
);
