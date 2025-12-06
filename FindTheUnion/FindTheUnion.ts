/**
 * Brute force union of two arrays (unsorted allowed).
 *
 * Idea:
 * - Use a Set to collect distinct values from both arrays.
 * - Convert the Set back to an array.
 * - Sort before returning (so the union is ordered).
 *
 * Time Complexity: O((n + m) * log(n + m))
 * Space Complexity: O(n + m)
 */
export function findUnionBrute(arr1: number[], arr2: number[]): number[] {
    if (!arr1 || !arr2) {
        throw new Error("Input arrays must be non-null");
    }

    const set = new Set<number>();

    for (const x of arr1) {
        set.add(x);
    }
    for (const x of arr2) {
        set.add(x);
    }

    const result = Array.from(set);
    result.sort((a, b) => a - b);

    return result;
}

/**
 * Optimised union for two sorted arrays using two pointers.
 *
 * Assumptions:
 * - arr1 and arr2 are sorted in non decreasing order.
 *
 * Time Complexity: O(n + m)
 * Space Complexity: O(n + m)
 */
export function findUnionOptimised(arr1: number[], arr2: number[]): number[] {
    if (!arr1 || !arr2) {
        throw new Error("Input arrays must be non-null");
    }

    const n = arr1.length;
    const m = arr2.length;

    let i = 0;
    let j = 0;
    const result: number[] = [];

    while (i < n && j < m) {
        const a = arr1[i];
        const b = arr2[j];

        if (a === b) {
            if (result.length === 0 || result[result.length - 1] !== a) {
                result.push(a);
            }
            i++;
            j++;
        } else if (a < b) {
            if (result.length === 0 || result[result.length - 1] !== a) {
                result.push(a);
            }
            i++;
        } else {
            if (result.length === 0 || result[result.length - 1] !== b) {
                result.push(b);
            }
            j++;
        }
    }

    // remaining elements of arr1
    while (i < n) {
        const a = arr1[i];
        if (result.length === 0 || result[result.length - 1] !== a) {
            result.push(a);
        }
        i++;
    }

    // remaining elements of arr2
    while (j < m) {
        const b = arr2[j];
        if (result.length === 0 || result[result.length - 1] !== b) {
            result.push(b);
        }
        j++;
    }

    return result;
}

/**
 * Test helper.
 */
function print(label: string, arr: number[]): void {
    console.log(label + " [" + arr.join(", ") + "]");
}

/**
 * Test cases
 */
const arr1 = [1, 2, 2, 3, 5];
const arr2 = [2, 3, 4, 4, 6];
const arr3: number[] = [];
const arr4 = [10, 10, 10];
const arr5 = [-3, -1, 0, 2];
const arr6 = [-2, -1, 0, 3, 3];

const pairs: [number[], number[]][] = [
    [arr1, arr2],
    [arr3, arr4],
    [arr5, arr6]
];

const namesTs = [
    "case1 arr1 & arr2",
    "case2 arr3 & arr4",
    "case3 arr5 & arr6"
];

for (let idx = 0; idx < pairs.length; idx++) {
    const [a, b] = pairs[idx];
    console.log("Testing " + namesTs[idx]);
    print("arr1:", a);
    print("arr2:", b);

    const brute = findUnionBrute(a, b);
    const opt = findUnionOptimised(a, b);

    print("Brute union:    ", brute);
    print("Optimised union:", opt);
    console.log();
}
