package problem2012;
import java.util.Scanner;
import java.util.Arrays;
//import java.lang.Math;

public final class Main {
    private Main() {
        // 유틸리티 클래스 생성자 숨김
    }
    
    public static void main(final String[] args) {
        // 테스트 실행
        runTests();
        
        // 실제 문제 실행
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        Arrays.sort(arr);
        //System.out.println("입력된 소팅값: " + Arrays.toString(arr));
        int[] real = new int[n];
        for (int i = 0; i < n; i++) {
            real[i] += i + 1;
        }
        int[] sum = new int[n];
        for (int i = 0; i < n; i++) {
            sum[i] = Math.abs(real[i] - arr[i]);
        }
        long all = 0;
        for (int i = 0; i < n; i++) {
            all += sum[i];
        }
        System.out.println(all);
        scanner.close();
    }
    
    // 문제 해결 로직을 별도 메서드로 분리
    public static long solve(final int n, final int[] expectedRanks) {
        Arrays.sort(expectedRanks);
        long totalDissatisfaction = 0;
        
        for (int i = 0; i < n; i++) {
            int actualRank = i + 1;
            int expectedRank = expectedRanks[i];
            totalDissatisfaction += Math.abs(actualRank - expectedRank);
        }
        
        return totalDissatisfaction;
    }
    
    // 테스트 메서드들
    public static void runTests() {
        System.out.println("=== 2012번 문제 테스트 시작 ===");
        
        // 예제 테스트
        testExample1();
        testPerfectMatch();
        testReverseOrder();
        testSingleStudent();
        testLargeNumbers();
        testDuplicateRanks();
        
        System.out.println("=== 2012번 문제 테스트 완료 ===\n");
    }
    
    private static void testExample1() {
        int n = 5;
        int[] expectedRanks = {1, 5, 3, 1, 2};
        long result = solve(n, expectedRanks);
        System.out.println("예제 테스트: " + (result == 3 ? "통과" : "실패 (예상: 3, 실제: " + result + ")"));
    }
    
    private static void testPerfectMatch() {
        int n = 3;
        int[] expectedRanks = {1, 2, 3};
        long result = solve(n, expectedRanks);
        System.out.println("완벽 매칭 테스트: " + (result == 0 ? "통과" : "실패 (예상: 0, 실제: " + result + ")"));
    }
    
    private static void testReverseOrder() {
        int n = 3;
        int[] expectedRanks = {3, 2, 1};
        long result = solve(n, expectedRanks);
        System.out.println("역순 테스트: " + (result == 4 ? "통과" : "실패 (예상: 4, 실제: " + result + ")"));
    }
    
    private static void testSingleStudent() {
        int n = 1;
        int[] expectedRanks = {1};
        long result = solve(n, expectedRanks);
        System.out.println("단일 학생 테스트: " + (result == 0 ? "통과" : "실패 (예상: 0, 실제: " + result + ")"));
    }
    
    private static void testLargeNumbers() {
        int n = 4;
        int[] expectedRanks = {100, 50, 75, 25};
        long result = solve(n, expectedRanks);
        System.out.println("큰 숫자 테스트: " + (result == 240 ? "통과" : "실패 (예상: 240, 실제: " + result + ")"));
    }
    
    private static void testDuplicateRanks() {
        int n = 4;
        int[] expectedRanks = {2, 2, 2, 2};
        long result = solve(n, expectedRanks);
        System.out.println("중복 등수 테스트: " + (result == 4 ? "통과" : "실패 (예상: 4, 실제: " + result + ")"));
    }
}
