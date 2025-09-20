/**
 * 백준 13305번 - 주유소 [그리드 알고리즘]
 *
 * [문제 요약]
 * - 제일 왼쪽 도시에서 제일 오른쪽의 도시로 자동차를 이용해 이동
 * - 인접한 두 도시 사이 도로들은 서로 길이가 다를 수 있음
 * - 도로의 단위는 km이며, 도로를 이동할 때 1km당 1L의 기름 사용
 * - 처음 출밣할 때는 기름이 없어 주유소에서 기름을 넣고 출발해야 함
 * - 도시별로 주유소 가격이 다르며 (리터 당 가격) 가격 단위는 원 사용
 * - 각 도시를 경유하며, 주유를 자율적으로 해서 최소 비용으로 제일 오른쪽 도시로 이동하는 비용 계산
 *
 * [해결 방법]
 * - 2차원 배열로, [0]에는 도시의 순서, [1]에는 기름값을 저장
 * - 기름값을 기준으로 오름차순 정렬, 기름값이 동일하면 도시 순서를 기준으로 오름차순 정렬
 * - 정렬된 배열을 루프를 돌며, 해당 도시에서 가장 오른쪽 끝 도시까지의 거리만큼 기름값 계산
 * - 계산된 거리들은 다 0으로 설정
 * - 루프 반복
 *
 * [시간 복잡도]
 * - 배열 정렬 -> O(n log n)
 * - 정렬된 도시 루프 -> O(n^2)
 * - 전체 시간 복잡도 -> O(n^2)
 */

import java.util.Arrays;

public class Main {

    static long solve(int[] distance, int[] oilPrice) {
        int n = oilPrice.length;

        // 도시별 기름값과 도시 번호
        int[][] cityInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            cityInfo[i][0] = i;
            cityInfo[i][1] = oilPrice[i];
        }

        // 기름값 기준 오름차순 정렬, 같으면 도시 순서 기준
        Arrays.sort(cityInfo, (a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] dist = Arrays.copyOf(distance, distance.length); // 거리 배열 복사
        long totalCost = 0;

        for (int[] city : cityInfo) {
            int cityNum = city[0];
            int cityOil = city[1];

            if (cityNum == n - 1) continue; // 마지막 도시는 스킵

            for (int i = cityNum; i < n - 1; i++) {
                if (dist[i] == 0) continue; // 이미 기름값 계산된 거리
                totalCost += (long) cityOil * dist[i];
                dist[i] = 0; // 계산 완료 처리
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // 테스트 케이스 1
        int[] distance1 = {2, 3, 1}; // 도시 간 거리
        int[] oilPrice1 = {5, 2, 4, 1}; // 도시별 기름값
        System.out.println("Test1 total cost: " + solve(distance1, oilPrice1));

        // 테스트 케이스 2
        int[] distance2 = {3, 3, 4, 2};
        int[] oilPrice2 = {7, 6, 3, 2, 5};
        System.out.println("Test2 total cost: " + solve(distance2, oilPrice2));
    }
}