public class MaximumConsecutiveOnes {

    /**
     * Problem statement:
     * Given a binary array nums (containing only 0s and 1s), return the maximum number
     * of consecutive 1s in the array.
     *
     * Example:
     * - nums = [1, 1, 0, 1, 1, 1] -> answer = 3
     */

    /**
     * Approach 1: Brute force using nested loops.
     *
     * Idea:
     * - For every index i, treat it as a possible starting point of a streak of 1s.
     * - From i, move j to the right while nums[j] is 1 and count the length of this streak.
     * - Update the global maximum streak length.
     * - This recomputes overlapping work many times, so it is not efficient but easy to understand.
     *
     * Assumptions:
     * - The array contains only 0s and 1s. If other values exist, they are treated as a break in streak (not equal to 1).
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0 (no ones, no streak).
     * - Array with all zeros -> return 0.
     * - Array with all ones -> return nums.length.
     *
     * Time Complexity: O(n^2)
     * - For each starting index i, the inner loop may scan up to n - i elements.
     * - Worst case when the array is all ones, the total number of operations is roughly n + (n - 1) + ... + 1 = O(n^2).
     *
     * Space Complexity: O(1)
     * - Uses only constant extra space.
     *
     * Example test cases:
     * - [1, 1, 0, 1, 1, 1] -> 3
     * - [1, 0, 1, 1, 0, 1] -> 2
     * - [0, 0, 0]          -> 0
     * - [1, 1, 1, 1]       -> 4
     */
    public static int maxConsecutiveOnesBrute(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        if (nums.length == 0) {
            return 0;
        }

        int maxStreak = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentStreak = 0;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) {
                    currentStreak++;
                    maxStreak = Math.max(maxStreak, currentStreak);
                } else {
                    // streak broken
                    break;
                }
            }
        }

        return maxStreak;
    }

    /**
     * Approach 2: Better approach using a single pass and a running counter.
     *
     * Idea:
     * - Traverse the array once.
     * - Maintain a currentStreak that counts how many consecutive 1s we have seen so far.
     * - When we see 1: increment currentStreak and update maxStreak.
     * - When we see 0 (or any non 1 value): reset currentStreak to 0, since the streak is broken.
     *
     * Why this is better than brute:
     * - Instead of starting from every index and recalculating, we reuse the information from the previous element.
     * - Each element is processed exactly once.
     *
     * Assumptions:
     * - The array contains only 0s and 1s for the standard problem.
     * - Non 1 values act as streak breakers.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0.
     * - All zeros -> return 0.
     * - All ones -> return nums.length.
     *
     * Time Complexity: O(n)
     * - A single pass over the array.
     *
     * Space Complexity: O(1)
     * - Uses a constant number of variables.
     *
     * Example test cases:
     * - [1, 1, 0, 1, 1, 1] -> 3
     * - [1, 0, 1, 1, 0, 1] -> 2
     * - [0, 0, 0]          -> 0
     * - [1, 1, 1, 1]       -> 4
     * - []                 -> 0
     */
    public static int maxConsecutiveOnesBetter(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        if (nums.length == 0) {
            return 0;
        }

        int maxStreak = 0;
        int currentStreak = 0;

        for (int value : nums) {
            if (value == 1) {
                currentStreak++;
                maxStreak = Math.max(maxStreak, currentStreak);
            } else {
                currentStreak = 0;
            }
        }

        return maxStreak;
    }

    /**
     * Approach 3: Optimised pattern using explicit window boundaries.
     *
     * Idea:
     * - This is still O(n) time and O(1) space like the better approach, but uses an explicit
     *   window pattern that scales easily to more advanced variations, for example:
     *   maximum consecutive ones if you are allowed to flip at most one zero.
     * - For the basic problem with no flips:
     *   - We expand the right pointer one step at a time.
     *   - When we see 1: extend the window and update maximum.
     *   - When we see 0: the window of valid ones starts after this index.
     *
     * How it works here:
     * - left marks the start index of the current window of consecutive ones.
     * - When we see a zero at index right, the next potential streak starts at right + 1,
     *   so we set left = right + 1.
     * - The length of the current valid window is right - left + 1 when nums[right] is 1.
     *
     * This approach is functionally equivalent to the better solution in terms of complexity,
     * but it is written in a sliding window style that is very useful for related problems.
     *
     * Assumptions:
     * - The array contains only 0s and 1s as per the classic problem.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0.
     * - All zeros -> return 0.
     * - All ones -> return nums.length.
     *
     * Time Complexity: O(n)
     * - Two pointers (left and right) each move from left to right at most once.
     *
     * Space Complexity: O(1)
     * - Uses a constant amount of extra space.
     *
     * Example test cases:
     * - [1, 1, 0, 1, 1, 1] -> 3
     * - [1, 0, 1, 1, 0, 1] -> 2
     * - [0, 0, 0]          -> 0
     * - [1, 1, 1, 1]       -> 4
     */
    public static int maxConsecutiveOnesOptimised(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        if (nums.length == 0) {
            return 0;
        }

        int maxStreak = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                // current streak broken, next streak starts after this zero
                left = right + 1;
            } else {
                int currentLength = right - left + 1;
                maxStreak = Math.max(maxStreak, currentLength);
            }
        }

        return maxStreak;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 0, 1, 1, 1};     // answer 3
        int[] nums2 = {1, 0, 1, 1, 0, 1};     // answer 2
        int[] nums3 = {0, 0, 0};              // answer 0
        int[] nums4 = {1, 1, 1, 1};           // answer 4
        int[] nums5 = {};                     // answer 0
        int[] nums6 = {0, 1, 1, 1, 0, 1, 1};  // answer 3

        int[][] tests = {nums1, nums2, nums3, nums4, nums5, nums6};
        String[] names = {"nums1", "nums2", "nums3", "nums4", "nums5", "nums6"};

        for (int i = 0; i < tests.length; i++) {
            int[] arr = tests[i];
            System.out.println("Testing " + names[i] + ":");

            System.out.println("Brute:     " + maxConsecutiveOnesBrute(arr));
            System.out.println("Better:    " + maxConsecutiveOnesBetter(arr));
            System.out.println("Optimised: " + maxConsecutiveOnesOptimised(arr));
            System.out.println();
        }
    }
}
