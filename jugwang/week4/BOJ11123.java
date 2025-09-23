/*
양 한마리... 양 두마리... (백준 11123번)

- 시간 복잡도: O(N*M)
  - N: 세로 크기, M: 가로 크기
  - 모든 셀을 한 번씩 방문하고, DFS로 연결된 영역을 탐색
  - 각 셀은 최대 한 번만 방문되므로 O(N*M)

- 풀이
  1. 2D 그리드에서 연결된 '#' 영역의 개수를 구하는 문제 (Connected Components)
  2. '#'는 양이 있는 곳, '.'는 빈 공간
  3. 상하좌우로 연결된 '#' 영역을 하나의 양 무리로 카운트
  4. DFS를 사용해 연결된 영역을 모두 방문 처리
  5. 방문하지 않은 '#'를 만날 때마다 새로운 양 무리로 카운트하고 DFS 시작
 
*/

class Solution {
    int n, m;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    String[][] map;
    boolean[][] visited;

    public Solution(int m, int n) {
        this.m = m;
        this.n = n;
        map = new String[n][m];
        visited = new boolean[n][m];
    }

    public void dfs(int x, int y) {
        // 방문 처리
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 체크
            if ((0<=nx && nx<m) && (0<=ny && ny<n)) {
                // 양이 있는 곳이고 방문하지 않은 경우
                if ((map[ny][nx].equals("#")) && !visited[ny][nx]) {
                    // 재귀 호출
                    dfs(nx, ny);
                }
            }
        }
    }


    public int getSolution(){
        // DFS를 통해 연결된 양 무리의 개수를 센다.
        int count = 0;
        // 그리드 탐색
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 양이 있는 곳이고 방문하지 않은 경우 
                if (!visited[y][x] && map[y][x].equals("#")) {
                    dfs(x, y);
                    count++;
                }
            }
        }
        return count;
    }

    public void solution(){
        int count = getSolution();
        System.out.println(count);
    }
}

public class BOJ11123 {
    public static void main(String[] args) {
        // Test cases
        String[][][] testCases = {
            {
                {"#.#."},
                {".#.#"},
                {"#.##"},
                {".#.#"}
            },
            {
                {"###.#"},
                {"..#.."},
                {"#.###"}
            }
        };
        int[][] dimensions = {{4, 4}, {3, 5}}; // {n, m}
        int[] expectedResults = {6, 3};

        for (int t = 0; t < testCases.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int n = dimensions[t][0];
            int m = dimensions[t][1];
            
            System.out.println("Input: " + n + " " + m);
            System.out.println("Grid:");
            for (int i = 0; i < n; i++) {
                System.out.println(testCases[t][i][0]);
            }

            Solution solved = new Solution(m, n);

            // 지도 읽기
            for (int i = 0; i < n; i++) {
                String part = testCases[t][i][0];
                for (int j = 0; j < m; j++) {
                    solved.map[i][j] = String.valueOf(part.charAt(j));
                }
            }

            // 결과 계산을 위해 solution 메서드 수정 필요
            int result = solved.getSolution();
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}