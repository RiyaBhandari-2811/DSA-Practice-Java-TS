class RomanToInteger {

    /**
     * Problem:
     * Convert a Roman numeral string to an integer.
     *
     * Symbols:
     * I = 1, V = 5, X = 10, L = 50,
     * C = 100, D = 500, M = 1000
     *
     * Special subtraction cases:
     * IV = 4, IX = 9
     * XL = 40, XC = 90
     * CD = 400, CM = 900
     */

    /**
     * Approach 1: Brute Force (Explicit Subtraction Cases)
     *
     * Idea:
     * - Convert Roman string to character array
     * - Check current and next character
     * - Handle subtraction cases manually
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static romanToIntBrute(s: string): number {
        const map: Record<string, number> = {
            I: 1, V: 5, X: 10, L: 50,
            C: 100, D: 500, M: 1000
        };

        let result = 0;

        for (let i = 0; i < s.length; i++) {
            const curr = map[s[i]];
            const next = i + 1 < s.length ? map[s[i + 1]] : 0;

            if (curr < next) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }

    /**
     * Approach 2: Optimised Left-to-Right Comparison
     *
     * Idea:
     * - Traverse from left to right
     * - If current value is less than next, subtract it
     * - Else add it
     *
     * This removes the need to explicitly check
     * all subtraction combinations.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static romanToIntBetter(s: string): number {
        const values: Record<string, number> = {
            I: 1, V: 5, X: 10, L: 50,
            C: 100, D: 500, M: 1000
        };

        let sum = 0;

        for (let i = 0; i < s.length; i++) {
            if (
                i < s.length - 1 &&
                values[s[i]] < values[s[i + 1]]
            ) {
                sum -= values[s[i]];
            } else {
                sum += values[s[i]];
            }
        }
        return sum;
    }

    /**
     * Approach 3: Optimal (Right-to-Left Traversal)
     *
     * Idea:
     * - Traverse from right to left
     * - Keep track of previous value
     * - If current < previous, subtract
     * - Else add
     *
     * This is the most intuitive and interview preferred solution.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static romanToIntOptimal(s: string): number {
        const map: Record<string, number> = {
            I: 1, V: 5, X: 10, L: 50,
            C: 100, D: 500, M: 1000
        };

        let total = 0;
        let prev = 0;

        for (let i = s.length - 1; i >= 0; i--) {
            const curr = map[s[i]];

            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }
            prev = curr;
        }
        return total;
    }
}

// Driver code
console.log(RomanToInteger.romanToIntBrute("III"));      // 3
console.log(RomanToInteger.romanToIntBetter("LVIII"));  // 58
console.log(RomanToInteger.romanToIntOptimal("MCMXCIV"));// 1994
