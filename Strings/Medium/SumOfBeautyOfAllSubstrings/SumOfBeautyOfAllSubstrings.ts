/**
 * Problem:
 * Given a string s, return the sum of beauty of all substrings.
 *
 * Beauty of a substring =
 * (maximum frequency of any character) -
 * (minimum frequency of any character, excluding zero frequency)
 */

export class SumOfBeautyOfAllSubstrings {
  /**
   * ------------------------------------------------------------
   * Approach 1: Brute Force (Substring + HashMap)
   * ------------------------------------------------------------
   *
   * Idea:
   * - Generate all substrings
   * - For each substring:
   *   - Build frequency map from scratch
   *   - Find max and min frequency
   *   - Add (max - min) to result
   *
   * Time Complexity:
   * - Substrings: O(n^2)
   * - Frequency per substring: O(n)
   * - Total: O(n^3)
   *
   * Space Complexity:
   * - Map of at most 26 characters: O(1)
   *
   * Stable: Yes
   */
  static beautySumBrute(s: string): number {
    const n = s.length;
    let result = 0;

    for (let i = 0; i < n; i++) {
      for (let j = i; j < n; j++) {
        const sub = s.substring(i, j + 1);
        result += this.getBeauty(sub);
      }
    }

    return result;
  }

  private static getBeauty(str: string): number {
    const freq: Map<string, number> = new Map();

    for (const ch of str) {
      freq.set(ch, (freq.get(ch) ?? 0) + 1);
    }

    let max = Number.MIN_SAFE_INTEGER;
    let min = Number.MAX_SAFE_INTEGER;

    for (const count of freq.values()) {
      max = Math.max(max, count);
      min = Math.min(min, count);
    }

    return max - min;
  }

  /**
   * ------------------------------------------------------------
   * Approach 2: Optimal (Incremental Frequency Expansion)
   * ------------------------------------------------------------
   *
   * Idea:
   * - Fix starting index i
   * - Maintain frequency array
   * - Expand substring by increasing j
   * - Update frequency incrementally
   * - Compute beauty in O(26) time
   *
   * Key Insight:
   * - Each substring differs by only one character
   * - Alphabet size is fixed (26)
   *
   * Time Complexity:
   * - O(n^2 * 26) -> O(n^2)
   *
   * Space Complexity:
   * - Frequency array of size 26: O(1)
   *
   * Stable: Yes
   */
  static beautySumOptimal(s: string): number {
    const n = s.length;
    let result = 0;

    for (let i = 0; i < n; i++) {
      const freq = new Array<number>(26).fill(0);

      for (let j = i; j < n; j++) {
        const index = s.charCodeAt(j) - "a".charCodeAt(0);
        freq[index]++;
        result += this.calculateBeauty(freq);
      }
    }

    return result;
  }

  private static calculateBeauty(freq: number[]): number {
    let max = 0;
    let min = Number.MAX_SAFE_INTEGER;

    for (const count of freq) {
      if (count > 0) {
        max = Math.max(max, count);
        min = Math.min(min, count);
      }
    }

    return max - min;
  }
}

/* Example usage */
const s = "aabcbaa";
console.log(SumOfBeautyOfAllSubstrings.beautySumBrute(s));
console.log(SumOfBeautyOfAllSubstrings.beautySumOptimal(s));
