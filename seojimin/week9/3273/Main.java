/**
 * 백준 3273번 - 두 수의 합 [정렬 알고리즘 유형]
 * <p>
 * [문제 요약]
 * - 수로 다른 양의 정수로 이뤄진 수열 존재
 * - 수열의 서로 다른 요소의 합이 주어진 수 x와 같은 케이스의 개수를 구하는 문제
 * <p>
 * [해결 방법]
 * - 투 포인터 알고리즘 사용
 * - 수열을 받고, 오름차순으로 정렬해둔 뒤 적용
 * - 시작 포인터와, 끝부분 포인터를 활용
 * - 루프 조건은 시작 포인터 < 끝부분 포인터
 * - 만약 두 수의 합이 x보다 작다 -> 시작 포인터++ (숫자를 더 키워야 하기에)
 * - 만약 두 수의 합이 x보다 크다 -> 끝 포인터-- (숫자를 더 줄여야 하기에)
 * - 만약 두 수의 합이 x와 같다 -> 시작 포인터++, 끝 포인터-- (각자의 포인터와 맞는 쌍을 찾았기에 더이상 맞는 쌍 없음)
 * <p>
 * [시간 복잡도]
 * - 정렬: O(N log N)
 * - 투 포인터 탐색: O(N)
 * - 전체: O(N log N)
 */

import java.util.Arrays;

public class Main {

    static int solve(int n, int x, int[] nums) {

        Arrays.sort(nums);

        int count = 0;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = nums[start] + nums[end];
            if (sum==x) {
                count++;
                start++;
                end--;
            } else if (sum < x) start++;
            else end--;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 12, 7, 10, 9, 1, 2, 3, 11};
        System.out.println(solve(9, 13, nums));
    }
}