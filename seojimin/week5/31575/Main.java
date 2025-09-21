/**
 * 백준 31575번 - 도시와 비트코인 [dfs 알고리즘]
 *
 * [문제 요약]
 * - 가로 N * 세로 M 크기의 격자 모양 도시
 * - 진우는 북서쪽 끝에 거래소는 남동쪽 끝에 존재
 * - 도시 구역은 지나갈 수 있는 곳과 없는 곳 존재
 * - 거래소로 가는 방법은 한칸씩 동쪽(오른쪽) 또는 남쪽(아래쪽)으로만 이동하여 거래소 도착 가능
 * - 거래소로 갈 수 있는지 구하는 프로그램 작성
 *
 * [해결 방법]
 * - 결국 상하좌우 전진 여부를 체크하던 방법을 활용해, 우하 방향 전진 가능성을 체크하여 dfs로 나아가면 된다
 *
 * [시간 복잡도]
 * - 최대 모든 격자를 방문한다 할 때 -> O(n*m)
 * - 전체 시간 복잡도 -> O(n*m)
 */

public class Main {

    static boolean[][] visited;
    static int[][] board;
    static int[] dx = {1, 0}; // 아래, 오른쪽
    static int[] dy = {0, 1};
    static int m;
    static int n;

    // 실제 문제 해결 함수
    static boolean solve(int[][] map) {
        m = map.length;
        n = map[0].length;
        board = map;
        visited = new boolean[m][n];

        return dfs(0, 0);
    }

    static boolean dfs(int x, int y) {
        if (x == m - 1 && y == n - 1) return true;

        visited[x][y] = true;

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny] && board[nx][ny] == 1) {
                if (dfs(nx, ny)) return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 테스트 케이스 1
        int[][] test1 = {
                {1, 0, 1},
                {1, 1, 1},
                {0, 1, 1}
        };

        // 테스트 케이스 2
        int[][] test2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        // 테스트 케이스 3
        int[][] test3 = {
                {1, 1},
                {1, 1}
        };

        System.out.println("Test1: " + (solve(test1) ? "Yes" : "No"));
        System.out.println("Test2: " + (solve(test2) ? "Yes" : "No"));
        System.out.println("Test3: " + (solve(test3) ? "Yes" : "No"));
    }
}