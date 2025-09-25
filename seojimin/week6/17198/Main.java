/**
 * 백준 17198번 - Bucket Brigade [BFS 알고리즘]
 *
 * [문제 요약]
 * - 헛간에 불이 남
 * - 불을 끄기 위해 소를 이용해 호수까지 길을 이어야 함
 * - 바위가 있는 곳엔 소를 배치할 수 없음
 *
 * [해결 방법]
 * - BFS를 돌려 경로를 찾자
 * - 상,하,좌,우 방문하되 돌이 있지 않고 방문하지 않은 경우 큐에 넣어주자
 *
 * [시간 복잡도]
 * - 최악의 경우 모든 칸 방문 -> O(100) -> O(1)
 */

public class Main {

    static boolean[][] visited;
    static char[][] farm;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int solve(char[][] farm){

        visited = new boolean[10][10];

        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (farm[i][j] == 'B') {
                    q.add(new int[]{i,j,0}); // 시작점으로 할 헛간의 위치 기록
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()){
            int[] curs = q.poll();
            int x = curs[0];
            int y = curs[1];
            int cow = curs[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < 10 && ny >= 0 && ny <10){
                    if (farm[nx][ny] == 'L') { // 호수에 도착했다면, cow 수 출력 후 종료
                        return cow;
                    }

                    if (farm[nx][ny] != 'R' && !visited[nx][ny]){
                        q.add(new int[]{nx, ny, cow+1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char[][] farm = {
                "..........".toCharArray(),
                "..........".toCharArray(),
                "..........".toCharArray(),
                "..B.......".toCharArray(),
                "..........".toCharArray(),
                ".....R....".toCharArray(),
                "..........".toCharArray(),
                "..........".toCharArray(),
                ".....L....".toCharArray(),
                "..........".toCharArray()
        };

        System.out.println(solve(farm));
    }
}