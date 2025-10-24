/**
 * 백준 1202번 - 보석 도둑 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 보석 N개와 가방 K개가 주어짐
 *  - 보석은 각각의 무게(W)와 가치(V)를 지니고 있다
 *  - 가방은 담을 수 있는 최대 용량(C)이 정해져있다
 * - 각 가방에 단 하나에 보석만을 담을 수 있을 때, 최댓값을 구하자
 *
 * [해결 방법]
 * - 보석과 가방을 모두 하나의 리스트에 담는다
 *  - 단 가방의 경우 V가 들어갈 자리에 -1을 넣어 가방임을 표시
 * - 정렬을 진행하며, 기본적으로 무게(용량)을 기준으로 오름차순 정렬을 진행
 *  - 무게가 동일한 경우 가치가 높은 순으로 내림차순 정렬
 * - 리스트를 순회하며, 우선순위 큐에 가치를 넣어주고
 * - 가방의 차례가 되면 지금까지의 우선순위 큐중 가장 큰 값을 가져와 결과에 더한다
 * - 해당 방식을 반복한다
 *
 * [시간 복잡도]
 * 정렬: O((N + K) log (N + K))
 * 우선순위 큐 삽입/삭제: O((N + K) log N)
 * 전체: O((N + K) log N)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static long solve(int n, int k, int[][] jewels, int[] bags){

        List<int[]> inputs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inputs.add(new int[]{jewels[i][0], jewels[i][1]});
        }

        for (int i = 0; i < k; i++) {
            int temp = bags[i];
            inputs.add(new int[]{temp, -1});
        }

        // 무게 기준 오름차순 정렬, 무게 같으면 가치 내림차순
        inputs.sort((o1, o2) -> {
            if (o1[0] == o2[0]) return o2[1] - o1[1];
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long result = 0;

        for (int[] input : inputs) {
            int value = input[1];

            if (value == -1) {
                // 가방인 경우 → 지금까지 PQ에 들어온(즉, 이 가방 무게 이하인) 보석 중 제일 비싼 거 선택
                if (!pq.isEmpty()) {
                    result += pq.poll();
                }
            } else {
                // 보석인 경우 → PQ에 가치 추가
                pq.offer(value);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] jewels = new int[][]{
                {1,65},
                {5,23},
                {2,99}
        };

        System.out.println(solve(3, 2, jewels, new int[]{10, 2}));
    }
}