
/**
 *
 * Problem. Return the second smallest distinct element. If none, return Integer.MAX_VALUE sentinel.
 *
 *  *Example
 *
 *  *Input: [4, 2, 2, 7, 3]
 * Output: 3
 *
 *
 *  *Sample test cases
 *
 *  *[4,2,2,7,3] -> 3
 * [1] -> INT_MAX
 * [5,4] -> 5
 */

/** *
 * Worst -> do sorting and return second last index so TC will be O(n logn) and SC depending on the implementation so O(1) or O(n)
 * Best -> N.A.
 * Optimised -> Keep two variables, first and second, to track the largest and second-largest distinct values while iterating. TC: O(n) and SC: O(1)
 *
 */

public class Find_the_second_smallest_element_in_an_array {

    public static void main(String[] args) {

        int[][] testCases = {
            {4, 2, 2, 7, 3}, // expected: 3
            {1}, // expected: INT_MAX
            {5, 4}, // expected: 5
            {2, 1}, // expected: 2
            {1, 1, 1}, // expected: INT_MAX
            {5, 4, 3, 2, 1}, // expected: 2
            {1, 2, 3, 4, 5}, // expected: 2
            {3, 1, 2}, // expected: 2
            {1, 2, 2, 3, 3, 3}, // expected: 2
            {Integer.MAX_VALUE, Integer.MAX_VALUE}, // expected: INT_MAX
            {Integer.MIN_VALUE, Integer.MAX_VALUE}, // expected: Integer.MAX_VALUE
            {0, 0, 1}, // expected: 1
            {-1, -1, 0, 5}, // expected: 0
            {-5, -2, -3, -5}, // expected: -3
            {-5, -5, -5, -5}, // expected: INT_MAX
            {100, 90, 90, 80}, // expected: 90
            {2, 2, 3, 3, 4, 4}, // expected: 3
            {2, 2, 2, 3}, // expected: 3
            {3, 3, 2, 2, 1, 1}, // expected: 2
            {7, 7, 5, 6, 6, 5}, // expected: 6
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = secondSmallestDistinct(testCases[i]);
            System.out.println("Test " + (i + 1) + " -> " + result);
        }
    }

    public static int secondSmallestDistinct(int[] nums) {
        if (nums == null || nums.length < 2) {
            return Integer.MAX_VALUE;
        }

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num == first || num == second) {
                continue;
            }

            if (num < first) {
                second = first;
                first = num;
            } else if (num < second && num != first) {
                second = num;
            }
        }

        return (second == Integer.MAX_VALUE) ? Integer.MAX_VALUE : second;
    }
}
