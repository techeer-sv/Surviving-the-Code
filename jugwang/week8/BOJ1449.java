/*
수리공 항승 (백준 1449번)

- 시간 복잡도: O(N log N)
  - N: 물이 새는 곳의 개수
  - 정렬이 필요하므로 O(N log N)
  - 그 후 순차적으로 처리하므로 O(N)
  - 전체적으로 O(N log N)

- 풀이
  1. 물이 새는 곳을 효율적으로 수리하기 위해 테이프를 최소한으로 사용하는 문제
  2. 그리디 알고리즘: 물이 새는 위치를 정렬한 후, 왼쪽부터 차례로 테이프를 붙임
  3. 각 테이프는 길이 L만큼 커버할 수 있고, 물이 새는 위치 기준으로 ±0.5 범위를 덮어야 함
  4. 현재 테이프가 커버하지 못하는 위치가 나오면 새로운 테이프 사용
  5. 테이프의 시작점은 물이 새는 위치에서 0.5를 뺀 지점부터 시작하여 최대한 효율적으로 배치
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ1449 {
    public static void main(String[] args) {
        // Test cases
        int[] leakCounts = {4, 4, 3};
        double[] tapeLengths = {2, 3, 1};
        int[][] leakPositions = {
            {1, 2, 100, 101},
            {1, 2, 3, 4},
            {3, 2, 1}
        };
        int[] expectedResults = {2, 2, 3};

        for (int t = 0; t < leakCounts.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int num = leakCounts[t];
            double length = tapeLengths[t];
            
            System.out.println("Number of leaks: " + num + ", Tape length: " + length);
            System.out.print("Leak positions: ");
            
            List<double[]> leaks = Arrays.stream(leakPositions[t])
                    .mapToDouble(i -> (double)i)
                    .mapToObj(i -> new double[]{i})
                    .collect(Collectors.toList());
            
            for (int pos : leakPositions[t]) {
                System.out.print(pos + " ");
            }
            System.out.println();
            
            // 정렬된 순서 보여주기
            double[] sortedPositions = Arrays.stream(leakPositions[t])
                    .mapToDouble(i -> (double)i)
                    .sorted()
                    .toArray();
            System.out.print("Sorted positions: ");
            for (double pos : sortedPositions) {
                System.out.print((int)pos + " ");
            }
            System.out.println();
            
            int result = solution(leaks, length);
            
            System.out.println("Result: " + result + " tapes needed");
            System.out.println("Expected: " + expectedResults[t] + " tapes needed");
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static int solution(List<double[]> arr, double length) {
        // 물이 새는 위치 정렬
        arr.sort(Comparator.comparingDouble(d -> d[0]));

        // 초기 상태: 첫 번째 테이프 사용, 첫 번째 누수 위치에서 0.5를 뺀 지점부터 커버 시작
        double[] initialState = new double[] {
                1.0, // 테이프 개수
                arr.get(0)[0] - 0.5 +length // 첫 번째 누수 위치에서 0.5를 뺀 지점부터 커버 시작
        };

        double[] ans = arr.stream()
                .skip(1) // 첫 번째 누수 위치는 이미 처리했으므로 건너뜀
                .reduce(initialState, ( state,  leak) -> {
                    double count = state[0]; // 사용한 테이프 개수
                    double usingLength = state[1]; // 현재 테이프가 커버하는 최대 위치

                    // 테잎의 총 길이 만큼 커버 가능
                    // 테잎이 커버하는 범위에서 벗어나면 새 테잎 사용
                    if (leak[0] - 0.5 >= usingLength) {
                        count++; // 새 테잎 사용
                        usingLength = leak[0] - 0.5 + length;
                    }
                    // 현재 상태 반환
                    return new double[]{count, usingLength};
                });
        return (int)ans[0]; // 사용한 테이프 개수 반환
    }
}