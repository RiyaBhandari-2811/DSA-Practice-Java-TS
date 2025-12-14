/**
 * BRUTE FORCE APPROACH
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function bruteForce(arr: number[]): void {
  let maxSum = -Infinity;
  let start = 0, end = 0;

  for (let i = 0; i < arr.length; i++) {
    let sum = 0;
    for (let j = i; j < arr.length; j++) {
      sum += arr[j];
      if (sum > maxSum) {
        maxSum = sum;
        start = i;
        end = j;
      }
    }
  }

  printResult(arr, maxSum, start, end);
}

/**
 * BETTER APPROACH
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function betterApproach(arr: number[]): void {
  let maxSum = -Infinity;
  let start = 0, end = 0;

  for (let i = 0; i < arr.length; i++) {
    let currSum = 0;
    for (let j = i; j < arr.length; j++) {
      currSum += arr[j];
      if (currSum > maxSum) {
        maxSum = currSum;
        start = i;
        end = j;
      }
    }
  }

  printResult(arr, maxSum, start, end);
}

/**
 * OPTIMAL APPROACH (Kadane's Algorithm)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function optimalKadane(arr: number[]): void {
  let maxSum = -Infinity;
  let currSum = 0;
  let start = 0, tempStart = 0, end = 0;

  for (let i = 0; i < arr.length; i++) {
    currSum += arr[i];

    if (currSum > maxSum) {
      maxSum = currSum;
      start = tempStart;
      end = i;
    }

    if (currSum < 0) {
      currSum = 0;
      tempStart = i + 1;
    }
  }

  printResult(arr, maxSum, start, end);
}

function printResult(arr: number[], sum: number, start: number, end: number): void {
  let subarray = "";
  for (let i = start; i <= end; i++) {
    subarray += arr[i] + " ";
  }
  console.log("Max Sum:", sum, "| Subarray:", subarray);
}

// Test
const arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4];
bruteForce(arr);
betterApproach(arr);
optimalKadane(arr);
