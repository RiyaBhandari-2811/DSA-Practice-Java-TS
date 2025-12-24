/**
 * Problem:
 * Find all elements that appear more than n/3 times.
 *
 * Note:
 * - At most two elements can satisfy this condition.
 */

class MajorityElementNby3 {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static majorityBrute(nums: number[]): number[] {
    const result: number[] = [];
    const n = nums.length;

    for (let i = 0; i < n; i++) {
      if (result.includes(nums[i])) continue;

      let count = 0;
      for (let j = 0; j < n; j++) {
        if (nums[j] === nums[i]) count++;
      }

      if (count > Math.floor(n / 3)) {
        result.push(nums[i]);
      }
    }
    return result;
  }

  /**
   * Approach 2: Hashing
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static majorityHashing(nums: number[]): number[] {
    const freq = new Map<number, number>();
    const result: number[] = [];
    const n = nums.length;

    for (const num of nums) {
      freq.set(num, (freq.get(num) || 0) + 1);
    }

    for (const [key, value] of freq.entries()) {
      if (value > Math.floor(n / 3)) {
        result.push(key);
      }
    }

    return result;
  }

  /**
   * Approach 3: Boyer Moore Voting Algorithm (Extended) - Optimal
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static majorityOptimal(nums: number[]): number[] {
    let count1 = 0,
      count2 = 0;
    let candidate1 = 0,
      candidate2 = 0;

    for (const num of nums) {
      if (num === candidate1) {
        count1++;
      } else if (num === candidate2) {
        count2++;
      } else if (count1 === 0) {
        candidate1 = num;
        count1 = 1;
      } else if (count2 === 0) {
        candidate2 = num;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }

    count1 = 0;
    count2 = 0;

    for (const num of nums) {
      if (num === candidate1) count1++;
      else if (num === candidate2) count2++;
    }

    const result: number[] = [];
    const n = nums.length;

    if (count1 > Math.floor(n / 3)) result.push(candidate1);
    if (count2 > Math.floor(n / 3)) result.push(candidate2);

    return result;
  }
}

// Test
const nums = [1, 1, 1, 3, 3, 2, 2, 2];
console.log(MajorityElementNby3.majorityBrute(nums));
console.log(MajorityElementNby3.majorityHashing(nums));
console.log(MajorityElementNby3.majorityOptimal(nums));
