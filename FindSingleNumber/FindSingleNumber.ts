/**
 * Problem:
 * In an array where every number appears exactly twice except one number
 * which appears once, return that single number.
 *
 * Example:
 * [4,1,2,1,2] → 4
 */

/* -------------------------------------------------------------------------- */
/*                              1. BRUTE FORCE                                 */
/* -------------------------------------------------------------------------- */
/**
 * Brute Force: Double Loop
 *
 * Idea:
 * - For every nums[i], count how many times it appears.
 * - If count == 1 → that is the answer.
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Edge Cases:
 * - null/undefined → throw
 * - empty array → throw
 */
export function findSingleBrute(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array should not be null or empty");
    }

    for (let i = 0; i < nums.length; i++) {
        let count = 0;

        for (let j = 0; j < nums.length; j++) {
            if (nums[j] === nums[i]) count++;
        }

        if (count === 1) return nums[i];
    }

    throw new Error("No unique element found");
}

/* -------------------------------------------------------------------------- */
/*                                2. HASHING                                   */
/* -------------------------------------------------------------------------- */
/**
 * Hashing:
 *
 * Idea:
 * - Insert into a Set.
 * - 2 * (sum of unique elements) - (sum of all array elements)
 *   gives the single number.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
export function findSingleHash(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array should not be null or empty");
    }

    const set = new Set<number>();
    let sumSet = 0;
    let sumArr = 0;

    for (const num of nums) {
        if (!set.has(num)) {
            set.add(num);
            sumSet += num;
        }
        sumArr += num;
    }

    return 2 * sumSet - sumArr;
}

/* -------------------------------------------------------------------------- */
/*                                3. XOR (Optimal)                             */
/* -------------------------------------------------------------------------- */
/**
 * XOR Approach (Optimal):
 *
 * Reason:
 * - a ^ a = 0
 * - a ^ 0 = a
 * - XOR removes duplicates automatically.
 *
 * Example:
 * nums = [4,1,2,1,2]
 * xor = 4 ^ 1 ^ 2 ^ 1 ^ 2
 * pairs cancel:
 * → (1 ^ 1) = 0
 * → (2 ^ 2) = 0
 * → xor = 4
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function findSingleXor(nums: number[]): number {
    if (!nums || nums.length === 0) {
        throw new Error("Array should not be null or empty");
    }

    let xor = 0;

    for (const num of nums) {
        xor ^= num;
    }

    return xor;
}

/* -------------------------------------------------------------------------- */
/*                                   TESTS                                    */
/* -------------------------------------------------------------------------- */
const tests = [
    { name: "t1", arr: [2, 2, 1] },             // 1
    { name: "t2", arr: [4, 1, 2, 1, 2] },       // 4
    { name: "t3", arr: [7, 7, 9] },             // 9
];

for (const t of tests) {
    console.log("Testing:", t.name);
    console.log("Brute:", findSingleBrute(t.arr));
    console.log("Hash:", findSingleHash(t.arr));
    console.log("XOR:", findSingleXor(t.arr));
    console.log("");
}
