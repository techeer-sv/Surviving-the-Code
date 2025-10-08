/**
 * 백준 26876번 - New Time [ 그리디, BFS 알고리즘 ]
 *
 * [문제 요약]
 * - **:**로 표현하는 시간이 맞지 않는 시계 존재
 * - 버튼 A -> 1분 뒤로만 조정 / 버튼 B -> 1시간 뒤로만 조정
 * - 최소한의 버튼을 누르면서 시간을 알맞게 조정하자
 *
 * [해결 방법1 -> 그리디 알고리즘]
 * - 현재 시간 분으로 환산 -> 현재 시간 * 60 + 현재 분
 * - 목표 시간 분으로 환산 -> 목표 시간 * 60 + 목표 분
 * - 목표 시간이 현재 시간보다 이른 시간 즉 분으로 환산한 수치가 더 작다면 -> 목표 시간 + 24 * 60
 * - 다음날 해당 시간으로 맞춘다고 보면 된다
 * - 이렇게 설정한 뒤 목표 시간 - 현재 시간 진행
 * - 결과가 나오면 해당 결과를 60으로 나눈 수치 -> B버튼 누른 횟수
 * - 해당 결과를 60으로 나눈 나머지 -> A버튼 누른 횟수
 *
 * [해결 방법2 -> BFS 알고맂므]
 * - 현재 시간에서 목표 시간으로 가는 카운트를 측정
 * - 현재 시간 부터 큐에 담기 시작해 bfs를 돌리며 매 루프마다 A버튼을 눌렀을 때, B버튼을 눌렀을 때 모두 큐에 추가
 * - 만약 목표 시간에 도달하면 count 출력하며 마무리
 *
 * [시간 복잡도]
 *  - 해결방법 1 -> 별다른 루프도 없고, 입력 크기와 무관하게 연산 횟수 고정 -> O(1)
 *  - 해결방법 2 -> 최대로 연산하는 경우갸 24 * 60 -> O(24 * 60) -> O(1)
 */

import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static int solve1(int startH, int startM, int endH, int endM){

        // 시작 시간
        int startTime = startH*60 + startM;

        // 목표 시간
        int endTime = endH*60  + endM;

        if (endTime < startTime) endTime += 24 * 60;

        int result = endTime - startTime;
        int hourButton = result / 60;
        int minButton = result % 60;

        return hourButton + minButton;
    }

    static int solve2(int startH, int startM, int endH, int endM){
        boolean[][] visited = new boolean[24][60];
        Queue<int[]> q = new LinkedList<>();

        // 시작 시간 (시, 분, 버튼 누른 횟수)
        q.add(new int[]{startH, startM, 0});
        visited[startH][startM] = true;

        int count = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int ch = cur[0];
            int cm = cur[1];
            int step = cur[2];

            // 목표 시간 도달
            if (ch == endH && cm == endM) {
                return step;
            }

            // 버튼 A (+1분)
            int nh = ch;
            int nm = cm + 1;
            if (nm == 60) {
                nm = 0;
                nh = (nh + 1) % 24;
            }
            if (!visited[nh][nm]) {
                visited[nh][nm] = true;
                q.add(new int[]{nh, nm, step + 1});
            }

            // 버튼 B (+1시간)
            nh = (ch + 1) % 24;
            nm = cm;
            if (!visited[nh][nm]) {
                visited[nh][nm] = true;
                q.add(new int[]{nh, nm, step + 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(solve1(11,57,12,0));
        System.out.println(solve1(9,9,21,21));
        System.out.println(solve1(19,44,8,50));

        System.out.println(solve2(11,57,12,0));
        System.out.println(solve2(9,9,21,21));
        System.out.println(solve2(19,44,8,50));
    }
}