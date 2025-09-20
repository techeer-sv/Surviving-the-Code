/**
 * 백준2891번 - 카약과 강풍 [그리디 알고리즘]
 *
 * [문제 요약]
 * - 카약 대회에 N개의 팀 출전
 * - 강풍으로 인 일부 카약 S개 파손
 * - 여분을 가지고 있는 R개의 팀
 * - 여분을 빌려 줄 수 있으나 이는 양옆의 인접한 팀에 한해서만 가능
 * - 카약을 빌린다고 할때 출발하지 못하는 팀이 최소가 되는 값
 *
 * [해결 방법]
 * - 파손되지 않은 카약은 우선 출전 가능
 * - 파손된 카약은 0으로, 여분을 보유한 카약은 2로 설정 (디폴트는 1)
 * - 루프를 돌며, 현재 2인 팀은 다음 팀의 파손 여부를 체크해 나눠줄지 결정
 * - 루프를 돌며, 현재 0인 팀은 다음 팅의 여분 보유 여부를 체크해 뺏어올지 결정
 * - 최종적으로 출전 불가 팀 카운트
 *
 * [시간 복잡도]
 * - 카약 분배 루프 -> O(N)
 * - 결과 찾기 루프 -> O(N)
 * - 전체 시간 복잡도 -> O(N)
 */


import java.util.Arrays;

public class Main {

    static int solve(int n, int[] broken, int[] extra) {
        int[] kayak = new int[n];
        Arrays.fill(kayak, 1); // 기본 카약 1개

        // 파손된 카약
        for (int b : broken) {
            kayak[b - 1] = 0; // 파손된 팀은 0
        }

        // 여분 카약
        for (int e : extra) {
            kayak[e - 1]++; // 여분 있으면 1 증가 (기본 1+1=2)
        }

        for (int i = 0; i < n - 1; i++) {
            if (kayak[i] == 2) {
                if (kayak[i + 1] == 0) { // 오른쪽 팀 파손 시 나눠줌
                    kayak[i]--;
                    kayak[i + 1]++;
                }
                continue;
            }

            if (kayak[i] == 0) {
                if (kayak[i + 1] == 2) { // 오른쪽 팀 여분 시 가져옴
                    kayak[i]++;
                    kayak[i + 1]--;
                }
            }
        }

        int result = 0;
        for (int k : kayak) {
            if (k == 0) result++;
        }
        return result;
    }

    public static void main(String[] args) {
        // 테스트 케이스 1
        int n1 = 5;
        int[] broken1 = {2, 4};
        int[] extra1 = {3, 5};

        // 테스트 케이스 2
        int n2 = 7;
        int[] broken2 = {3, 6};
        int[] extra2 = {2, 4};

        System.out.println("Test1 result: " + solve(n1, broken1, extra1)); // 기대값 계산
        System.out.println("Test2 result: " + solve(n2, broken2, extra2)); // 기대값 계산
    }
}