/**
 * Brute Force: Linear Search for Missing Number
 *
 * Idea:
 * - For each candidate x from 0 to n, scan the entire array to check if x exists.
 * - The first number not present in the array is the missing number.
 *
 * Edge cases:
 * - null/undefined array → throw error
 * - empty array → throw error
 * - invalid input (duplicates or values outside [0, n]) → may return wrong result
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
export function findMissingBrute(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array must have at least one element");
    }

    const n = nums.length;

    for (let candidate = 0; candidate <= n; candidate++) {
        let found = false;

        for (const value of nums) {
            if (value === candidate) {
                found = true;
                break;
            }
        }

        if (!found) return candidate;
    }

    throw new Error("Input does not match constraints");
}

/**
 * Better: Using Hashing with a Set
 *
 * Idea:
 * - Insert all elements into a Set.
 * - Iterate from 0 to n and return the first number not in the set.
 *
 * Edge cases:
 * - null array → throw error
 * - empty array → throw error
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
export function findMissingHashing(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array must have at least one element");
    }

    const n = nums.length;
    const set = new Set(nums);

    for (let candidate = 0; candidate <= n; candidate++) {
        if (!set.has(candidate)) return candidate;
    }

    throw new Error("Invalid input");
}

/**
 * Optimal: Using Sum Formula
 *
 * Idea:
 * - Expected sum of numbers from 0..n → n * (n + 1) / 2
 * - Compute actual sum of array
 * - missing = expected - actual
 *
 * Edge cases:
 * - overflow not an issue in JS (number type handles large values)
 * - invalid inputs produce wrong results
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function findMissingSum(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array must have at least one element");
    }

    const n = nums.length;
    const expected = (n * (n + 1)) / 2;
    const actual = nums.reduce((sum, val) => sum + val, 0);

    return expected - actual;
}

/**
 * Optimal: Using XOR
 *
 * Idea:
 * - XOR all numbers from 0..n → xorRange
 * - XOR all elements of the array → xorArray
 * - missing = xorRange ^ xorArray
 *
 * Why XOR works:
 * - a ^ a = 0
 * - a ^ 0 = a
 * - XOR is commutative + associative
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function findMissingXor(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array must have at least one element");
    }

    const n = nums.length;

    let xorRange = 0;
    let xorArray = 0;

    for (let i = 0; i <= n; i++) {
        xorRange ^= i;
    }

    for (const val of nums) {
        xorArray ^= val;
    }

    return xorRange ^ xorArray;
}

/** Test Cases */
const tests = [
    { name: "nums1", arr: [3, 0, 1] },                  // missing 2
    { name: "nums2", arr: [0, 1] },                     // missing 2
    { name: "nums3", arr: [9, 6, 4, 2, 3, 5, 7, 0, 1] }, // missing 8
    { name: "nums4", arr: [1] },                        // missing 0
    { name: "nums5", arr: [0] },                        // missing 1
    { name: "nums6", arr: [4, 2, 1, 0] }                // missing 3
];

for (const t of tests) {
    console.log("Testing:", t.name);
    console.log("Brute:", findMissingBrute(t.arr));
    console.log("Hashing:", findMissingHashing(t.arr));
    console.log("Sum:", findMissingSum(t.arr));
    console.log("XOR:", findMissingXor(t.arr));
    console.log("");
}
