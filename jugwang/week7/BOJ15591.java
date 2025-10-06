/*
MooTube (Silver) (백준 15591번)

- 시간 복잡도: O(Q × N)
  - Q: 쿼리 개수, N: 노드 개수
  - 각 쿼리마다 BFS로 트리 전체 탐색하므로 O(N)
  - 전체 시간 복잡도는 O(Q × N)

- 풀이
  1. 트리에서 두 동영상 간의 경로상 가장 작은 가중치가 USADO 값
  2. 시작 노드에서 BFS로 모든 연결된 노드 탐색
  3. 경로상 최소 가중치가 k 이상인 노드들의 개수 계산
  4. 각 쿼리마다 독립적으로 BFS 수행하여 조건을 만족하는 노드 개수 반환

- 슈도코드
    ```
    solution(questionNum, questions, nodeNum):
        for each question:
            distance = question[0]  // 최소 USADO 값
            start = question[1]     // 시작 노드
            result = BFS(distance, start, nodeNum)
        return results
    
    BFS(distance, start, nodeNum):
        count = 0
        visited[nodeNum+1] = false로 초기화
        queue = new Queue()
        
        queue.add([start, MAX_VALUE])
        visited[start] = true
        
        while queue is not empty:
            current = queue.poll()
            currentNode = current[0]
            currentDistance = current[1]
            
            for each neighbor in adjacentList[currentNode]:
                if not visited[neighbor.to]:
                    visited[neighbor.to] = true
                    newDistance = min(currentDistance, neighbor.weight)
                    if newDistance >= distance:
                        queue.add([neighbor.to, newDistance])
                        count++
        
        return count
    ```

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int to;
    int weight;

    public Node(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Solution {
    ArrayList<ArrayList<Node>> link;

    public Solution(int nodeNum) {
        this.link = new ArrayList<>();
        for(int i=0; i<=nodeNum; i++) {
            link.add(new ArrayList<>());
        }
    }

    public int[] solution(int questionNum, int[][] questions, int nodeNum) {
        int[] response = new int[questionNum];

        for (int i=0; i<questionNum; i++) {
            int distance = questions[i][0];
            int start = questions[i][1];

            response[i] = bfs(distance, start, nodeNum);
        }

        return response;
    }

    public int bfs(int distance ,int start, int nodeNum) {
        int count = 0;
        boolean[] visited = new boolean[nodeNum+1];
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{start,Integer.MAX_VALUE});
        visited[start] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            int currentNode = poll[0];
            int currentDistance = poll[1];

            for (Node nextNode : link.get(currentNode)) {
                if(!visited[nextNode.to]) {
                    visited[nextNode.to] = true;
                    int newDistance = Math.min(currentDistance, nextNode.weight);
                    if (newDistance >= distance) {
                        q.add(new int[]{nextNode.to, newDistance});
                        count++;
                    }
                }
            }
        }

        return count;
    }
}


public class BOJ15591 {
    public static void main(String[] args) {
        // Test cases
        int[] nodeNums = {4};
        int[] questionNums = {3};
        int[][][] edges = {
            {{1, 2, 3}, {2, 3, 2}, {2, 4, 4}}
        };
        int[][][] questions = {
            {{1, 2}, {4, 1}, {3, 1}}
        };
        int[][] expectedResults = {
            {3, 0, 2}
        };

        for (int t = 0; t < nodeNums.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int nodeNum = nodeNums[t];
            int questionNum = questionNums[t];
            
            System.out.println("Nodes: " + nodeNum + ", Questions: " + questionNum);
            System.out.println("Edges:");
            for (int[] edge : edges[t]) {
                System.out.println("  " + edge[0] + " - " + edge[1] + " (weight: " + edge[2] + ")");
            }
            System.out.println("Questions:");
            for (int[] question : questions[t]) {
                System.out.println("  k=" + question[0] + ", start=" + question[1]);
            }

            Solution solved = new Solution(nodeNum);
            
            // 간선 추가
            for (int[] edge : edges[t]) {
                solved.link.get(edge[0]).add(new Node(edge[1], edge[2]));
                solved.link.get(edge[1]).add(new Node(edge[0], edge[2]));
            }
            
            int[] results = solved.solution(questionNum, questions[t], nodeNum);
            
            System.out.print("Results: ");
            for (int i = 0; i < results.length; i++) {
                System.out.print(results[i]);
                if (i < results.length - 1) System.out.print(", ");
            }
            System.out.println();
            
            System.out.print("Expected: ");
            for (int i = 0; i < expectedResults[t].length; i++) {
                System.out.print(expectedResults[t][i]);
                if (i < expectedResults[t].length - 1) System.out.print(", ");
            }
            System.out.println();
            
            boolean isSuccess = java.util.Arrays.equals(results, expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}