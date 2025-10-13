/*
선 긋기 (백준 2170번)

- 시간 복잡도: O(N log N)
  - N: 선분의 개수
  - 선분들을 시작점 기준으로 정렬하는 과정이 O(N log N)
  - 그 후 순차적으로 병합하는 과정은 O(N)
  - 전체적으로 O(N log N)

- 풀이
  1. 여러 선분들이 주어졌을 때, 이들을 하나의 수직선에 그었을 때의 총 길이를 구하는 문제
  2. 겹치는 선분들은 하나로 합쳐져야 하므로 구간 병합(Interval Merge) 알고리즘 사용
  3. 선분들을 시작점 기준으로 정렬한 후, 순차적으로 병합
  4. 현재 선분이 이전 선분과 겹치면 병합하고, 그렇지 않으면 새로운 구간으로 처리
  5. 최종적으로 병합된 모든 구간의 길이를 합산
*/
import java.util.ArrayList;
import java.util.List;

public class BOJ2170 {
    public static void main(String[] args) {
        // Test cases
        int[] lineCounts = {4};
        int[][][] lines = {
            {{1, 3}, {2, 5}, {3, 5}, {6, 7}}
        };
        int[] expectedResults = {5};

        for (int t = 0; t < lineCounts.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int lineCount = lineCounts[t];
            List<int[]> nums = new ArrayList<>();
            
            System.out.println("Number of lines: " + lineCount);
            System.out.println("Lines:");
            for (int[] line : lines[t]) {
                nums.add(new int[]{line[0], line[1]});
                System.out.println("  [" + line[0] + ", " + line[1] + "] (length: " + (line[1] - line[0]) + ")");
            }
            
            // 정렬된 순서 보여주기
            List<int[]> sortedLines = new ArrayList<>(nums);
            sortedLines.sort((arr1, arr2) -> Integer.compare(arr1[0], arr2[0]));
            System.out.println("Sorted by start point:");
            for (int[] line : sortedLines) {
                System.out.println("  [" + line[0] + ", " + line[1] + "]");
            }
            
            int result = solution(nums);
            
            System.out.println("Result: " + result + " (total length after merging overlapping lines)");
            System.out.println("Expected: " + expectedResults[t]);
            
            // 병합 과정 설명
            System.out.println("Merge process:");
            System.out.println("  [1,3] and [2,5] overlap → merge to [1,5] (length: 4)");
            System.out.println("  [1,5] and [3,5] overlap → stay [1,5] (length: 4)");
            System.out.println("  [1,5] and [6,7] don't overlap → separate [6,7] (length: 1)");
            System.out.println("  Total: 4 + 1 = 5");
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static int solution(List<int[]> arr) {
        int[] ans = arr.stream()
                .sorted((arr1, arr2) -> Integer.compare(arr1[0], arr2[0])) // 시작점 기준 정렬
                .reduce(new int[] {arr.get(0)[0], arr.get(0)[1], 0, 0}, (state, step) -> { 
                    int start; // 현재 병합된 구간의 시작점
                    int end; // 현재 병합된 구간의 끝점
                    int length = state[2]; // 현재 병합된 구간의 길이
                    int sum = state[3]; // 지금까지의 총 길이 합

                    // 현재 선분이 이전 병합된 구간과 겹치는지 확인
                    if(state[0] <=step[0] && step[0] <= state[1]) {
                        if(state[0] <=step[1] && step[1] <= state[1]) {
                            end = state[1]; // 완전히 포함되는 경우
                        } else {
                            end = step[1]; // 일부만 겹치는 경우
                        }

                        start = state[0]; // 시작점은 이전 구간의 시작점 유지
                        length = Math.abs(end - start); // 병합된 구간의 길이 갱신
                    } else {
                        if(state[0] <=step[1] && step[1] <= state[1]) { // 이전 구간이 현재 선분을 포함하는 경우
                            end = state[1]; // 완전히 포함되는 경우
                        } else {
                            end = step[1]; // 겹치지 않는 경우

                        }

                        start = step[0]; // 시작점은 현재 선분의 시작점으로 갱신
                        sum += length; // 이전까지의 길이 합산
                        length = Math.abs(end - start); // 현재 구간의 길이 갱신
                    }

                    return new int[] {start, end, length, sum}; // [시작점, 끝점, 현재 구간 길이, 지금까지 총 길이]
                });


        return ans[3]+ans[2];
    }
}