/**
 * Problem:
 * Sort characters in a string by descending frequency.
 * If two characters have the same frequency, any order is acceptable.
 */

class SortCharactersByFrequency {
  /* ============================================================
       Approach 1: Pure Brute Force
       ============================================================ */

  /**
   * Idea:
   * - Count frequency using Map
   * - Repeatedly find the character with max frequency
   * - Append it and remove from map
   *
   * Time Complexity:
   * - Frequency count: O(n)
   * - Finding max k times: O(k^2)
   * - Total: O(n + k^2)
   *
   * Space Complexity: O(k)
   *
   * Stable: No
   */
  static frequencySortBrute(s: string): string {
    const freq = new Map<string, number>();

    for (const ch of s) {
      freq.set(ch, (freq.get(ch) || 0) + 1);
    }

    let result = "";

    while (freq.size > 0) {
      let maxChar = "";
      let maxFreq = 0;

      for (const [ch, count] of freq) {
        if (count > maxFreq) {
          maxFreq = count;
          maxChar = ch;
        }
      }

      result += maxChar.repeat(maxFreq);
      freq.delete(maxChar);
    }

    return result;
  }

  /* ============================================================
       Approach 2: Sorting Map Entries (Better)
       ============================================================ */

  /**
   * Idea:
   * - Count frequency
   * - Convert map entries to array
   * - Sort by frequency descending
   * - Build result
   *
   * Time Complexity: O(n + k log k)
   * Space Complexity: O(k)
   *
   * Stable: No
   */
  static frequencySortBySorting(s: string): string {
    const freq = new Map<string, number>();

    for (const ch of s) {
      freq.set(ch, (freq.get(ch) || 0) + 1);
    }

    const entries = Array.from(freq.entries());
    entries.sort((a, b) => b[1] - a[1]);

    let result = "";

    for (const [ch, count] of entries) {
      result += ch.repeat(count);
    }

    return result;
  }

  /* ============================================================
       Approach 3: Max Heap (Priority Queue Simulation)
       ============================================================ */

  /**
   * Idea:
   * - Count frequency
   * - Use array + sort to simulate max heap
   * - Always take highest frequency first
   *
   * Time Complexity: O(n + k log k)
   * Space Complexity: O(k)
   *
   * Stable: No
   */
  static frequencySortHeap(s: string): string {
    const freq = new Map<string, number>();

    for (const ch of s) {
      freq.set(ch, (freq.get(ch) || 0) + 1);
    }

    const heap = Array.from(freq.keys());
    heap.sort((a, b) => freq.get(b)! - freq.get(a)!);

    let result = "";

    for (const ch of heap) {
      result += ch.repeat(freq.get(ch)!);
    }

    return result;
  }

  /* ============================================================
       Approach 4: Bucket Sort (Optimal)
       ============================================================ */

  /**
   * Idea:
   * - Max frequency can be at most n
   * - Create buckets where index = frequency
   * - Place characters into corresponding bucket
   * - Traverse buckets from high to low
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: No
   */
  static frequencySortBucket(s: string): string {
    const freq = new Map<string, number>();

    for (const ch of s) {
      freq.set(ch, (freq.get(ch) || 0) + 1);
    }

    const buckets: string[][] = Array.from({ length: s.length + 1 }, () => []);

    for (const [ch, count] of freq) {
      buckets[count].push(ch);
    }

    let result = "";

    for (let i = buckets.length - 1; i >= 0; i--) {
      for (const ch of buckets[i]) {
        result += ch.repeat(i);
      }
    }

    return result;
  }
}

/* ============================================================
   Test
   ============================================================ */

const s = "tree";

console.log(SortCharactersByFrequency.frequencySortBrute(s));
console.log(SortCharactersByFrequency.frequencySortBySorting(s));
console.log(SortCharactersByFrequency.frequencySortHeap(s));
console.log(SortCharactersByFrequency.frequencySortBucket(s));

console.log(SortCharactersByFrequency.frequencySortBucket("Aabb"));
console.log(SortCharactersByFrequency.frequencySortBucket("cccaaa"));
