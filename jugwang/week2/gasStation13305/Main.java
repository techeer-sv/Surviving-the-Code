import java.math.BigInteger;

class City{
    public int distance;
    public int price;
}


public class Main {
    public static BigInteger solution(int cityNum, int[] distances, int[] prices) {

        City[] cities = new City[cityNum];

        for (int i = 0; i < cityNum; i++) {
            cities[i] = new City();
            cities[i].distance = Integer.parseInt(distances[i]);
            cities[i].price = Integer.parseInt(prices[i]);
        }

        BigInteger totalPrice = BigInteger.ZERO;
        int min = cities[0].price;
        for (int i = 0 ; i < cityNum ; i++) {
            if (cities[i].price < min) {
                min = cities[i].price;
            }
            totalPrice = totalPrice.add(BigInteger.valueOf(cities[i].distance).multiply(BigInteger.valueOf(min)));
        }

        return totalPrice;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {4, {2, 3, 1}, {5, 2, 4, 1}},
            {4, {3, 3, 4}, {1, 1, 1, 1}},
        };
        int[] expectedResults = {18, 10};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            int result = solution(testCases[i][0], testCases[i][1], testCases[i][2]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
