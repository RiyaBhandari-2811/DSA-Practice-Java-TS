/**
 * Problem:
 * Return the largest-valued odd integer substring
 * from the given numeric string.
 */

class LargestOddNumberInString {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n^3)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static largestOddBrute(s: string): string {
    let ans = "";

    for (let i = 0; i < s.length; i++) {
      for (let j = i; j < s.length; j++) {
        let sub = s.substring(i, j + 1);

        let k = 0;
        while (k < sub.length && sub[k] === "0") k++;
        if (k === sub.length) continue;

        sub = sub.substring(k);
        const lastDigit = Number(sub[sub.length - 1]);

        if (lastDigit % 2 === 1) {
          if (ans === "" || sub.length > ans.length) {
            ans = sub;
          }
        }
      }
    }

    return ans;
  }

  /**
   * Approach 2: Optimal (Greedy)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static largestOddOptimal(s: string): string {
    let idx = -1;

    for (let i = s.length - 1; i >= 0; i--) {
      if (Number(s[i]) % 2 === 1) {
        idx = i;
        break;
      }
    }

    if (idx === -1) return "";

    let res = s.substring(0, idx + 1);

    let k = 0;
    while (k < res.length && res[k] === "0") k++;

    return k === res.length ? "" : res.substring(k);
  }
}

// Test
console.log(LargestOddNumberInString.largestOddBrute("35427"));
console.log(LargestOddNumberInString.largestOddOptimal("35427"));

console.log(LargestOddNumberInString.largestOddBrute("4206"));
console.log(LargestOddNumberInString.largestOddOptimal("4206"));
