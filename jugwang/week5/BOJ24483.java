/*
알고리즘 수업 - 깊이 우선 탐색 5 (백준 24483)

- 시간 복잡도: O(V + E)
  - V: 정점의 개수, E: 간선의 개수
  - DFS는 각 정점과 간선을 한 번씩만 방문
  - 정렬: O(V log V) (각 정점의 인접 리스트 정렬)
  - 전체: O(V log V + E)

- 풀이
  1. 무방향 그래프에서 DFS를 수행하며 각 노드의 깊이(depth)와 방문 순서(order)를 기록
  2. 인접 정점은 오름차순으로 방문 (작은 번호부터)
  3. Stack을 사용한 반복적 DFS로 구현 (재귀 호출로 인한 StackOverflow 방지)
  4. nextNeighborIndex 배열로 각 노드에서 다음 방문할 인접 노드 추적
  5. 연결되지 않은 정점은 -1로 출력
  6. 각 노드 i에 대해 depth[i] × order[i]를 계산하여 모든 합을 구함
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class BOJ24483 {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] depth;
    static int[] orders;
    // 각 노드마다 다음으로 방문할 이웃의 인덱스를 저장하는 배열
    static int[] nextNeighborIndex;
    static long sum = 0;
    static int order = 1;

    public static long solution(int node, int edge, int start, int[][] edges) {
        graph = new ArrayList<>();
        depth = new int[node + 1];
        orders = new int[node + 1];
        nextNeighborIndex = new int[node + 1];
        sum = 0;
        order = 1;

        // 그래프 초기화
        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
            depth[i] = -1;
        }

        // 간선 정보로 그래프 구성
        for (int i = 0; i < edge; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            // 무방향 그래프이므로 양쪽에 추가
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        // 인접 노드들을 오름차순으로 정렬
        for (int i = 1; i <= node; i++) {
            graph.get(i).sort(Comparator.naturalOrder());
        }

        dfs(start);

        // 결과 계산
        for (int i = 1; i <= node; i++) {
            sum += (long)depth[i]*(long)orders[i];
        }

        return sum;
    }

    public static void dfs(int startNode) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        // 시작 노드의 깊이는 0
        depth[startNode] = 0;
        // 시작 노드의 방문 순서 기록
        orders[startNode] = order++;

        while (!stack.isEmpty()) {
            int currentNode = stack.peek(); // peek()으로 현재 위치를 확인
            boolean foundNext = false; // 다음 방문할 노드를 찾았는지 여부

            // 현재 노드에서 아직 확인하지 않은 이웃 노드들을 탐색
            while (nextNeighborIndex[currentNode] < graph.get(currentNode).size()) {
                int neighbor = graph.get(currentNode).get(nextNeighborIndex[currentNode]);
                nextNeighborIndex[currentNode]++; // 다음 이웃을 가리키도록 인덱스 증가

                if (depth[neighbor] == -1) { // 아직 방문하지 않은 이웃이라면
                    depth[neighbor] = depth[currentNode] + 1; // 깊이 계산
                    orders[neighbor] = order++;
                    stack.push(neighbor); 
                    foundNext = true;
                    break; // 다음 노드로 탐색을 넘김
                }
            }

            // 만약 현재 노드의 모든 이웃을 확인했다면 
            if (!foundNext) {
                stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        // Test cases
        int[] nodeValues = {5};
        int[] edgeValues = {5};
        int[] startValues = {1};
        int[][][] edgesList = {
            {
                {1, 4},
                {1, 2},
                {2, 3},
                {2, 4},
                {3, 4}
            }
        };
        long[] expectedResults = {20};

        for (int t = 0; t < nodeValues.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int node = nodeValues[t];
            int edge = edgeValues[t];
            int start = startValues[t];
            
            System.out.println("Input: " + node + " " + edge + " " + start);
            System.out.println("Edges:");
            for (int i = 0; i < edge; i++) {
                System.out.println(edgesList[t][i][0] + " " + edgesList[t][i][1]);
            }

            long result = solution(node, edge, start, edgesList[t]);

            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    
}