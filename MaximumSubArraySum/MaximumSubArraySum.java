public class MaximumSubArraySum {

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * - Generate all possible subarrays using two loops.
     * - Compute sum for each subarray.
     * - Track maximum sum and corresponding subarray indices.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * Stable: Yes (does not modify input)
     *
     * How it works:
     * - For each start index i, keep adding elements till j
     * - Update maxSum whenever a higher sum is found
     */
    public static void bruteForce(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    start = i;
                    end = j;
                }
            }
        }

        printResult(arr, maxSum, start, end);
    }

    /**
     * BETTER APPROACH (Prefix-like accumulation)
     *
     * Idea:
     * - Fix starting index
     * - Keep accumulating sum forward
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * How it works:
     * - Same as brute but avoids recomputation of subarray sums
     */
    public static void betterApproach(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int start = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            int currSum = 0;
            for (int j = i; j < arr.length; j++) {
                currSum += arr[j];
                if (currSum > maxSum) {
                    maxSum = currSum;
                    start = i;
                    end = j;
                }
            }
        }

        printResult(arr, maxSum, start, end);
    }

    /**
     * OPTIMAL APPROACH (Kadane's Algorithm)
     *
     * Idea:
     * - Maintain current sum
     * - Reset sum if it becomes negative
     * - Track subarray indices
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * How it works:
     * - If current sum < 0, start new subarray
     * - Update max sum when a better sum is found
     */
    public static void optimalKadane(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        int start = 0, tempStart = 0, end = 0;

        for (int i = 0; i < arr.length; i++) {
            currSum += arr[i];

            if (currSum > maxSum) {
                maxSum = currSum;
                start = tempStart;
                end = i;
            }

            if (currSum < 0) {
                currSum = 0;
                tempStart = i + 1;
            }
        }

        printResult(arr, maxSum, start, end);
    }

    private static void printResult(int[] arr, int sum, int start, int end) {
        System.out.print("Max Sum: " + sum + " | Subarray: ");
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        bruteForce(arr);
        betterApproach(arr);
        optimalKadane(arr);
    }
}
