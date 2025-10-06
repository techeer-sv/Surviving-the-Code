/*
영역 구하기 (백준 2583번)

- 시간 복잡도: O(M × N)
  - M: 세로 크기, N: 가로 크기
  - 모든 셀을 한 번씩 방문하고, BFS로 연결된 영역을 탐색
  - 각 셀은 최대 한 번만 방문되므로 O(M × N)
  - 결과 정렬 시 O(K log K) (K는 영역의 개수)

- 풀이
  1. M×N 격자에서 직사각형들로 막힌 영역을 제외한 빈 공간의 영역들을 찾는 문제
  2. 직사각형 좌표를 이용해 격자에 막힌 부분을 표시 (true로 설정)
  3. BFS를 사용하여 연결된 빈 공간(false) 영역들을 탐색
  4. 각 영역의 크기를 계산하고 오름차순으로 정렬하여 출력
  5. 영역의 개수와 각 영역의 크기를 반환

- 슈도코드
    ```
    solution(row, col):
        count = 0
        areas = new List()
        
        for i = 0 to col-1:
            for j = 0 to row-1:
                if not visited[j][i] and not grid[j][i]:  // 빈 공간이면
                    visited[j][i] = true
                    areaSize = BFS(i, j)
                    areas.add(areaSize)
                    count++
        
        sort(areas)
        return count, areas
    
    BFS(startX, startY):
        queue = new Queue()
        directions = [(0,1), (0,-1), (-1,0), (1,0)]
        count = 1
        
        queue.add([startX, startY])
        
        while queue is not empty:
            current = queue.poll()
            cx = current[0], cy = current[1]
            
            for each direction in directions:
                nx = cx + dx, ny = cy + dy
                
                if 범위 내 and not visited[ny][nx] and not grid[ny][nx]:
                    visited[ny][nx] = true
                    queue.add([nx, ny])
                    count++
        
        return count
    ```

*/


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    boolean[][] visited;
    boolean[][] grid;
    ArrayList<Integer> extent = new ArrayList<>();

    int[] dx = {0, 0, -1, 1};
    int[] dy = {1, -1, 0, 0};

    public Solution(int row, int col) {
        this.visited = new boolean[row][col];
        this.grid = new boolean[row][col];
    }

    public int[] solution(int row, int col) {
        int num = 0;

        for(int i=0; i<col; i++) {
            for(int j=0; j<row; j++) {
                if(!visited[j][i]) {
                    if(!grid[j][i]){
                        visited[j][i] = true;
                        num++;
                        extent.add(bfs(new Node(i, j)));
                    }
                }
            }
        }
        Collections.sort(extent);
        
        // 결과 배열 생성 (첫 번째 요소는 영역 개수, 나머지는 각 영역 크기)
        int[] result = new int[num + 1];
        result[0] = num;
        for (int i = 0; i < extent.size(); i++) {
            result[i + 1] = extent.get(i);
        }
        return result;
    }

    public int bfs(Node node) {
        int count = 1;
        Queue<Node> q = new LinkedList<Node>();

        q.add(node);

        while(!q.isEmpty()) {
            Node poll = q.poll();

            int cx = poll.x;
            int cy = poll.y;

            for (int i=0; i<4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if((0<=nx&&nx<grid[0].length) && (0<=ny&&ny<grid.length)) {
                    if(!visited[ny][nx]) {
                        if(!grid[ny][nx]) {
                            visited[ny][nx] = true;
                            count++;
                            q.add(new Node(nx, ny));
                        }
                    }
                }
            }
        }
        return count;
    }
}

public class BOJ2583 {
    public static void main(String[] args) {
        // Test cases
        int[] rows = {5};
        int[] cols = {7};
        int[] squareNums = {3};
        int[][][] rectangles = {
            {{0, 2, 4, 4}, {1, 1, 2, 5}, {4, 0, 6, 2}}
        };
        int[][] expectedResults = {
            {3, 1, 7, 13}  // 첫 번째는 영역 개수, 나머지는 각 영역 크기
        };

        for (int t = 0; t < rows.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int row = rows[t];
            int col = cols[t];
            int squareNum = squareNums[t];
            
            System.out.println("Grid: " + row + " x " + col + ", Rectangles: " + squareNum);
            System.out.println("Rectangles:");
            for (int[] rect : rectangles[t]) {
                System.out.println("  (" + rect[0] + "," + rect[1] + ") to (" + rect[2] + "," + rect[3] + ")");
            }

            Solution solved = new Solution(row, col);
            
            // 직사각형 그리기
            for (int[] rect : rectangles[t]) {
                drawing(rect[0], rect[1], rect[2], rect[3], solved.grid);
            }
            
            int[] results = solved.solution(row, col);
            
            System.out.print("Results: ");
            System.out.print(results[0] + " areas: ");
            for (int i = 1; i < results.length; i++) {
                System.out.print(results[i]);
                if (i < results.length - 1) System.out.print(", ");
            }
            System.out.println();
            
            System.out.print("Expected: ");
            System.out.print(expectedResults[t][0] + " areas: ");
            for (int i = 1; i < expectedResults[t].length; i++) {
                System.out.print(expectedResults[t][i]);
                if (i < expectedResults[t].length - 1) System.out.print(", ");
            }
            System.out.println();
            
            boolean isSuccess = java.util.Arrays.equals(results, expectedResults[t]);
            System.out.println(isSuccess ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }

    public static void drawing(int x1, int y1, int x2, int y2, boolean[][] grid) {
        for (int y=y1; y<y2; y++) {
            for (int x=x1; x<x2; x++) {
                grid[y][x] = true;
            }
        }
    }
}