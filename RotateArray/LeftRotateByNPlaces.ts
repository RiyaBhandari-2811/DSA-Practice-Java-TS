/**
 * Brute force: Rotate array left by N places by rotating 1 place N times.
 *
 * Idea:
 * - For each rotation:
 *   - Store arr[0]
 *   - Shift all elements left by 1
 *   - Put stored element at arr[n-1]
 *
 * Time Complexity: O(n * k)
 * Space Complexity: O(1)
 *
 * Edge cases:
 * - null/undefined -> error
 * - [] or [x] -> no rotation
 * - k > length -> use k = k % length
 */
export function leftRotateBrute(arr: number[], k: number): void {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    const n = arr.length;
    if (n <= 1) return;

    k = k % n;
    if (k === 0) return;

    for (let rot = 0; rot < k; rot++) {
        const first = arr[0];

        for (let i = 1; i < n; i++) {
            arr[i - 1] = arr[i];
        }

        arr[n - 1] = first;
    }
}

/**
 * Optimised rotation using Reversal Algorithm.
 *
 * Idea:
 * - Reverse first k elements
 * - Reverse last n-k elements
 * - Reverse whole array
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
export function leftRotateOptimised(arr: number[], k: number): void {
    if (!arr) {
        throw new Error("Array must be non-null");
    }

    const n = arr.length;
    if (n <= 1) return;

    k = k % n;
    if (k === 0) return;

    reverse(arr, 0, k - 1);
    reverse(arr, k, n - 1);
    reverse(arr, 0, n - 1);
}

function reverse(arr: number[], l: number, r: number): void {
    while (l < r) {
        const temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
        l++;
        r--;
    }
}

/** Utility printer */
function print(arr: number[]): void {
    console.log("[" + arr.join(", ") + "]");
}

/** Test Cases */
const tests: number[][] = [
    [1, 2, 3, 4, 5],
    [10],
    [],
    [-1, -2, -3, -4, -5],
    [5, 5, 5, 5],
    [1, 2]
];

const ks = [2, 3, 4, 7, 1, 5];

for (let i = 0; i < tests.length; i++) {
    console.log("Original:", tests[i], "Rotate by:", ks[i]);

    let b = [...tests[i]];
    let o = [...tests[i]];

    leftRotateBrute(b, ks[i]);
    console.log("Brute:     ", b);

    leftRotateOptimised(o, ks[i]);
    console.log("Optimised: ", o);

    console.log();
}
