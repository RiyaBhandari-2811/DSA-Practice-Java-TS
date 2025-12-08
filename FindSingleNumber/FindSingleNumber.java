public class FindSingleNumber {

    /**
     * Problem:
     * Given an array where every number appears exactly twice except one number
     * which appears only once, return the number that appears once.
     *
     * Example:
     * nums = [4,1,2,1,2] → answer = 4
     */

    /**
     * Approach 1: Brute Force (Double Loop)
     *
     * Idea:
     * - For each element nums[i], count how many times it appears by scanning the array.
     * - If its frequency is 1, return that value.
     *
     * Edge Cases:
     * - nums is null → IllegalArgumentException
     * - nums is empty → IllegalArgumentException
     * - If no single element exists → IllegalStateException
     *
     * Time Complexity: O(n^2)
     * - For each element, we scan the entire array to count its occurrences.
     *
     * Space Complexity: O(1)
     *
     * Example:
     * - [2,2,1] → 1
     * - [4,1,2,1,2] → 4
     */
    public static int findSingleBrute(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }
            if (count == 1) {
                return nums[i];
            }
        }

        throw new IllegalStateException("No element appears exactly once");
    }

    /**
     * Approach 2: Hashing using a HashMap / HashSet
     *
     * Idea:
     * - Insert count of each number in a HashMap OR:
     * - Use a HashSet:
     *      Sum of unique elements × 2 - sum of all elements = single number.
     *
     * Why better?
     * - Counting becomes O(1) using hashing.
     *
     * Edge Cases:
     * - nums null/empty → IllegalArgumentException
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Example:
     * - [2,2,1] → 1
     * - [4,1,2,1,2] → 4
     */
    public static int findSingleUsingHashing(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        int sumSet = 0;
        int sumArray = 0;

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumSet += num;
            }
            sumArray += num;
        }

        return 2 * sumSet - sumArray;
    }

    /**
     * Approach 3: Optimal Using XOR
     *
     * Idea:
     * - XOR has special properties:
     *      x ^ x = 0
     *      x ^ 0 = x
     *      XOR is associative and commutative
     * - So, XOR of all elements cancels out pairs and leaves the single number.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Edge Cases:
     * - nums null/empty → IllegalArgumentException
     *
     * Example:
     * - [2,2,1] → 1
     * - [4,1,2,1,2] → 4
     */
    public static int findSingleUsingXor(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        return xor;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};
        int[] nums3 = {7, 7, 9};

        int[][] tests = {nums1, nums2, nums3};
        String[] names = {"nums1", "nums2", "nums3"};

        for (int i = 0; i < tests.length; i++) {
            System.out.println("Testing " + names[i]);

            System.out.println("Brute:     " + findSingleBrute(tests[i]));
            System.out.println("Hashing:   " + findSingleUsingHashing(tests[i]));
            System.out.println("XOR:       " + findSingleUsingXor(tests[i]));

            System.out.println();
        }
    }
}
