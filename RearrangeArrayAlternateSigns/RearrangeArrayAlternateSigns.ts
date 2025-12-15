/**
 * BRUTE FORCE APPROACH
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Stable: Yes
 */
function bruteForce(arr: number[]): void {
  const pos: number[] = [];
  const neg: number[] = [];

  for (const num of arr) {
    if (num >= 0) pos.push(num);
    else neg.push(num);
  }

  let i = 0,
    p = 0,
    n = 0;
  while (p < pos.length && n < neg.length) {
    arr[i++] = pos[p++];
    arr[i++] = neg[n++];
  }

  while (p < pos.length) arr[i++] = pos[p++];
  while (n < neg.length) arr[i++] = neg[n++];

  console.log(arr.join(" "));
}

/**
 * BETTER APPROACH
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * Stable: Yes
 */
function betterApproach(arr: number[]): void {
  const res = new Array(arr.length);
  let posIdx = 0,
    negIdx = 1;

  for (const num of arr) {
    if (num >= 0 && posIdx < arr.length) {
      res[posIdx] = num;
      posIdx += 2;
    } else if (num < 0 && negIdx < arr.length) {
      res[negIdx] = num;
      negIdx += 2;
    }
  }

  for (let i = 0; i < arr.length; i++) arr[i] = res[i];
  console.log(arr.join(" "));
}

/**
 * OPTIMAL APPROACH (Equal positives and negatives)
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Stable: No
 */
function optimal(arr: number[]): void {
  let pos = 0,
    neg = 1;
  const n = arr.length;

  while (pos < n && neg < n) {
    if (arr[pos] >= 0) pos += 2;
    else if (arr[neg] < 0) neg += 2;
    else {
      const temp = arr[pos];
      arr[pos] = arr[neg];
      arr[neg] = temp;
    }
  }

  console.log(arr.join(" "));
}

// Test
const arr = [3, 1, -2, -5, 2, -4];
bruteForce([...arr]);
betterApproach([...arr]);
optimal([...arr]);
