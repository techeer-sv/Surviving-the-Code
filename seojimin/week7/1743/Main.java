/**
 * 백준 1743번 - 음식물 피하기 [ BFS 알고리즘 ]
 *
 * [문제 요약]
 * - 뭉쳐있는 음식물의 최대 개수 카운ㅌ
 *
 * [해결 방법]
 * - 기존에 하던것처럼 전체 돌면서 음식물 존재 시 bfs 적용
 * - 최댓값 갱신
 *
 * [시간 복잡도]
 * - N(n*m)
 */


import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static int n,m,k;
    static int[][] food;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int max;

    static int solve(int n, int m, int k, int[][] trash){
        food = new int[n][m];
        visited = new boolean[n][m];
        max = 0;

        for (int i = 0; i < k; i++) {
            int x = trash[i][0];
            int y = trash[i][1];

            food[x-1][y-1] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (food[i][j] == 1 && !visited[i][j]){
                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        return max;
    }

    static int bfs(int a, int b){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a,b});
        visited[a][b] = true;

        int count = 1;

        while(!q.isEmpty()){
            int[] pos = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m){
                    if (!visited[nx][ny] && food[nx][ny] == 1){
                        q.add(new int[]{nx, ny});
                        count++;
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] trash = {
                {3,2},
                {2,2},
                {3,1},
                {2,3},
                {1,1}
        };

        System.out.println(solve(3,4,5,trash));
    }
}
