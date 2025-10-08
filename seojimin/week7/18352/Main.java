/**
 * 백준 18352번 - 특정 거리의 도시 찾기 [ BFS 알고리즘 ]
 *
 * [문제 요약]
 * - 노드와 단방향 간선이 주어진다
 * - 주어진 노드에서 특정 거리에 있는 노드들을 출력하자
 *
 * [해결 방법]
 * - 그래프에 노드 및 단방향 간선을 담는다
 * - 출발 노드부터 bfs 시작
 * - 특정 거리에 있는 노드는 리스트에 담고, 정렬 후 출력
 *
 * [시간 복잡도]
 * - O(n+m)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static void solve(int n, int m, int k, int x, int[][] edges){

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            graph[a - 1].add(b - 1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x-1,0});

        visited[x-1] = true;

        List<Integer> result = new ArrayList<>();

        while(!q.isEmpty()){
            int[] pos = q.poll();
            int now = pos[0];
            int distance = pos[1];

            for (int i : graph[now]) {
                if (!visited[i]) {
                    q.add(new int[]{i, distance+1});
                    visited[i] = true;
                    if (distance+1 == k) result.add(i);
                }
            }
        }

        if (result.isEmpty()){
            System.out.println(-1);
            return;
        }

        Collections.sort(result);
        for (Integer i : result) {
            System.out.println(i+1);
        }
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1,2},
                {1,3},
                {2,3},
                {2,4}
        };

        solve(4,4,2,1,edges);
    }
}