/*
트리의 부모 찾기 (백준 11725번)

- 시간 복잡도: O(N)
  - N: 노드의 개수
  - 트리는 N개의 노드와 N-1개의 간선을 가짐
  - BFS로 모든 노드를 한 번씩 방문하므로 O(N)
  - 각 간선도 최대 한 번씩만 탐색하므로 전체 O(N)

- 풀이
  1. 트리에서 루트 노드(1번)를 기준으로 각 노드의 부모를 찾는 문제
  2. 무방향 그래프로 주어진 간선 정보를 인접 리스트로 구성
  3. BFS를 사용하여 루트(1번)부터 시작해 레벨 순으로 탐색
  4. 방문하지 않은 인접 노드를 만나면 현재 노드를 그 노드의 부모로 설정
  5. 결과적으로 2번 노드부터 N번 노드까지의 부모 정보를 얻음

- 슈도코드
    ```
    BFS(graph, nodeNum):
        visited[nodeNum+1] = false로 초기화
        parents[nodeNum+1] = 0으로 초기화
        queue = new Queue()
        
        visited[1] = true
        queue.add(1)
        
        while queue is not empty:
            currentNode = queue.poll()
            
            for each neighbor in graph[currentNode]:
                if not visited[neighbor]:
                    visited[neighbor] = true
                    parents[neighbor] = currentNode
                    queue.add(neighbor)
        
        return parents[2...N]
    ```

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] parents;

    public Solution(int nodeNum) {
        parents = new int[nodeNum+1];
    }

    public int[] solution(int[][] node, int nodeNum) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= nodeNum; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < node.length; i++) {
            graph.get(node[i][0]).add(node[i][1]);
            graph.get(node[i][1]).add(node[i][0]);
        }
        bfs(graph, nodeNum);

        // for (int i = 2; i <= nodeNum; i++) {
        //     System.out.println(parents[i]);
        // }
        return parents;
    }

    public void bfs(ArrayList<ArrayList<Integer>> graph, int nodeNum) {
        boolean[] visited = new boolean[nodeNum+1];

        Queue<Integer> queue = new LinkedList<>();

        visited[1] = true;
        queue.add(1);
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            for (int child : graph.get(currentNode)) {
                if (!visited[child]) {
                    visited[child] = true;
                    parents[child] = currentNode;
                    queue.add(child);
                }
            }
        }
    }
}

public class BOJ11725 {
    public static void main(String[] args) {
        // Test cases
        int[] nodeNums = {7, 12};
        int[][][] edges = {
            {
                {1, 6}, {6, 3}, {3, 5}, {4, 1}, {2, 4}, {4, 7}
            },
            {
                {1, 2}, {1, 3}, {2, 4}, {3, 5}, {3, 6}, {4, 7}, 
                {4, 8}, {5, 9}, {5, 10}, {6, 11}, {6, 12}
            }
        };
        int[][] expectedResults = {
            {4, 6, 1, 3, 1, 4},
            {1, 1, 2, 3, 3, 4, 4, 5, 5, 6, 6}
        };

        for (int t = 0; t < nodeNums.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int nodeNum = nodeNums[t];
            
            System.out.println("Input:");
            System.out.println(nodeNum);
            System.out.println("Edges:");
            for (int i = 0; i < edges[t].length; i++) {
                System.out.println(edges[t][i][0] + " " + edges[t][i][1]);
            }

            Solution solved = new Solution(nodeNum);
            int[] result = solved.solution(edges[t], nodeNum);
            
            System.out.println("Result:");
            boolean isSuccess = true;
            for (int i = 2; i <= nodeNum; i++) {
                System.out.println(result[i]);
                if (result[i] != expectedResults[t][i-2]) {
                    isSuccess = false;
                }
            }
            
            System.out.println("Expected:");
            for (int i = 0; i < expectedResults[t].length; i++) {
                System.out.println(expectedResults[t][i]);
            }
            
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}