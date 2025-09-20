/**
 * 백준 1303번 - 전쟁 - 전투 [DFS 알고리즘]
 * (틀려서 GPT 도움 매우 많이 받음 나중에 다시 풀어보기)
 * [문제 요약]
 * - 전쟁 발발
 * - 병사들이 섞인 난전 발생
 * - 아군은 흰 옷, 적군은 파란 옷이기에 구분 가능
 * - 아군끼리 모이면 모일수록 강해짐
 * - N명이 뭉쳐있으면 N^2만큼의 위력 가능
 * - 난전 상황에서의 승리를 예측하기 위해, 각 진영의 위력 총합을 계산해보자
 * - 단, 대각선으로 인접한 경우는 뭉쳐있다고 보지 않음
 *
 * [해결 방법]
 * - dfs를 활용
 * - dfs에 넘기면 알아서 다 하도록 하는 것이 아닌, 직접 이중 루프를 돌리면서 dfs 활용
 * - 루프를 돌며, 해당 영역의 군사의 상,하,좌,우에 인접한 영역 내 군사의 진영을 체크하여, 같은 진영 군사면 해당 영역 군사를 다시 dfs함수에 재귀
 * - 이런식으로 계속해서 각 진영별 위력 더하기
 *
 * [시간 복잡도]
 * - 입력 받기 이중 루프 -> O(m*n)
 * - 모든 칸 탐색 이중 루프 -> O(m*n)
 * - 재귀 함수 내 상하좌우 탐색 -> O(1)
 * - 전체 시간 복잡도 -> O(m*n)
 */

public class Main {

    static boolean[][] visited;
    static char[][] soldiers;
    static int n;
    static int m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[] solve(int m, int n, char[][] grid) {
        Main.m = m;
        Main.n = n;
        soldiers = grid;
        visited = new boolean[m][n];

        int whitePower = 0;
        int bluePower = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    int size = dfs(i, j, soldiers[i][j]);
                    if (soldiers[i][j] == 'W') {
                        whitePower += size * size;
                    } else {
                        bluePower += size * size;
                    }
                }
            }
        }

        return new int[]{whitePower, bluePower};
    }

    static int dfs(int x, int y, char color) {
        visited[x][y] = true;
        int count = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && nx < m && ny >=0 && ny < n) {
                if (!visited[nx][ny] && soldiers[nx][ny] == color) {
                    count += dfs(nx, ny, color);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] test = {
                {'W','W','B'},
                {'B','B','B'},
                {'W','B','W'}
        };
        int[] result = solve(3, 3, test);
        System.out.println(result[0] + " " + result[1]); // whitePower bluePower 출력
    }
}