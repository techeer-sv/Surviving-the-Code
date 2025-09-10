/*
유기농 배추 (백준 1012)

- 시간 복잡도
  - DFS/BFS로 연결 컴포넌트 탐색 시 시간 복잡도는 O(M*N)

- 풀이
  - 배추가 심어진 칸(1)을 그래프의 노드로 보고 상하좌우로 연결된 칸을 하나의 연결 컴포넌트로 간주
  - 모든 좌표를 순회하며 방문하지 않은 배추 칸을 발견하면 DFS를 수행해 연결된 모든 칸을 방문 처리
  - DFS 호출 횟수(컴포넌트 수)가 필요한 지렁이 수가 됨
*/

class Solution {
    int n, m;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    boolean[][] map;
    boolean[][] visited;

    // 생성자
    public Solution(int m, int n) {
        this.n = n;
        this.m = m;
    }

    public void dfs(int x, int y) {
        // 방문 처리
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 체크
            if ((0<=nx && nx<m) && (0<=ny && ny<n)) {
                // 방문하지 않고 값이 "1"인 경우 제귀 
                if (!visited[ny][nx] && map[ny][nx]) {
                    dfs(nx, ny);
                }
            }
        }
    }

    public int solution(){
        int result = 0;

        // 맵을 전체 탐색
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 값이 "1"이고 방문하지 않았다면
                if (map[y][x] && !visited[y][x]) {
                    dfs(x, y);
                    // 카운트
                    result++;
                }
            }
        }
        return result;
    }
}

public class BOJ1012 {
    public static void main(String[] args) {
        // Example 1
        {
            int m1 = 10, n1 = 8, k1 = 17;
            int[][] points1 = new int[][]{
                {0,0},{1,0},{1,1},{4,2},{4,3},{4,5},{2,4},{3,4},{7,4},{8,4},{9,4},{7,5},{8,5},{9,5},{7,6},{8,6},{9,6}
            };
            Solution s1 = new Solution(m1, n1);
            s1.map = new boolean[n1][m1];
            s1.visited = new boolean[n1][m1];
            for (int[] p : points1) s1.map[p[1]][p[0]] = true;
            int r1 = s1.solution();

            int m2 = 10, n2 = 10, k2 = 1;
            int[][] points2 = new int[][]{
                {5,5}
            };
            Solution s2 = new Solution(m2, n2);
            s2.map = new boolean[n2][m2];
            s2.visited = new boolean[n2][m2];
            for (int[] p : points2) s2.map[p[1]][p[0]] = true;
            int r2 = s2.solution();

            System.out.println("--- Test Case (Example 1) ---");
            System.out.println("Inputs: [" + m1 + ", " + n1 + ", " + k1 + "] with " + points1.length + " points; and [" + m2 + ", " + n2 + ", " + k2 + "] with " + points2.length + " point");
            System.out.println("Results:\n" + r1 + "\n" + r2);
            System.out.println("Expected:\n5\n1");
            System.out.println(((r1==5)&&(r2==1)) ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }

        // Example 2
        {
            int m = 5, n = 3, k = 6;
            int[][] points = new int[][]{
                {0,2},{1,2},{2,2},{3,2},{4,2},{4,0}
            };
            Solution s = new Solution(m, n);
            s.map = new boolean[n][m];
            s.visited = new boolean[n][m];
            for (int[] p : points) s.map[p[1]][p[0]] = true;
            int r = s.solution();

            System.out.println("--- Test Case (Example 2) ---");
            System.out.println("Input: [" + m + ", " + n + ", " + k + "] with " + points.length + " points");
            System.out.println("Result:\n" + r);
            System.out.println("Expected:\n2");
            System.out.println((r==2) ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
