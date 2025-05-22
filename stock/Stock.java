package stock;

public class Stock {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i ++) {
            if (min > prices[i]) min = prices[i];
            if (i > 0) {
                int profit = prices[i] - min;
                if (profit >= max) max = profit;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Stock s = new Stock();
        int result = s.maxProfit(new int[]{1,2});
        System.out.println(result);
    }
}
