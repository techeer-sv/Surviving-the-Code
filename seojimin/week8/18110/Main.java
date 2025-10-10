/**
 * 백준 18110번 - solved.ac [정렬 알고리즘]
 *
 * [문제 요약]
 * - 사람들이 문제에 대해 평가한 난이도가 주어진다
 * - 절삭 평균을 적용한 평균값을 구하라
 *
 * [해결 방법]
 * - 입력받은 난이도 오름차순 정렬
 * - 그 중 15%의 반올림이 몇명인지 구함
 * - 상,하위 15%를 제외한 나머지를 활용한 평균을 구하자
 *
 * [시간 복잡도]
 * - 정렬 -> O(N logN)
 * - 순회 -> O(N)
 * - 최종 -> O(N logN)
 */

import java.util.Arrays;

public class Main {

    static int solve(int n, int[] arr){
        int[] input = arr.clone();

        Arrays.sort(input);

        int cut = (int)Math.round(n * 0.15);

        int sum = 0;
        for (int i = cut; i < n-cut; i++) {
            sum += input[i];
        }

        return (int)Math.round((double) sum / (n - cut * 2));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,5,5,7,8};
        System.out.println(solve(5, arr));
    }
}
