/**
 * Problem:
 * Given an unsorted array of integers, find the length of the longest
 * consecutive elements sequence.
 *
 * A consecutive sequence means numbers follow each other with a difference of 1.
 * The sequence does NOT need to be contiguous in the array.
 *
 * Example:
 * Input:  [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: Longest sequence is [1, 2, 3, 4]
 */

class LongestConsecutiveSequence {

  /**
   * Approach 1: Brute Force
   *
   * Idea:
   * - For each element, try to find the next consecutive numbers
   *   by scanning the entire array.
   * - Keep extending the sequence until the next number is not found.
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   *
   * Stable: Not Applicable
   * - Order does not matter, only length is returned.
   */
  static longestConsecutiveBrute(nums: number[]): number {
    let longest = 0;

    for (let i = 0; i < nums.length; i++) {
      let current = nums[i];
      let count = 1;

      while (this.linearSearch(nums, current + 1)) {
        current++;
        count++;
      }

      longest = Math.max(longest, count);
    }

    return longest;
  }

  private static linearSearch(arr: number[], target: number): boolean {
    for (let num of arr) {
      if (num === target) return true;
    }
    return false;
  }

  /**
   * Approach 2: Better (Sorting)
   *
   * Idea:
   * - Sort the array.
   * - Traverse and count consecutive increasing numbers.
   * - Skip duplicates.
   *
   * Time Complexity: O(n log n)
   * Space Complexity: O(1) (ignoring sort space)
   *
   * Stable: Not Applicable
   */
  static longestConsecutiveSorting(nums: number[]): number {
    if (nums.length === 0) return 0;

    nums.sort((a, b) => a - b);

    let longest = 1;
    let currentStreak = 1;

    for (let i = 1; i < nums.length; i++) {
      if (nums[i] === nums[i - 1]) {
        continue;
      } else if (nums[i] === nums[i - 1] + 1) {
        currentStreak++;
      } else {
        longest = Math.max(longest, currentStreak);
        currentStreak = 1;
      }
    }

    return Math.max(longest, currentStreak);
  }

  /**
   * Approach 3: Optimal (HashSet)
   *
   * Idea:
   * - Store all elements in a Set.
   * - Only start counting when the current number is the start
   *   of a sequence (num - 1 does not exist).
   * - Count forward until sequence breaks.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Not Applicable
   */
  static longestConsecutiveOptimal(nums: number[]): number {
    const set = new Set(nums);
    let longest = 0;

    for (let num of set) {
      if (!set.has(num - 1)) {
        let currentNum = num;
        let count = 1;

        while (set.has(currentNum + 1)) {
          currentNum++;
          count++;
        }

        longest = Math.max(longest, count);
      }
    }

    return longest;
  }
}

// Test cases
const nums = [100, 4, 200, 1, 3, 2];

console.log(LongestConsecutiveSequence.longestConsecutiveBrute(nums));
console.log(LongestConsecutiveSequence.longestConsecutiveSorting([...nums]));
console.log(LongestConsecutiveSequence.longestConsecutiveOptimal(nums));
