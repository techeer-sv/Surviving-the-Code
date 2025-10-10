/**
 * 백준 10867번 - 중복 빼고 정렬하기 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 입력받는 숫자 정렬
 *
 * [해결 방법]
 * - 배열로 숫자 받고 Arrays.sort 정렬
 * - sb에 담으며 이전 배열에서 이미 담은 숫자와 동일하면 건너뜀
 *
 * [시간 복잡도]
 * - 정렬 -> O(n log n)
 */

import java.util.Arrays;

public class Main {

    static void solve(int n, String[] split){
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(split[i]);

        Arrays.sort(nums);

        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append(" ");
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) sb.append(nums[i]).append(" ");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        String[] input = new String[]{"1","4","2","3","1","4","2","3","1","2"};

        solve(10,input);
    }

}

