/**
 * Approach 1: Brute force (just sort)
 *
 * Time: O(n log n)
 * Space: O(1)
 */
export function sortBrute(nums: number[]): number[] {
  return [...nums].sort((a, b) => a - b);
}

/**
 * Approach 2: Counting Sort logic
 *
 * Time: O(n)
 * Space: O(1)
 */
export function sortCounting(nums: number[]): number[] {
  const arr = [...nums];
  let zero = 0,
    one = 0,
    two = 0;

  for (const x of arr) {
    if (x === 0) zero++;
    else if (x === 1) one++;
    else two++;
  }

  let i = 0;
  while (zero-- > 0) arr[i++] = 0;
  while (one-- > 0) arr[i++] = 1;
  while (two-- > 0) arr[i++] = 2;

  return arr;
}

/**
 * Approach 3: Dutch National Flag Algorithm (Optimal)
 *
 * Time: O(n)
 * Space: O(1)
 */
export function sortDNF(nums: number[]): number[] {
  const arr = [...nums];

  let low = 0;
  let mid = 0;
  let high = arr.length - 1;

  while (mid <= high) {
    if (arr[mid] === 0) {
      [arr[mid], arr[low]] = [arr[low], arr[mid]];
      low++;
      mid++;
    } else if (arr[mid] === 1) {
      mid++;
    } else {
      [arr[mid], arr[high]] = [arr[high], arr[mid]];
      high--;
    }
  }

  return arr;
}

/* TEST */
console.log(sortBrute([2, 0, 2, 1, 1, 0]));
console.log(sortCounting([2, 0, 2, 1, 1, 0]));
console.log(sortDNF([2, 0, 2, 1, 1, 0]));
