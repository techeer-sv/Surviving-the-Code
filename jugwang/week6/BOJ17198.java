/*
Bucket Brigade (백준 17198번)

- 시간 복잡도: O(1)
  - 10x10 격자에서 BFS 수행
  - 최대 100개의 셀을 방문하므로 O(100) = O(1) (상수 시간)

- 풀이
  1. 10x10 격자에서 호수(L)에서 헛간(B)까지의 최단 경로를 구하는 문제
  2. 바위(R)는 지나갈 수 없는 장애물
  3. BFS를 사용하여 L에서 시작해 B에 도달하는 최소 이동 횟수 계산
  4. 상하좌우 4방향으로 이동 가능
  5. B에 도달하는 순간 이동 횟수를 반환

- 슈도코드
    ```
    BFS(startX, startY):
        visited[10][10] = false로 초기화
        queue = new Queue()
        directions = [(0,1), (0,-1), (-1,0), (1,0)]
        
        visited[startY][startX] = true
        queue.add([startX, startY, 0])
        
        while queue is not empty:
            current = queue.poll()
            cx = current[0], cy = current[1], count = current[2]
            
            for each direction in directions:
                nx = cx + dx, ny = cy + dy
                
                if 범위 내(0 ≤ nx,ny < 10):
                    if board[ny][nx] == 'B':
                        return count  // B에 도달
                    
                    if not visited[ny][nx] and board[ny][nx] != 'R':
                        visited[ny][nx] = true
                        queue.add([nx, ny, count+1])
        
        return 0  // 도달 불가능
    ```

*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    char[][] board;

    // 생성자
    public Solution(char[][] board) {
        this.board = board;
    }


    public int solution(int startX, int startY) {
        // BFS로 'B'까지의 최단 거리 계산
        return bfs(startX, startY);
    }

    public int bfs(int startX, int startY) {
        boolean[][] visited = new boolean[10][10];
        Queue<int[]> queue = new LinkedList<>();

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        // 시작 지점 방문 처리
        visited[startY][startX] = true;
        // 큐에 시작 지점 추가(x, y, count)
        queue.add(new int[]{startX, startY, 0});

        while (!queue.isEmpty()) {
            // 큐에서 추출
            int[] aPoll = queue.poll();

            // x,y 좌표와 현재까지의 이동 횟수 추출
            int cx = aPoll[0];
            int cy = aPoll[1];
            int count = aPoll[2];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if((0<=nx && nx<10) && (0<=ny && ny<10)){
                    // 'B'에 도달하면 현재까지의 이동 횟수 반환
                    if (board[ny][nx] == 'B') {
                        return count;
                    }
                    // 방문하지 않았고, 'R'이 아니면 방문처리 및 큐에 추가
                    if(!visited[ny][nx] && board[ny][nx] != 'R'){
                        visited[ny][nx] = true;
                        queue.add(new int[]{nx, ny, count+1});
                    }
                }
            }
        }
        return 0;
    }
}

public class BOJ17198 {
    public static void main(String[] args) {
        // Test cases
        char[][] grid = {
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', 'B', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', 'R', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', 'L', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
        };

        int[] expectedResults = {7};

        for (int t = 0; t < 1; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int startX = 5;
            int startY = 8;

            System.out.println("Input: " + startX + " " + startY);
            System.out.println("Grid:");
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }

            Solution solved = new Solution(grid);
            int result = solved.solution(startX, startY);

            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}