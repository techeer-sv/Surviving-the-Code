/**
 * 백준 2012번 - 등수 매기기 [그리디 알고리즘]
 *
 * [문제 요약]
 * - N명의 학생들은 자신의 예상 등수를 제출
 * - 조교는 프로그램을 전부 날림
 * - 예상 등수를 기반으로 임의의 등수를 매기기로 결정
 * - 자신의 예상 등수와 실제 등수가 다른 경우 그 차이를 '불만도'로 수치화 ( ㅣ예상 등수 - 실제 등수ㅣ )
 * - 이 불만도의 총합을 최소로 하는 프로그램 작성
 *
 * [해결 방법]
 * - 1차적으로 학생들의 예상 등수를 오름차순으로 정렬
 * - 정렬된 순위에서 1순위부터 차례대로 채워넣기 시작
 * - 채워 넣으면서 불만도를 계산하여 합산
 * - 이때 불만도는 20억을 넘을 수 있으므로 long 타입으로 설정(N <= 500,000이기 때문)
 *
 * [시간 복잡도]
 * - Arrays.sort()의 복잡도는 평균 O(NlogN)
 * - 두 번 1중 반복문만을 도니 O(N)
 * - 전체 시간 복잡도: O(N) + O(NlogN) + O(N) -> O(NlogN)
 */

import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] expectedRank = new int[n];
        for (int i = 0; i < n; i++) {
            expectedRank[i] = Integer.parseInt(br.readLine());
        }

        // 예상 등수 정렬
        Arrays.sort(expectedRank);

        long bulman = 0;

        for (int i = 1; i <= n; i++) {
            bulman += Math.abs(i - expectedRank[i-1]);
        }

        System.out.println(bulman);
    }

}



