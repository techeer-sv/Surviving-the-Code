import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        // CI/CD 환경에서는 테스트 실행, 실제 입력이 필요한 경우는 주석 처리
        runTests();
        
        // 실제 백준 제출용 코드 (CI/CD에서는 주석 처리)
        /*
        Scanner scanner = new Scanner(System.in);
        int city = scanner.nextInt();

        int [] dist = new int[city-1];
        for(int i=0; i<dist.length; i++){
            dist[i] = scanner.nextInt();
        }

        int [] cost = new int[city];
        for(int i=0; i<cost.length; i++){
            cost[i] = scanner.nextInt();
        }
        System.out.println("입력된 도시수: " + (city));
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + (sum));
        */
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 13305번 문제 테스트 시작 ===\n");
        
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
        testCase6();
        testCase7();
        testCase8();
        testCase9();
        testCase10();
        testEdgeCases();
        testLargeNumbers();
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
    
    private static void testCase1() {
        System.out.println("테스트 케이스 1: 4개 도시, 거리[2,3,1], 가격[5,2,4,1]");
        System.out.println("예상 결과: 총 경비 18");
        System.out.println("실행 결과:");
        
        int city = 4;
        int[] dist = {2, 3, 1};
        int[] cost = {5, 2, 4, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase2() {
        System.out.println("테스트 케이스 2: 3개 도시, 거리[1], 가격[3,2,1]");
        System.out.println("예상 결과: 총 경비 3");
        System.out.println("실행 결과:");
        
        int city = 3;
        int[] dist = {1};
        int[] cost = {3, 2, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase3() {
        System.out.println("테스트 케이스 3: 5개 도시, 거리[1,2,3,4], 가격[5,4,3,2,1]");
        System.out.println("예상 결과: 총 경비 10");
        System.out.println("실행 결과:");
        
        int city = 5;
        int[] dist = {1, 2, 3, 4};
        int[] cost = {5, 4, 3, 2, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase4() {
        System.out.println("테스트 케이스 4: 6개 도시, 거리[2,2,2,2,2], 가격[1,2,3,4,5,6]");
        System.out.println("예상 결과: 총 경비 10");
        System.out.println("실행 결과:");
        
        int city = 6;
        int[] dist = {2, 2, 2, 2, 2};
        int[] cost = {1, 2, 3, 4, 5, 6};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase5() {
        System.out.println("테스트 케이스 5: 4개 도시, 거리[3,2,1], 가격[2,3,1,4]");
        System.out.println("예상 결과: 총 경비 6");
        System.out.println("실행 결과:");
        
        int city = 4;
        int[] dist = {3, 2, 1};
        int[] cost = {2, 3, 1, 4};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase6() {
        System.out.println("테스트 케이스 6: 7개 도시, 거리[1,1,1,1,1,1], 가격[7,6,5,4,3,2,1]");
        System.out.println("예상 결과: 총 경비 21");
        System.out.println("실행 결과:");
        
        int city = 7;
        int[] dist = {1, 1, 1, 1, 1, 1};
        int[] cost = {7, 6, 5, 4, 3, 2, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase7() {
        System.out.println("테스트 케이스 7: 3개 도시, 거리[5], 가격[10,5,8]");
        System.out.println("예상 결과: 총 경비 50");
        System.out.println("실행 결과:");
        
        int city = 3;
        int[] dist = {5};
        int[] cost = {10, 5, 8};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase8() {
        System.out.println("테스트 케이스 8: 4개 도시, 거리[2,3,4], 가격[1,1,1,1]");
        System.out.println("예상 결과: 총 경비 9");
        System.out.println("실행 결과:");
        
        int city = 4;
        int[] dist = {2, 3, 4};
        int[] cost = {1, 1, 1, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase9() {
        System.out.println("테스트 케이스 9: 5개 도시, 거리[1,2,3,4], 가격[5,5,5,5,5]");
        System.out.println("예상 결과: 총 경비 50");
        System.out.println("실행 결과:");
        
        int city = 5;
        int[] dist = {1, 2, 3, 4};
        int[] cost = {5, 5, 5, 5, 5};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testCase10() {
        System.out.println("테스트 케이스 10: 6개 도시, 거리[10,20,30,40,50], 가격[100,90,80,70,60,50]");
        System.out.println("예상 결과: 총 경비 7500");
        System.out.println("실행 결과:");
        
        int city = 6;
        int[] dist = {10, 20, 30, 40, 50};
        int[] cost = {100, 90, 80, 70, 60, 50};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testEdgeCases() {
        System.out.println("테스트 케이스: 경계값 테스트");
        System.out.println("2개 도시, 거리[1], 가격[1,1]");
        System.out.println("예상 결과: 총 경비 1");
        System.out.println("실행 결과:");
        
        int city = 2;
        int[] dist = {1};
        int[] cost = {1, 1};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
    
    private static void testLargeNumbers() {
        System.out.println("테스트 케이스: 큰 수 테스트");
        System.out.println("3개 도시, 거리[1000000], 가격[1000000,999999,1000000]");
        System.out.println("실행 결과:");
        
        int city = 3;
        int[] dist = {1000000};
        int[] cost = {1000000, 999999, 1000000};
        
        System.out.println("입력된 도시수: " + city);
        System.out.println("입력된 거리배열: " + Arrays.toString(dist));
        System.out.println("입력된 도시당주유가격배열: " + Arrays.toString(cost));

        long leastCost = cost[0];
        long sum = 0;
        for(int i = 0; i< dist.length; i++){
           if (leastCost > cost[i]){
               leastCost = cost[i];
           }
           sum += leastCost * dist[i];
        }
        System.out.println(" 총 경비: " + sum);
        System.out.println("---");
    }
}
