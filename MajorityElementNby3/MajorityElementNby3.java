
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementNby3 {

    /**
     * Problem: Find all elements that appear more than n/3 times in the array.
     *
     * Constraint: - At most two elements can satisfy this condition.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - For each element, count its frequency. - If frequency > n/3, add
     * it to result. - Avoid duplicates in result.
     *
     * Time Complexity: O(n^2) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static List<Integer> majorityBrute(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (result.contains(nums[i])) {
                continue;
            }

            int count = 0;
            for (int j = 0; j < n; j++) {
                if (nums[j] == nums[i]) {
                    count++;
                }
            }

            if (count > n / 3) {
                result.add(nums[i]);
            }
        }
        return result;
    }

    /**
     * Approach 2: Hashing
     *
     * Idea: - Store frequency of each element in a HashMap. - Add elements
     * whose frequency > n/3.
     *
     * Time Complexity: O(n) Space Complexity: O(n)
     *
     * Stable: Yes
     */
    public static List<Integer> majorityHashing(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        int n = nums.length;

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > n / 3) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    /**
     * Approach 3: Boyer Moore Voting Algorithm (Extended) - Optimal
     *
     * Idea: - Maintain two candidates and their counts. - First pass finds
     * potential candidates. - Second pass verifies their actual counts.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static List<Integer> majorityOptimal(int[] nums) {
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int num : nums) {
            if (num == candidate1) {
                count1++; 
            }else if (num == candidate2) {
                count2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        int n = nums.length;

        if (count1 > n / 3) {
            result.add(candidate1);
        }
        if (count2 > n / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 2, 2, 2};

        System.out.println(majorityBrute(nums));
        System.out.println(majorityHashing(nums));
        System.out.println(majorityOptimal(nums));
    }
}
