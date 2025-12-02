/**
 * 백준 1946번 - 신입사원 [정렬 알고리즘]
 *
 * [문제 요약]
 *  - 두가지 유형의 순위가 주어진다
 *  - 특정 후보보다 두 가지 유형 모두 순위가 뒤지는 사람은 신입 사원이 될 수 없다
 *
 * [해결 방법]
 * - 먼저 하나의 유형(A)으로 오름차순 정렬
 * - A유형 1위는 무조건 뽑히니 카운트해두고, 해당 후보의 B유형 순위를 기억
 * - A유형의 순위대로 오름차순 정렬되었기에, 순위를 내려가며 B 유형의 순위가 기억해 둔 순위보다 낮은지 체크
 *      - 더 낮은 경우 A유형의 윗순위자보다 B유형 역시 아랫순위라는 의미이기에 탈락
 * - 만약 B유형의 순위가 기억해둔 순위보다 높은경우, 카운트하고 기억해둔 숫자를 해당 순위로 업데이트
 *
 * [시간 복잡도]
 *  - 정렬: O(N log N)
 *  - 순회: O(N)
 *  - 총합: O(N log N)
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Main {

    static void solve(int t, int[] nArr, String[][][] inputArr){
        int iter = 0;
        while(t-- > 0){
            int n = nArr[iter];
            List<int[]> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String[] split = inputArr[iter][i];
                list.add(new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])});            }

            list.sort(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            int count = 0;
            int state = n;
            for (int i = 0; i<list.size(); i++){
                int[] now = list.get(i);
                if (i == 0){
                    count++;
                    state = now[1];
                    continue;
                }
                if (now[1] < state){
                    count++;
                    state = now[1];
                }
            }

            System.out.println(count);

            iter++;
        }
    }

    public static void main(String[] args) {
        int[] nArr = new int[]{5,7};
        String[][] case1 = {
                {"3", "2"},
                {"1", "4"},
                {"4", "1"},
                {"2", "3"},
                {"5", "5"}
        };

        // 테스트 케이스 2
        String[][] case2 = {
                {"3", "6"},
                {"7", "3"},
                {"4", "2"},
                {"1", "4"},
                {"5", "7"},
                {"2", "5"},
                {"6", "1"}
        };

        String[][][] inputArr = {case1, case2};

        solve(2, nArr, inputArr);
    }
}