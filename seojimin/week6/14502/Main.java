/**
 * 백준 14502번 - 연구소 [ BFS 알고리즘 ]
 *
 * [문제 요약]
 * - 바이러스 유출, 확산 방지를 위해 연구소에 벽을 세우려고 함
 * - 연구소는 크기가 N*M인 직사각형
 * - 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지
 * - 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다.
 * - 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 *
 * [해결 방법]
 * - 먼저 벽을 어디 세울지 모르기에, 벽 3개를 세우는 모든 경우의 수를 탐색해야 함
 * - 그리고 벽을 세운 뒤 바이러스의 확산을 진행시킨 후, 남은 안전구역의 수를 계산
 * - 계산한 값이 이전 값보다 큰 경우 업데이트, 이를 통해 최댓값을 구한다
 *
 * [시간 복잡도]
 *  - 벽 세우는 브루트 포스 -> O(e^3) // e = 벽 3개 세우는 경우의 수
 *  - 연구소 안전 구역 탐색 -> O(n * m)
 *  - 전체 시간 복잡도 -> O(e^3 * n * m)
 */

import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    static int n,m;
    static int[][] lab;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static int solve(int[][] map){
        n = map.length;
        m = map[0].length;
        lab = map;

        List<int[]> virus = new ArrayList<>();
        List<int[]> empty = new ArrayList<>();

        // 연구소 현황 체크
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (lab[i][j] == 2) virus.add(new int[]{i,j}); // 바이러스 위치 저장
                if (lab[i][j] == 0) empty.add(new int[]{i,j}); // 안전 구역 위치 저장
            }
        }

        int maxCount = 0;

        // 벽 3개를 브루트 포스로 세우며 카운트
        for (int i = 0; i < empty.size(); i++) {
            for (int j = i+1; j < empty.size(); j++) {
                for (int k = j+1; k <empty.size(); k++) {
                    int[][] copy = copyLab(); // 원본 훼손을 하지 않고 복사본에 진행

                    // 벽 세우기
                    copy[empty.get(i)[0]][empty.get(i)[1]] = 1;
                    copy[empty.get(j)[0]][empty.get(j)[1]] = 1;
                    copy[empty.get(k)[0]][empty.get(k)[1]] = 1;

                    // 바이러스 확산
                    spread(copy, virus);

                    // 안전구역 계산
                    int count  = 0;

                    for (int l = 0; l < n; l++) {
                        for (int o = 0; o < m; o++) {
                            if (copy[l][o] == 0) count++;
                        }
                    }

                    if (count > maxCount) maxCount = count;
                }
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        int[][] lab1 = {
                {2,0,0,0,1,1,0},
                {0,0,1,0,1,2,0},
                {0,1,1,0,1,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,0,1,1},
                {0,1,0,0,0,0,0},
                {0,1,0,0,0,0,0}
        };

        int[][] lab2 = {
                {0,0,0},
                {0,2,0},
                {0,0,0}
        };

        System.out.println(solve(lab1)); // 첫 번째 테스트 케이스
        System.out.println(solve(lab2)); // 두 번째 테스트 케이스
    }

    static int[][] copyLab(int[][] map) {
        int[][] copy = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    static void spread(int[][] copyLab, List<int[]> virus){
        Queue<int[]> q = new LinkedList<>(virus);

        while(!q.isEmpty()){
            int[] curs = q.poll();
            int x = curs[0];
            int y = curs[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny<m){
                    if (copyLab[nx][ny] == 0){
                        copyLab[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}