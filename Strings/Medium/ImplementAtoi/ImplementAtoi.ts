class ImplementAtoi {

    /**
     * Problem:
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
     *
     * Rules:
     * 1. Ignore leading whitespaces
     * 2. Handle optional '+' or '-' sign
     * 3. Read digits until a non-digit is found
     * 4. Clamp result within [-2^31, 2^31 - 1]
     */

    /**
     * Approach 1: Brute Force (Using Number Parsing)
     *
     * Idea:
     * - Trim leading spaces
     * - Extract sign
     * - Collect digits into a string
     * - Parse using Number
     * - Clamp manually
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Yes
     */
    static myAtoiBrute(s: string): number {
        if (!s || s.length === 0) return 0;

        s = s.trim();
        if (s.length === 0) return 0;

        let index = 0;
        let sign = 1;

        if (s[index] === '+' || s[index] === '-') {
            sign = s[index] === '-' ? -1 : 1;
            index++;
        }

        let numStr = "";

        while (index < s.length && s[index] >= '0' && s[index] <= '9') {
            numStr += s[index];
            index++;
        }

        if (numStr.length === 0) return 0;

        let value = Number(numStr) * sign;

        const INT_MAX = 2 ** 31 - 1;
        const INT_MIN = -(2 ** 31);

        if (value > INT_MAX) return INT_MAX;
        if (value < INT_MIN) return INT_MIN;

        return value;
    }

    /**
     * Approach 2: Iterative Parsing with Overflow Check
     *
     * Idea:
     * - Traverse character by character
     * - Build number using arithmetic
     * - Check overflow before multiplying by 10
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static myAtoiBetter(s: string): number {
        let i = 0;
        const n = s.length;

        while (i < n && s[i] === ' ') i++;

        let sign = 1;
        if (i < n && (s[i] === '+' || s[i] === '-')) {
            sign = s[i] === '-' ? -1 : 1;
            i++;
        }

        let result = 0;
        const INT_MAX = 2 ** 31 - 1;
        const INT_MIN = -(2 ** 31);

        while (i < n && s[i] >= '0' && s[i] <= '9') {
            const digit = s.charCodeAt(i) - '0'.charCodeAt(0);

            if (result > Math.floor((INT_MAX - digit) / 10)) {
                return sign === 1 ? INT_MAX : INT_MIN;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    /**
     * Approach 3: Optimal (Single Pass, Precise Overflow Handling)
     *
     * Idea:
     * - One pass only
     * - Inline overflow detection
     * - Stop parsing as soon as invalid character appears
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static myAtoiOptimal(s: string): number {
        let i = 0;
        const n = s.length;
        let sign = 1;
        let result = 0;

        const INT_MAX = 2 ** 31 - 1;
        const INT_MIN = -(2 ** 31);

        while (i < n && s[i] === ' ') i++;

        if (i < n && (s[i] === '+' || s[i] === '-')) {
            sign = s[i] === '-' ? -1 : 1;
            i++;
        }

        while (i < n && s[i] >= '0' && s[i] <= '9') {
            const digit = s.charCodeAt(i) - '0'.charCodeAt(0);

            if (
                result > Math.floor(INT_MAX / 10) ||
                (result === Math.floor(INT_MAX / 10) && digit > 7)
            ) {
                return sign === 1 ? INT_MAX : INT_MIN;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}

// Driver code
console.log(ImplementAtoi.myAtoiBrute("42"));
console.log(ImplementAtoi.myAtoiBetter("   -42"));
console.log(ImplementAtoi.myAtoiOptimal("4193 with words"));
console.log(ImplementAtoi.myAtoiOptimal("2147483648"));
console.log(ImplementAtoi.myAtoiOptimal("-91283472332"));
