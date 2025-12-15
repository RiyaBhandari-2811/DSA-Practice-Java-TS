/**
 * BRUTE FORCE APPROACH
 *
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function bruteForce(prices: number[]): void {
  let maxProfit = 0;
  let buy = -1, sell = -1;

  for (let i = 0; i < prices.length; i++) {
    for (let j = i + 1; j < prices.length; j++) {
      const profit = prices[j] - prices[i];
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
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * Stable: Yes
 */
function betterApproach(prices: number[]): void {
  let minPrice = prices[0];
  let minDay = 0;
  let maxProfit = 0;
  let buy = 0, sell = 0;

  for (let i = 1; i < prices.length; i++) {
    const profit = prices[i] - minPrice;

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
 * Same as better approach (single pass)
 */
function optimal(prices: number[]): void {
  betterApproach(prices);
}

function printResult(prices: number[], profit: number, buy: number, sell: number): void {
  if (profit === 0) {
    console.log("No profit possible");
    return;
  }

  console.log(
    `Buy on day ${buy} (price=${prices[buy]}), ` +
    `Sell on day ${sell} (price=${prices[sell]}), ` +
    `Profit=${profit}`
  );
}

// Test
const prices = [7, 1, 5, 3, 6, 4];
bruteForce(prices);
betterApproach(prices);
optimal(prices);
