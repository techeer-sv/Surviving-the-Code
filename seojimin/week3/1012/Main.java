/**
 * 백준 1012번 - 유기농 배추 [dfs 알고리즘]
 *
 * [문제 요약]
 * - 무농약 유기농 배추 재배를 위핸 배추 흰지렁이
 * - 배추 흰지렁이는 배추 근처에 서식해 배추를 보호, 특정 배추에 해당 지렁이 살고 있으면 인접 배추로 이동 가능
 * - 이는 인접 배추 역시 보호받을 수 있음을 뜻함
 * - 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우가 서로 인접해있는 것
 * - M * N 영역 군데군데에 배추가 심어져 있고, 서로 인접해있는 영역이 몇개인지 알면 필요한 지렁이수를 구할 수 있다.
 *
 * [해결 방법]
 * - 이전의 군인 문제랑 비슷한 영역 문제, 좀 더 쉬운 버전인듯하다
 * - 이번엔 인접한 배추영역의 개수를 찾는 문제
 * - 비슷하게 모든 칸을 루프하며, 방문하지 않았고 배추가 있는 경우 dfs를 적용하고
 * - dfs함수 내에선 상하좌우를 탐색하며 배추가 있고 방문하지 않은 경우 해당 좌표에도 dfs를 적용해 방문표시를 하도록 한다
 * - 이렇게 최종적으로 영역의 수를 구할 수 있다.
 * - 상하좌우 이동 테크닉이 도움이 되었다.
 *
 * [시간 복잡도]
 * - 모든 칸 탐색 -> O(n*m)
 * - dfs내 상하좌우 탐색 -> O(1)
 * - 전체 시간 복잡도 -> O(n*m)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int n;
    static int m;
    static boolean visited[][];
    static int board[][];

    // 상하좌우 이동
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken()); // 세로 길이
            n = Integer.parseInt(st.nextToken()); // 가로 길이
            int k = Integer.parseInt(st.nextToken()); // 배추 개수

            // 배추 정보 입력
            board = new int[m][n];
            visited = new boolean[m][n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            }

            // 모든 칸 탐색
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && board[i][j] == 1){
                        dfs(i, j);
                        count++;
                    }
                }
            }

            System.out.println(count);
            t--;
        }
    }

    static void dfs(int x, int y){
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < m && ny >=0 && ny < n){
                if (!visited[nx][ny] && board[nx][ny] == 1){
                    dfs(nx,ny);
                }
            }
        }
    }
}