import java.util.Arrays;

public class Main {
    public static int solution(int first, int need) {

        int answer = 1;
        while (need > first) {
            if ((need%10) == 1){
                answer++;
                need /= 10;
            } else if ((need%2) == 0) {
                answer++;
                need /= 2;
            } else {
                return -1;
            }
        }
        if (first == need) {
            return answer;
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
