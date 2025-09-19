/**
 * 백준 16173번 - 점프왕 쩰리 (Small) [dfs 알고리즘]
 *
 * [문제 요약]
 * - 쩰리 -> 점프하는것을 좋아하는 젤리
 * - 새로운 점프 게임을 원함
 * - 새로운 점프 게임의 조건
     * - 가로와 세로의 칸 수가 같은 정사각형의 구역 내부에서만 움직일 수 있다
     * - 정사각형 구역 외부로 나가는 경우 바닥으로 떨어져 즉시 사망
     * - 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸 (다른 출발점에서는 출발X)
     * - 이동 가능 방향은 오른쪽과 아래 뿐, 위쪽과 왼쪽은 이동 불가
     * - 가장 오른쪽, 가장 아래 칸에 도달하면 즉시 승리
     * - 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 씅 ㅕ있는 수 만큼
     * - 칸에 쓰여 있는 수 초과나 그 미만으로 이동 불가
 * - 주어진 구역에서 승리할 수 있는지 확인하기
 *
 * [해결 방법]
 * - DFS를 활용하여 해결
 * - 아래로 점프하는 경우와, 오른쪽으로 점프하는 경우를 각각의 트리의 가지로 판단
 * - 점차적으로 트리 가지로 뻗어가며, 아예 불가능한 경우 return
 * - 이때 방문여부(시도 여부)를 체크하며 불필요한 반복 제거
 * - 만약 -1에 도달하면, 결과를 HaruHaru로 변경(Default는 Hing)
 * - 도달 가능성만을 판단하기에, 어떤 경우의 수라도 -1에 한번이라도 도달한다면 성공한 것
 * - 처음에 방문여부를 체크하지 않았었기에, 채점했을 때 시간초과 발생
 * [시간 복잡도]
 * - 각 칸 최대 1번씩 방문 -> O(n^2)
 * - 각 칸에서의 연산 -> O(1)
 * - 전체 시간 복잡도 -> O(n^2)
 */

public class Main {

    static int[][] board;
    static boolean[][] visited;
    static int n;

    static boolean solve(int[][] inputBoard) {
        n = inputBoard.length;
        board = inputBoard;
        visited = new boolean[n][n];
        return jump(0, 0);
    }

    static boolean jump(int x, int y) {
        if (x >= n || y >= n || visited[x][y]) return false;
        visited[x][y] = true;

        if (board[x][y] == -1) return true;

        // 아래로 점프
        if (jump(x + board[x][y], y)) return true;

        // 오른쪽으로 점프
        if (jump(x, y + board[x][y])) return true;

        return false;
    }

    public static void main(String[] args) {
        int[][] testBoard = {
                {2, 3, 3, 1},
                {1, 2, 1, 3},
                {1, 2, 3, 1},
                {3, 1, 1, -1}
        };

        System.out.println(solve(testBoard) ? "HaruHaru" : "Hing");
    }
}