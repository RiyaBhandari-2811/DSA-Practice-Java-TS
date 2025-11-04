
/**
 *
 *  *Problem. Given an integer array nums, return the smallest element.
 *
 *  *Example
 *
 *  *Input: [3, 5, 1, 9, 2]
 * Output: 1
 *
 *
 *  *Constraints
 * Same as problem 1.
 *
 *  *Sample test cases
 *
 *  *[3,5,1,9,2] -> 1
 * [-2,-8,-1] -> -8
 * [7] -> 7
 *
 */
/**
 * How input works here:
 * First, you enter the number of test cases t.
 * For each test case:
 * Enter the size of the array.
 * Then enter n space-separated integers.
 */
import java.util.*;

public class Find_the_smallest_element_in_an_array {

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
                System.out.println(findTheSmallestElement(nums));
            }
        }
    }

    static int findTheSmallestElement(int[] nums) {
        int minVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < minVal) {
                minVal = nums[i];
            }
        }

        return minVal;
    }
}
