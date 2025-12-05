/**
 * Left rotate an array by one position.
 *
 * Problem:
 * - Given a number array, rotate it left by one step.
 *   Example: [1, 2, 3, 4, 5] → [2, 3, 4, 5, 1]
 *
 * Idea:
 * - Store the first element.
 * - Shift all elements left by one position.
 * - Place the stored element at the end.
 *
 * Edge Cases:
 * - null or undefined -> throw error.
 * - [] -> return immediately.
 * - [x] -> no rotation needed.
 * - Works with duplicates, negatives, mixed values.
 *
 * Time Complexity: O(n)
 * - All elements shift once.
 *
 * Space Complexity: O(1)
 * - Uses only one temporary variable.
 */
export function leftRotateByOne(arr: number[]): void {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    const n = arr.length;
    if (n === 0 || n === 1) {
        return;
    }

    const first = arr[0];

    for (let i = 1; i < n; i++) {
        arr[i - 1] = arr[i];
    }

    arr[n - 1] = first;
}

/**
 * Utility function to display the array.
 */
function printArray(arr: number[]): void {
    console.log("[" + arr.join(", ") + "]");
}

/**
 * Test cases using console.log only.
 */
const tests: number[][] = [
    [1, 2, 3, 4, 5],
    [10],
    [],
    [-1, -2, -3],
    [5, 5, 5]
];

const names = [
    "case1 [1,2,3,4,5]",
    "case2 [10]",
    "case3 []",
    "case4 [-1,-2,-3]",
    "case5 [5,5,5]"
];

for (let i = 0; i < tests.length; i++) {
    console.log("Testing " + names[i]);
    const arr = [...tests[i]];
    leftRotateByOne(arr);
    printArray(arr);
    console.log();
}
