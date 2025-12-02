/**
 * 백준 2470번 - 두 용액 이름 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 양수의 산성용액과 음수의 알칼리성 용액으로 이뤄진 수열이 주어짐
 * - 두 수의 합의 절댓값이 0에 최대한 가까운 두 요소를 찾아내자
 *
 * [해결 방법]
 * - 입력받은 후 오름차순 정렬
 * - 투포인터를 활용해 양쪽 끝에서 부터 이동해가며 최솟값 업데이트
 *
 * [시간 복잡도]
 * - 정렬: O(N log N)
 * - 투 포인터 탐색: O(N)
 * - 전체: O(N log N)
 */

import java.util.Arrays;

public class Main {

    static void solve(int n, int[] arr){
        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        int ans1 = 0, ans2 = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                ans1 = arr[left];
                ans2 = arr[right];
            }

            if (sum > 0) right--;  // 합이 양수 → 오른쪽 포인터 줄이기
            else left++;           // 합이 음수 → 왼쪽 포인터 올리기
        }

        System.out.println(ans1 + " " + ans2);
    }

    public static void main(String[] args) {
        solve(5, new int[]{-2, 4, -99, -1, 98});
    }
}