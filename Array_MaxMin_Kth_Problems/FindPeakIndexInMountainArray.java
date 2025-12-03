public class FindPeakIndexInMountainArray {

    /**
     * Brute force linear scan to find the peak index in a mountain array.
     *
     * Idea:
     * - Scan the entire array and track the index of the maximum element.
     * - Return the index of the first occurrence of the maximum value.
     * - For a valid mountain array, the maximum will always be somewhere between 1 and n - 2.
     *
     * Assumptions:
     * - A "mountain array" has length at least 3.
     * - It is strictly increasing up to the peak and strictly decreasing after the peak.
     * - This method does not verify the mountain property; it simply finds the maximum element index.
     *
     * Edge cases:
     * - Null array -> IllegalArgumentException.
     * - Length less than 3 -> IllegalArgumentException.
     * - All elements equal -> returns index 0 (first maximum).
     * - Multiple equal maximum values -> returns index of the first maximum.
     * - If the array is not a proper mountain (for example only increasing or only decreasing),
     *   this still returns the index of the maximum element but the input is considered invalid
     *   with respect to the mountain definition.
     *
     * Time Complexity: O(n)
     * - We traverse the array once and compare each element at most one time.
     *
     * Space Complexity: O(1)
     * - We use a constant amount of extra space for a few variables.
     *
     * Example test cases:
     * - [0, 2, 1, 0] -> peak index 1
     * - [0, 1, 2, 3, 2, 1] -> peak index 3
     * - [3, 5, 3, 2, 0] -> peak index 1
     * - [1, 3, 3, 2] -> peak index 1 (first occurrence of the maximum value 3)
     */
    public static int findPeakBrute(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Array must be non null and have length at least 3");
        }

        int peakIndex = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[peakIndex]) {
                peakIndex = i;
            }
        }

        return peakIndex;
    }

    /**
     * Better linear scan using the mountain property to find the peak index.
     *
     * Idea:
     * - In a valid mountain array, elements strictly increase up to the peak and then strictly decrease.
     * - Start from index 0 and move right while arr[i] < arr[i + 1].
     * - The first index i where arr[i] > arr[i + 1] is the peak index.
     * - This can stop early without scanning the entire array if the peak appears close to the beginning.
     *
     * Assumptions:
     * - Input is a valid mountain array:
     *   - length at least 3
     *   - there exists an index peak such that
     *       arr[0] < arr[1] < ... < arr[peak]
     *       arr[peak] > arr[peak + 1] > ... > arr[n - 1]
     *
     * Edge cases:
     * - Null array -> IllegalArgumentException.
     * - Length less than 3 -> IllegalArgumentException.
     * - If the array is strictly increasing or strictly decreasing only, then there is no valid peak
     *   according to the mountain definition. For such invalid inputs:
     *   - The method will return the last index where the pattern arr[i] < arr[i + 1] held,
     *     which is not a valid mountain peak by definition. The caller is expected to pass a valid mountain.
     * - Duplicate values near the top (for example [1, 3, 3, 2]) break the strict mountain definition.
     *   The method will stop when arr[i] > arr[i + 1] fails and will return the index of the last
     *   element before the sequence stops strictly increasing. This is a local maximum but not a strict peak.
     *
     * Time Complexity: O(k) worst case O(n)
     * - Where k is the index of the peak.
     * - In the worst case, the peak may be near the end, giving O(n).
     * - In many practical mountain arrays, this can terminate earlier than a full scan.
     *
     * Space Complexity: O(1)
     * - Uses constant extra space.
     *
     * Example test cases:
     * - [0, 2, 1, 0] -> peak index 1
     * - [0, 1, 2, 3, 2, 1] -> peak index 3
     * - [1, 2, 3, 4, 5, 3, 1] -> peak index 4
     * - [1, 3, 3, 2] (not strictly mountain) -> returns 1
     */
    public static int findPeakBetter(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Array must be non null and have length at least 3");
        }

        int i = 0;

        // Move up while the sequence is strictly increasing
        while (i + 1 < arr.length && arr[i] < arr[i + 1]) {
            i++;
        }

        // For a valid mountain array, i is now the peak index
        return i;
    }

    /**
     * Optimised binary search to find the peak index in a mountain array.
     *
     * Idea:
     * - Use the structure of a mountain array to perform binary search.
     * - For a given mid:
     *     - If arr[mid] < arr[mid + 1]:
     *         We are on the increasing slope. The peak lies to the right.
     *         Move left to mid + 1.
     *     - Else:
     *         We are on the decreasing slope or at the peak.
     *         The peak lies at mid or to the left. Move right to mid.
     * - Repeat until left and right converge to a single index, which will be the peak.
     *
     * Assumptions:
     * - Input is a valid mountain array:
     *   - length at least 3
     *   - strictly increasing then strictly decreasing.
     * - This method does not explicitly validate the full mountain property to keep O(log n) time.
     *
     * Edge cases:
     * - Null array -> IllegalArgumentException.
     * - Length less than 3 -> IllegalArgumentException.
     * - If the array is not a valid mountain (for example only increasing or only decreasing), the
     *   result may still be some local maximum, but the behavior is not guaranteed by the mountain definition.
     * - Duplicate values near the peak can cause the peak to be any index within a flat local maximum,
     *   since the classic mountain problem assumes strict inequalities.
     *
     * Time Complexity: O(log n)
     * - Each step halves the search space.
     *
     * Space Complexity: O(1)
     * - Uses a constant amount of extra space.
     *
     * Example test cases:
     * - [0, 2, 1, 0] -> peak index 1
     * - [0, 1, 2, 3, 2, 1] -> peak index 3
     * - [3, 5, 3, 2, 0] -> peak index 1
     * - [1, 2, 3, 4, 5, 3, 1] -> peak index 4
     */
    public static int findPeakOptimised(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Array must be non null and have length at least 3");
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If we are on the increasing slope, move right
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                // We are on the decreasing slope or at the peak, move left
                right = mid;
            }
        }

        // left == right at the peak index
        return left;
    }

    public static void main(String[] args) {
        int[] mountain1 = {0, 2, 1, 0};                // peak index 1
        int[] mountain2 = {0, 1, 2, 3, 2, 1};          // peak index 3
        int[] mountain3 = {3, 5, 3, 2, 0};             // peak index 1
        int[] mountain4 = {1, 2, 3, 4, 5, 3, 1};       // peak index 4
        int[] mountain5 = {1, 3, 3, 2};                // not a strict mountain, used to see behavior with duplicates
        int[] mountain6 = {1, 2, 1};                   // smallest valid mountain, peak index 1

        int[][] tests = {mountain1, mountain2, mountain3, mountain4, mountain5, mountain6};
        String[] names = {"mountain1", "mountain2", "mountain3", "mountain4", "mountain5", "mountain6"};

        for (int t = 0; t < tests.length; t++) {
            int[] arr = tests[t];
            System.out.println("Testing " + names[t]);
            System.out.println("Brute peak index:     " + findPeakBrute(arr));
            System.out.println("Better peak index:    " + findPeakBetter(arr));
            System.out.println("Optimised peak index: " + findPeakOptimised(arr));
            System.out.println();
        }
    }
}
