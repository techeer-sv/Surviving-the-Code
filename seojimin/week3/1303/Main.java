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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] visited;
    static char[][] soldiers;
    static int n;
    static int m;
    static int whitePower = 0;
    static int bluePower = 0;

    // 상하좌우 이동
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 가로 크기
        m = Integer.parseInt(st.nextToken()); // 세로 크기

        soldiers = new char[m][n];
        visited = new boolean[m][n];

        // 군사 정보 입력 받기
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                soldiers[i][j] = line.charAt(j);
            }
        }

        // 모든 칸 탐색
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 만약 방문되지 않은 인덱스면
                if (!visited[i][j]) {
                    // dfs를 돌려 인접 군사 수를 가져옴
                    int size = dfs(i, j, soldiers[i][j]);
                    // 인접 군사수를 자신의 진영에 맞게 제곱해서 더함
                    if (soldiers[i][j] == 'W') {
                        whitePower += size * size;
                    } else {
                        bluePower += size * size;
                    }
                }
            }
        }

        System.out.println(whitePower + " " + bluePower);

    }

    // 해당 인덱스의 연결된 군사의 수를 카운트해서 넘긴다
    static int dfs(int x, int y, char color) {
        // 방문하는 인덱스에는 방문 표시
        visited[x][y] = true;

        // 자신 포함 1로 시작
        int count = 1;

        // 상,하,좌,우를 다 체크
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 만약 x,y가 0보다 크거나 같고, m혹은 n보다 작으면 -> 즉 어느 끝부분을 넘어간 것이 아니면
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                // 해당 인접 영역이 방문되었는지 그리고 그 영역의 군사 색은 어떤건지 판단
                if (!visited[nx][ny] && soldiers[nx][ny] == color) {
                    // 만약 같은 진영의 군사라면, 해당 병사로부터 얼마나 인접했는지 dfs 재귀
                    count += dfs(nx, ny, color);
                }
            }
        }

        return count;
    }
}