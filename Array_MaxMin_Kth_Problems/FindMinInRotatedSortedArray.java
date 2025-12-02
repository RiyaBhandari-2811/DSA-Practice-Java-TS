public class FindMinInRotatedSortedArray {

    /**
     * Linear scan approach to find the minimum in a rotated sorted array. Works
     * for any array, ignores the rotated property.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     */
    public static int findMinLinear(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int min = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        return min;
    }

    /**
     * Binary search approach to find the minimum in a rotated sorted array with
     * distinct elements.
     *
     * Idea: - Use the fact that at least one half of the array is sorted. -
     * Compare middle element with the rightmost element to decide which side
     * contains the minimum.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     */
    public static int findMinBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        int left = 0;
        int right = nums.length - 1;

        // If array is already sorted and not rotated
        if (nums[left] <= nums[right]) {
            return nums[left];
        }

        // Binary search on rotated sorted array
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than right element,
            // minimum is in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                // Minimum is in the left half including mid
                right = mid;
            }
        }

        // left == right at the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums3 = {11, 13, 15, 17};
        int[] nums4 = {2}; // single element

        System.out.println(findMinLinear(nums1));        // 1
        System.out.println(findMinBinarySearch(nums1));  // 1

        System.out.println(findMinLinear(nums2));        // 0
        System.out.println(findMinBinarySearch(nums2));  // 0

        System.out.println(findMinLinear(nums3));        // 11
        System.out.println(findMinBinarySearch(nums3));  // 11

        System.out.println(findMinLinear(nums4));        // 2
        System.out.println(findMinBinarySearch(nums4));  // 2
    }
}
