public class LongestSubarrayWithGivenSumKPositives {

    /**
     * Problem:
     * Given an array of integers and a target sum K, find the length of the longest
     * contiguous subarray whose sum is exactly K.
     *
     * Special case:
     * - When the array contains only positive numbers (and/or zeros), we can use a
     *   sliding window for an O(n) optimal solution.
     *
     * Example:
     * - nums = [1, 2, 3, 1, 1, 1, 2], K = 6 → longest subarray length = 3
     *     Explanation: subarray [1, 2, 3] or [1, 1, 1, 2] (length 4) if it sums to 5 etc.
     */

    /**
     * Approach 1: Brute force using nested loops (check all subarrays).
     *
     * Idea:
     * - For every starting index i, extend the ending index j from i to n - 1.
     * - Maintain a running sum for the subarray nums[i..j].
     * - If sum == K, update the maximum length.
     *
     * Works for:
     * - Any integers (positive, zero, negative).
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0 (no subarray).
     * - No subarray sums to K -> return 0.
     *
     * Time Complexity: O(n^2)
     * - Outer loop over i (0..n-1), inner loop over j (i..n-1).
     *
     * Space Complexity: O(1)
     * - Uses only a few integer variables.
     *
     * Example test cases:
     * - nums = [1, 2, 3, 1, 1, 1, 2], K = 6 -> 3 ([1,2,3])
     * - nums = [1, 1, 1, 1], K = 2 -> 2 ([1,1])
     * - nums = [2, 3, 5], K = 10 -> 3 ([2,3,5])
     * - nums = [2, -1, 2, 3], K = 3 -> 3 ([-1,2,2] or [1,2]? depends on exact example)
     */
    public static int longestSubarrayBrute(int[] nums, int K) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        int n = nums.length;
        int maxLen = 0;

        for (int start = 0; start < n; start++) {
            int sum = 0;
            for (int end = start; end < n; end++) {
                sum += nums[end];

                if (sum == K) {
                    int length = end - start + 1;
                    if (length > maxLen) {
                        maxLen = length;
                    }
                }
            }
        }

