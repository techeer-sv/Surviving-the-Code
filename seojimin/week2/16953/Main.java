/**
 * 백준 16953번 - A -> B [그리드 알고리즘]
 *
 * [문제 요약]
 * - 정수 A를 B로 바꾸는 연산의 최소 횟수 찾기
 * - 불가능한 경우 -1로 응답
 *
 * [해결 방법]
 * - B의 끝자리가 1이라면 1을 없애고, B가 2의 배수라면 2로 나누고 연산 횟수 추가
 * - B가 A보다 작아지거나, 위의 두 방법 모두 적용 불가능한 경우 A->B가 불가능하다고 판단
 * - 불가능한 경우 -1을 반환
 * - 최종적으로 연산횟수 반환
 *
 * [시간 복잡도]
 * - B를 A에 가까워지게 하는 연산 루프 -> O(N)
 * - 전체 시간 복잡도 -> O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); // 시작 숫자
        int b = Integer.parseInt(st.nextToken()); // 목표 숫자

        int count = 0;
        while (a < b) {
            // a == b 라면 종료
            if (a == b) {
                break;
            }

            // b의 끝자리가 1인 경우
            if (b % 10 == 1) {
                count++;
                b = b / 10;
            }

            // b가 2의 배수인 경우
            else if (b % 2 == 0){
                count++;
                b = b / 2;
            }

            else break; // 만약 위의 두 케이스 모두 적용 불가능할 시
        }

        System.out.println(count);
    }
}