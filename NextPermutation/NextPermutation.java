import java.util.*;

/**
 * Next Permutation
 */
public class NextPermutation {

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * - Generate all permutations
     * - Sort them lexicographically
     * - Find current permutation
     * - Return next one
     *
     * Time Complexity: O(n! * n)
     * Space Complexity: O(n!)
     *
     * Stable: No
     *
     * How it works:
     * - Uses permutation generation
     * - Extremely slow, for learning only
     */
    public static void bruteForce(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        permute(nums, 0, perms);

        perms.sort((a, b) -> {
            for (int i = 0; i < a.size(); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) - b.get(i);
                }
            }
            return 0;
        });

        for (int i = 0; i < perms.size(); i++) {
            if (matches(nums, perms.get(i))) {
                List<Integer> next = perms.get((i + 1) % perms.size());
                for (int j = 0; j < nums.length; j++) {
                    nums[j] = next.get(j);
                }
                break;
            }
        }
    }

    private static void permute(int[] nums, int idx, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> temp = new ArrayList<>();
            for (int n : nums) temp.add(n);
            res.add(temp);
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);
            permute(nums, idx + 1, res);
            swap(nums, i, idx);
        }
    }

    private static boolean matches(int[] nums, List<Integer> list) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != list.get(i)) return false;
        }
        return true;
    }

    /**
     * BETTER APPROACH
     *
     * Idea:
     * - Copy array
     * - Use built-in next lexicographic logic via sorting suffix
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     *
     * Stable: No
     */
    public static void betterApproach(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        Arrays.sort(nums, i + 1, n);
    }

    /**
     * OPTIMAL APPROACH
     *
     * Idea:
     * - Find first decreasing index from right
     * - Swap with just larger element on right
     * - Reverse the suffix
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: No
     *
     * How it works:
     * - Guarantees next lexicographical permutation
     */
    public static void optimal(int[] nums) {
        int n = nums.length;
        int idx = -1;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        for (int i = n - 1; i > idx; i--) {
            if (nums[i] > nums[idx]) {
                swap(nums, i, idx);
                break;
            }
        }

        reverse(nums, idx + 1, n - 1);
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        optimal(nums);
        System.out.println(Arrays.toString(nums));
    }
}
