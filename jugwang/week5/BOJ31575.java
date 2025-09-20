/*
도시와 비트코인 (백준 31575)

- 시간 복잡도: O(N*M)
  - 최악의 경우 모든 셀을 방문해야 하므로 O(N*M)
  - DFS는 각 셀을 최대 한 번씩만 방문

- 풀이
  1. (0,0)에서 시작하여 (N-1, M-1)까지 도달할 수 있는지 확인하는 경로 탐색 문제
  2. 오른쪽(→)과 아래쪽(↓)으로만 이동 가능
  3. 1인 셀만 지날 수 있고, 0인 셀은 지날 수 없음
  4. DFS를 사용하여 목적지까지의 경로가 존재하는지 탐색
  5. visited 배열로 이미 방문한 셀을 체크하여 무한루프 방지

*/

public class BOJ31575 {
    static int m, n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static String solution(int n, int m, int[][] grid) {
        BOJ31575.n = n;
        BOJ31575.m = m;
        map = new int[m][n];
        visited = new boolean[m][n];
        
        // 테스트 데이터 복사
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = grid[i][j];
            }
        }
        
        // 결과 계산
        visited[0][0] = true;
        if (dfs(0, 0)) {
            return "Yes";
        } else {
            return "No";
        }
        
    }
    
    public static boolean dfs(int x, int y) {

        // 도착지점에 도달했으면 true 반환
        if (x == n-1 && y == m-1) {
            return true;
        }

        for (int i = 0; i < 2; i++) {
            // 다음 좌표 계산
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크
            if (((0 <= nx && nx < n) && (0 <= ny && ny < m))) {
                // 방문하지 않았고 1인 경우에만 방문
                if (map[ny][nx] == 1 && !visited[ny][nx]) {
                    // 방문 체크
                    visited[ny][nx] = true;
                    // 재귀 호출
                    if (dfs(nx, ny)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // Test cases
        int[][][] testCases = {
            {
                {1, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 1, 1}
            }
        };
        int[] nValues = {5};
        int[] mValues = {4};
        String[] expectedResults = {"Yes"};

        for (int t = 0; t < testCases.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int n = nValues[t];
            int m = mValues[t];
            
            System.out.println("Input: " + n + " " + m);
            System.out.println("Grid:");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(testCases[t][i][j] + " ");
                }
                System.out.println();
            }
            
            String result = solution(n, m, testCases[t]);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result.equals(expectedResults[t]) ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    
}
