import java.util.Arrays;

public class Main {
    public static int solution(int first, int need) {
        
        // 연산 횟수
        int answer = 1;
        while (need > first) {
            // 10으로 나누어 떨어지면 1을 제거
            if ((need%10) == 1){
                answer++;
                need /= 10;
            // 2로 나누어 떨어지면 2로 나누기
            } else if ((need%2) == 0) {
                answer++;
                need /= 2;
            // 위 두 조건을 만족하지 않으면 -1 반환 (불가능한 경우)
            } else {
                return -1;
            }
        }
        // B가 A와 같아지면 연산 횟수 반환
        if (first == need) {
            return answer;
        // 위 두 조건을 만족하지 않으면 -1 반환 (불가능한 경우)
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {2, 162},
            {4, 42},
            {100, 40021},
        };
        int[] expectedResults = {5, -1, 5};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            int result = solution(testCases[i][0], testCases[i][1]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }        
    }
}
