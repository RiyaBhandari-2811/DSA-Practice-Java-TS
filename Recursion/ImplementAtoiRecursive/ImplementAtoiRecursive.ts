/**
 * Approach: Recursive Parsing
 *
 * Idea:
 * - Skip leading whitespaces
 * - Handle optional '+' or '-' sign
 * - Recursively process digits
 * - Build number using integer arithmetic
 * - Detect overflow before multiplying by 10
 * - Stop recursion on non-digit
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)  // recursion stack
 *
 * Stable: Yes
 */
function myAtoiRecursive(s: string): number {
  if (!s || s.length === 0) return 0;

  let index = 0;
  const n = s.length;

  // Skip leading whitespaces
  while (index < n && s[index] === " ") {
    index++;
  }

  if (index === n) return 0;

  let sign = 1;
  if (s[index] === "+" || s[index] === "-") {
    sign = s[index] === "-" ? -1 : 1;
    index++;
  }

  return parseDigits(s, index, n, 0, sign);
}

function parseDigits(
  s: string,
  index: number,
  n: number,
  result: number,
  sign: number,
): number {
  // Base case: end of string or non-digit
  if (index >= n || s[index] < "0" || s[index] > "9") {
    return result * sign;
  }

  const digit = s.charCodeAt(index) - "0".charCodeAt(0);

  // Overflow detection
  if (
    result > Math.floor(Number.MAX_SAFE_INTEGER / 10) ||
    (result === Math.floor(Number.MAX_SAFE_INTEGER / 10) && digit > 7)
  ) {
    return sign === 1 ? 2147483647 : -2147483648;
  }

  return parseDigits(s, index + 1, n, result * 10 + digit, sign);
}
