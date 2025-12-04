/**
 * Brute force removal of duplicates from a sorted array using extra space.
 *
 * Problem:
 * - Remove duplicates from a sorted array so each unique number appears once.
 * - Modify the array in place for the first uniqueCount elements.
 *
 * Idea:
 * - Use an extra temp array to store unique elements.
 * - Copy them back to the original array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Edge cases:
 * - null / undefined -> error
 * - [] -> 0
 * - [x] -> 1
 * - duplicates anywhere
 * - negative + positive mixed allowed (array must be sorted)
 */
export function removeDuplicatesBrute(arr: number[]): number {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    const n = arr.length;
    if (n === 0) return 0;
    if (n === 1) return 1;

    const temp: number[] = [];
    temp.push(arr[0]);

    for (let i = 1; i < n; i++) {
        if (arr[i] !== temp[temp.length - 1]) {
            temp.push(arr[i]);
        }
    }

    for (let i = 0; i < temp.length; i++) {
        arr[i] = temp[i];
    }

    return temp.length;
}

/**
 * Optimised two-pointer technique to remove duplicates in-place.
 *
 * Idea:
 * - writeIndex = where the next unique element should be written.
 * - readIndex scans the array.
 * - If arr[readIndex] != arr[writeIndex - 1], it's unique → place at writeIndex.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Edge cases:
 * - null / undefined -> error
 * - [] -> 0
 * - [x] -> 1
 * - all equal or all unique arrays behave correctly
 */
export function removeDuplicatesOptimised(arr: number[]): number {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    const n = arr.length;
    if (n === 0) return 0;
    if (n === 1) return 1;

    let writeIndex = 1;

    for (let readIndex = 1; readIndex < n; readIndex++) {
        if (arr[readIndex] !== arr[writeIndex - 1]) {
            arr[writeIndex] = arr[readIndex];
            writeIndex++;
        }
    }

    return writeIndex;
}

/**
 * Helper for printing the first "length" elements of the array.
 */
function printArray(arr: number[], length: number): void {
    console.log("[" + arr.slice(0, length).join(", ") + "]");
}

/**
 * Test Cases (console logs only)
 */
const tests: number[][] = [
    [1, 1, 2],
    [0, 0, 1, 1, 1, 2, 2, 3, 3, 4],
    [],
    [1],
    [1, 2, 3, 4],
    [2, 2, 2, 2],
    [-3, -3, -2, -2, -2, 0, 1, 1],
    [1, 1],
    [1, 2],
    [-1, -1, -1, -1]
];

const names = [
    "case1 [1,1,2]",
    "case2 [0,0,1,1,1,2,2,3,3,4]",
    "case3 []",
    "case4 [1]",
    "case5 [1,2,3,4]",
    "case6 [2,2,2,2]",
    "case7 [-3,-3,-2,-2,-2,0,1,1]",
    "case8 [1,1]",
    "case9 [1,2]",
    "case10 [-1,-1,-1,-1]"
];

for (let i = 0; i < tests.length; i++) {
    const original = tests[i];

    const arrBrute = [...original];
    const arrOpt = [...original];

    console.log("Testing " + names[i]);

    const lenBrute = removeDuplicatesBrute(arrBrute);
    console.log("Brute length:", lenBrute, "array:", arrBrute.slice(0, lenBrute));

    const lenOpt = removeDuplicatesOptimised(arrOpt);
    console.log("Optimised length:", lenOpt, "array:", arrOpt.slice(0, lenOpt));

    console.log();
}
