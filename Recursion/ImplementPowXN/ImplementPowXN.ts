class ImplementPowXN {
  /**
   * Implement Pow(x, n)
   *
   * Problem:
   * - Given a number x and an integer n, compute x raised to the power n (x^n).
   *
   * Examples:
   * - x = 2, n = 10 → 1024
   * - x = 2, n = -2 → 0.25
   *
   * ------------------------------------------------------------
   * ITERATIVE APPROACH (Binary Exponentiation - Loop)
   *
   * Idea:
   * - Handle negative power by converting:
   *     x^(-n) = 1 / (x^n)
   * - Repeatedly:
   *     - If power is odd, multiply result by current x
   *     - Square x
   *     - Divide power by 2
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   */
  static myPowIterative(x: number, n: number): number {
    let power = n;

    // handle negative power
    if (power < 0) {
      x = 1 / x;
      power = -power;
    }

    let result = 1;

    while (power > 0) {
      // if power is odd
      if (power % 2 === 1) {
        result = result * x;
      }

      // square the base
      x = x * x;

      // reduce power
      power = Math.floor(power / 2);
    }

    return result;
  }

  /**
   * ------------------------------------------------------------
   * RECURSIVE APPROACH (Divide and Conquer)
   *
   * Idea:
   * - Handle negative power once at the start.
   * - Recursively divide power by 2.
   * - While returning:
   *     - If n is even → half * half
   *     - If n is odd  → half * half * x
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(log n)
   */
  static myPowRecursive(x: number, n: number): number {
    if (n < 0) {
      return 1 / this.power(x, -n);
    }
    return this.power(x, n);
  }

  private static power(x: number, n: number): number {
    // base case
    if (n === 0) {
      return 1;
    }

    const half = this.power(x, Math.floor(n / 2));

    // if n is even
    if (n % 2 === 0) {
      return half * half;
    }

    // if n is odd
    return half * half * x;
  }
}

// ------------------------------------------------------------
// For demonstration / testing

const tests: Array<[number, number]> = [
  [2, 10],
  [2, -2],
  [5, 0],
  [2, -3],
];

const names = [
  "case1 x=2, n=10",
  "case2 x=2, n=-2",
  "case3 x=5, n=0",
  "case4 x=2, n=-3",
];

for (let i = 0; i < tests.length; i++) {
  const [x, n] = tests[i];
  console.log("Testing", names[i]);
  console.log("Iterative :", ImplementPowXN.myPowIterative(x, n));
  console.log("Recursive :", ImplementPowXN.myPowRecursive(x, n));
  console.log();
}
