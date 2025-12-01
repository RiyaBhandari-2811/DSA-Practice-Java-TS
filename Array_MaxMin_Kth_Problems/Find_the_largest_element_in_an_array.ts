/**
 * Problem: Given an integer array nums, return the largest element.
 *
 * Example static inputs:
 * [3, 5, 1, 9, 2] -> 9
 * [-2, -8, -1] -> -1
 * [7] -> 7
 */

function findTheLargestElement(nums: number[]): number {
  let maxVal = nums[0];

  for (let i = 1; i < nums.length; i++) {
    if (nums[i] > maxVal) {
      maxVal = nums[i];
    }
  }

  return maxVal;
}

const testCases: number[][] = [[3, 5, 1, 9, 2], [-2, -8, -1], [7]];

testCases.forEach((testCase) => {
  console.log(
    `Input: ${testCase} -> Output: ${findTheLargestElement(testCase)}`
  );
});
