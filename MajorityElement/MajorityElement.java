import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    /**
     * Problem:
     * Given an array of size n, return the element that appears more than n/2 times.
     * It is guaranteed that a majority element always exists.
     *
     * Example:
     * [3,2,3] -> 3
     * [2,2,1,1,1,2,2] -> 2
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - For each element, count its frequency.
     * - If frequency > n/2, return that element.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int majorityBrute(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[i] == nums[j]) count++;
            }
            if (count > n / 2) return nums[i];
        }

        return -1;
    }

    /**
     * Approach 2: Hashing
     *
     * Idea:
     * - Store frequency of each element.
     * - Return element whose count exceeds n/2.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int majorityHashing(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
            if (freq.get(num) > n / 2) {
                return num;
            }
        }

        return -1;
    }

    /**
     * Approach 3: Sorting
     *
     * Idea:
     * - Sort array.
     * - Element at index n/2 is guaranteed to be majority.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1)
     */
    public static int majoritySorting(int[] nums) {
        java.util.Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * Approach 4: Boyer Moore Voting Algorithm (Optimal)
     *
     * Idea:
     * - Maintain candidate and count.
     * - Increase count if same element, decrease otherwise.
     * - Final candidate is majority element.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int majorityBoyerMoore(int[] nums) {
        int count = 0;
        int candidate = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};

        System.out.println(majorityBrute(nums1));
        System.out.println(majorityHashing(nums2));
        System.out.println(majoritySorting(nums2));
        System.out.println(majorityBoyerMoore(nums2));
    }
}
