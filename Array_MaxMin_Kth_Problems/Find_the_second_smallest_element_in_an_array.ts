function findTheSecondSmallestElement(nums: number[]): number {
  const INT_MAX = Number.MAX_SAFE_INTEGER;

  if (nums.length <= 1) return INT_MAX;

  let firstMinVal = INT_MAX;
  let secondMinVal = INT_MAX;

  for (const num of nums) {
    if (num === firstMinVal || num === secondMinVal) continue; // skip duplicates

    if (num < firstMinVal) {
      secondMinVal = firstMinVal;
      firstMinVal = num;
    } else if (num < secondMinVal && num !== firstMinVal) {
      secondMinVal = num;
    }
  }

  return secondMinVal === INT_MAX ? INT_MAX : secondMinVal;
}

// test cases
const testCases: number[][] = [
  [4, 2, 2, 7, 3], // expected: 3
  [1], // expected: INT_MAX
  [5, 4], // expected: 5
  [2, 1], // expected: 2
  [1, 1, 1], // expected: INT_MAX
  [5, 4, 3, 2, 1], // expected: 2
  [1, 2, 3, 4, 5], // expected: 2
  [3, 1, 2], // expected: 2
  [1, 2, 2, 3, 3, 3], // expected: 2
  [Number.MAX_SAFE_INTEGER, Number.MAX_SAFE_INTEGER], // expected: INT_MAX
  [Number.MIN_SAFE_INTEGER, Number.MAX_SAFE_INTEGER], // expected: Number.MAX_SAFE_INTEGER
  [0, 0, 1], // expected: 1
  [-1, -1, 0, 5], // expected: 0
  [-5, -2, -3, -5], // expected: -3
  [-5, -5, -5, -5], // expected: INT_MAX
  [100, 90, 90, 80], // expected: 90
  [2, 2, 3, 3, 4, 4], // expected: 3
  [2, 2, 2, 3], // expected: 3
  [3, 3, 2, 2, 1, 1], // expected: 2
  [7, 7, 5, 6, 6, 5], // expected: 6
];

testCases.forEach((testCase) => {
  console.log(
    `Input: [${testCase}] -> Output: ${findTheSecondSmallestElement(testCase)}`
  );
});
