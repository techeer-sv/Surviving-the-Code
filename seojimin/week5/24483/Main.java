/**
 * 백준 24483번 - 알고리즘 수업 - 깊이 우선 탐색 5 [dfs 알고리즘]
 *
 * [문제 요약]
 * - N개의 정점과 M개의 간선으로 구성된 무방향 그래프
 * - 정점 번호는 1번부터 N번, 모든 간선의 가중치는 1
 * - 인접 정점을 오름차순으로 방문하는 깊이 우선 탐색 코드
 * - i번 노드의 방문 순서를 ti라고 할 때, (해당 노드의 깊이 * 방문 순서)의 합을 구하자
 *
 * [해결 방법]
 * - 결국 해당 노드를 몇 번째로 방문했는지와, 깊이를 알아내야 함
 * - 이전 문제와 비슷하게 풀이하되, 방문을 몇번째로 했는지 그리고 di * ti의 곱의 합산을 저장할 전역변수를 하나 두자
 * - depth는 dfs를 깊게 재귀될수록 더해진다면, count는 무조건 호출될 때 마다 1씩 추가된다
 *
 * [시간 복잡도]
 * - 정렬 -> O(m log m)
 * - dfs 수행 -> 모든 노드와 간선 최대 1번씩 방문 -> O(n + m)
 * - 전체 시간 복잡도 -> O(m log m)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[] depth; // 깊이
    static long[] order; // 순서
    static long count;
    static int n;

    static long solve(int n, int m, int r, int[][] edges) {
        // 그래프 초기화
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int node1 = edges[i][0] - 1;
            int node2 = edges[i][1] - 1;
            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        // 인접 리스트 정렬
        for (int i = 0; i < n; i++) {
            Collections.sort(graph[i]);
        }

        order = new long[n];
        depth = new int[n];
        Arrays.fill(depth, -1);

        count = 1;
        dfs(r - 1, 0);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += order[i] * depth[i];
        }

        return sum;
    }

    static void dfs(int node, int dept) {
        visited[node] = true;
        order[node] = count++;
        depth[node] = dept;

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, dept + 1);
            }
        }
    }

    public static void main(String[] args) {
        // 테스트 케이스 1
        int n1 = 5, m1 = 5, r1 = 1;
        int[][] edges1 = {
                {1, 4}, {1, 2}, {2, 3}, {2, 4}, {3, 4}
        };
        System.out.println("Test1: " + solve(n1, m1, r1, edges1));

        // 테스트 케이스 2
        int n2 = 4, m2 = 3, r2 = 2;
        int[][] edges2 = {
                {2, 1}, {2, 3}, {3, 4}
        };
        System.out.println("Test2: " + solve(n2, m2, r2, edges2));

        // 테스트 케이스 3 (단일 노드)
        int n3 = 1, m3 = 0, r3 = 1;
        int[][] edges3 = {};
        System.out.println("Test3: " + solve(n3, m3, r3, edges3));
    }
}