/**
 *
 * Problem. Return the second largest distinct element in nums. If it does not exist, return Integer.MIN_VALUE (or an agreed sentinel).
 *
 *  *Example
 *
 *  *Input: [5, 1, 5, 3]
 * Output: 3
 *
 *
 *
 *  *Edge cases: All elements equal -> no second largest.
 *
 *  *Sample test cases
 *
 * [5,1,5,3] -> 3
 * [2,2,2] -> INT_MIN
 * [10,9] -> 9
 */

function secondLargestDistinct(nums: number[]): number {
  if (!nums || nums.length < 2) {
    return Number.MIN_SAFE_INTEGER;
  }

  let first = Number.MIN_SAFE_INTEGER;
  let second = Number.MIN_SAFE_INTEGER;

  for (const num of nums) {
    if (num > first) {
      second = first;
      first = num;
    } else if (num > second && num !== first) {
      second = num;
    }
  }

  return second === Number.MIN_SAFE_INTEGER ? Number.MIN_SAFE_INTEGER : second;
}

const testCases = [
  [5, 1, 5, 3], // o/p: 3
  [2, 2, 2], // o/p: MIN_SAFE_INTEGER (no second largest distinct)
  [10, 9], // o/p: 9
];

testCases.forEach((nums, index) => {
  console.log(`Test Case ${index + 1}:`, secondLargestDistinct(nums));
});
