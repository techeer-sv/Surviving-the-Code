import java.util.Arrays;

public class Main {
    public static int solution(int fatigue, int work, int rest, int burnOut) {
        // 피로도가 번아웃을 넘어가면 0을 반환
        if (fatigue > burnOut) {
            return 0;
        }

        int worked = 0;
        int fatigued = 0;

        // 24시간 동안 작업을 진행
        for (int i = 0; i < 24; i++) {
            // 피로도가 번아웃을 넘어가지 않으면 작업을 진행
            if (fatigued + fatigue <= burnOut) {
                worked += work;
                fatigued += fatigue;
            } else {
                // 피로도가 번아웃을 넘어가면 휴식
                fatigued -= rest;
                // 휴식 후 피로도가 0보다 작아지면 0으로 초기화
                if (fatigued < 0) {
                    fatigued = 0;
                }
            }
        }
        return worked;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {5, 3, 2, 10},
            {10, 5, 1, 10},
            {11, 5, 1, 10}
        };
        int[] expectedResults = {24, 15, 0};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            int result = solution(testCases[i][0], testCases[i][1], testCases[i][2], testCases[i][3]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
