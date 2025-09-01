

public class Main {
    public static void main (String[] args) {
        runTests();
    }
    
    // ===== 테스트 코드 =====
    public static void runTests() {
        System.out.println("\n=== 백준 16953번 문제 테스트 시작 ===\n");
        
        // 테스트 케이스
        final int[][] testCases = {
            {2, 162}, // 예상 결과: 8
            {4, 42}, // 예상 결과: 5
            {1, 1}, // 예상 결과: 1
            {2, 3}, // 예상 결과: -1 (변환 불가능)
            {10, 40}, // 예상 결과: 3
            {1, 1000000000} // 예상 결과: 30
        };
        
        final int[] expectedResults = {8, 5, 1, -1, 3, 30};
        
        for (int i = 0; i < testCases.length; i++) {
            int start = testCases[i][0];
            int last = testCases[i][1];
            
            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("case: " + start + " → " + last);
            
            int count = 1;
            int tempLast = last;
            while (tempLast > start){
                if(tempLast % 2 == 0){
                    tempLast = tempLast / 2;
                }
                else if (tempLast % 10 == 1){
                    tempLast = (tempLast-1)/10;
                }
                else {
                    break;
                }
                count++;
            }

            int result;
            if(tempLast == start){
                result = count;
            }
            else {
                result = -1;
            }
            
            int expectedResult = expectedResults[i];
            System.out.println("result: " + result + ", expected: " + expectedResult);
            if (result == expectedResult) {
                System.out.println("expect = " + expectedResult + " 맞췄습니다.");
            } else {
                System.out.println("expect = " + expectedResult + " / result = " + result + " 서로 다릅니다.");
            }
            System.out.println("---");
            System.out.flush();
            System.out.println();
        }
        
        System.out.println("\n=== 모든 테스트 완료 ===");
    }
}
