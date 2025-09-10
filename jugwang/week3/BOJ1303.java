/*
전쟁-전투 (백준 1303)

- 시간 복잡도
  - 각 칸을 최대 한 번만 방문하므로 DFS/BFS 전체 복잡도는 O(N*M)

- 풀이
  - 격자의 각 칸을 순회하며 방문하지 않은 칸에서 시작해 같은 색(W/B)으로 연결된 컴포넌트 크기를 DFS로 계산
  - 해당 컴포넌트의 크기 k에 대해 k^2를 해당 진영의 점수에 누적
  - 모든 칸을 처리한 뒤 W 점수와 B 점수를 "W B" 형식으로 출력
  - 연결 기준: 상하좌우 4방향, 같은 문자일 때만 연결
*/

class Solution {
    
    int n, m;
    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};
    String[][] map;
    boolean[][] visited;

    // 생성자
    public Solution(int m, int n) {
        this.n = n;
        this.m = m;
        map = new String[n][m];
        visited = new boolean[n][m];
    }

    public int dfs(int x, int y) {
        // 방문하므로 1개로 시작
        int num = 1;
        // 방문 처리
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 체크
            if ((0<=nx && nx<m) && (0<=ny && ny<n)) {
                // 방문하지 않고 현재와 다음의 문자가 같으면
                if ((map[ny][nx].equals(map[y][x])) && !visited[ny][nx]) {
                    // 재귀호출 
                    num += dfs(nx, ny);
                }
            }
        }
        return num;
    }


    public void solution(){

        int countW = 0;
        int countB = 0;
        // 맵 탐색
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                // 방문 체크
                if (!visited[y][x]) {
                    // "W"이면 W 카운트
                    if (map[y][x].equals("W")) {
                        countW += Math.pow(dfs(x, y), 2);
                    // "B"이면 B 카운트
                    }else {
                        countB += Math.pow(dfs(x, y), 2);
                    }
                }
            }
        }
        System.out.println(countW + " " + countB);
    }
}

public class BOJ1303 {
    public static void main(String[] args) {
        // Example Test (from prompt)
        int m = 5, n = 5;
        String[] lines = new String[]{
            "WBWWW",
            "WWWWW",
            "BBBBB",
            "BBBWW",
            "WWWWW"
        };

        Solution s = new Solution(m, n);
        for (int i = 0; i < n; i++) {
            String[] part = lines[i].split("");
            for (int j = 0; j < m; j++) {
                s.map[i][j] = part[j];
            }
        }
        System.out.println("--- Test Case (Example) ---");
        System.out.println("Input: " + m + " " + n);
        for (String line : lines) System.out.println(line);
        System.out.println("Result:");
        s.solution();
        System.out.println("Expected:\n130 65");
        System.out.println();
    }
}
