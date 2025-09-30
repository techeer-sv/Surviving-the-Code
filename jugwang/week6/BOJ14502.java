/*
연구소 (백준 14502번)

- 시간 복잡도: O(C(빈칸수, 3) × N × M)
  - 빈 칸 중 3개를 선택하는 조합의 수: C(빈칸수, 3)
  - 각 조합마다 BFS로 바이러스 확산 시뮬레이션: O(N × M)
  - 최대 64개 칸에서 3개 선택 시 약 41,664 × 64 = 약 260만 연산

- 풀이
  1. N×M 연구소에서 벽 3개를 추가로 세워 안전 영역을 최대화하는 문제
  2. 0: 빈 칸, 1: 벽, 2: 바이러스
  3. 브루트포스로 빈 칸 중 3곳을 선택해 벽을 세우는 모든 경우의 수 탐색
  4. 각 경우마다 BFS로 바이러스가 상하좌우로 확산되는 시뮬레이션
  5. 바이러스 확산 후 남은 안전 영역(0)의 개수를 계산하여 최댓값 반환

- 슈도코드
    ```
    solution(board, row, col, empty, virus):
        max = 0
        
        // 빈 칸 중 3개를 선택하는 모든 조합
        for i = 0 to empty.size()-3:
            for j = i+1 to empty.size()-2:
                for k = j+1 to empty.size()-1:
                    // 보드 복사
                    testLab = copy(board)
                    
                    // 선택한 3곳에 벽 설치
                    testLab[empty[i].x][empty[i].y] = 1
                    testLab[empty[j].x][empty[j].y] = 1
                    testLab[empty[k].x][empty[k].y] = 1
                    
                    // 바이러스 확산 시뮬레이션
                    safeArea = BFS(testLab, virus)
                    max = Math.max(max, safeArea)
        
        return max
    
    BFS(lab, virus):
        queue = new Queue()
        directions = [(0,1), (0,-1), (-1,0), (1,0)]
        
        // 모든 바이러스를 큐에 추가
        for each v in virus:
            queue.add(v)
        
        while queue is not empty:
            current = queue.poll()
            
            for each direction in directions:
                nx = current.x + dx, ny = current.y + dy
                
                if 범위 내 and lab[nx][ny] == 0:
                    lab[nx][ny] = 2  // 바이러스 확산
                    queue.add(new Node(nx, ny))
        
        // 안전 영역 개수 계산
        return count of cells where lab[i][j] == 0
    ```

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    public int x;
    public int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    public int solution(
            int[][] board,
            int row, int col,
            List<Node> empty,
            List<Node> virus) {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < empty.size(); i++) {
            for (int j = i+1; j < empty.size(); j++) {
                for (int k = j+1; k < empty.size(); k++) {

                    int[][] testLab = new int[row][col];

                    for (int m = 0; m <row; m++) {
                        System.arraycopy(board[m], 0, testLab[m], 0, col);
                    }

                    testLab[empty.get(i).x][empty.get(i).y] = 1;
                    testLab[empty.get(j).x][empty.get(j).y] = 1;
                    testLab[empty.get(k).x][empty.get(k).y] = 1;

                    int answer = bfs(testLab, virus);

                    if (answer > max) {
                        max = answer;
                    }
                }
            }
        }
        return max;
    }

    public int bfs(int[][] lab, List<Node> virus) {
        boolean[][] visited = new boolean[lab.length][lab[0].length];

        int numSafety=0;
        Queue<Node> q = new LinkedList<>();

        for (Node node : virus) {
            int[] dx = {0, 0, -1, 1};
            int[] dy = {1, -1, 0, 0};

            q.add(node);
            while (!q.isEmpty()) {
                Node nNode = q.poll();

                int nx = nNode.x;
                int ny = nNode.y;

                for (int i = 0; i < 4; i++) {
                    int cx = nx + dx[i];
                    int cy = ny + dy[i];

                    if ((0<=cx&&cx<lab.length)&&(0<=cy&&cy<lab[0].length)) {
                        if (!visited[cx][cy]){
                            visited[cx][cy] = true;
                            if(lab[cx][cy]==0) {
                                lab[cx][cy] = 2;
                                q.add(new Node(cx, cy));
                            }
                        }

                    }
                }
            }
        }
        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                if (lab[i][j] == 0) {
                    numSafety++;
                }
            }
        }

        return numSafety;
    }
}

public class BOJ14502 {
    public static void main(String[] args) {
        // Test cases
        int[] nValues = {7, 4, 8};
        int[] mValues = {7, 6, 8};
        int[][][] grids = {
            {
                {2, 0, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 2, 0},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0}
            },
            {
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 2},
                {1, 1, 1, 0, 0, 2},
                {0, 0, 0, 0, 0, 2}
            },
            {
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
            }
        };
        int[] expectedResults = {27, 9, 3};

        for (int t = 0; t < nValues.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int n = nValues[t];
            int m = mValues[t];
            
            System.out.println("Input: " + n + " " + m);
            System.out.println("Grid:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(grids[t][i][j] + " ");
                }
                System.out.println();
            }

            List<Node> empty = new ArrayList<>();
            List<Node> virus = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grids[t][i][j] == 0) {
                        empty.add(new Node(i, j));
                    } else if (grids[t][i][j] == 2) {
                        virus.add(new Node(i, j));
                    }
                }
            }

            Solution solved = new Solution();
            int result = solved.solution(grids[t], n, m, empty, virus);

            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}