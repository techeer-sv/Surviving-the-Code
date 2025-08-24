// package problemtwo; - CI 실행을 위해 제거
import java.util.Scanner;
import java.util.Arrays;

public final class Main {
    private Main() {
        // 유틸리티 클래스 생성자 숨김
    }
    
    public static void main(final String[] args) {
        // 테스트 실행
        runTests();
        
        // 실제 문제 실행
        Scanner scanner = new Scanner(System.in);
        int[] infor = new int[3];
        for (int i = 0; i < 3; i++) {
            infor[i] = scanner.nextInt();
        }
        int[] def = new int[infor[0]];
        for (int i = 0; i < def.length; i++) {
            def[i] = 1;
        }
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int[] broken = new int[infor[1]];
        for (int i = 0; i < broken.length; i++) {
            broken[i] = scanner.nextInt();
            def[broken[i] - 1] -= 1;
        }
        //System.out.println("입력된 부서진: " + Arrays.toString(broken));
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int[] room = new int[infor[2]];
        for (int i = 0; i < room.length; i++) {
            room[i] = scanner.nextInt();
            def[room[i] - 1] += 1;
        }
        //System.out.println("입력된 여유: " + Arrays.toString(room));
        //System.out.println("입력된 기본값: " + Arrays.toString(def));

        int count = 0;
        for (int i = 0; i < def.length; i++) {
            if (def[i] == 0) {
                if (i > 0 && def[i - 1] == 2) {
                    def[i - 1]--;
                    def[i]++;
                } else if (i < def.length - 1 && def[i + 1] == 2) {
                    def[i + 1]--;
                    def[i]++;
                } else {
                    count++;
                }
            }
        }
        //System.out.println("결과 기본값: " + Arrays.toString(def));
        System.out.println(count);
        scanner.close();
    }
    
    // 문제 해결 로직을 별도 메서드로 분리
    public static int solve(final int totalKayaks, final int brokenCount, final int reserveCount, 
                           final int[] broken, final int[] reserve) {
        int[] kayaks = new int[totalKayaks];
        Arrays.fill(kayaks, 1);
        
        // 부서진 카약 처리
        for (int i = 0; i < brokenCount; i++) {
            kayaks[broken[i] - 1] -= 1;
        }
        
        // 여유 카약 처리
        for (int i = 0; i < reserveCount; i++) {
            kayaks[reserve[i] - 1] += 1;
        }
        
        int count = 0;
        for (int i = 0; i < kayaks.length; i++) {
            if (kayaks[i] == 0) {
                // 왼쪽에서 빌릴 수 있는지 확인
                if (i > 0 && kayaks[i - 1] == 2) {
                    kayaks[i - 1]--;
                    kayaks[i]++;
                } else if (i < kayaks.length - 1 && kayaks[i + 1] == 2) {
                    kayaks[i + 1]--;
                    kayaks[i]++;
                } else {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // 테스트 메서드들
    public static void runTests() {
        System.out.println("=== 2891번 문제 테스트 시작 ===");
        
        testExample1();
        testExample2();
        testAllBroken();
        testNoBroken();
        testAdjacentReserve();
        testNonAdjacentReserve();
        
        System.out.println("=== 2891번 문제 테스트 완료 ===\n");
    }
    
    private static void testExample1() {
        int totalKayaks = 5;
        int brokenCount = 2;
        int reserveCount = 1;
        int[] broken = {2, 4};
        int[] reserve = {1};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("예제1 테스트: " + (result == 1 ? "통과" : "실패 (예상: 1, 실제: " + result + ")"));
    }
    
    private static void testExample2() {
        int totalKayaks = 5;
        int brokenCount = 2;
        int reserveCount = 2;
        int[] broken = {2, 4};
        int[] reserve = {1, 5};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("예제2 테스트: " + (result == 0 ? "통과" : "실패 (예상: 0, 실제: " + result + ")"));
    }
    
    private static void testAllBroken() {
        int totalKayaks = 3;
        int brokenCount = 3;
        int reserveCount = 0;
        int[] broken = {1, 2, 3};
        int[] reserve = {};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("모든 카약 부서짐 테스트: " + (result == 3 ? "통과" : "실패 (예상: 3, 실제: " + result + ")"));
    }
    
    private static void testNoBroken() {
        int totalKayaks = 3;
        int brokenCount = 0;
        int reserveCount = 0;
        int[] broken = {};
        int[] reserve = {};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("부서진 카약 없음 테스트: " + (result == 0 ? "통과" : "실패 (예상: 0, 실제: " + result + ")"));
    }
    
    private static void testAdjacentReserve() {
        int totalKayaks = 4;
        int brokenCount = 1;
        int reserveCount = 1;
        int[] broken = {2};
        int[] reserve = {1};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("인접 여유 카약 테스트: " + (result == 0 ? "통과" : "실패 (예상: 0, 실제: " + result + ")"));
    }
    
    private static void testNonAdjacentReserve() {
        int totalKayaks = 4;
        int brokenCount = 1;
        int reserveCount = 1;
        int[] broken = {2};
        int[] reserve = {4};
        int result = solve(totalKayaks, brokenCount, reserveCount, broken, reserve);
        System.out.println("비인접 여유 카약 테스트: " + (result == 1 ? "통과" : "실패 (예상: 1, 실제: " + result + ")"));
    }
}
