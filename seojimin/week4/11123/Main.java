/**
 * 백준 11123번 - 양 한마리... 양 두마리... [dfs 알고리즘]
 *
 * [문제 요약]
 * - 양을 #으로, 풀을 .으로 표현
 * - 서로 다른 # 두 개 이상 붙어있다면 한 무리의 양 존재
 * - 초원에서 풀을 뜯고 있는 양들을 그리드로 표현할 수 있다
 * - 현재 양 몇무리가 있는지 출력하자
 *
 * [해결 방법]
 * - 결국 영역 개수 문제
 * - 기존의 사용했던 상하좌우 테크닉을 다시 사용하자
 * - 전체 이중 루프 돌면서, 방문하지 않았고 양인 경우 dfs 사용
 *
 * [시간 복잡도]
 * - 이중 루프 -> O(h * w)
 * - 전체 시간 복잡도 -> O(h * w)
 */

public class Main {

    static boolean[][] visited;
    static char[][] board;
    static int h;
    static int w;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int solve(char[][] map){
        h = map.length;
        w = map[0].length;
        board = map;
        visited = new boolean[h][w];

        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (!visited[i][j] && board[i][j] == '#') {
                    count++;
                    dfs(i, j, board[i][j]);
                }
            }
        }
        return count;
    }

    static void dfs(int x, int y, char sheep){
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < h && ny >= 0 && ny < w){
                if (!visited[nx][ny] && sheep == board[nx][ny]){
                    dfs(nx, ny, sheep);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        char[][] test1 = {
                {'#', '.', '.', '#'},
                {'.', '#', '#', '.'},
                {'#', '.', '.', '#'}
        };

        char[][] test2 = {
                {'.', '.', '.'},
                {'.', '#', '.'},
                {'.', '.', '.'}
        };

        System.out.println("Test1 count: " + solve(test1)); // 기대값: 영역 개수
        System.out.println("Test2 count: " + solve(test2)); // 기대값: 영역 개수
    }
}