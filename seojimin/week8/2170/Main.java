/**
 * 백준 2170번 - 선 긋기 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 일직선 위에 여러 개의 선분이 주어짐 (x, y)
 *  * - 선분들은 서로 겹칠 수 있으며, 겹치는 구간은 한 번만 계산해야 함
 *  * - 모든 선분을 합쳤을 때의 총 길이를 구하는 문제
 *
 * [해결 방법]
 * - 모든 선분 (x, y)을 입력받아 리스트에 저장
 * -  각 선분을 시작점 x 기준으로 오름차순 정렬
 * - 정렬된 선분들을 순회하면서 다음 규칙에 따라 병합:
 *  * - 현재 선분의 시작점이 이전 선분의 끝보다 작거나 같으면 겹침 → 두 구간을 병합
 *  * - 그렇지 않으면 새로운 구간으로 추가
 * - 병합된 선분 리스트를 순회하면서 각 구간의 (끝 - 시작)을 모두 합산

 * [시간 복잡도]
 * - 정렬: O(N log N)
 * - 병합 과정: O(N)
 * - 종합: O(N log N)
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    static int solve(int n, int[][] lines){

        // 모든 선분 (x, y)을 입력받아 리스트에 저장
        List<int[]> inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = lines[i][0];
            int y = lines[i][1];

            inputs.add(new int[]{x,y});
        }

        // 각 선분을 시작점 x 기준으로 오름차순 정렬
        inputs.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 정렬된 선분들을 순회하면서 병합
        List<int[]> result = new ArrayList<>();
        for (int[] input : inputs) {
            if (result.isEmpty()) {
                result.add(input);
                continue;
            }

            int start = input[0];
            int end = input[1];

            int[] last = result.get(result.size() - 1);

            // 겹치는 경우 → 병합
            if (last[1] >= start){
                result.remove(result.size() - 1);
                result.add(new int[]{last[0], Math.max(end, last[1])});
            }
            // 겹치지 않으면 새 구간 추가
            else result.add(new int[]{start, end});
        }

        int sum = 0;
        for (int[] res : result) {
            sum += res[1] - res[0];
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] lines = {
                {1,3},
                {2,5},
                {3,5},
                {6,7}
        };
        System.out.println(solve(4, lines));
    }
}