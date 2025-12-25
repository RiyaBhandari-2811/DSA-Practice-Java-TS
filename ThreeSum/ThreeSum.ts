/**
 * Problem:
 * Find all unique triplets whose sum is zero.
 */

class ThreeSum {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n^3)
   * Space Complexity: O(n)
   */
  static threeSumBrute(nums: number[]): number[][] {
    const set = new Set<string>();
    const result: number[][] = [];
    const n = nums.length;

    for (let i = 0; i < n; i++) {
      for (let j = i + 1; j < n; j++) {
        for (let k = j + 1; k < n; k++) {
          if (nums[i] + nums[j] + nums[k] === 0) {
            const triplet = [nums[i], nums[j], nums[k]].sort((a, b) => a - b);
            const key = triplet.join(",");
            if (!set.has(key)) {
              set.add(key);
              result.push(triplet);
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Approach 2: Better (Hashing)
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n)
   */
  static threeSumHashing(nums: number[]): number[][] {
    const resultSet = new Set<string>();
    const result: number[][] = [];
    const n = nums.length;

    for (let i = 0; i < n; i++) {
      const seen = new Set<number>();

      for (let j = i + 1; j < n; j++) {
        const third = -nums[i] - nums[j];
        if (seen.has(third)) {
          const triplet = [nums[i], nums[j], third].sort((a, b) => a - b);
          const key = triplet.join(",");
          if (!resultSet.has(key)) {
            resultSet.add(key);
            result.push(triplet);
          }
        }
        seen.add(nums[j]);
      }
    }

    return result;
  }

  /**
   * Approach 3: Optimal (Sorting + Two Pointers)
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1) (excluding output)
   */
  static threeSumOptimal(nums: number[]): number[][] {
    const result: number[][] = [];
    const n = nums.length;

    nums.sort((a, b) => a - b);

    for (let i = 0; i < n; i++) {
      if (i > 0 && nums[i] === nums[i - 1]) continue;

      let left = i + 1;
      let right = n - 1;

      while (left < right) {
        const sum = nums[i] + nums[left] + nums[right];

        if (sum === 0) {
          result.push([nums[i], nums[left], nums[right]]);
          left++;
          right--;

          while (left < right && nums[left] === nums[left - 1]) left++;
          while (left < right && nums[right] === nums[right + 1]) right--;
        } else if (sum < 0) {
          left++;
        } else {
          right--;
        }
      }
    }

    return result;
  }
}

// Test
const nums = [-1, 0, 1, 2, -1, -4];
console.log(ThreeSum.threeSumBrute(nums));
console.log(ThreeSum.threeSumHashing(nums));
console.log(ThreeSum.threeSumOptimal(nums));
