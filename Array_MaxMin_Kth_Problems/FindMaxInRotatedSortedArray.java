public class FindMaxInRotatedSortedArray {

    /**
     * Linear scan approach to find the maximum in a rotated sorted array.
     * Works for any array, ignores the rotated property.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static int findMaxLinear(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

    /**
     * Binary search approach to find the maximum in a rotated sorted array
     * with distinct elements.
     *
     * Idea:
     * - First find the index of the minimum element using binary search.
     * - In a rotated sorted array, the maximum element lies just before the minimum
     *   in circular fashion.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    public static int findMaxBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int n = nums.length;

        // If array is already sorted (not rotated)
        if (nums[0] <= nums[n - 1]) {
            return nums[n - 1];
        }

        // Find index of the minimum element
        int minIndex = findMinIndex(nums);

        // Max is just before the min, in circular manner
        int maxIndex = (minIndex - 1 + n) % n;
        return nums[maxIndex];
    }

    /**
     * Helper method to find index of minimum element in rotated sorted array.
     * Classic binary search for rotation pivot.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
    private static int findMinIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left; // index of smallest element
    }

    public static void main(String[] args) {
        int[] nums1 = {7, 9, 12, 15, 2, 3, 5};
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums3 = {10, 20, 30, 40};   // not rotated
        int[] nums4 = {5};                // single element

        System.out.println(findMaxLinear(nums1));        // 15
        System.out.println(findMaxBinarySearch(nums1));  // 15

        System.out.println(findMaxLinear(nums2));        // 7
        System.out.println(findMaxBinarySearch(nums2));  // 7

        System.out.println(findMaxLinear(nums3));        // 40
        System.out.println(findMaxBinarySearch(nums3));  // 40

        System.out.println(findMaxLinear(nums4));        // 5
        System.out.println(findMaxBinarySearch(nums4));  // 5
    }
}
