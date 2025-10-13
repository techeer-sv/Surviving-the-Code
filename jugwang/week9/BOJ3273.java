/*
두 수의 합 (백준 3273번)

- 시간 복잡도: O(N²)
  - N: 수열의 길이
  - 이중 for문을 사용하여 모든 쌍을 확인하므로 O(N²)
  - 정렬과 중복 제거는 O(N log N)이지만 이중 루프가 지배적

- 풀이
  1. 크기가 N인 수열에서 서로 다른 두 수를 선택하여 합이 X가 되는 쌍의 개수를 구하는 문제
  2. 배열을 정렬하고 중복을 제거한 후 이중 루프로 모든 쌍을 확인
  3. 정렬된 배열의 특성을 이용하여 합이 목표값보다 크면 내부 루프를 중단하는 최적화
  4. Two Pointer 기법을 사용하면 O(N log N)으로 최적화 가능하지만, 현재는 브루트포스 방식 사용
*/

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ3273 {
    public static void main(String[] args) {
        // Test cases
        int[] arraySizes = {9};
        int[][] arrays = {
            {5, 12, 7, 10, 9, 1, 2, 3, 11}
        };
        int[] targets = {13};
        int[] expectedResults = {3};

        for (int t = 0; t < arraySizes.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int num = arraySizes[t];
            int target = targets[t];
            
            System.out.println("Array size: " + num);
            System.out.print("Original array: ");
            for (int val : arrays[t]) {
                System.out.print(val + " ");
            }
            System.out.println();
            
            List<Integer> arr = Arrays.stream(arrays[t])
                    .boxed() // int[] to Integer[]
                    .sorted() // 오름차순 정렬
                    .distinct() // 중복 제거
                    .collect(Collectors.toList()); // List<Integer>로 변환
            
            System.out.print("Sorted unique array: ");
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
            System.out.println("Target sum: " + target);
            
            int result = solution(num, arr, target);
            
            // 실제 쌍들을 찾아서 보여주기
            System.out.println("Valid pairs that sum to " + target + ":");
            for (int i = 0; i < arr.size(); i++) {
                for (int j = i + 1; j < arr.size(); j++) {
                    if (arr.get(i) + arr.get(j) == target) {
                        System.out.println("  " + arr.get(i) + " + " + arr.get(j) + " = " + target);
                    }
                    if (arr.get(i) + arr.get(j) > target) {
                        break;
                    }
                }
            }
            
            System.out.println("Result: " + result + " pairs found");
            System.out.println("Expected: " + expectedResults[t] + " pairs");
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static int solution(int num, List<Integer> arr, int target) {
        int count = 0; // 합이 target인 쌍의 개수
        
        for (int i = 0; i < num; i++) { // 첫 번째 수 선택
            for (int j = i + 1; j < num; j++) { // 두 번째 수 선택
                if (arr.get(i) + arr.get(j) == target) { // 합이 target인 경우
                    count++; // 카운트 증가
                }
                if (arr.get(i) + arr.get(j) > target) { // 합이 target보다 크면 내부 루프 종료
                    break; // 반복문 종료
                }
            }
        }
        return count; // 합이 target인 쌍의 개수 반환
    }
}