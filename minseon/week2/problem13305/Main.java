import java.util.Arrays;

public class Main {
    public static void main (String[] args){
        runTests();
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 13305번 문제 테스트 시작 ===\n");
        
        // 테스트 케이스
        final int[][][] testCases = {
            {{4, 2, 3, 1}, {5, 2, 4, 1}}, // 예상 결과: 18
            {{3, 1}, {3, 2, 1}}, // 예상 결과: 3
            {{5, 1, 2, 3, 4}, {5, 4, 3, 2, 1}}, // 예상 결과: 10
            {{4, 3, 2, 1}, {2, 3, 1, 4}}, // 예상 결과: 6
            {{6, 10, 20, 30, 40, 50}, {100, 90, 80, 70, 60, 50}} // 예상 결과: 7500
        };
        
        final long[] expectedResults = {18, 3, 10, 6, 7500};
        
        for (int i = 0; i < testCases.length; i++) {
            int[] dist = testCases[i][0];
            int[] cost = testCases[i][1];
            int city = dist.length + 1;
            
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("case: city=" + city + ", dist=" + Arrays.toString(dist) + ", cost=" + Arrays.toString(cost));
            
            long leastCost = cost[0];
            long sum = 0;
            for(int j = 0; j < dist.length; j++){
               if (leastCost > cost[j]){
                   leastCost = cost[j];
               }
               sum += leastCost * dist[j];
            }
            
            long expectedResult = expectedResults[i];
            System.out.println("result: " + sum + ", expected: " + expectedResult);
            if (sum == expectedResult) {
                System.out.println("expect = " + expectedResult + " 맞췄습니다.");
            } else {
                System.out.println("expect = " + expectedResult + " / result = " + sum + " 서로 다릅니다.");
            }
            System.out.println("---");
            System.out.flush();
            System.out.println();
        }
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
}
