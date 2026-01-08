
public class AllocateBooks {

    /**
     * Problem: Allocate books to m students such that the maximum pages
     * allocated is minimized.
     */
    /**
     * Helper Method: Checks if allocation is possible with given maxPages.
     */
    private static boolean canAllocate(int[] arr, int m, int maxPages) {
        int students = 1;
        int pages = 0;

        for (int book : arr) {
            if (pages + book > maxPages) {
                students++;
                pages = book;
            } else {
                pages += book;
            }
        }

        return students <= m;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea: - Answer lies between max(arr) and sum(arr) - Try every possible
     * maxPages and check feasibility
     *
     * Time Complexity: O((sum - max) * n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int allocateBooksBrute(int[] arr, int m) {
        if (m > arr.length) {
            return -1;
        }

        int max = 0;
        int sum = 0;

        for (int pages : arr) {
            max = Math.max(max, pages);
            sum += pages;
        }

        for (int maxPages = max; maxPages <= sum; maxPages++) {
            if (canAllocate(arr, m, maxPages)) {
                return maxPages;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea: - Minimum pages = max(arr) - Maximum pages = sum(arr) - If
     * allocation possible, try smaller maxPages
     *
     * Time Complexity: O(n * log(sum)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int allocateBooksOptimal(int[] arr, int m) {
        if (m > arr.length) {
            return -1;
        }

        int low = 0;
        int high = 0;

        for (int pages : arr) {
            low = Math.max(low, pages);
            high += pages;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canAllocate(arr, m, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {12, 34, 67, 90};
        int m = 2;

        System.out.println(allocateBooksBrute(arr, m));
        System.out.println(allocateBooksOptimal(arr, m));
    }
}
