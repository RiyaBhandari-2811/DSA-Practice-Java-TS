/**
 * Problem:
 * Determine if two strings are isomorphic.
 */

class IsomorphicStrings {

  /**
   * Approach 1: Brute Force (Two Maps)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   *
   * Stable: Yes
   */
  static isIsomorphicBrute(s: string, t: string): boolean {
    if (s.length !== t.length) return false;

    const mapST = new Map<string, string>();
    const mapTS = new Map<string, string>();

    for (let i = 0; i < s.length; i++) {
      const c1 = s[i];
      const c2 = t[i];

      if (mapST.has(c1)) {
        if (mapST.get(c1) !== c2) return false;
      } else {
        mapST.set(c1, c2);
      }

      if (mapTS.has(c2)) {
        if (mapTS.get(c2) !== c1) return false;
      } else {
        mapTS.set(c2, c1);
      }
    }

    return true;
  }

  /**
   * Approach 2: Optimised (Last Seen Index)
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static isIsomorphicOptimal(s: string, t: string): boolean {
    if (s.length !== t.length) return false;

    const lastS = new Array<number>(256).fill(0);
    const lastT = new Array<number>(256).fill(0);

    for (let i = 0; i < s.length; i++) {
      const c1 = s.charCodeAt(i);
      const c2 = t.charCodeAt(i);

      if (lastS[c1] !== lastT[c2]) return false;

      lastS[c1] = i + 1;
      lastT[c2] = i + 1;
    }

    return true;
  }
}

// Test
console.log(IsomorphicStrings.isIsomorphicBrute("egg", "add"));
console.log(IsomorphicStrings.isIsomorphicOptimal("egg", "add"));

console.log(IsomorphicStrings.isIsomorphicBrute("foo", "bar"));
console.log(IsomorphicStrings.isIsomorphicOptimal("foo", "bar"));
