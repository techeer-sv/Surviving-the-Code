/*
특정 거리의 도시 찾기 (백준 18352번)

- 시간 복잡도: O(V + E)
  - V: 도시의 개수, E: 도로의 개수
  - BFS로 그래프를 한 번 탐색하므로 O(V + E)
  - 결과 정렬 시 O(K log K) (K는 결과 도시 개수)

- 풀이
  1. 방향 그래프에서 시작 도시로부터 정확히 K 거리에 있는 모든 도시 찾기
  2. BFS를 사용하여 레벨별로 탐색 (각 레벨이 거리를 의미)
  3. 거리 K에 도달한 모든 도시를 결과 리스트에 저장
  4. 결과가 없으면 -1 출력, 있으면 오름차순 정렬하여 출력

- 슈도코드
    ```
    BFS(startNode, targetDistance):
        queue = new Queue()
        visited[V] = false로 초기화
        resultCities = new List()
        
        queue.add([startNode, 0])
        visited[startNode] = true
        
        while queue is not empty:
            current = queue.poll()
            currentNode = current[0]
            currentDistance = current[1]
            
            if currentDistance == targetDistance:
                resultCities.add(currentNode)
                continue
            
            if currentDistance > targetDistance:
                continue
                
            for each neighbor in adjacentList[currentNode]:
                if not visited[neighbor]:
                    visited[neighbor] = true
                    queue.add([neighbor, currentDistance + 1])
        
        if resultCities is empty:
            return [-1]
        else:
            sort(resultCities)
            return resultCities
    ```

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    ArrayList<ArrayList<Integer>> map = new ArrayList<>();
    ArrayList<Integer> endNode = new ArrayList<>();
    boolean[] visited;

    public Solution(int nodeNum) {
        for (int i=0; i<nodeNum; i++) {
            this.map.add(new ArrayList<>());
        }
        visited = new boolean[nodeNum];
    }

    public ArrayList<Integer> solution(int startNode, int distance) {
        bfs(startNode, distance);
        if(endNode.isEmpty()) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(-1);
            return result;
        }
        Collections.sort(endNode);
        return endNode;
    }

    public void bfs(int startNode, int distance) {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{startNode, 0});
        visited[startNode] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int node = poll[0];
            int count = poll[1];

            if (count == distance) {
                endNode.add(node);
                continue;
            } else if (count > distance) {
                continue;
            }

            for(int neighbor : map.get(node)) {
                if(visited[neighbor]) continue;
                visited[neighbor] = true;
                q.add(new int[]{neighbor, count+1});
            }
        }
    }
}

public class BOJ18352 {
    public static void main(String[] args) {
        // Test cases
        int[] cityNums = {4, 4, 4};
        int[] roadNums = {4, 3, 4};
        int[] distances = {2, 2, 1};
        int[] startCities = {1, 1, 1};
        int[][][] roads = {
            {{1, 2}, {1, 3}, {2, 3}, {2, 4}},
            {{1, 2}, {1, 3}, {1, 4}},
            {{1, 2}, {1, 3}, {2, 3}, {2, 4}}
        };
        int[][] expectedResults = {
            {4},
            {-1},
            {2, 3}
        };

        for (int t = 0; t < cityNums.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int cityNum = cityNums[t];
            int roadNum = roadNums[t];
            int distance = distances[t];
            int startCity = startCities[t];
            
            System.out.println("Cities: " + cityNum + ", Roads: " + roadNum + ", Distance: " + distance + ", Start: " + startCity);
            System.out.println("Roads:");
            for (int[] road : roads[t]) {
                System.out.println("  " + road[0] + " -> " + road[1]);
            }

            Solution solved = new Solution(cityNum + 1);
            
            // 도로 추가
            for (int[] road : roads[t]) {
                solved.map.get(road[0]).add(road[1]);
            }
            
            ArrayList<Integer> results = solved.solution(startCity, distance);
            
            System.out.print("Results: ");
            for (int i = 0; i < results.size(); i++) {
                System.out.print(results.get(i));
                if (i < results.size() - 1) System.out.print(", ");
            }
            System.out.println();
            
            System.out.print("Expected: ");
            for (int i = 0; i < expectedResults[t].length; i++) {
                System.out.print(expectedResults[t][i]);
                if (i < expectedResults[t].length - 1) System.out.print(", ");
            }
            System.out.println();
            
            boolean isSuccess = results.size() == expectedResults[t].length;
            if (isSuccess) {
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i) != expectedResults[t][i]) {
                        isSuccess = false;
                        break;
                    }
                }
            }
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
