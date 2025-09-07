/*
점프왕 쩰리(small) (백준 16173)

- 시간 복잡도
  - 보드 크기가 N x N일 때, 각 칸을 최대 한 번만 방문하므로 시간 복잡도는 O(N^2)

- 풀이
  - 시작점 (0,0)에서 출발하여 현재 칸의 숫자만큼 오른쪽 또는 아래로 점프
  - 범위를 벗어나는 위치는 패스 
  - 이미 방문한 칸은 재방문하지 않아 사이클을 방지
  - 값이 -1인 칸에 도달하면 성공
  - DFS로 경로를 탐색하고, 도달 가능하면 "HaruHaru", 아니면 "Hing" 반환
*/


import java.util.Arrays;

public class BOJ16173 {
    static int[][] map;
    static boolean[][] visited;
    static int size;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static boolean dfs(int x, int y) {

        // 방문 처리
        visited[y][x] = true;

        // 목적지 도달
        if (map[y][x] == -1) {
            return true;
        }

        // 왼쪽, 아래쪽 방향 탐색
        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i]*map[y][x];
            int ny = y + dy[i]*map[y][x];
            // 범위 체크
            if (((x <= nx && nx < size) && (y < ny && ny < size))
                    || ((x < nx && nx < size) && (y <= ny && ny < size))) {
                // 방문 체크
                if (visited[ny][nx]) {
                    continue;
                }
                // 재귀 호출
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }
        // 목적지 도달 실패
        return false;
    }

    public static String solution(){
        // true 면 목적지 도달, false 면 목적지 도달 실패
        return dfs(0, 0) ? "HaruHaru" : "Hing";
    }

    public static void main(String[] args) {
        // Test cases based on provided examples
        int[][][] testCases = new int[][][] {
            { {1, 1, 10}, 
            {1, 5, 1}, 
            {2, 2, -1} },
            { {2, 2, 1}, 
            {2, 2, 2}, 
            {1, 2, -1} }
        };
        String[] expectedResults = new String[] {"HaruHaru", "Hing"};

        for (int i = 0; i < testCases.length; i++) {
            size = testCases[i].length;
            map = testCases[i];
            visited = new boolean[size][size];

            System.out.println("--- Test Case " + (i + 1) + " ---");
            System.out.println("Input size: " + size);
            System.out.println("Input board: " + Arrays.deepToString(testCases[i]));
            String result = solution();
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println(result.equals(expectedResults[i]) ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}
