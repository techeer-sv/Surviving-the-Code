/**
 * 백준 20044번 - Project Teams [그리디 알고리즘]
 *
 * [문제 요약]
 * - 코딩 프로젝트 수업 팀 구성
 * - 팀 하나당 2명의 학생으로 구성
 * - 학생 별 코딩 역량을 제각각
 * - 모든 학생은 빠짐없이 팀에 소속되어야 함
 * - 공정성을 높이기 위해 팀원 코딩 역량의 합을 최대한 비슷하게 유지하는 것이 목표
 * - 팀 구성에 도움을 줄 수 있는 프로그램 제작
 *
 * [해결 방법]
 *  - 우선 학생들의 코딩 역량을 입력받고
 *  - 오름차순으로 정렬
 *  - 양 끝에서부터 조합해 팀 매칭
 *  - 매칭된 팀의 역량 합이 최소인지 판단해 최솟값 업데이트
 *
 * [시간 복잡도]
 *  - 팀 매칭 루프 -> O(N)
 *  - 전체 시간 복잡도 -> O(N)
 */


import java.util.Arrays;

public class Main {

    // 팀 역량 최소값 계산 함수
    static int solve(int n, int[] student) {
        Arrays.sort(student);

        int min = student[0] + student[2*n - 1];
        for (int i = 1; i < n; i++) {
            int teamPower = student[i] + student[2*n - 1 - i];
            min = Math.min(min, teamPower);
        }
        return min;
    }

    public static void main(String[] args) {

        int[] test1 = {1, 2, 3, 4};          // 2팀, 역량: 1+4, 2+3 -> min = 5
        int[] test2 = {5, 1, 3, 2, 4, 6};    // 3팀, 역량: 1+6, 2+5, 3+4 -> min = 7

        System.out.println("Test1 min team power: " + solve(2, test1));
        System.out.println("Test2 min team power: " + solve(3, test2));
    }
}