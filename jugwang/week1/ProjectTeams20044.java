import java.util.Arrays;

public class ProjectTeams20044 {
    public static int solution(int[] arr) {
        // 정렬하기
        Arrays.sort(arr);

        // 더한 후 비교하기
        int k = 0;
        int m = arr.length - 1;
        // int의 최댓값으로 초기화
        int min = Integer.MAX_VALUE;
        // 더한 후 비교하기
        while (k < m) {
            int sum = arr[k] + arr[m];
            k++;
            m--;
            if (sum < min) {
                min = sum;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {1, 7, 5, 8},
            {1, 7, 3, 5, 9, 2}
        };
        int[] expectedResults = {9, 8};

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            int result = solution(testCases[i]);
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result == expectedResults[i] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
