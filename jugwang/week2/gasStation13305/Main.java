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

        // long 보다 큰 BigInteger 사용
        BigInteger totalPrice = BigInteger.ZERO;
        // 첫 도시의 가격을 최소 가격으로 설정
        int min = cities[0].price;
        // 도시를 순회하며 최소 가격을 찾고 총 가격을 계산
        for (int i = 0 ; i < cityNum ; i++) {
            if (cities[i].price < min) {
                min = cities[i].price;
            }
            // 최소 가격과 거리를 곱하여 총 가격을 계산
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
