/**
 * 백준 1449번 - 수리공 항승 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 파이프의 물새는 곳을 전부 막기 위해 필요한 테이프 수 구하기
 *
 * [해결 방법]
 * - 물 새는 위치를 받은 후 오름차순 정렬
 * - 각 구멍을 순회하며, 아직 막히지 않은 구멍이면, 새로운 테이프를 붙이고 그 길이(L)만큼 막음.
 * - 이미 테이프가 덮인 구멍은 스킵
 *
 * [시간 복잡도]
 * - 정렬 -> O(n logn)
 * - 테이프 1000이 최대 -> O(n)
 * - 최종 -> O(n logn)
 */

import java.util.Arrays;

public class Main {

    static int solve(int n, int l, int[] arr){

        int[] nums = arr.clone();
        Arrays.sort(nums);

        int count = 0;
        boolean[] tape = new boolean[1001];
        for (int num : nums) {
            if (!tape[num]) {
                count++;
                for (int i = num; i < Math.min(num + l, 1001); i++) {
                    tape[i] = true;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,100,101};
        System.out.println(solve(4,2,arr));
    }
}