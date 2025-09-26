/**
 * 백준 6186번 - Best Grass [ BFS 알고리즘 ]
 *
 * [문제 요약]
 *  - 가로 길이: R, 세로 길이: C
 *  - 해당 그리드에 있는 클램프는 #으로 표시
 *  - 대각선 제외 상하 혹은 좌우로 2개의 #으로도 표시
 *  - 그렇기에 해당 그리드에 몇 개의 클램프 뭉치가 있는지 구하는 문제
 *
 * [해결 방법]
 * - BFS 알고리즘 활용
 * - 이번에는 근처(상,하,좌,우)에 방문하지 않은 '#'이 있다면 바로 해당 좌표로 재귀함수를 호출하지 않음
 * - 대신 큐를 활용하여 처리
 * - 처음 bfs함수를 호출하며 큐를 생성하고, 해당 함수의 좌표를 큐에 담음
 * - 그 후 상,하,좌,우를 살피며 방문하지 않은 '#'을 탐색 찾은 경우 해당 좌표로 깊이 들어가지 않고 큐에 담은 다음 그 외 주위에 '#'이 있는지 마저 탐색
 * - 그렇기에 한번 쭉 근처 노드르 탐색하고, '#'이 있었다면 큐에 들어있을 것이기에 큐가 비어있지 않아 다시 한번 반복문을 통해 해당 좌표의 주위를 탐색하게 된다
 * - 만약 주위에 '#'이 하나도 없었다면 그대로 함수는 종료된다
 *
 * [시간 복잡도]
 * - 그리드 전체를 방문할 수 있기에
 * - 전체 시간 복잡도 -> O(r * c)
 */

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int r;
    static int c;
    static char[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int solve(char[][] map){
        r = map.length;
        c = map[0].length;
        board = map;
        visited = new boolean[r][c];

        int count = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (!visited[i][j] && board[i][j] == '#') {
                    count++;
                    bfs(i,j);
                }
            }
        }

        return count;
    }

    static void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        while(!q.isEmpty()){
            int cur[] = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c){
                    if (!visited[nx][ny] && board[nx][ny] == '#') {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        char[][] test1 = {
                {'.','#','.'},
                {'#','.','.'},
                {'#','.','.'}
        };

        char[][] test2 = {
                {'.','#','.','.','.','.'},
                {'.','.','#','.','.','.'},
                {'.','.','#','.','.','#'},
                {'.','.','.','#','#','.'},
                {'.','#','.','.','.','.'}
        };

        System.out.println(solve(test1));
        System.out.println(solve(test2));
    }
}
