/*
촌수계산 (백준 2644)

- 시간 복잡도: O(V + E)
  - V: 사람의 수(노드), E: 부모-자식 관계의 수(간선)
  - DFS로 그래프를 한 번 탐색하므로 O(V + E)

- 풀이
  1. 가족 관계를 무방향 그래프로 모델링 (부모-자식 양방향 연결)
  2. 한 사람에서 다른 사람까지의 최단 경로(촌수)를 구하는 문제
  3. DFS를 사용하여 시작점에서 목표점까지 탐색하며 깊이(촌수) 계산
  4. 목표점에 도달하면 그때의 깊이가 촌수
  5. 연결되지 않은 경우 -1 반환

*/

import java.util.ArrayList;

public class BOJ2644 {
    static ArrayList<ArrayList<Integer>> graph;
    static int node;
    static int edge;
    static int person1;
    static int person2;
    static int answer = 0;
    static boolean[] visited;

    public static int solution(int n, int p1, int p2, int[][] relations) {
        node = n;
        person1 = p1;
        person2 = p2;
        answer = 0;
        
        visited = new boolean[node + 1];
        graph = new ArrayList<>();
        // 그래프 초기화
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < relations.length; i++) {
            // 양방향 그래프에 노드 추가
            int parent = relations[i][0];
            int children = relations[i][1];
            graph.get(parent).add(children);
            graph.get(children).add(parent);
        }
        
        int count = 0;
        dfs(person1, count);
        
        if(answer == 0){
            answer = -1;
        }
        return answer;
    }

    public static void dfs(int currentNode, int depth) {
        // 목표에 도달하면 촌수를 정답에 추가
        if (currentNode == person2) {
            answer+=depth;
        }

        // 방문 처리
        visited[currentNode] = true;

        // 인접 노드 탐색
        for (int neighbor : graph.get(currentNode)) {
            // 방문하지 않은 노드면 재귀 호출
            if (!visited[neighbor]) {
                dfs(neighbor, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[] nodeValues = {9, 9};
        int[][] personPairs = {{7, 3}, {8, 6}};
        int[][][] relations = {
            {
                {1, 2}, {1, 3}, {2, 7}, {2, 8}, {2, 9}, {4, 5}, {4, 6}
            },
            {
                {1, 2}, {1, 3}, {2, 7}, {2, 8}, {2, 9}, {4, 5}, {4, 6}
            }
        };
        int[] expectedResults = {3, -1};

        for (int t = 0; t < nodeValues.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int n = nodeValues[t];
            int p1 = personPairs[t][0];
            int p2 = personPairs[t][1];
            
            System.out.println("Input:");
            System.out.println(n);
            System.out.println(p1 + " " + p2);
            System.out.println(relations[t].length);
            System.out.println("Relations:");
            for (int i = 0; i < relations[t].length; i++) {
                System.out.println(relations[t][i][0] + " " + relations[t][i][1]);
            }

            int result = solution(n, p1, p2, relations[t]);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }


}