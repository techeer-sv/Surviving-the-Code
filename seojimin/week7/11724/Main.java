/**
 * 백준 11724번 - 연결 요소의 개수 [BFS 알고리즘]
 *
 * [문제 요약]
 * - 무방향 그래프가 주어진다
 * - 서로 연결된 무리의 개수를 구하라
 *
 * [해결 방법]
 * - 그래프에 간선에 따른 연결 정보를 담고
 * - 카운트를 올리며 연결 요소들을 방문처리한다
 *
 * [시간 복잡도]
 * - 최악 전체 노드 순회 -> O(n)
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    static int solve(int n, int m, int[][] edges){

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int a = edges[i][0];
            int b = edges[i][1];

            graph[a-1].add(b-1);
            graph[b-1].add(a-1);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                count++;
                visited[i] = true;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);

                while(!q.isEmpty()){
                    int temp = q.poll();
                    for (Integer integer : graph[temp]) {
                        if (!visited[integer]) {
                            q.add(integer);
                            visited[integer] = true;
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1,2},
                {2,5},
                {5,1},
                {3,4},
                {4,6}
        };

        System.out.println(solve(6,5,edges));
    }
}