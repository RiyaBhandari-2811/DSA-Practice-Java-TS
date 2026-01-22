class IntegerToRoman {

    /**
     * Problem:
     * Convert an integer to a Roman numeral.
     *
     * Constraints:
     * 1 <= num <= 3999
     */

    /**
     * Approach 1: Brute Force (Repeated Subtraction)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    static intToRomanBrute(num: number): string {
        let result = "";

        while (num >= 1000) { result += "M"; num -= 1000; }
        while (num >= 900)  { result += "CM"; num -= 900; }
        while (num >= 500)  { result += "D"; num -= 500; }
        while (num >= 400)  { result += "CD"; num -= 400; }
        while (num >= 100)  { result += "C"; num -= 100; }
        while (num >= 90)   { result += "XC"; num -= 90; }
        while (num >= 50)   { result += "L"; num -= 50; }
        while (num >= 40)   { result += "XL"; num -= 40; }
        while (num >= 10)   { result += "X"; num -= 10; }
        while (num >= 9)    { result += "IX"; num -= 9; }
        while (num >= 5)    { result += "V"; num -= 5; }
        while (num >= 4)    { result += "IV"; num -= 4; }
        while (num >= 1)    { result += "I"; num -= 1; }

        return result;
    }

    /**
     * Approach 2: Better (Predefined Arrays)
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    static intToRomanBetter(num: number): string {
        const values = [
            1000, 900, 500, 400,
            100, 90, 50, 40,
            10, 9, 5, 4, 1
        ];

        const symbols = [
            "M", "CM", "D", "CD",
            "C", "XC", "L", "XL",
            "X", "IX", "V", "IV", "I"
        ];

        let result = "";

        for (let i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result += symbols[i];
                num -= values[i];
            }
        }
        return result;
    }

    /**
     * Approach 3: Optimal (Greedy)
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    static intToRomanOptimal(num: number): string {
        return this.intToRomanBetter(num);
    }
}

// Driver code
console.log(IntegerToRoman.intToRomanBrute(3));      // III
console.log(IntegerToRoman.intToRomanBetter(58));    // LVIII
console.log(IntegerToRoman.intToRomanOptimal(1994)); // MCMXCIV
