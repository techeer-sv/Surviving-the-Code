/*
solved.ac 성공 (백준 18110번)

- 시간 복잡도: O(N log N)
  - N: 의견의 개수
  - 정렬이 필요하므로 O(N log N)
  - Stream의 sorted() 메서드가 지배적인 시간 복잡도

- 풀이
  1. solved.ac에서 문제 난이도를 결정하는 방식을 구현하는 문제
  2. 전체 의견 수의 15%에 해당하는 상위, 하위 의견을 제외 (반올림 적용)
  3. 나머지 의견들의 평균을 구하여 반올림한 값이 최종 난이도
  4. 의견이 0개인 경우 난이도는 0
  5. Stream API를 활용하여 정렬, 상하위 제외, 평균 계산을 연쇄적으로 처리
*/

import java.util.ArrayList;

class Solution {
    public int solution(ArrayList<Integer> arr) {

        int cut = (int)(Math.round(arr.size() * 0.15)); // 상하위 15% 컷

        double ans = arr.stream()
                .sorted()  // 정렬
                .skip(cut) // 상위 15% 제외
                .limit(arr.size() - 2 * cut) // 하위 15% 제외
                .mapToInt(Integer::intValue) // 정수형 스트림으로 변환
                .average() // 평균 계산
                .orElse(0.0); // 의견이 없으면 0.0 반환

        return (int)Math.round(ans); // 반올림하여 정수로 반환
    }
}

public class BOJ18110 {
    public static void main(String[] args) {
        // Test cases
        int[] inputCounts = {5, 10};
        int[][] opinions = {
            {1, 5, 5, 7, 8},
            {1, 13, 12, 15, 3, 16, 13, 12, 14, 15}
        };
        int[] expectedResults = {6, 13};

        for (int t = 0; t < inputCounts.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int count = inputCounts[t];
            ArrayList<Integer> nums = new ArrayList<>();
            
            System.out.println("Number of opinions: " + count);
            System.out.print("Opinions: ");
            for (int opinion : opinions[t]) {
                nums.add(opinion);
                System.out.print(opinion + " ");
            }
            System.out.println();
            
            // 15% 계산 설명
            double percentage = count * 0.15;
            int cut = (int)(Math.round(percentage));
            System.out.println("15% of " + count + " = " + percentage + " → cut " + cut + " from each end");
            
            Solution solved = new Solution();
            int result = solved.solution(nums);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}