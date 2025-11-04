
/**
 *
 * Problem. Return the second largest distinct element in nums. If it does not exist, return Integer.MIN_VALUE (or an agreed sentinel).
 *
 *  *Example
 *
 *  *Input: [5, 1, 5, 3]
 * Output: 3
 *
 *
 *
 *  *Edge cases: All elements equal -> no second largest.
 *
 *  *Sample test cases
 *
 * [5,1,5,3] -> 3
 * [2,2,2] -> INT_MIN
 * [10,9] -> 9
 */
/**
 * How input works here:
 * First, you enter the number of test cases t.
 * For each test case:
 * Enter the size of the array.
 * Then enter n space-separated integers.
 */
/** *
 * Worst -> do sorting and return second last index so TC will be O(n logn) and SC depending on the implementation so O(1) or O(n)
 * Best -> N.A.
 * Optimised -> Keep two variables, first and second, to track the largest and second-largest distinct values while iterating. TC: O(n) and SC: O(1)
 *
 */
import java.util.*;

public class Find_the_second_largest_element_in_an_array {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Number of test cases
            int t = sc.nextInt();

            while (t-- > 0) {
                // Input: size of array
                int n = sc.nextInt();
                int[] nums = new int[n];

                // Input: array elements
                for (int i = 0; i < n; i++) {
                    nums[i] = sc.nextInt();
                }

                // Call the method and print result
                System.out.println(secondLargestDistinct(nums));
            }
        }
    }

    public static int secondLargestDistinct(int[] nums) {
        if (nums == null || nums.length < 2) {
            return Integer.MIN_VALUE;
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }

        return (second == Integer.MIN_VALUE) ? Integer.MIN_VALUE : second;
    }

}
