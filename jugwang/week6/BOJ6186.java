/*
Best Grass (백준 6186번)

- 시간 복잡도: O(R * C)
  - R: 행의 개수, C: 열의 개수
  - 모든 셀을 한 번씩 방문하고, BFS로 연결된 영역을 탐색
  - 각 셀은 최대 한 번만 방문되므로 O(R * C)

- 풀이
  1. 2D 그리드에서 연결된 '#' 영역의 개수를 구하는 문제 (Connected Components)
  2. '#'는 잔디가 있는 곳, '.'는 빈 공간
  3. 상하좌우로 연결된 '#' 영역을 하나의 잔디 덩어리로 카운트
  4. BFS를 사용하여 연결된 영역을 모두 방문 처리
  5. 방문하지 않은 '#'을 만날 때마다 새로운 잔디 덩어리로 카운트하고 BFS 시작
  6. 큐를 사용한 반복적 BFS로 구현

    ```
    solution():
        for 행 수 만큼 반복:
        for 열 수 만큼 반복:
            if 값이'#'이고 그리고 방문하지 않았다면:
                BFS(j, i) 
                count추가
        count 반환

    BFS(x, y):
        while queue가 빌 때까지 반복:
            큐에서 현재 위치 current를 poll.
            current의 x, y 좌표를 추출
            
            for 상, 하, 좌, 우 4방향에 대해 반복:
                nx = cx + dx[i]
                ny = cy + dy[i]

                if 범위 내 값이'#'이고 그리고 방문하지 않았다면:
                    방문처리
                    큐에 (nx, ny) 추가
  ```

*/

import java.util.Queue;
import java.util.LinkedList;

class Solution {
    int row;
    int col;
    char[][] board;
    boolean[][] visited;
    int count = 0;

    public Solution(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new char[row][col];
        this.visited = new boolean[row][col];
    }

    public int solution() {
        // 그리드 탐색
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 방문하지 않은 '#'을 찾으면 BFS 시작
                if (!visited[i][j] && board[i][j] == '#') {
                    bfs(j, i);
                    count++;
                }
            }
        }
        return count;
    }

    public void bfs(int x, int y) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        visited[y][x] = true;
        // 시작 위치를 큐에 추가
        queue.add(new int[]{x, y});
        
        // Queue가 빌 때까지 반복
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            // x,y 좌표 추출
            int cx = poll[0];
            int cy = poll[1];

            // 방문 처리
            visited[cy][cx] = true;
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크
                if((0<=ny&&ny<row) && (0<=nx&&nx<col)) {
                    if(!visited[ny][nx] && board[ny][nx] == '#') {
                        // 큐에 추가
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}

public class BOJ6186 {
    public static void main(String[] args) {
        // Test Case
        int[] rowValues = {5};
        int[] colValues = {6};
        String[] boardValues =
            {
                ".#....",
                "..#...",
                "..#..#",
                "...##.",
                ".#...."
            };

        int[] expectedResults = {5};

        Solution solved = new Solution(rowValues[0], colValues[0]);
        for (int i = 0; i < rowValues[0]; i++) {
            for (int j = 0; j < colValues[0]; j++) {
                solved.board[i][j] = boardValues[i].charAt(j);
            }
        }

        System.out.println("--- Test Case " + 1 + " ---");

        int n = rowValues[0];
        int m = colValues[0];

        System.out.println("Input: " + n + " " + m);
        System.out.println("Grid:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(solved.board[i][j] + " ");
            }
            System.out.println();
        }
        int answer = solved.solution();

        System.out.println("Result: " + answer);
        System.out.println("Expected: " + expectedResults[0]);
        System.out.println(answer == expectedResults[0] ? ">>> SUCCESS" : ">>> FAILURE");
        System.out.println();
    }
}