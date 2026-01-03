
public class KokoEatingBananas {

    /**
     * Problem: Find the minimum eating speed k such that Koko can eat all
     * bananas within h hours.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Try every possible k from 1 to max pile size. - For each k,
     * calculate total hours required. - Return the first k where total hours <=
     * h.
     *
     * Time Complexity: O(maxPile * n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int minEatingSpeedBrute(int[] piles, int h) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        for (int k = 1; k <= maxPile; k++) {
            long hours = 0;
            for (int pile : piles) {
                hours += (pile + k - 1) / k;
            }
            if (hours <= h) {
                return k;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea: - Minimum speed = 1 - Maximum speed = max pile size - If speed k
     * works, try smaller k - Else increase k
     *
     * Time Complexity: O(n * log(maxPile)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int minEatingSpeedOptimal(int[] piles, int h) {
        int low = 1;
        int high = 0;

        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long hours = 0;
            for (int pile : piles) {
                hours += (pile + mid - 1) / mid;
            }

            if (hours <= h) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;

        System.out.println(minEatingSpeedBrute(piles, h));
        System.out.println(minEatingSpeedOptimal(piles, h));
    }
}
