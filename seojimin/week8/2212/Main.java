/**
 * 백준 2212번 - 센서 [정렬 알고리즘]
 * <p>
 * [문제 요약]
 * - 일직선 상에 센서들이 배치
 * - 집중국을 설치해 센서들로부터 정보 수집
 * - 모든 센서는 최소 하나의 집중국과 통신가능해야 함
 * - 집중국의 개수(K)를 정해줄 때, 집중국의 수신 가능 영역 길이의 최소값 구하기
 * <p>
 * [해결 방법]
 * - 1차적으로 센서들의 위치를 받고, 중복 제거 및 오름차순 정렬
 * - 정렬된 센서들의 위치를 기반으로, 각 센서별 거리 배열을 생성
 * - 거리 배열을 내림차순으로 정렬
 * - 정렬된 거리 배열에서 K-1개 만큼의 거리를 제거
 * - 나머지 거리를 종합한다.
 * - 거리를 계산하여 활용하는 것이 포인트1
 * - 내림차순 정렬하여, k-1개 만큼 제외하는 것이 포인트2
 * - k-1개인 이유는 시작지점에 1개 집중국 박고 나머지 집중국을 활용해 구간을 생성하며 거리가 먼 순부터 없앤다
 * <p>
 * [시간 복잡도]
 * - O(N logN)
 */

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class Main {

    static int solve(int n, int k, int[] input) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(input[i]);
        }

        if (set.size() <= k) {
            return 0;
        }

        int[] sensor = new int[set.size()];
        int iter = 0;
        for (int i : set) {
            sensor[iter++] = i;
        }
        Arrays.sort(sensor);

        int[] distance = new int[sensor.length - 1];
        for (int i = 1; i < sensor.length; i++) {
            distance[i - 1] = sensor[i] - sensor[i - 1];
        }

        Arrays.sort(distance);
        for (int i = 0; i < distance.length / 2; i++) {
            int temp = distance[i];
            distance[i] = distance[distance.length - 1 - i];
            distance[distance.length - 1 - i] = temp;
        }

        int sum = 0;
        for (int i = k - 1; i < distance.length; i++) {
            sum += distance[i];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 6, 9, 3, 6, 7};
        System.out.println(solve(6, 2, input));
    }
}