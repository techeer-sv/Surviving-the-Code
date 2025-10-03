/*
New Time (백준 26876번)

- 시간 복잡도: O(24 * 60) = O(1440) = O(1)
  - 시계의 모든 가능한 상태는 24시간 * 60분 = 1440가지
  - BFS로 모든 상태를 최대 한 번씩만 방문하므로 O(1440)
  - 상수 시간으로 취급 가능

- 풀이
  1. 시계에서 가능한 두 가지 조작: 시간 +1, 분 +1
  2. 시작 시간에서 목표 시간까지의 최단 경로(최소 조작 횟수)를 구하는 문제
  3. BFS를 사용하여 각 시간 상태에서 가능한 다음 상태들을 탐색
  4. 24시간제와 60분제 순환 처리 (24시 → 0시, 60분 → 0분 + 시간+1)
  5. visited 배열로 이미 방문한 시간 상태 체크하여 무한루프 방지

- 슈도코드
    ```
    BFS(startHour, startMinute):
        queue = new Queue()
        visited[24][60] = false로 초기화
        queue.add([startHour, startMinute, 0])
        
        while queue is not empty:
            current = queue.poll()
            currentHour = current[0]
            currentMinute = current[1]
            count = current[2]
            
            if currentHour == targetHour and currentMinute == targetMinute:
                return count
            
            // 시간 +1 조작
            nextHour = currentHour + 1
            if nextHour == 24: nextHour = 0
            if not visited[nextHour][currentMinute]:
                visited[nextHour][currentMinute] = true
                queue.add([nextHour, currentMinute, count+1])
            
            // 분 +1 조작
            nextMinute = currentMinute + 1
            nextHour = currentHour
            if nextMinute == 60:
                nextMinute = 0
                nextHour = (currentHour + 1) % 24
            if not visited[nextHour][nextMinute]:
                visited[nextHour][nextMinute] = true
                queue.add([nextHour, nextMinute, count+1])
    ```

*/

import java.util.LinkedList; 
import java.util.Queue;

class Solution {
    int[] dHour = {1, 0};
    int[] dMinute = {0, 1};
    boolean[][] visited = new boolean[24][60];

    int targethour;
    int targetminute;

    int ans = 0;

    public Solution(int hour, int minute) {
        this.targethour = hour;
        this.targetminute = minute;
    }

    public int solution(int hour, int minute) {
        bfs(hour, minute);
        return ans;
    }

    public void bfs(int hour, int minute) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{hour, minute, 0});

        while (!queue.isEmpty()) {
            // 큐에서 추출
            int[] qPoll = queue.poll();
            // x,y 좌표와 현재까지의 이동 횟수 추출
            int cHour = qPoll[0];
            int cMinute = qPoll[1];
            int count = qPoll[2];
            // 시간에 도달시 ans에 count 저장 후 종료
            if (cHour == targethour && cMinute == targetminute) {
                ans = count;
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nHour = cHour + dHour[i];
                int nMinute = cMinute + dMinute[i];

                // 60분 도달시 시간 +1, 분 0으로 순환 처리
                if (nMinute == 60) {
                    nHour++;
                    nMinute = 0;
                }
                // 24시간 도달시 0으로 순환 처리
                if (nHour == 24) {
                    nHour = 0;
                }
                // 방문하지 않았다면 방문처리 및 큐에 추가
                if(!visited[nHour][nMinute]) {
                    visited[nHour][nMinute] = true;
                    queue.add(new int[]{nHour, nMinute, count+1});
                }
            }
        }
    }
}

public class BOJ26876 {
    public static void main(String[] args) {
        // Test cases
        int[][] startTimes = {{11, 57}, {9, 9}, {19, 44}};
        int[][] targetTimes = {{12, 0}, {21, 21}, {8, 50}};
        int[] expectedResults = {3, 24, 19};

        for (int t = 0; t < startTimes.length; t++) {
            System.out.println("--- Test Case " + (t + 1) + " ---");
            
            int startHour = startTimes[t][0];
            int startMinute = startTimes[t][1];
            int targetHour = targetTimes[t][0];
            int targetMinute = targetTimes[t][1];
            
            System.out.println("Start Time: " + String.format("%02d:%02d", startHour, startMinute));
            System.out.println("Target Time: " + String.format("%02d:%02d", targetHour, targetMinute));

            Solution solved = new Solution(targetHour, targetMinute);
            int result = solved.solution(startHour, startMinute);
            
            System.out.println("Result: " + result);
            System.out.println("Expected: " + expectedResults[t]);
            System.out.println(result == expectedResults[t] ? ">>> SUCCESS" : ">>> FAILURE");
            System.out.println();
        }
    }
}