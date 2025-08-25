// package problemthree; - CI 실행을 위해 제거
import java.util.Scanner;
import java.util.Arrays;

public final class Main {
    private Main() {
        // 유틸리티 클래스 생성자 숨김
    }
    
    public static void main(final String[] args) {
        // 테스트 실행
        runTests();
        
        // CI 환경에서는 테스트만 실행하고 종료
        if (args.length > 0 && args[0].equals("--test-only")) {
            System.out.println("테스트 모드로 실행 완료");
            return;
        }
        
        // 실제 문제 실행 (로컬에서만)
        try {
            Scanner scanner = new Scanner(System.in);

        int teams = scanner.nextInt();
        int[] students = new int[2 * teams];

        for (int i = 0; i < 2 * teams; i++) {
            students[i] = scanner.nextInt();
        }
        Arrays.sort(students);
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(students));
        scanner.close();

        int[] part1 = new int[teams];
        int[] part2 = new int[teams];
        for (int i = 0; i < teams; i++) {
            part1[i] = students[i];
            part2[i] = students[teams + i];
        }
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(part1));
        //System.out.println("입력된 코딩 역량 배열: " + Arrays.toString(part2));
        int[] sum = new int[teams];
        for (int i = 0; i < teams; i++) {
            sum[i] = part1[i] + part2[part2.length - 1 - i];
        }
        Arrays.sort(sum);
        System.out.println(sum[0]);
        } catch (Exception e) {
            System.out.println("입력 처리 중 오류 발생: " + e.getMessage());
        }
    }
    
    // 문제 해결 로직을 별도 메서드로 분리
    public static int solve(final int teams, final int[] students) {
        Arrays.sort(students);
        
        int[] teamSums = new int[teams];
        
        // 최소값과 최대값을 매칭하여 팀 구성
        for (int i = 0; i < teams; i++) {
            teamSums[i] = students[i] + students[students.length - 1 - i];
        }
        
        // 팀 합계를 정렬하여 최소값 반환
        Arrays.sort(teamSums);
        return teamSums[0];
    }
    
    // 테스트 메서드들
    public static void runTests() {
        System.out.println("=== 20044번 문제 테스트 시작 ===");
        
        testExample1();
        testExample2();
        testSingleTeam();
        testAllSameValues();
        testLargeGap();
        testThreeTeams();
        testUnevenDistribution();
        
        System.out.println("=== 20044번 문제 테스트 완료 ===\n");
    }
    
    private static void testExample1() {
        int teams = 2;
        int[] students = {1, 7, 5, 8};
        int result = solve(teams, students);
        System.out.println("예제1 테스트: " + (result == 9 ? "통과" : "실패 (예상: 9, 실제: " + result + ")"));
    }
    
    private static void testExample2() {
        int teams = 3;
        int[] students = {1, 2, 3, 4, 5, 6};
        int result = solve(teams, students);
        System.out.println("예제2 테스트: " + (result == 7 ? "통과" : "실패 (예상: 7, 실제: " + result + ")"));
    }
    
    private static void testSingleTeam() {
        int teams = 1;
        int[] students = {5, 10};
        int result = solve(teams, students);
        System.out.println("단일 팀 테스트: " + (result == 15 ? "통과" : "실패 (예상: 15, 실제: " + result + ")"));
    }
    
    private static void testAllSameValues() {
        int teams = 2;
        int[] students = {5, 5, 5, 5};
        int result = solve(teams, students);
        System.out.println("모든 값 동일 테스트: " + (result == 10 ? "통과" : "실패 (예상: 10, 실제: " + result + ")"));
    }
    
    private static void testLargeGap() {
        int teams = 2;
        int[] students = {1, 1, 100, 100};
        int result = solve(teams, students);
        System.out.println("큰 차이 테스트: " + (result == 101 ? "통과" : "실패 (예상: 101, 실제: " + result + ")"));
    }
    
    private static void testThreeTeams() {
        int teams = 3;
        int[] students = {1, 2, 3, 4, 5, 6};
        int result = solve(teams, students);
        System.out.println("3개 팀 테스트: " + (result == 7 ? "통과" : "실패 (예상: 7, 실제: " + result + ")"));
    }
    
    private static void testUnevenDistribution() {
        int teams = 2;
        int[] students = {1, 2, 8, 9};
        int result = solve(teams, students);
        System.out.println("불균등 분포 테스트: " + (result == 10 ? "통과" : "실패 (예상: 10, 실제: " + result + ")"));
    }
}