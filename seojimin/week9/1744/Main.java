/**
 * 백준 1744번 - 수 묶기 [정렬 알고리즘]
 *
 * [문제 요약]
 * - 길이가 N인 수열이 주어짐
 * - 위치 상관없이 두 개씩 묶어 곱하거나, 각각을 더하도록 해서 수열의 모든 요소의 합의 최댓값 구하기
 *
 * [해결 방법]
 * - 양수인 케이스와 음수인 케이스로 분리
 * - 양수의 경우 내림차순 정렬 후 순회하며 2개씩 묶어 곱한 뒤 더하고 1개 남은 경우 그냥 더하기
 *      - 단, 묶어질 숫자에 1이 포함된 경우 묶지 않고 따로 더하기(1을 곱하면 그대로이기에 오히려 각각을 더하는게 더 크다)
 * - 음수의 경우 절대값이 큰 요소끼리 묶어야 커지므로, 오름차순 정렬
 *      - 1개의 값이 남았을 경우, 0이 수열에 포함되는지 확인
 *      - 0이 있으면 해당 음수와 0을 곱하는게 이득이기에 곱했다는 가정하에 더하지 않음
 *      - 0이 없으면 해당 음수를 총합에 더함
 *
 * [시간 복잡도]
 * - O(N log N)
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static int solve(int n, int[] input){

        List<Integer> plusList = new ArrayList<>();
        List<Integer> minusList = new ArrayList<>();
        boolean zero = false;

        for (int i = 0; i < n; i++) {
            int temp = input[i];
            if (temp < 0) minusList.add(temp);
            else if (temp == 0) zero = true;
            else plusList.add(temp);
        }

        plusList.sort(Collections.reverseOrder());
        Collections.sort(minusList);

        int sum = 0;

        // plusList부터 시작
        for (int i = 0; i < plusList.size(); i++) {
            int now = plusList.get(i);

            if (i + 1 < plusList.size() && now > 1 && plusList.get(i + 1) > 1) {
                sum += now * plusList.get(i + 1);
                i++;
            } else {
                sum += now;
            }
        }

        // minus 케이스
        for (int i = 0; i < minusList.size(); i++) {
            if (i + 1 < minusList.size()) {
                sum += minusList.get(i) * minusList.get(i + 1);
                i++;
            } else {
                // 남는 음수 하나가 있을 때
                if (!zero) sum += minusList.get(i); // 0이 없으면 더함
                // 0이 있으면 곱해서 0이 되므로 아무것도 안 더함
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(solve(6, new int[]{0,1,2,4,3,5}));
    }
}