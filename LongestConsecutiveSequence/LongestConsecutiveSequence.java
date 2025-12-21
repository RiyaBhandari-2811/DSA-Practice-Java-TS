import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    /**
     * Problem:
     * Given an unsorted array of integers, find the length of the longest
     * consecutive elements sequence.
     *
     * Definition:
     * A consecutive sequence means numbers follow each other with a difference of 1.
     * The sequence does NOT need to be contiguous in the array.
     *
     * Example:
     * Input:  [100, 4, 200, 1, 3, 2]
     * Output: 4
     * Explanation: Longest sequence is [1, 2, 3, 4]
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - For each element, try to build a consecutive sequence.
     * - Repeatedly check if the next number exists using linear search.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * Stable: Not Applicable
     * - Only the length of sequence is required.
     */
    public static int longestConsecutiveBrute(int[] nums) {
        int longest = 0;

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            int count = 1;

            while (linearSearch(nums, current + 1)) {
                current++;
                count++;
            }

            longest = Math.max(longest, count);
        }

        return longest;
    }

    private static boolean linearSearch(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) return true;
        }
        return false;
    }

    /**
     * Approach 2: Better (Sorting)
     *
     * Idea:
     * - Sort the array.
     * - Traverse and count consecutive increasing elements.
     * - Skip duplicates.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) (ignoring sorting space)
     *
     * Stable: Not Applicable
     */
    public static int longestConsecutiveSorting(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int longest = 1;
        int currentStreak = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                // continue;
            } else if (nums[i] == nums[i - 1] + 1) {
                currentStreak++;
            } else {
                longest = Math.max(longest, currentStreak);
                currentStreak = 1;
            }
        }

        return Math.max(longest, currentStreak);
    }

    /**
     * Approach 3: Optimal (HashSet)
     *
     * Idea:
     * - Store all elements in a HashSet.
     * - Start counting only if the current number is the beginning
     *   of a sequence (num - 1 does not exist).
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * Stable: Not Applicable
     */
    public static int longestConsecutiveOptimal(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int count = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};

        System.out.println(longestConsecutiveBrute(nums));
        System.out.println(longestConsecutiveSorting(nums.clone()));
        System.out.println(longestConsecutiveOptimal(nums));
    }
}
