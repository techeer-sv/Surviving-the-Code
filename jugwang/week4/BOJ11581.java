/*
구호물자 (백준 11581)

- 시간 복잡도: O(V + E)
  - V: 노드의 수, E: 간선의 수
  - DFS로 그래프를 한 번 탐색하므로 O(V + E)

- 풀이
  1. 방향 그래프에서 사이클 존재 여부를 판단하는 문제
  2. DFS를 사용하여 현재 경로에서 사이클을 탐지
  3. visited 배열: 전체 방문 여부 체크
  4. path 배열: 현재 DFS 경로에서의 방문 여부 체크
  5. path에서 이미 방문한 노드를 다시 만나면 사이클 존재
  6. 백트래킹 시 path에서 제거하여 다른 경로 탐색 가능

*/

import java.util.ArrayList;

public class BOJ11581 {
    static ArrayList<ArrayList<Integer>> graph;
    static int node;
    static boolean[] visited;
    static boolean[] path;

    public static String solution(int n, int[][] edges) {
        node = n;
        visited = new boolean[node + 1];
        path = new boolean[node + 1];
        graph = new ArrayList<>();
        
        // 그래프 초기화
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프에 노드 추가
        for (int i = 1; i < node; i++) {
            for (int j = 0; j < edges[i-1].length; j++) {
                graph.get(i).add(edges[i-1][j]);
            }
        }

        return dfs(1) ? "CYCLE" : "NO CYCLE";
    }

    public static boolean dfs(int currentNode) {
        // 방문 처리 및 경로 기록
        visited[currentNode] = true;
        path[currentNode] = true;

        // 인접 노드 탐색
        for (int neighbor : graph.get(currentNode)) {
            // 현재 경로에 이미 있는 노드를 다시 만나면 사이클 존재
            if (path[neighbor]) {
                return true;
            }

            // 방문하지 않은 노드에 대해 재귀 호출
            if (!visited[neighbor]) {
                if (dfs(neighbor)) {
                    return true;
                }
            }
        }

        path[currentNode] = false;
        return false;
    }

    public static void main(String[] args) {
        // Test cases
        int[] nodeValues = {3, 3};
        int[][][] edgesList = {
            {
                {2, 3}, // 노드 1의 간선들
                {3}     // 노드 2의 간선들
            },
            {
                {2, 3}, // 노드 1의 간선들
                {1, 3}  // 노드 2의 간선들
            }
        };
        String[] expectedResults = {"NO CYCLE", "CYCLE"};

        for (int t = 0; t < nodeValues.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int n = nodeValues[t];
            
            System.out.println("Input:");
            System.out.println(n);
            for (int i = 0; i < n - 1; i++) {
                System.out.print(edgesList[t][i].length);
                for (int j = 0; j < edgesList[t][i].length; j++) {
                    System.out.print(" " + edgesList[t][i][j]);
                }
                System.out.println();
            }

            String result = solution(n, edgesList[t]);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result.equals(expectedResults[t]) ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }


}