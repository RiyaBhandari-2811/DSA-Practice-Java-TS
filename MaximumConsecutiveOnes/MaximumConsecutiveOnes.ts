/**
 * Maximum Consecutive Ones
 * Given a binary array, return the maximum number of consecutive 1s.
 *
 * Example:
 * [1,1,0,1,1,1] → 3
 */

/* -------------------------------------------------------------------------- */
/*                              1. BRUTE FORCE                                 */
/* -------------------------------------------------------------------------- */
/**
 * Brute Force: Nested loops
 *
 * Idea:
 * - For each index i, treat it as a start of streak.
 * - Continue j forward until nums[j] is not 1.
 * - Track longest streak.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Edge cases:
 * - null/undefined → error
 * - empty → 0
 * - all zeros → 0
 * - all ones → n
 */
export function maxConsecutiveOnesBrute(nums: number[]): number {
    if (!nums) throw new Error("Array cannot be null");

    if (nums.length === 0) return 0;

    let maxStreak = 0;

    for (let i = 0; i < nums.length; i++) {
        let current = 0;

        for (let j = i; j < nums.length; j++) {
            if (nums[j] === 1) {
                current++;
                maxStreak = Math.max(maxStreak, current);
            } else {
                break;
            }
        }
    }

    return maxStreak;
}

/* -------------------------------------------------------------------------- */
/*                               2. BETTER O(N)                               */
/* -------------------------------------------------------------------------- */
/**
 * Better: Single pass count
 *
 * Idea:
 * - Count streak as long as nums[i] is 1.
 * - Reset streak when we see a 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Edge cases handled:
 * - empty array → 0
 * - all zeros → 0
 * - all ones → n
 */
export function maxConsecutiveOnesBetter(nums: number[]): number {
    if (!nums) throw new Error("Array cannot be null");

    if (nums.length === 0) return 0;

    let maxStreak = 0;
    let current = 0;

    for (const v of nums) {
        if (v === 1) {
            current++;
            maxStreak = Math.max(maxStreak, current);
        } else {
            current = 0;
        }
    }

    return maxStreak;
}

/* -------------------------------------------------------------------------- */
/*                           3. OPTIMISED SLIDING WINDOW                      */
/* -------------------------------------------------------------------------- */
/**
 * Optimised: Sliding window style (still O(n))
 *
 * Idea:
 * - Maintain left pointer at start of current streak.
 * - When nums[right] = 0 → next streak starts at right + 1.
 * - Window size when nums[right] = 1 → right - left + 1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function maxConsecutiveOnesOptimised(nums: number[]): number {
    if (!nums) throw new Error("Array cannot be null");

    if (nums.length === 0) return 0;

    let left = 0;
    let maxStreak = 0;

    for (let right = 0; right < nums.length; right++) {
        if (nums[right] === 0) {
            left = right + 1;  
        } else {
            maxStreak = Math.max(maxStreak, right - left + 1);
        }
    }

    return maxStreak;
}

/* -------------------------------------------------------------------------- */
/*                                   TESTS                                    */
/* -------------------------------------------------------------------------- */
const tests = [
    { name: "t1", arr: [1, 1, 0, 1, 1, 1] }, // 3
    { name: "t2", arr: [1, 0, 1, 1, 0, 1] }, // 2
    { name: "t3", arr: [0, 0, 0] },          // 0
    { name: "t4", arr: [1, 1, 1, 1] },       // 4
    { name: "t5", arr: [] },                // 0
    { name: "t6", arr: [0, 1, 1, 1, 0, 1] }  // 3
];

for (const t of tests) {
    console.log("Testing:", t.name);
    console.log("Brute:", maxConsecutiveOnesBrute(t.arr));
    console.log("Better:", maxConsecutiveOnesBetter(t.arr));
    console.log("Optimised:", maxConsecutiveOnesOptimised(t.arr));
    console.log("");
}
