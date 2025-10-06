/*
연결 요소의 개수 (백준 11724번)

- 시간 복잡도: O(V + E)
  - V: 정점의 개수, E: 간선의 개수
  - 모든 정점을 한 번씩 방문하고, 각 정점에서 인접한 정점들을 탐색
  - BFS를 통해 연결된 모든 정점을 방문하므로 O(V + E)
  - 전체적으로 모든 정점과 간선을 한 번씩만 처리

- 풀이
  1. 무방향 그래프에서 연결 요소(Connected Component)의 개수를 구하는 문제
  2. 연결 요소란 서로 연결된 정점들의 집합으로, 다른 연결 요소와는 경로가 없음
  3. BFS 또는 DFS를 사용하여 각 연결 요소를 탐색
  4. 방문하지 않은 정점에서 BFS를 시작할 때마다 하나의 연결 요소를 발견
  5. 모든 정점을 확인하여 총 연결 요소의 개수를 계산

- 의사코드
    ```
    solution(graph, nodeCount):
        count = 0
        
        for i = 1 to nodeCount:
            if not visited[i]:
                BFS(i, graph)  // 새로운 연결 요소 발견
                count++
        
        return count
    
    BFS(startNode, graph):
        queue = new Queue()
        queue.add(startNode)
        visited[startNode] = true
        
        while queue is not empty:
            currentNode = queue.poll()
            
            for each neighbor in graph[currentNode]:
                if not visited[neighbor]:
                    visited[neighbor] = true
                    queue.add(neighbor)
    ```

*/

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class Solution {
    boolean[] visited;

    public Solution(int node) {
        this.visited = new boolean[node+1];
    }

    public int solution(ArrayList<ArrayList<Integer>> graph, int node) {
        int count = 0;

        for(int i=1; i<=node; i++) {
            if(!visited[i]) {
                visited[i] = true;
                bfs(i, graph);
                count++;
            }
        }
        return count;
    }

    public void bfs(int startNode, ArrayList<ArrayList<Integer>> graph) {
        Queue<Integer> q = new ArrayDeque<>();

        q.add(startNode);
        visited[startNode] = true;

        while(!q.isEmpty()){
            int currentNode = q.poll();

            visited[currentNode] = true;

            for (int neighbor : graph.get(currentNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(neighbor);
                }
            }
        }
    }
}

public class BOJ11724 {
    public static void main(String[] args) {
        // Test cases
        int[] vertices = {6, 6};
        int[] edgeCounts = {5, 8};
        int[][][] edges = {
            {{1, 2}, {2, 5}, {5, 1}, {3, 4}, {4, 6}},
            {{1, 2}, {2, 5}, {5, 1}, {3, 4}, {4, 6}, {5, 4}, {2, 4}, {2, 3}}
        };
        int[] expectedResults = {2, 1};

        for (int t = 0; t < vertices.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int vertex = vertices[t];
            int edgeCount = edgeCounts[t];
            
            System.out.println("Vertices: " + vertex + ", Edges: " + edgeCount);
            System.out.println("Edge connections:");
            for (int[] edge : edges[t]) {
                System.out.println("  " + edge[0] + " - " + edge[1]);
            }

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= vertex; i++) {
                graph.add(new ArrayList<>());
            }
            
            // 간선 정보 설정 (무방향 그래프)
            for (int[] edge : edges[t]) {
                int node1 = edge[0];
                int node2 = edge[1];
                graph.get(node1).add(node2);
                graph.get(node2).add(node1);
            }
            
            Solution solved = new Solution(vertex);
            int result = solved.solution(graph, vertex);
            
            System.out.println("Result: " + result + " connected components");
            System.out.println("Expected: " + expectedResults[t] + " connected components");
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}