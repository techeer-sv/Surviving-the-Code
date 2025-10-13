/*
센서 (백준 2212번)

- 시간 복잡도: O(N log N)
  - N: 센서의 개수
  - 중복 제거 및 정렬이 필요하므로 O(N log N)
  - 거리 계산 및 정렬: O(N log N)
  - 전체적으로 O(N log N)

- 풀이
  1. N개의 센서와 K개의 집중국을 이용하여 모든 센서를 커버하는 문제
  2. 집중국의 수신 가능 영역의 길이 합을 최소화하는 그리디 알고리즘
  3. 센서들을 정렬한 후, 인접한 센서 간의 거리를 계산
  4. K개의 집중국으로 나누려면 (K-1)개의 가장 긴 거리를 제거
  5. 남은 거리들의 합이 최소 수신 가능 영역의 길이
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ2212 {
    public static void main(String[] args) {
        // Test cases
        int[] sensorCounts = {6, 10};
        int[] centerCounts = {2, 5};
        int[][] sensorPositions = {
            {1, 6, 9, 3, 6, 7},
            {20, 3, 14, 6, 7, 8, 18, 10, 12, 15}
        };
        int[] expectedResults = {5, 7};

        for (int t = 0; t < sensorCounts.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int sensorCount = sensorCounts[t];
            int centerCount = centerCounts[t];
            
            System.out.println("Sensors: " + sensorCount + ", Centers: " + centerCount);
            System.out.print("Sensor positions: ");
            for (int pos : sensorPositions[t]) {
                System.out.print(pos + " ");
            }
            System.out.println();
            
            List<Integer> centers = Arrays.stream(sensorPositions[t])
                    .boxed()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
            
            System.out.print("Unique sorted positions: ");
            for (int pos : centers) {
                System.out.print(pos + " ");
            }
            System.out.println();
            
            int result = solution(centers, centerCount);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static int solution(List<Integer> arr, int center) {
        // 집중국의 수가 센서의 수보다 많거나 같으면 모든 센서를 개별적으로 커버 가능
        if (center >= arr.size()) return 0;
        
        // 인접한 센서 간의 거리 계산
        ArrayList<Integer> newArr = new ArrayList<>();

        // 인접한 센서 간의 거리 계산
        for (int i=0; i<arr.size()-1; i++) {
            // 인접한 센서 간의 거리 추가
            newArr.add(arr.get(i+1)- arr.get(i));
        }

        return newArr.stream() 
                .sorted() // 거리 오름차순 정렬
                .limit(newArr.size() - (center-1)) // 가장 큰 (center-1)개의 거리 제외
                .mapToInt(Integer::intValue) // 정수형 스트림으로 변환
                .sum(); // 남은 거리들의 합 반환
    }
}