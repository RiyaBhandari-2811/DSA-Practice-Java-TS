/**
 * Problem: Given an integer array nums, return the smallest element.
 *
 * Example static inputs:
 * [3, 5, 1, 9, 2] -> 1
 * [-2, -8, -1] -> -8
 * [7] -> 7
 */

function findTheSmallestElement(nums: number[]): number {
  let minVal: number = nums[0];

  for (let i: number = 1; i < nums.length; i++) {
    if (nums[i] < minVal) {
      minVal = nums[i];
    }
  }

  return minVal;
}

[[3, 5, 1, 9, 2], [-2, -8, -1], [7]].forEach((testCase: number[]): void => {
  console.log(
    `Input: ${testCase} -> Output: ${findTheSmallestElement(testCase)}`
  );
});
