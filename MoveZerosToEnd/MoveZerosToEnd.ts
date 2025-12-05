/**
 * Brute force approach to move all zeros to the end of the array.
 *
 * Idea:
 * - Create a temporary array.
 * - Copy all non-zero elements into temp.
 * - Fill the rest with zeros.
 * - Copy temp back into arr.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Edge cases:
 * - null/undefined -> error
 * - [] -> do nothing
 * - [0,0,0] -> unchanged
 * - [1,2,3] -> unchanged
 */
export function moveZerosBrute(arr: number[]): void {
  if (!arr) {
    throw new Error("Array must be non-null");
  }

  const n = arr.length;
  const temp: number[] = new Array(n);
  let index = 0;

  // Copy non-zero elements
  for (let i = 0; i < n; i++) {
    if (arr[i] !== 0) {
      temp[index++] = arr[i];
    }
  }

  // Remaining positions are zeros by default

  // Copy back
  for (let i = 0; i < n; i++) {
    arr[i] = temp[i] ?? 0;
  }
}

/**
 * Optimised two-pointer approach.
 *
 * Idea:
 * - Maintain a "writeIndex" to place non-zero values.
 * - Traverse array:
 *   - If arr[i] != 0, write it at arr[writeIndex] and set arr[i] = 0 if moved.
 * - Ensures stable order of non-zero elements.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Edge cases same as brute.
 */
export function moveZerosOptimised(arr: number[]): void {
  if (!arr) {
    throw new Error("Array must be non-null");
  }

  const n = arr.length;
  let writeIndex = 0;

  for (let i = 0; i < n; i++) {
    if (arr[i] !== 0) {
      arr[writeIndex] = arr[i];
      if (i !== writeIndex) {
        arr[i] = 0;
      }
      writeIndex++;
    }
  }
}

/**
 * Helper to print arrays easily.
 */
function print(arr: number[]): void {
  console.log("[" + arr.join(", ") + "]");
}

/**
 * Test Cases
 */
const tests: number[][] = [
  [0, 1, 0, 3, 12],
  [1, 2, 3],
  [0, 0, 0],
  [],
  [-1, 0, -2, 0, 3],
  [5, 5, 5, 0, 0],
];

const names = [
  "case1 [0,1,0,3,12]",
  "case2 [1,2,3]",
  "case3 [0,0,0]",
  "case4 []",
  "case5 [-1,0,-2,0,3]",
  "case6 [5,5,5,0,0]",
];

for (let i = 0; i < tests.length; i++) {
  console.log("Testing " + names[i]);

  const arrBrute = [...tests[i]];
  const arrOpt = [...tests[i]];

  moveZerosBrute(arrBrute);
  console.log("Brute result:     ", arrBrute);

  moveZerosOptimised(arrOpt);
  console.log("Optimised result: ", arrOpt);

  console.log();
}
