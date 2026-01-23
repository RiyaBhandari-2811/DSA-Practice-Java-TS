class LongestPalindromicSubstring {

    /**
     * Approach 1: Brute Force
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     */
    static longestPalindromeBrute(s: string): string {
        let ans = "";

        const isPal = (l: number, r: number): boolean => {
            while (l < r) {
                if (s[l] !== s[r]) return false;
                l++; r--;
            }
            return true;
        };

        for (let i = 0; i < s.length; i++) {
            for (let j = i; j < s.length; j++) {
                if (isPal(i, j) && j - i + 1 > ans.length) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * Approach 2: Dynamic Programming
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     */
    static longestPalindromeDP(s: string): string {
        const n = s.length;
        const dp: boolean[][] = Array.from({ length: n }, () => Array(n).fill(false));
        let ans = "";

        for (let len = 1; len <= n; len++) {
            for (let i = 0; i + len - 1 < n; i++) {
                const j = i + len - 1;

                if (s[i] === s[j] && (len <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (len > ans.length) ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * Approach 3: Expand Around Center
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    static longestPalindromeExpand(s: string): string {
        let start = 0, maxLen = 1;

        const expand = (l: number, r: number): number => {
            while (l >= 0 && r < s.length && s[l] === s[r]) {
                l--; r++;
            }
            return r - l - 1;
        };

        for (let i = 0; i < s.length; i++) {
            const len = Math.max(expand(i, i), expand(i, i + 1));
            if (len > maxLen) {
                maxLen = len;
                start = i - Math.floor((len - 1) / 2);
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * Approach 4: Manacher’s Algorithm
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    static longestPalindromeManacher(s: string): string {
        let t = "^";
        for (const c of s) t += "#" + c;
        t += "#$";

        const p = new Array(t.length).fill(0);
        let center = 0, right = 0;

        for (let i = 1; i < t.length - 1; i++) {
            const mirror = 2 * center - i;
            if (i < right) p[i] = Math.min(right - i, p[mirror]);

            while (t[i + 1 + p[i]] === t[i - 1 - p[i]]) {
                p[i]++;
            }

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        let maxLen = 0, centerIndex = 0;
        for (let i = 1; i < p.length - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIndex = i;
            }
        }

        const start = Math.floor((centerIndex - maxLen) / 2);
        return s.substring(start, start + maxLen);
    }
}

// Driver
const s = "babad";
console.log(LongestPalindromicSubstring.longestPalindromeBrute(s));
console.log(LongestPalindromicSubstring.longestPalindromeDP(s));
console.log(LongestPalindromicSubstring.longestPalindromeExpand(s));
console.log(LongestPalindromicSubstring.longestPalindromeManacher(s));
