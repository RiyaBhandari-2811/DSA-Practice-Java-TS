public class BestTimeToBuyAndSellStock {

    /**
     * BRUTE FORCE APPROACH
     *
     * Idea:
     * - Try all possible pairs (buy day < sell day)
     * - Calculate profit for each pair
     * - Track maximum profit and days
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * How it works:
     * - Nested loops compare every future price with current buy price
     */
    public static void bruteForce(int[] prices) {
        int maxProfit = 0;
        int buy = -1, sell = -1;

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxProfit) {
                    maxProfit = profit;
                    buy = i;
                    sell = j;
                }
            }
        }

        printResult(prices, maxProfit, buy, sell);
    }

    /**
     * BETTER APPROACH
     *
     * Idea:
     * - Track minimum price before current day
     * - Calculate profit for each day
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * How it works:
     * - Update min price
     * - Compare current profit with max profit
     */
    public static void betterApproach(int[] prices) {
        int minPrice = prices[0];
        int minDay = 0;
        int maxProfit = 0;
        int buy = 0, sell = 0;

        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - minPrice;

            if (profit > maxProfit) {
                maxProfit = profit;
                buy = minDay;
                sell = i;
            }

            if (prices[i] < minPrice) {
                minPrice = prices[i];
                minDay = i;
            }
        }

        printResult(prices, maxProfit, buy, sell);
    }

    /**
     * OPTIMAL APPROACH
     *
     * Idea:
     * - Same as better (single pass greedy)
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     *
     * How it works:
     * - Buy at lowest price so far
     * - Sell when profit is maximum
     */
    public static void optimal(int[] prices) {
        betterApproach(prices);
    }

    private static void printResult(int[] prices, int profit, int buy, int sell) {
        if (profit == 0) {
            System.out.println("No profit possible");
            return;
        }
        System.out.println(
            "Buy on day " + buy + " (price=" + prices[buy] + "), " +
            "Sell on day " + sell + " (price=" + prices[sell] + "), " +
            "Profit=" + profit
        );
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};

        bruteForce(prices);
        betterApproach(prices);
        optimal(prices);
    }
}
