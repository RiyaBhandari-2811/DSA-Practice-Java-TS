/**
 * Problem:
 * Count total number of good digit strings of length n.
 *
 * A digit string is good if:
 * - Digits at even indices are even (0, 2, 4, 6, 8)
 * - Digits at odd indices are prime (2, 3, 5, 7)
 *
 * Leading zeros are allowed.
 * Return answer modulo (10^9 + 7).
 */

const MOD = 1_000_000_007n;

/**
 * ------------------------------------------------------------
 * Approach 1: Brute Force (Conceptual Only)
 *
 * Idea:
 * - Generate all digit strings of length n
 * - Validate each index
 *
 * Why not implemented:
 * - Total strings = 10^n
 * - n can be as large as 10^15
 *
 * Time Complexity: O(10^n * n)
 * Space Complexity: O(n)
 *
 * Stable: Yes
 */
// ❌ Not implemented due to constraints

/**
 * ------------------------------------------------------------
 * Approach 2: Mathematical Counting (Linear Multiplication)
 *
 * Idea:
 * - Count even and odd indices
 * - Multiply choices directly
 * - Take modulo at each step
 *
 * Drawback:
 * - Runs in O(n), too slow for large n
 *
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function countGoodNumbersBetter(n: number): number {
  const evenCount = Math.floor((n + 1) / 2);
  const oddCount = Math.floor(n / 2);

  let result = 1n;

  for (let i = 0; i < evenCount; i++) {
    result = (result * 5n) % MOD;
  }

  for (let i = 0; i < oddCount; i++) {
    result = (result * 4n) % MOD;
  }

  return Number(result);
}

/**
 * ------------------------------------------------------------
 * Approach 3: Optimal (Iterative Binary Exponentiation)
 *
 * Idea:
 * - Use formula:
 *     5^(evenCount) * 4^(oddCount) % MOD
 * - Compute powers in O(log n)
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function countGoodNumbersOptimal(n: number): number {
  const evenCount = BigInt(Math.floor((n + 1) / 2));
  const oddCount = BigInt(Math.floor(n / 2));

  const evenWays = modPowIterative(5n, evenCount);
  const oddWays = modPowIterative(4n, oddCount);

  return Number((evenWays * oddWays) % MOD);
}

/**
 * Iterative Binary Exponentiation
 */
function modPowIterative(base: bigint, exp: bigint): bigint {
  let result = 1n;

  while (exp > 0n) {
    if (exp & 1n) {
      result = (result * base) % MOD;
    }
    base = (base * base) % MOD;
    exp >>= 1n;
  }

  return result;
}

/**
 * ------------------------------------------------------------
 * Approach 4: Optimal (Recursive Binary Exponentiation)
 *
 * Idea:
 * - Same math as iterative approach
 * - Use divide-and-conquer recursion
 *
 * Time Complexity: O(log n)
 * Space Complexity: O(log n)  // recursion stack
 *
 * Stable: Yes
 */
function countGoodNumbersRecursive(n: number): number {
  const evenCount = BigInt(Math.floor((n + 1) / 2));
  const oddCount = BigInt(Math.floor(n / 2));

  const evenWays = modPowRecursive(5n, evenCount);
  const oddWays = modPowRecursive(4n, oddCount);

  return Number((evenWays * oddWays) % MOD);
}

/**
 * Recursive Binary Exponentiation
 */
function modPowRecursive(base: bigint, exp: bigint): bigint {
  if (exp === 0n) return 1n;

  const half = modPowRecursive(base, exp / 2n);
  let result = (half * half) % MOD;

  if (exp & 1n) {
    result = (result * base) % MOD;
  }

  return result;
}

/**
 * ------------------------------------------------------------
 * Sanity Tests
 */
console.log(countGoodNumbersOptimal(1)); // 5
console.log(countGoodNumbersOptimal(4)); // 400
console.log(countGoodNumbersOptimal(50)); // 564908303

console.log(countGoodNumbersRecursive(50)); // 564908303
