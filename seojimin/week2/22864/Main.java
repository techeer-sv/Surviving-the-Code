/**
 * 백준 22864번 - 피로도 [그리드 알고리즘]
 *
 * [문제 요약]
 * - 하루에 한 시간 단위로 일을 하거나 일을 쉴 수 있다
 * - 하루에 한 시간 일하면 피로도는 A만큼 쌓이고 일은 B만큼 처리 가능
 * - 한 시간을 쉬면 피로도는 C만큼 줄어듬
 * - 피로도가 음수로 내려가면 0으로 바뀜, 쉬면 당연히 처리한 일은 없음
 * - 피로도를 최대한 M을 넘지 않게 일을 하려고 함, M을 넘기면 번아웃이 와 이미 했던 일들도 던지고 일 그만둠
 * - 피로관리하면서 하루에 최대로 일할 수 있는 경우를 구하자 (하루는 24시간)
 *
 * [해결 방법]
 * - 24시간 만큼 루프를 돌도록 설정
 * - 현재 피로도에 한 시간 피로도를 더했을 때, 한계를 넘는지 체크
 * - 넘는 경우, 휴식을 부여해 현재 피로도에서 C만큼 차감(단 음수가 되는 경우, 0으로 설정)
 * - 넘지 않는 경우, 일을 하여 일처리량 증가
 * - 단, 애초에 한계 피로도보다 한시간 피로도가 더 높은 경우 바로 0을 반환
 *
 * [시간 복잡도]
 * - 24시간 루프 -> O(n)
 * - 전체 시간 복잡도 -> O(n)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); // 시간별 피로도
        int b = Integer.parseInt(st.nextToken()); // 시간별 일처리
        int c = Integer.parseInt(st.nextToken()); // 시간별 회복량
        int m = Integer.parseInt(st.nextToken()); // 한계 피로도

        int work = 0;
        int piro = 0;
        for (int i = 0; i < 24; i++) {
            if (piro + a > m){ // 휴식이 필요한 상태라면
                piro -= c; // c만큼 회복
                if (piro < 0){
                    piro = 0;
                }
                continue;
            }
            // 일 할 수 있는 상태면
            work += b;
            piro += a;
        }

        System.out.println(work);
    }
}