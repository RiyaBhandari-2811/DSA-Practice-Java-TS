/**
 * findMaxMinInOnePass.ts
 *
 * This file contains two approaches to find the minimum and maximum
 * values in an array.
 *
 * 1. Brute Force Approach (Sorting)
 *    - Definition: Sort the array in ascending order and return the first element
 *      as the minimum and the last element as the maximum.
 *    - Time Complexity: O(n log n)
 *    - Space Complexity: O(1)
 *
 * 2. Single Pass Approach (Optimized)
 *    - Definition: Traverse the array once while maintaining two variables (min, max).
 *      Update them whenever a smaller or larger element is found.
 *    - Time Complexity: O(n)
 *    - Space Complexity: O(1)
 */

/**
 * Brute Force Approach:
 * Sort the array to get the min and max.
 *
 * @param arr - array of numbers
 * @returns [min, max]
 * @throws Error if array is empty
 */
export function bruteForce(arr: number[]): [number, number] {
  if (!arr || arr.length === 0) {
    throw new Error("Array must not be empty");
  }

  const sorted = [...arr].sort((a, b) => a - b);
  const min = sorted[0];
  const max = sorted[sorted.length - 1];

  return [min, max];
}

/**
 * Single Pass Approach:
 * Scan the array once and track min and max.
 *
 * @param arr - array of numbers
 * @returns [min, max]
 * @throws Error if array is empty
 */
export function singlePass(arr: number[]): [number, number] {
  if (!arr || arr.length === 0) {
    throw new Error("Array must not be empty");
  }

  let min = arr[0];
  let max = arr[0];

  for (let i = 1; i < arr.length; i++) {
    const current = arr[i];

    if (current < min) {
      min = current;
    }

    if (current > max) {
      max = current;
    }
  }

  return [min, max];
}

// Demo Usage
const arr = [3, 1, 5, -2, 7, 7];
console.log("Brute Force ->", bruteForce(arr));
console.log("Single Pass ->", singlePass(arr));
