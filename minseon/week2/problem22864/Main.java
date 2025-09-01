

public class Main {
    public static void main (String[] args){
        runTests();
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 22864번 문제 테스트 시작 ===\n");
        
        // 테스트 케이스
        final int[][] testCases = {
            {5, 3, 2, 10}, // 예상 결과: 24
            {3, 2, 1, 5}, // 예상 결과: 48
            {10, 5, 5, 20}, // 예상 결과: 10
            {1, 1, 1, 1}, // 예상 결과: 24
            {0, 1, 1, 0}, // 예상 결과: 0
            {100, 50, 25, 200} // 예상 결과: 0
        };
        
        final int[] expectedResults = {24, 48, 10, 24, 0, 0};
        
        for (int i = 0; i < testCases.length; i++) {
            int a = testCases[i][0];
            int b = testCases[i][1];
            int c = testCases[i][2];
            int m = testCases[i][3];
            
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("case: a=" + a + ", b=" + b + ", c=" + c + ", m=" + m);
            
            int work = 0;
            int tired = 0;
            for (int hour = 1; hour <= 24; hour++){
                if(tired + a <= m){
                    work += b;
                    tired += a;
                }
                else {
                    tired = Math.max(0, tired - c);
                }
            }
            
            int expectedResult = expectedResults[i];
            System.out.println("result: " + work + ", expected: " + expectedResult);
            if (work == expectedResult) {
                System.out.println("expect = " + expectedResult + " 맞췄습니다.");
            } else {
                System.out.println("expect = " + expectedResult + " / result = " + work + " 서로 다릅니다.");
            }
            System.out.println("---");
            System.out.flush();
            System.out.println();
        }
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
}
