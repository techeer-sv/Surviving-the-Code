/**
 * 백준 24482번 - 알고리즘 수업 - 깊이 우선 탐색 4 [dfs 알고리즘 유형]
 *
 * [문제 요약]
 * - N개의 정점과 M개의 간선으로 구성된 무방향 그래프
 * - 정점 번호는 1~N이고 모든 간선의 가중치는 1
 * - 정점 R에서 시작하여 깊이 우선 탐색으로 만들어 지는 트리를 깊이 우선 탐색 트리라고 함
 * - 깊이 우선 탐색 트리에 있는 모든 노드의 깊이를 출력하자
 * - 시작 정점 R의 깊이는 0이고 방문되지 않는 노드의 깊이는 -1로 출력
 *
 * [해결 방법]
 * - 결국에는 이 각 노드별로 깊이가 몇인지를 구해야 함
 * - 그리고 노드별 깊이를 저장할 수 있는 depth[]를 활용
 * - 노드 그래프를 만들고, 내림차순 정렬(정점 번호를 내림차순으로 방문하기 때문)
 * - dfs를 활용해 내림차순으로 연결된 노드를 타고 가며 각각의 depth를 기록한다
 * - 만약 dfs를 활용한 순회로 방문하지 않은 노드는 연결되지 않았음으로 판단하여, -1로 유지
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
    static int[] depth;   // 각 노드 깊이
    static ArrayList<Integer>[] graph;

    static void solve(int n, int m, int r, int[][] edges) {

        // 그래프 초기화
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];
        depth = new int[n];
        Arrays.fill(depth, -1); // 미방문 노드 -1로 초기화

        // 간선 추가
        for (int[] e : edges) {
            int node1 = e[0] - 1;
            int node2 = e[1] - 1;
            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        // 내림차순 정렬
        for (int i = 0; i < n; i++) {
            Collections.sort(graph[i], Collections.reverseOrder());
        }

        dfs(r - 1, 0);
    }

    static void dfs(int node, int dept) {
        visited[node] = true; // 해당 노드 방문 여부 저장
        depth[node] = dept; // 해당 노드의 깊이를 저장

        for (int next : graph[node]) {
            if (!visited[next]) { // 방문하지 않은 노드가 있다면
                dfs(next, dept + 1); // 깊이를 추가하여 방문하자
            }
        }
    }

    public static void main(String[] args) {
        // 예제 테스트
        int n = 5;
        int m = 5;
        int r = 1;
        int[][] edges = {
                {1, 4},
                {1, 2},
                {2, 3},
                {2, 4},
                {3, 4}
        };

        solve(n, m, r, edges);

        for (int d : depth) {
            System.out.println(d);
        }
    }
}