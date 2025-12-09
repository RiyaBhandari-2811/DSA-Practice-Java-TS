/**
 * Longest subarray with given sum K
 *
 * Given an array and a target K, return the length of the longest contiguous
 * subarray whose sum is exactly K.
 *
 * We implement:
 * 1) Brute force (O(n^2))
 * 2) Prefix sum + HashMap (O(n), works with negatives)
 * 3) Sliding window (O(n), only for non negative numbers)
 */

/* -------------------------------------------------------------------------- */
/*                          1. BRUTE FORCE O(N^2)                             */
/* -------------------------------------------------------------------------- */
/**
 * Brute force: check all subarrays.
 *
 * Idea:
 * - For every start index i, extend end index j and keep a running sum.
 * - Whenever sum == k, update the maximum length.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
export function longestSubarrayBrute(nums: number[], k: number): number {
  if (!nums) {
    throw new Error("Array must not be null or undefined");
  }

  const n = nums.length;
  let maxLen = 0;

  for (let start = 0; start < n; start++) {
    let sum = 0;
    for (let end = start; end < n; end++) {
      sum += nums[end];
      if (sum === k) {
        const len = end - start + 1;
        if (len > maxLen) {
          maxLen = len;
        }
      }
    }
  }

  return maxLen;
}

/* -------------------------------------------------------------------------- */
/*                     2. PREFIX SUM + HASHMAP O(N)                           */
/* -------------------------------------------------------------------------- */
/**
 * Prefix sum with HashMap.
 *
 * Idea:
 * - Maintain running prefixSum.
 * - For each index i:
 *      prefixSum = nums[0] + ... + nums[i]
 *   We want a previous prefix p such that:
 *      prefixSum - p = k  =>  p = prefixSum - k
 * - Store the earliest index for each prefix in a Map.
 *
 * Works for:
 * - Positive, zero, negative numbers.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
export function longestSubarrayPrefixSum(nums: number[], k: number): number {
  if (!nums) {
    throw new Error("Array must not be null or undefined");
  }

  const n = nums.length;
  let maxLen = 0;
  let prefixSum = 0;

  const firstIndexOfPrefix = new Map<number, number>();
  // Prefix sum 0 occurs before the array starts, at index -1
  firstIndexOfPrefix.set(0, -1);

  for (let i = 0; i < n; i++) {
    prefixSum += nums[i];

    const needed = prefixSum - k;
    if (firstIndexOfPrefix.has(needed)) {
      const startIndex = (firstIndexOfPrefix.get(needed) as number) + 1;
      const len = i - startIndex + 1;
      if (len > maxLen) {
        maxLen = len;
      }
    }

    // Store the first occurrence of this prefix sum
    if (!firstIndexOfPrefix.has(prefixSum)) {
      firstIndexOfPrefix.set(prefixSum, i);
    }
  }

  return maxLen;
}

/* -------------------------------------------------------------------------- */
/*                  3. SLIDING WINDOW (POSITIVES ONLY) O(N)                   */
/* -------------------------------------------------------------------------- */
/**
 * Sliding window for non negative arrays (positives or positives + zeros).
 *
 * Idea:
 * - Maintain window [left, right] with current sum.
 * - Expand right, add nums[right] to sum.
 * - While sum > k, shrink from left and subtract nums[left].
 * - Whenever sum == k, update max length.
 *
 * Condition:
 * - All numbers must be non negative for this to be valid.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function longestSubarraySlidingWindowPositives(
  nums: number[],
  k: number
): number {
  if (!nums) {
    throw new Error("Array must not be null or undefined");
  }

  const n = nums.length;
  if (n === 0) return 0;

  // With non negative numbers, sum can never be negative
  if (k < 0) return 0;

  let left = 0;
  let sum = 0;
  let maxLen = 0;

  for (let right = 0; right < n; right++) {
    sum += nums[right];

    // Shrink while sum is too large
    while (left <= right && sum > k) {
      sum -= nums[left];
      left++;
    }

    if (sum === k) {
      const len = right - left + 1;
      if (len > maxLen) {
        maxLen = len;
      }
    }
  }

  return maxLen;
}

/* -------------------------------------------------------------------------- */
/*                                   TESTS                                    */
/* -------------------------------------------------------------------------- */

const tests = [
  {
    name: "t1 positives",
    arr: [1, 2, 3, 1, 1, 1, 2],
    k: 6, // longest: [1,2,3] -> 3
  },
  {
    name: "t2 positives",
    arr: [1, 1, 1, 1],
    k: 2, // longest: [1,1] -> 2
  },
  {
    name: "t3 positives",
    arr: [2, 3, 5],
    k: 10, // whole array -> 3
  },
  {
    name: "t4 positives",
    arr: [2, 2, 2],
    k: 4, // [2,2] -> 2
  },
  {
    name: "t5 with negatives (prefixSum works, sliding window is not valid logically)",
    arr: [1, -1, 5, -2, 3],
    k: 3, // longest: [1,-1,5,-2] -> 4
  },
];

for (const t of tests) {
  console.log(`Testing ${t.name} with k = ${t.k}`);
  console.log("Brute:        ", longestSubarrayBrute(t.arr, t.k));
  console.log("Prefix sum:   ", longestSubarrayPrefixSum(t.arr, t.k));
  console.log(
    "Sliding (+ve):",
    longestSubarraySlidingWindowPositives(t.arr, t.k)
  );
  console.log("");
}
