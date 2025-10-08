/*
음식물 피하기 (백준 1743번)

- 시간 복잡도: O(N × M + K)
  - N: 세로 크기, M: 가로 크기, K: 음식물 쓰레기 개수
  - 모든 음식물 쓰레기 위치를 한 번씩 방문하고, BFS로 연결된 음식물 덩어리를 탐색
  - 각 음식물은 최대 한 번만 방문되므로 O(K)
  - 전체적으로 O(N × M + K)

- 풀이
  1. N×M 통로에서 떨어진 음식물 쓰레기들이 상하좌우로 인접하면 하나의 덩어리가 됨
  2. 가장 큰 음식물 덩어리의 크기를 구하는 문제
  3. BFS를 사용하여 각 음식물 쓰레기에서 시작해 연결된 덩어리의 크기를 계산
  4. 방문하지 않은 음식물 쓰레기에서만 BFS를 시작
  5. 모든 덩어리 중 가장 큰 크기를 반환

- 의사코드
    ```
    solution(passage, row, col, locations):
        maxSize = 0
        
        for each food in locations:
            if not visited[food.y][food.x]:
                clusterSize = BFS(food, row, col, passage)
                maxSize = max(maxSize, clusterSize)
        
        return maxSize
    
    BFS(startNode, row, col, passage):
        queue = new Queue()
        directions = [(0,1), (0,-1), (-1,0), (1,0)]
        count = 1
        
        queue.add(startNode)
        visited[startNode.y][startNode.x] = true
        
        while queue is not empty:
            current = queue.poll()
            cx = current.x, cy = current.y
            
            for each direction in directions:
                nx = cx + dx, ny = cy + dy
                
                if 범위 내 and passage[ny][nx] and not visited[ny][nx]:
                    visited[ny][nx] = true
                    queue.add(new Node(nx, ny))
                    count++
        
        return count
    ```

*/

import java.util.ArrayList;
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
    boolean[][] visited;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    public Solution(int row, int col) {
        this.visited = new boolean[row+1][col+1];
    }

    public int solution(boolean[][] passage, int row, int col, ArrayList<Node> location) {
        int max = 0;

        for (Node i : location) {
            if(!visited[i.y][i.x]){
                int count = bfs(i, row, col, passage);
                if(count>max) {
                    max = count;
                }
            }
        }
        return max;
    }

    public int bfs(Node node, int row, int col, boolean[][] passage) {
        int count = 1;
        Queue<Node> q = new LinkedList<Node>();

        q.add(node);
        visited[node.y][node.x] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int cx = poll.x;
            int cy = poll.y;

            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if((1<=nx&&nx<=col) && (1<=ny&&ny<=row)) {
                    if(passage[ny][nx]){
                        if(!visited[ny][nx]) {
                            visited[ny][nx] = true;
                            q.add(new Node(nx, ny));
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}

public class BOJ1743 {
    public static void main(String[] args) {
        // Test cases
        int[] rows = {3};
        int[] cols = {4};
        int[] foodWasteCounts = {5};
        int[][][] foodPositions = {
            {{3, 2}, {2, 2}, {3, 1}, {2, 3}, {1, 1}}
        };
        int[] expectedResults = {4};

        for (int t = 0; t < rows.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int row = rows[t];
            int col = cols[t];
            int foodWaste = foodWasteCounts[t];
            
            System.out.println("Grid: " + row + " x " + col + ", Food waste count: " + foodWaste);
            System.out.println("Food positions:");
            for (int[] pos : foodPositions[t]) {
                System.out.println("  (" + pos[0] + "," + pos[1] + ")");
            }

            boolean[][] passage = new boolean[row+1][col+1];
            ArrayList<Node> location = new ArrayList<>();
            
            // 음식물 쓰레기 위치 설정
            for (int[] pos : foodPositions[t]) {
                int y = pos[0];
                int x = pos[1];
                passage[y][x] = true;
                location.add(new Node(x, y));
            }
            
            Solution solved = new Solution(row, col);
            int result = solved.solution(passage, row, col, location);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            
            boolean isSuccess = (result == expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}