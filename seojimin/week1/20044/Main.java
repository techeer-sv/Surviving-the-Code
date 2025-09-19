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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 학생 수
        int[] student = new int[n * 2];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < student.length; i++) {
            student[i] = Integer.parseInt(st.nextToken());
        }

        // 학생 코딩 역량 정렬
        Arrays.sort(student);

        // 팀 매칭
        int[] team = new int[n];
        team[0] = student[0] + student[n * 2 - 1];

        int min = team[0];

        for (int i = 1; i < n; i++) {
            team[i] = student[i] + student[n*2-1-i];
            min = Math.min(min, team[i]);
        }

        System.out.println(min);
    }
}