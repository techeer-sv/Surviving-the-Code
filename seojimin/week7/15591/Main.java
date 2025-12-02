/**
 * 백준 15591번 - MooTube (Silver) [BFS 알고리즘]
 *
 * [문제 요약]
 * - 채널마다 서로서로의 유사도가 주어짐
 * - 두 채널이 연결되는 사이 유사도가 적은 채널을 타고 왔으면, 두 채널의 유사도는 최소 유사도에 해당됨
 * - 질의로 오는 출발 채널과 제한 유사도를 토대로, 해당 채널에서 제한 유사도 이상으로 연결된 채널의 개수를 반환
 *
 * [해결 방법]
 * - 기존 그래프가 배열을 담도록 변경 -> 연결 노드와 함께 유사도를 담음
 * - 각 질의 별로 새롭게 방문 배열을 생성하며, 카운트 역시 새로 생성
 * - 시작 노드에서 부터 bfs를 시작, bfs를 돌면서 다음 노드와의 유사도를 현재까지의 최소 유사도와 비교
 * - 더 작은값을 현재 유사도로 설정한 뒤 k값보다 큰지 체크(작으면 해당 노드를 큐에 담지 않음)
 * - 큐에 담으며 카운트++
 *
 * [시간 복잡도]
 * - 질의 수 q, 모든 노드 수 n -> O(q*n)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static ArrayList<int[]>[] graph;
    static boolean[] visited;

    static int solve(int n, int q, int[][] edges, int[][] queries) {
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int c = edges[i][2];

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        int[] results = new int[q];

        for (int i = 0; i < q; i++) {
            int k = queries[i][0];
            int start = queries[i][1];

            visited = new boolean[n + 1];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{start, Integer.MAX_VALUE});
            visited[start] = true;

            int count = 0;

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int now = cur[0];
                int pathMin = cur[1];

                for (int[] next : graph[now]) {
                    int nextNode = next[0];
                    int usado = next[1];
                    if (!visited[nextNode]) {
                        int newMin = Math.min(pathMin, usado);
                        if (newMin >= k) {
                            visited[nextNode] = true;
                            queue.add(new int[]{nextNode, newMin});
                            count++;
                        }
                    }
                }
            }
            results[i] = count;
        }

        for (int r : results) {
            System.out.println(r);
        }

        return 0;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2, 3},
                {2, 3, 2},
                {2, 4, 4}
        };

        int[][] queries = {
                {1, 2},
                {4, 1},
                {3, 1}
        };

        solve(4, 3, edges, queries);
    }
}