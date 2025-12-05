/**
 * Linear search to find the first index of a target value.
 *
 * Idea:
 * - Loop from left to right.
 * - If element matches target → return index.
 * - If loop completes → return -1.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Edge cases:
 * - null/undefined -> throw error
 * - [] -> return -1
 */
export function linearSearch(arr: number[], target: number): number {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === target) {
            return i;
        }
    }

    return -1;
}

/**
 * Test cases — console.log only.
 */
const tests: number[][] = [
    [1, 2, 3, 4, 5],
    [10, 20, 30],
    [],
    [5, 5, 5],
    [-3, -1, 0, 2]
];

const targets = [4, 25, 3, 5, -1];

for (let i = 0; i < tests.length; i++) {
    console.log("Array:", tests[i], "Target:", targets[i]);
    console.log("Index:", linearSearch(tests[i], targets[i]));
    console.log();
}
