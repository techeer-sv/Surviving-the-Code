/*
중복 빼고 정렬하기 (백준 10867번)

- 시간 복잡도: O(N log N)
  - N: 입력 숫자의 개수
  - Stream의 sorted() 메서드는 Timsort를 사용하므로 O(N log N)
  - distinct() 메서드는 O(N)이지만 정렬이 지배적이므로 전체 시간 복잡도는 O(N log N)

- 풀이
  1. N개의 정수를 입력받아 중복을 제거하고 오름차순으로 정렬하는 문제
  2. Java 8의 Stream API를 활용하여 간결하게 구현
  3. sorted()로 정렬 후 distinct()로 중복 제거하거나, distinct() 후 sorted() 모두 가능
  4. forEach()로 결과를 공백으로 구분하여 출력
*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ10867 {
    public static void main(String[] args) {
        // Test cases
        int[] inputCounts = {10};
        int[][] inputNumbers = {
            {1, 4, 2, 3, 1, 4, 2, 3, 1, 2}
        };
        int[][] expectedResults = {
            {1, 2, 3, 4}
        };

        for (int t = 0; t < inputCounts.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int count = inputCounts[t];
            List<Integer> nums = new ArrayList<>();
            
            System.out.println("Input count: " + count);
            System.out.print("Input numbers: ");
            for (int num : inputNumbers[t]) {
                nums.add(num);
                System.out.print(num + " ");
            }
            System.out.println();
            
            List<Integer> result = solution(nums);
            
            System.out.print("Result: ");
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i));
                if (i < result.size() - 1) System.out.print(" ");
            }
            System.out.println();
            
            System.out.print("Expected: ");
            for (int i = 0; i < expectedResults[t].length; i++) {
                System.out.print(expectedResults[t][i]);
                if (i < expectedResults[t].length - 1) System.out.print(" ");
            }
            System.out.println();
            
            boolean isSuccess = result.size() == expectedResults[t].length;
            if (isSuccess) {
                for (int i = 0; i < result.size(); i++) {
                    if (!result.get(i).equals(expectedResults[t][i])) {
                        isSuccess = false;
                        break;
                    }
                }
            }
            
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static List<Integer> solution(List<Integer> arr) {
        return arr.stream()
                .sorted() // 정렬 후
                .distinct() // 중복 제거
                .collect(Collectors.toList()); // 리스트로 수집
    }
}