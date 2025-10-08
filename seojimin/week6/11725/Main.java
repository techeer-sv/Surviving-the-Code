/**
 * 백준 11725번 - 트리의 부모 찾기 [ BFS 알고리즘 ]
 *
 * [문제 요약]
 * - 루트 없는 트리가 주어진다. 이때, 트리의 루트를 1이라고 정했을 때, 각 노드의 부모를 구하는 프로그램을 작성
 *
 * [해결 방법]
 * - 우선 노드들 정보를 싹다 담아서 ArrayList에 graph형태로 구현
 * - 1번 노드부터 시작해서 연결된 노드에 bfs를 활용해 이동
 * - bfs이기에 넘어가면서 부모 노드를 기록하는 배열에 이전 노드를 기록
 *
 * [시간 복잡도]
 * - graph 초기화 -> O(n)
 * - bfs 모든 노드와 간선 방문 -> O(n)
 * - 마지막 parent 출력 -> O(n-1)
 * - 전체 시간 복잡도 -> O(n)
 */


import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int[] parent;

    static void solve(int n, int[][] edges){
        visited = new boolean[n];
        parent = new int[n];

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];

            graph[node1-1].add(node2-1);
            graph[node2-1].add(node1-1);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;

        while(!q.isEmpty()){
            int curs = q.poll();
            for (int next : graph[curs]) {
                if (!visited[next]){
                    q.add(next);
                    visited[next] = true;
                    parent[next] = curs;
                }
            }
        }

        for (int i = 1; i < parent.length; i++) {
            System.out.println(parent[i]+1);
        }
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {
                {1, 6},
                {6, 3},
                {3, 5},
                {4, 1},
                {2, 4},
                {4, 7}
        };

        solve(n, edges);
    }
}