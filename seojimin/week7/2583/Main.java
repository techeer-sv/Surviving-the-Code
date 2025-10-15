/**
 * 백준 2583번 - 영역 구하기 [BFS 알고리즘]
 *
 * [문제 요약]
 * - 눈금의 간격이 1인 M * N (M, N <= 100) 크기의 모눈종이
 * - 모눈종이 위에 눈금을 맞춰 K개의 직사각형을 그릴 때
 * - K개의 직사각형의 내부를 제외한 나머지 부분이 몇개의 분리된 영역으로 나눠짐
 * - 각 직사각형의 왼쪽 하단 좌표와 우측 상단 좌표가 주어짐
 * - 분리된 영역이 몇개인지 그리고 각 영역별 넓이를 구해 오름차순으로 출력
 *
 * [해결 방법]
 * - 우선 종이와 직사각형 영역을 다 받고, 직사각형 영역은 별도의 표시
 * - 종이 전체를 순환하며, 방문하지 않고 직사각형 영역 아닌 경우 bfs 함수 호출(함수 호출하며 count 추가)
 *  - bfs 함수 내에서 연결된 영역 전체 탐색
 *  - 탐색 후 계산된 넓이 area 배열에 저장
 *
 * [시간 복잡도]
 *  - bfs 및 영역 전체 순회 -> O(n * m)
 *  - 정렬 -> O(A logA)
 *  - 전체 시간 복잡도 -> O(n*m)
 *
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static int n,m,k;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    static void solve(String[] input){
        String[] first = input[0].split(" ");
        m = Integer.parseInt(first[0]);
        n = Integer.parseInt(first[1]);
        k = Integer.parseInt(first[2]);

        paper = new int[m][n];
        visited = new boolean[m][n];

        // 직사각형 색칠
        for (int i = 0; i < k; i++) {
            String[] pos = input[i + 1].split(" ");
            int x1 = Integer.parseInt(pos[0]);
            int y1 = Integer.parseInt(pos[1]);
            int x2 = Integer.parseInt(pos[2]);
            int y2 = Integer.parseInt(pos[3]);
            for (int j = y1; j < y2; j++) {
                for (int l = x1; l < x2; l++) {
                    paper[j][l] = 1;
                }
            }
        }

        List<Integer> area = new ArrayList<>();
        for (int i = m-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && paper[i][j] != 1){
                    int size = bfs(i, j);
                    area.add(size);
                }
            }
        }

        System.out.println(area.size());
        Collections.sort(area);
        for (int i : area) {
            System.out.print(i + " ");
        }
    }

    static int bfs(int a, int b){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{a,b});
        visited[a][b] = true;

        int extent = 1;

        while(!q.isEmpty()){
            int[] pos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n){
                    if (!visited[nx][ny] && paper[nx][ny] != 1){
                        q.add(new int[]{nx, ny});
                        extent++;
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return extent;
    }

    public static void main(String[] args) {
        // 테스트 케이스 입력용 예시
        List<String[]> testCases = new ArrayList<>();
        testCases.add(new String[]{
                "5 7 3",
                "0 2 4 4",
                "1 1 2 5",
                "4 0 6 2"
        });

        testCases.add(new String[]{
                "3 3 1",
                "0 0 1 1"
        });

        for (String[] testCase : testCases) {
            solve(testCase);
        }
    }
}