        return maxLen;
    }

    /**
     * Approach 2: Better using prefix sum + HashMap.
     *
     * Idea:
     * - Maintain a running prefix sum: prefix[i] = nums[0] + ... + nums[i].
     * - For each index i with prefixSum:
     *     We want a previous index j (j < i) such that:
     *         prefixSum(i) - prefixSum(j) = K
     *     → prefixSum(j) = prefixSum(i) - K
     * - Store the earliest index for each prefix sum in a HashMap.
     * - For each prefixSum(i), check if prefixSum(i) - K is already in the map:
     *     - If yes, we have a subarray from (firstIndexOf(prefixSum(i) - K) + 1) to i
     *       with sum K; update max length accordingly.
     *
     * Works for:
     * - Any integers: positive, zero, negative.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0.
     * - No subarray sums to K -> return 0.
     *
     * Time Complexity: O(n)
     * - Single pass through the array, each HashMap operation is O(1) average.
     *
     * Space Complexity: O(n)
     * - HashMap can store up to n distinct prefix sums.
     *
     * Example test cases:
     * - nums = [1, 2, 3, 1, 1, 1, 2], K = 6 -> 3 ([1,2,3])
     * - nums = [1, 1, 1, 1], K = 2 -> 2 ([1,1])
     * - nums = [2, 3, 5], K = 10 -> 3 ([2,3,5])
     * - nums = [1, -1, 5, -2, 3], K = 3 -> 4 ([1, -1, 5, -2])
     */
    public static int longestSubarrayPrefixSum(int[] nums, int K) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        int n = nums.length;
        int maxLen = 0;
        int prefixSum = 0;

        java.util.Map<Integer, Integer> firstIndexOfPrefix = new java.util.HashMap<>();
        // Important: prefix sum 0 first appears at index -1 (before array starts)
        firstIndexOfPrefix.put(0, -1);

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];

            // We want prefixSum(i) - prefixSum(j) = K → prefixSum(j) = prefixSum(i) - K
            int needed = prefixSum - K;

            if (firstIndexOfPrefix.containsKey(needed)) {
                int startIndex = firstIndexOfPrefix.get(needed) + 1;
                int length = i - startIndex + 1;
                if (length > maxLen) {
                    maxLen = length;
                }
            }

            // Only store the first occurrence of this prefix sum
            firstIndexOfPrefix.putIfAbsent(prefixSum, i);
        }

        return maxLen;
    }

    /**
     * Approach 3: Optimal sliding window (two pointers) for positives (and zeros).
     *
     * IMPORTANT:
     * - This method works correctly ONLY when all numbers are non-negative
     *   (typically all positive, but zero is also allowed).
     *
     * Idea (for non-negative arrays):
     * - Maintain a window [left, right] and its current sum.
     * - Expand right to include more elements and increase sum.
     * - While sum > K, shrink from the left to decrease sum.
     * - Whenever sum == K, update max length.
     *
     * Why positives / non-negatives only:
     * - With non-negative numbers, increasing the window size can never decrease the sum,
     *   and shrinking the window can never increase the sum. This monotonicity is crucial.
     * - With negative numbers present, this invariant breaks and the sliding window logic
     *   does not guarantee correctness.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> return 0.
     * - K < 0 and all numbers are non-negative -> impossible, return 0.
     * - No subarray sums to K -> return 0.
     * - If any negative value is present, this implementation will still run, but the
     *   result is NOT guaranteed to be correct (caller should ensure positivity).
     *
     * Time Complexity: O(n)
     * - Each of left and right moves at most n times.
     *
     * Space Complexity: O(1)
     * - Uses a constant number of variables.
     *
     * Example test cases:
     * - nums = [1, 2, 3, 1, 1, 1, 2], K = 6 -> 3 ([1,2,3])
     * - nums = [1, 1, 1, 1], K = 2 -> 2 ([1,1])
     * - nums = [2, 3, 5], K = 10 -> 3 ([2,3,5])
     * - nums = [2, 2, 2], K = 4 -> 2 ([2,2])
     */
    public static int longestSubarraySlidingWindowPositives(int[] nums, int K) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null");
        }

        int n = nums.length;
        if (n == 0) {
            return 0;
        }

        if (K < 0) {
            // With all non-negative numbers, we can never achieve a negative sum
            return 0;
        }

        int left = 0;
        int sum = 0;
        int maxLen = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            // Shrink from the left while sum is too large
            while (left <= right && sum > K) {
                sum -= nums[left];
                left++;
            }

            // Now sum <= K
            if (sum == K) {
                int length = right - left + 1;
                if (length > maxLen) {
                    maxLen = length;
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1, 1, 1, 2};
        int K1 = 6; // longest subarray: [1,2,3] -> length 3

        int[] nums2 = {1, 1, 1, 1};
        int K2 = 2; // longest subarray: [1,1] -> length 2

        int[] nums3 = {2, 3, 5};
        int K3 = 10; // whole array -> length 3

        int[] nums4 = {2, 2, 2};
        int K4 = 4; // [2,2] -> length 2

        int[] nums5 = {1, -1, 5, -2, 3};
        int K5 = 3; // prefixSum method can handle negatives, length 4: [1,-1,5,-2]

        int[][] arrays = {nums1, nums2, nums3, nums4, nums5};
        int[] targets = {K1, K2, K3, K4, K5};
        String[] names = {"nums1", "nums2", "nums3", "nums4", "nums5"};

        for (int i = 0; i < arrays.length; i++) {
            int[] arr = arrays[i];
            int K = targets[i];
            System.out.println("Testing " + names[i] + " with K = " + K);

            System.out.println("Brute:           " + longestSubarrayBrute(arr, K));
            System.out.println("PrefixSum:       " + longestSubarrayPrefixSum(arr, K));
            System.out.println("SlidingWindow(+): " + longestSubarraySlidingWindowPositives(arr, K));

            System.out.println();
        }
    }
}
