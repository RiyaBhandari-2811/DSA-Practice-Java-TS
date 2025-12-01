
/**
 * Problem: Given an integer array nums, return the largest element.
 *
 * Input: int[] nums
 * Output: int
 *
 * Example:
 * Input:
 * 3
 * 5
 * 3 5 1 9 2
 * 3
 * -2 -8 -1
 * 1
 * 7
 *
 * Output:
 * 9
 * -1
 * 7
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * Edge cases: Single element array.
 */

/**
 * How input works here:
 * First, you enter the number of test cases t.
 * For each test case:
 * Enter the size of the array.
 * Then enter n space-separated integers.
 */
import java.util.*;

public class Find_the_largest_element_in_an_array {

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
                System.out.println(findTheLargestElement(nums));
            }
        }
    }

    static int findTheLargestElement(int[] nums) {
        int maxVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
            }
        }

        return maxVal;
    }
}
