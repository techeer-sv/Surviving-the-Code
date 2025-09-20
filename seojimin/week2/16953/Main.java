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

public class Main {

    static int solve(int a, int b) {
        int count = 0;

        while (a < b) {
            if (b % 10 == 1) {
                b /= 10;
                count++;
            } else if (b % 2 == 0) {
                b /= 2;
                count++;
            } else {
                return -1; // 더 이상 연산 불가능
            }
        }

        if (a == b) return count + 1; // 마지막 연산 포함
        return -1;
    }

    public static void main(String[] args) {
        // 테스트 케이스 1
        int a1 = 2, b1 = 162;
        System.out.println("Test1 operations: " + solve(a1, b1)); // 기대값: 5

        // 테스트 케이스 2
        int a2 = 4, b2 = 42;
        System.out.println("Test2 operations: " + solve(a2, b2)); // 기대값: -1
    }
}