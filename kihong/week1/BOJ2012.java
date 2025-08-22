import java.util.Arrays;

/**
 * 백준 2012번 등수 매기기
 * 학생들이 예상한 등수와 실제 등수의 차이의 절댓값의 합을 계산하는 프로그램
 */
public final class BOJ2012 {

    /**
     * 학생들의 불만족도를 계산하는 메서드
     * 
     * @param n        학생 수
     * @param students 학생들이 예상한 등수 배열
     * @return 불만족도의 합
     */
    public static long solve(final int n, final int[] students) {
        // 결과 값
        long result = 0;

        // 학생들이 예상한 등수를 정렬
        Arrays.sort(students);

        // 불만족도의 합을 계산
        for (int i = 0; i < n; i++) {
            int expectRank = students[i];
            int actualRank = i + 1;
            result += Math.abs(actualRank - expectRank);
        }

        return result;
    }

    /**
     * 메인 메서드
     * 
     * @param args 명령행 인수 (사용하지 않음)
     */
    public static void main(final String[] args) {
        // 테스트 케이스
        final int[][] testCases = {
                { 5, 1, 5, 3, 1, 2 }, // 예상 결과: 3
                { 2, 2, 2 } // 예상 결과: 1
        };

        for (int i = 0; i < testCases.length; i++) {
            int[] testCase = testCases[i];
            int n = testCase[0];
            int[] students = new int[n];

            // 테스트 케이스에서 학생 데이터 추출
            for (int j = 0; j < n; j++) {
                students[j] = testCase[j + 1];
            }

            long expectedResult = (n == 5) ? 3 : 1;
            long result = solve(n, students);

            System.out.println("Test case " + (i + 1) + ":");
            System.out.println("case: " + Arrays.toString(students));
            System.out.println("result: " + result + ", expected: " + expectedResult);
            if (result == expectedResult) {
                System.out.println("expect = " + expectedResult + " 맞췄습니다.");
            } else {
                System.out.println("expect = " + expectedResult
                        + " / result = " + result + " 서로 다릅니다.");
            }
            System.out.println("---"); // 구분선 추가
            System.out.flush(); // 출력 버퍼 플러시
        }
    }
}
